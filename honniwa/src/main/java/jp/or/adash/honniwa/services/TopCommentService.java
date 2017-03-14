package jp.or.adash.honniwa.services;

import java.io.IOException;
import java.util.List;

import jp.or.adash.honniwa.dao.CommentBookDao;
import jp.or.adash.honniwa.dao.Transaction;
import jp.or.adash.honniwa.dto.Book;


/**
 * 書籍リストのサービスを提供するクラス
 * @author morii
 * 
 */
public class TopCommentService {
	private final CommentBookDao commentBookDao;
	private final Transaction transaction;
	
	
	/**
	 * コンストラクタ
	 */
	public TopCommentService() {
		this.commentBookDao = new CommentBookDao();
		this.transaction = new Transaction();
	}

	
	public List<Book> getBookList() {

		try {
			//データベースに接続して、トランザクションを開始を行う
			transaction.begin();

			//書籍リストを取得する
			List<Book> books = commentBookDao.getBookList(transaction);

			// トランザクションをコミットする
			transaction.commit();


			return books;

		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			if (transaction.isActive()) {
				try {
					// トランザクションをロールバックする
					transaction.rollback();
				} catch (RuntimeException e) {
					//logger.warn(e.getMessage(),e);
				}
			
			}	
		}
	
	}
	
}