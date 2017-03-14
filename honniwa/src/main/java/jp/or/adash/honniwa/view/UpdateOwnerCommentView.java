/**
 *
 */
package jp.or.adash.honniwa.view;

import jp.or.adash.honniwa.dto.OwnerComment;
/**
 * 利用者情報表示するクラス
 * @author wanikawa
 *
 */
public class UpdateOwnerCommentView {

	private final String year;
	private final String month;
	private final String day;
	private final String bookId;
	private final String title;
	private final OwnerComment ownerComment;
	private final String message;

	/**
	 * コンストラクタ
	 */
	public UpdateOwnerCommentView(){
		 this.year="";
		 this.month="";
		 this.day="";
		 this.bookId="";
		 this.title="";
		 this.ownerComment=null;
		 this.message="";
	}
	
	/**
	 * コンストラクタ
	 * @param year	感想記入年
	 * @param month	感想記入月
	 * @param day		感想記入日
	 * @param bookId	書籍管理番号
	 * @param title	書籍タイトル
	 * @param ownerComment	寄贈者感想情報
	 * @param message　画面に表示するメッセージ
	 */
	public UpdateOwnerCommentView(String year,String month,String day,String bookId,String title,OwnerComment ownerComment,String message){
		 this.year=year;
		 this.month=month;
		 this.day=day;
		 this.bookId=bookId;
		 this.title=title;
		 this.ownerComment=ownerComment;
		 this.message=message;
	}

	/**
	 * 感想記入年を取得する
	 * @return year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * 感想記入月を取得する
	 * @return month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * 感想記入日を取得する
	 * @return day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * 書籍管理番号を取得する
	 * @return bookId
	 */
	public String getBookId() {
		return bookId;
	}

	/**
	 * 書籍タイトルを取得する
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 管理者情報を取得する
	 * @return ownerComment
	 */
	public OwnerComment getOwnerComment() {
		return ownerComment;
	}

	/**
	 * 画面に表示するメッセージを取得する
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	
}