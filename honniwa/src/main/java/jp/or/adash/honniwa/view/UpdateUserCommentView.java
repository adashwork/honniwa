package jp.or.adash.honniwa.view;

import jp.or.adash.honniwa.dto.Book;

/**
 * ユーザー感想入力画面に受け渡すエンティティクラス
 * @author wanikawa
 *
 */
public class UpdateUserCommentView {

	private final Book book;
	private final String commentId;
	private final String inputName;
	private final String inputYear;
	private final String inputMonth;
	private final String inputDay;
	private final String inputTwitter;
	private final String inputFacebook;
	private final String inputComment;
	private final String message;
	
	/**
	 * デフォルトコンストラクタ
	 */
	public UpdateUserCommentView(){
		this.book=null;
		this.commentId="";
		this.inputName = "";
		this.inputYear = "";
		this.inputMonth = "";
		this.inputDay = "";
		this.inputTwitter = "";
		this.inputFacebook = "";
		this.inputComment = "";
		this.message="";
	}
	
	/**
	 * コンストラクタ
	 * @param book	書籍情報
	 * @param message	表示するメッセージ(画面に表示)
	 */
	public UpdateUserCommentView(Book book,String message){
		this.book=book;
		this.commentId="";
		this.inputName = "";
		this.inputYear = "";
		this.inputMonth = "";
		this.inputDay = "";
		this.inputTwitter = "";
		this.inputFacebook = "";
		this.inputComment = "";
		this.message=message;
	}
	
	/**
	 * コンストラクタ
	 * @param book		書籍情報
	 * @param commentId	ユーザーコメント管理番号
	 * @param inputName	名前(画面に表示)
	 * @param inputYear	感想記入年(画面に表示)
	 * @param inputMonth	感想記入月(画面に表示)	
	 * @param inputDay	感想記入日(画面に表示)
	 * @param inputTwitter	Twitterアカウント(画面に表示)
	 * @param inputFacebook	Facebookアカウント(画面に表示)
	 * @param inputComment	ユーザー感想(画面に表示)
	 * @param message	表示するメッセージ(画面に表示)
	 */
	public UpdateUserCommentView(Book book,String commentId,String inputName,String inputYear,String inputMonth,String inputDay,String inputTwitter,String inputFacebook,String inputComment,String message){
		this.book =book;
		this.commentId=commentId;
		this.inputName = inputName;
		this.inputYear = inputYear;
		this.inputMonth = inputMonth;
		this.inputDay = inputDay ;
		this.inputTwitter = inputTwitter;
		this.inputFacebook = inputFacebook;
		this.inputComment = inputComment;
		this.message=message;
	}
	
	/**
	 * 書籍情報を取得する
	 * @return book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * ユーザーコメント管理番号を取得する
	 * @return commentId
	 */
	public String getCommentId() {
		return commentId;
	}

	/**
	 * 名前(画面に表示)を取得する
	 * @return inputName
	 */
	public String getInputName() {
		return inputName;
	}

	/**
	 * 感想記入年(画面に表示)を取得する
	 * @return inputYear
	 */
	public String getInputYear() {
		return inputYear;
	}

	/**
	 * 感想記入月(画面に表示)を取得する
	 * @return inputMonth
	 */
	public String getInputMonth() {
		return inputMonth;
	}

	/**
	 * 感想記入日(画面に表示)を取得する
	 * @return inputDay
	 */
	public String getInputDay() {
		return inputDay;
	}

	/**
	 * Twitterアカウント(画面に表示)を取得する
	 * @return inputTwitter
	 */
	public String getInputTwitter() {
		return inputTwitter;
	}

	/**
	 * facebookアカウント(画面に表示)を取得する
	 * @return inputFacebook
	 */
	public String getInputFacebook() {
		return inputFacebook;
	}

	/**
	 * ユーザーコメント(画面に表示)を取得する
	 * @return inputComment
	 */
	public String getInputComment() {
		return inputComment;
	}

	/**
	 * 画面に表示するメッセージ(画面に表示)を取得する
	 * @return message
	 */
	public String getMessage() {
		return message;
	}	
	
}
