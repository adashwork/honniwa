package jp.or.adash.honniwa.view;

import java.util.List;

import jp.or.adash.honniwa.dto.Book;


public class BookListView {
	
	private final String searchCondition;
	private final List<Book> bookList;
	
	/**
	 * コンストラクタ
	 * @param title 書籍名（検索条件）
	 * @param bookList 書籍一覧
	 */
	public BookListView(String searchCondition, List<Book> bookList) {
		this.searchCondition = searchCondition;
		this.bookList = bookList;
	}

	/**
	 * 書籍名（検索条件）を取得する
	 * @return 書籍名（検索条件）
	 */
	public String getSearchCondition() {
		return searchCondition;
	}

	/**
	 * 書籍一覧を取得する
	 * @return 書籍一覧
	 */
	public List<Book> getBookList() {
		return bookList;
	}

}
