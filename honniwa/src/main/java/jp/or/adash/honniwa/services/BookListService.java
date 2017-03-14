package jp.or.adash.honniwa.services;

import java.io.IOException;
import java.util.List;

import jp.or.adash.honniwa.dao.BookDao;
import jp.or.adash.honniwa.dao.Transaction;
import jp.or.adash.honniwa.dto.Book;

/**
 * 検索結果一覧情報取得に関するサービスを提供するクラス
 * @author T.Kajihara
 *
 */
public class BookListService {
	private final BookDao bookDao;
	private final Transaction transaction;
	/**
	 * コンストラクタ
	 */
	public BookListService() {
	this.bookDao = new BookDao();
	this.transaction = new Transaction();
	}

	/**
	 * 書籍リストを取得する
	 * @param searchCondition
	 * @return
	 */
	public List<Book> getBookList(String searchCondition) {

				List<Book> bookList = null;

				try {
					// データベース接続を開始する
					transaction.begin();

					// データベースにアクセスして、書籍リストを取得する
					bookList = bookDao.selectByCondition(transaction, searchCondition);

					// データベース接続を正常終了する
					transaction.commit();

				} catch(IOException e) {
					// 異常終了
//					throw new IllegalStateException(e);
				} finally {
					// データベース接続がある場合
					if (transaction.isActive()) {
						// データベース接続をエラー終了する
						transaction.rollback();
					}
				}

				return bookList;
			}

}
