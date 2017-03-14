package jp.or.adash.honniwa.view;

import java.sql.Date;

import jp.or.adash.honniwa.dto.Book;

/**
 *
 *書籍一覧画面に一覧や検索情報を表示するクラス
 * @author Y.sakane & S.kuroda
 *
 */

public class OwnerIDView {

	private final String date;
	private final String ownerID;
	private final String email;
	private final String twitter;
	private final String name;
	private final String facebook;
	
	
	/**
	 * 更新後のメッセージ
	 */
	private final String updateMessage;

	/**
	 * コンストラクタ
	 * @param books 書籍リスト
	 * @param isbn ISBN
	 * @param title 書籍名
	 * @param author 著者名
	 * @param company 出版社名
	 * @param okbook 貸出可フラグ
	 * @param ngbook 禁帯出フラグ
	 * @param lostbook 紛失フラグ
	 * @param debit 借受状況
	 * @param returnlimit 返却期日
	 * @param abbreviation 他館略称
	 * @param remarks 備考
	 * @param annotation 件名
	 * @param shelf 棚場所
	 * @param customerId 利用者ID
	 */
	public OwnerIDView( String date, String ownerID, String email, String twitter, String name, String facebook, String updateMessage){
		
		this.date = date;
		this.ownerID = ownerID;
		this.email = email;
		this.twitter = twitter;
		this.name = name;
		this.facebook = facebook;
		this.updateMessage = updateMessage;
	}
	
	/**
	 * コンストラクタ
	 * @param books
	 * @param updateMessage
	 */
	public OwnerIDView(){
		
		this.date = "";
		this.ownerID = "";
		this.email = "";
		this.twitter = "";
		this.name = "";
		this.facebook = "";
		this.updateMessage = "";
	}
	
	/**
	 * インポート用表示メッセージ
	 * @return インポート後に表示されるメッセージ
	 */
	public String getUpdateMessage() {
		return this.updateMessage;
	}

	/**
	 * 書籍リストを取得する
	 * @return 書籍リスト
	 *
	 */
	

	/**
	 * ISBNを取得する
	 * @return ISBN
	 */
	public String getIsbn() {
		return date;
	}

	/**
	 * 書籍名を取得する
	 * @return 書籍名
	 */
	public String getTitle() {
		return ownerID;
	}

	/**
	 * 著者名を取得する
	 * @return 著者名
	 */
	public String getAuthor() {
		return email;
	}

	/**
	 * 出版社名を取得する
	 * @return 出版社名
	 */
	public String getCompany() {
		return twitter;
	}


	/**
	 * 貸出可フラグを取得する
	 * @return 貸出可フラグ
	 */
	public String getOkbook() {
		return name;
	}

	/**
	 * 禁帯出フラグを取得する
	 * @return 禁帯出フラグ
	 */
	public String getNgbook() {
		return facebook;
	}
	
}
