/**
 *
 */
package jp.or.adash.honniwa.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.honniwa.dto.Book;

/**
 * 書籍CSVファイルにアクセスするクラス
 * @author T.Kawasaki
 *
 */
public class BookCsvDao {

	/**
	 * ファイルを読み込む
	 * @param file ファイル
	 * @return 書籍のリスト
	 * @throws IOException ファイルの読み込みに失敗した場合に発生する例外
	 */
	public List<Book> read(InputStream file) throws IOException {
		List<Book> bookList = new ArrayList<Book>();

		BufferedReader br = new BufferedReader(new InputStreamReader(file));

		// ファイルを1行ずつ読み込む
		String line;
		while ((line = br.readLine()) != null) {

			// 1行分のデータを項目ごとに分割する
			String[] lineData = line.split("\",\"");

			// Bookオブジェクトに格納する
			//Book book = new Book(lineData[1], lineData[1]

		}

		return bookList;
	}
}
