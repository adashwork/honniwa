package jp.or.adash.honniwa.dto;
/**
 * 寄贈者感想
 * @author wanikawa
 *
 */
public class OwnerComment {
	
	private final String date;	//記入日
	private final String bookId;//書籍管理番号
	private final String ownerId;//オーナーID
	private final String name; 	//オーナー名
	private final String email;	//Emailアドレス
	private final String twitter;	//twitterアドレス
	private final String facebook;	//FaceBookアドレス
	private final String comment;	//オーナー感想
	
	/**
	 * デフォルトコンストラクタ
	 */
	public OwnerComment(){
		this.date="";
		this.bookId="";
		this.ownerId="";
		this.name="";
		this.email="";
		this.twitter="";
		this.facebook="";
		this.comment="";
	}
	/**
	 * オーナー情報コンストラクタ
	 * @param date	記入日
	 * @param bookId 書籍管理番号
	 * @param ownerId オーナーID
	 * @param name	オーナー名
	 * @param email	Emailアドレス
	 * @param twitter　twitterアドレス
	 * @param facebook Facebookアドレス
	 * @param comment 寄贈者コメント
	 */
	public OwnerComment(String date,
						String bookId,
						String ownerId,
						String name,
						String email,
						String twitter,
						String facebook,
						String comment){
		this.date=date;
		this.bookId=bookId;
		this.ownerId=ownerId;
		this.name=name;
		this.email=email;
		this.twitter=twitter;
		this.facebook=facebook;
		this.comment=comment;
	}

	/**
	 * 記入日を取得する。
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 書籍管理番号を取得する。
	 * @return bookId
	 */
	public String getBookId() {
		return bookId;
	}
	/**
	 * オーナーIDを取得する。
	 * @return ownerId
	 */
	public String getOwnerId() {
		return ownerId;
	}
	/**
	 * オーナー名を取得する。
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Emailを取得する。
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * twitterアカウントを取得する。
	 * @return twitter
	 */
	public String getTwitter() {
		return twitter;
	}

	/**
	 * facebookアカウントを取得する。
	 * @return facebook
	 */
	public String getFacebook() {
		return facebook;
	}
	/**
	 * 寄贈者コメントを取得する。
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}
	
}
