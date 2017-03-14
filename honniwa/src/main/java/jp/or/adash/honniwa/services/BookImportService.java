/**
 *
 */
package jp.or.adash.honniwa.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jp.or.adash.honniwa.dao.BookCsvDao;
import jp.or.adash.honniwa.dao.BookDao;
import jp.or.adash.honniwa.dao.Transaction;
import jp.or.adash.honniwa.dto.Book;

import com.sun.jersey.core.header.FormDataContentDisposition;

/**
 * 書籍データインポートに関するサービスクラス
 * @author T.Kawasaki
 *
 */
public class BookImportService {

	private static final String NO_FILENAME_MESSAGE = "ファイル名がありません。";
	private static final String IMPORT_ERROR_MESSAGE = "ファイルのインポートに失敗しました。";
	private static final String IMPORT_SUCCESS_MESSAGE = "ファイルのインポートが完了しました。";

	private Transaction transaction;
	private BookCsvDao bookCsvDao;
	private BookDao bookDao;

	/**
	 * コンストラクタ
	 */
	public BookImportService() {
		transaction = new Transaction();
		bookCsvDao = new BookCsvDao();
		bookDao = new BookDao();
	}

	/**
	 * インポートファイルのチェックを行う
	 * @param file CSVファイルオブジェクト
	 * @param file2 ファイルの情報
	 * @return エラーメッセージ（エラーが無い場合は、nullが返る）
	 */
	public String checkFile(InputStream file, FormDataContentDisposition file2) {
		String message = null;

		// ファイル名が無ければ、エラー
		if (file2.getFileName().isEmpty()) {
			message = NO_FILENAME_MESSAGE;
		}

		return message;
	}

	/**
	 * インポート処理を実行する
	 * @param file インポートデータの入ったファイル
	 * @return 処理結果メッセージ
	 */
	public String execute(InputStream file) {
		String message = "";

		try {
			// ファイルの内容を読み込んで、リストに格納する
			List<Book> bookList = bookCsvDao.read(file);

			// トランザクションを開始する
			transaction.begin();
			// 書籍リストをデータベースに登録する
			insertBooks(bookList);
			// トランザクションをコミットする
			transaction.commit();

			// 完了メッセージを格納する
			message = IMPORT_SUCCESS_MESSAGE;
		} catch(IOException e) {
			// エラーメッセージを格納する
			message = IMPORT_ERROR_MESSAGE;
		} finally {
			try {
				// ファイルを閉じる
				file.close();
			} catch(IOException e) {

			}
		}

		return message;
	}

	/**
	 * 書籍リストをデータベースに登録する
	 * @param bookList 書籍リスト
	 */
	private void insertBooks(List<Book> bookList) throws IOException {
		// 書籍を一件ずつ取り出し、登録する
		for (Book book : bookList) {
			bookDao.insert(transaction, book);
		}
	}
}
