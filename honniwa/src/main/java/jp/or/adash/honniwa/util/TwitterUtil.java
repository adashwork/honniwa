package jp.or.adash.honniwa.util;

public class TwitterUtil {
	/**
	 * twitterアカウントの頭文字の@を消す
	 * @param twitter
	 * @return 頭文字の@を抜いたtwitterアカウント
	 */
	public static String getTwitter(String twitter){
		
		if(twitter.equals("")){
			return twitter;
		}
		
		String str = twitter.substring(0, 1);
		if(str.equals("@")){
			twitter= twitter.substring(1, twitter.length());
		}
		return twitter;
	}
}
