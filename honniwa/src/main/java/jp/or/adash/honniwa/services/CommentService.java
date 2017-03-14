/**
 * 
 */
package jp.or.adash.honniwa.services;

import java.io.IOException;
import java.util.List;

import jp.or.adash.honniwa.dao.BookDao;
import jp.or.adash.honniwa.dao.OwnerCommentDao;
import jp.or.adash.honniwa.dao.Transaction;
import jp.or.adash.honniwa.dao.UserCommentDao;
import jp.or.adash.honniwa.dto.Book;
import jp.or.adash.honniwa.dto.OwnerComment;
import jp.or.adash.honniwa.dto.UserComment;


/**
 * @author rinako
 * Dtoから取得したデータを処理
 *
 */
public class CommentService {
	private final BookDao selectBookDao;
	private final OwnerCommentDao selectOwnerCommentDao;
	private final UserCommentDao selectUserCommentDao;
	private final Transaction transaction;
	
	/**
	 * コンストラクタ
	 */
	public CommentService() {
		this.selectBookDao = new BookDao();
		this.selectOwnerCommentDao = new OwnerCommentDao();
		this.selectUserCommentDao = new UserCommentDao();
		this.transaction = new Transaction();
	}

	/**
	 * 検索条件を元に書籍リストを取得する
	 * @param title 書籍名
	 * @return 書籍リスト
	 */
	public Book getBook(String bookid) {

		Book book = null;

		try {
			// データベース接続を開始する
			transaction.begin();

			// データベースにアクセスして、書籍リストを取得する
			book = selectBookDao.find(transaction, bookid);

			// データベース接続を正常終了する
			transaction.commit();

		} catch(IOException e) {
			// 異常終了
//			throw new IllegalStateException(e);
		} finally {
			// データベース接続がある場合
			if (transaction.isActive()) {
				// データベース接続をエラー終了する
				transaction.rollback();
			}
		}

		return book;
	}
	public List<UserComment> getUserComment(String bookid) {

		List<UserComment> userCommentList = null;

		try {
					// データベース接続を開始する
					transaction.begin();

					// データベースにアクセスして、オーナーテーブル情報を取得する
					userCommentList = selectUserCommentDao.find(transaction, bookid);

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

				return userCommentList;
		}
	public OwnerComment getOwnerComment(String bookid) {

		OwnerComment ownerComment = null;

		try {
					// データベース接続を開始する
					transaction.begin();

					// データベースにアクセスして、commentテーブル情報を取得する
					ownerComment = selectOwnerCommentDao.find(transaction, bookid);

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

				return ownerComment;
		}
	
	
	
}
