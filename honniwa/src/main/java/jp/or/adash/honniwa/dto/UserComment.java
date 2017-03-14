package jp.or.adash.honniwa.dto;

/**
 * ユーザー感想
 * @author wanikawa
 *
 */
public class UserComment {
	
	private final String id;		//コメントID
	private final String date;		//記入日
	private final String name;		//記入者名
	private final String facebook;	//Facebookアカウント
	private final String twitter;	//Twitterアカウント
	private final String comment;	//感想
	
	/**
	 * 読者感想DTOのデフォルトコンストラクタ
	 */
	public UserComment(){
		this.id="";
		this.date="";
		this.name = "";
		this.facebook = "";
		this.twitter = "";
		this.comment = "";
	}
	
	/**
	 * 読者感想DTOのコンストラクタ
	 * @param date	記入日
	 * @param name	入力者名
	 * @param facebook　Facebookアカウント
	 * @param twitter　Twitterアカウント
	 * @param comment　感想
	 */
	public UserComment(String date,String name,String facebook,String twitter,String comment){
		this.id="";
		this.date = date;
		this.name = name;
		this.facebook = facebook;
		this.twitter = twitter;
		this.comment = comment;
	}
	/**
	 * 読者感想DTOのコンストラクタ
	 * @param id	コメントid
	 * @param date	記入日
	 * @param name	入力者名
	 * @param facebook　Facebookアカウント
	 * @param twitter　Twitterアカウント
	 * @param comment　感想
	 */
	public UserComment(String id,String date,String name,String facebook,String twitter,String comment){
		this.id=id;
		this.date = date;
		this.name = name;
		this.facebook = facebook;
		this.twitter = twitter;
		this.comment = comment;
	}
	
	/**
	 * コメントIDを取得する
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 記入日を取得する
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 入力者名を取得する
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return facebook
	 */
	public String getFacebook() {
		return facebook;
	}

	/**
	 * Facebookアカウントを取得する
	 * @return twitter
	 */
	public String getTwitter() {
		return twitter;
	}

	/**
	 * 感想を取得する
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}
	
	
}
