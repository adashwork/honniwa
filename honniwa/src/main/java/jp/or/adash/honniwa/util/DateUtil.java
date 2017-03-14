package jp.or.adash.honniwa.util;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
	
	public static String toHankaku(String word){
		return Normalizer.normalize(word, Normalizer.Form.NFKC);
	}
	
	/**
	 * 引数（date）から年、月、日などの情報を抽出する
	 * @param date		日付(2017ｰ8ｰ9)
	 * @param pattern	取り出す情報の指定(年:yyyy、月:M、日:d)
	 * @return 引数(pattern)に合致した日付情報
	 */
	public static String getDateInfo(String date,String pattern){
		String str;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date formatDate = sdf.parse(date);
			str = new SimpleDateFormat(pattern).format(formatDate);
		}catch(ParseException e){
			str = "";
		}
		 return str;
	}

	/**
	 * 引数の値が日付か確認する
	 * @param date 記入日付
	 * @return 引数が日付の場合true
	 */
	public static boolean checkDate(String date){

		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			//日付データが送られてきたとき
			format.setLenient(false);
		    format.parse(date); 
		} catch (ParseException e) {
		    //日付データではないとき
			return false;
		}
		
		return true;
	}
	
	/**
	 * 今日の日付を返す
	 * @return 今日の日付
	 */
	public static String getToday(){
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss");
		return now.format(dtf);
	}
	
}
