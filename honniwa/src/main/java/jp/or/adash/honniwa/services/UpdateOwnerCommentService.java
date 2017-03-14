package jp.or.adash.honniwa.services;

import java.io.IOException;

import jp.or.adash.honniwa.dao.BookDao;
import jp.or.adash.honniwa.dao.OwnerCommentDao;
import jp.or.adash.honniwa.dao.Transaction;
import jp.or.adash.honniwa.dto.Book;
import jp.or.adash.honniwa.dto.OwnerComment;
import jp.or.adash.honniwa.util.DateUtil;
import jp.or.adash.honniwa.util.TwitterUtil;
import jp.or.adash.honniwa.view.UpdateOwnerCommentView;
/**
 * オーナー感想入力機能コントローラ
 * @author kise
 *
 */
public class UpdateOwnerCommentService {
	
	private final BookDao bookDao;
	private final OwnerCommentDao ownerDao;
	private final Transaction transaction;
	
	private static final String SUCCESS_MESSAGE = "更新が完了しました。";
	private static final String FAIL_MESSAGE = "更新に失敗しました。";
	private static final String BOOKID_ERROR_MESSAGE = "書籍管理番号が間違っています。システム管理者に連絡してください。";
	private static final String BLANK_ERROR_MESSAGE = "おすすめ理由か感想のいずれかを入力してください。";
	private static final String DATE_ERROR_MESSAGE = "正しい日付を入力ください。";
	private static final String OWNERID_ERROR_MESSAGE = "オーナーIDは１１文字以上は入力不可です。";
	private static final String NAME_ERROR_MESSAGE = "名前は２００文字以上は入力不可です。";
	private static final String TWITTER_ERROR_MESSAGE = "Twitterは５０文字以上は入力不可です。";
	private static final String FACEBOOK_ERROR_MESSAGE = "Facebookは５０文字以上は入力不可です。";
	private static final String EMAIL_ERROR_MESSAGE = "Eメールアドレスは２００文字以上は入力不可です。";
	
	private static final int OWNERID_LIMIT = 11;
	private static final int NAME_LIMIT = 200;
	private static final int TWITTER_LIMIT = 50;
	private static final int FACEBOOK_LIMIT = 50;
	private static final int EMAIL_LIMIT = 200;
	/**
	 * コンストラクタ
	 */
	public UpdateOwnerCommentService() {
		this.bookDao = new BookDao();
		this.ownerDao = new OwnerCommentDao();
		this.transaction = new Transaction();
	}
	
	/**
	 * 画面に情報を表示する。
	 * @param bookId 書籍管理番号
	 * @return 感想編集画面
	 */
	public UpdateOwnerCommentView getTitle(String bookId) {

		UpdateOwnerCommentView view = null;
		
		try {
			// データベース接続を開始する
			transaction.begin();

			// データベースにアクセスして、書籍リストを取得する
			Book book = bookDao.find(transaction, bookId);
			if(book==null){
				book = new Book();
			}
			view = new UpdateOwnerCommentView("","","",book.getId(),book.getTitle(),new OwnerComment(),"");
			
			// データベース接続を正常終了する
			transaction.commit();

		}catch(IOException e) {
			// 異常終了
//			throw new IllegalStateException(e);
			} finally {
				// データベース接続がある場合
				if (transaction.isActive()) {
					// データベース接続をエラー終了する
					transaction.rollback();
				}
			}

		return view;
	}

	/**
	 * コメント情報を追加する
	 * @param bookId	書籍管理番号
	 * @param isbn		ISBN
	 * @param ownerId	オーナー番号
	 * @param name		入力者名
	 * @param twitter	Twitterアカウント
	 * @param facebook	Facebookアカウント
	 * @param comment		感想
	 * @param date		入力日付
	 * @return		感想編集画面
	 */
	public UpdateOwnerCommentView updateComment(String bookId,
	                          String title,
	                          String year, 
	                          String month, 
	                          String day, 
	                          String ownerId,
	                          String name,
	                          String twitter,
	                          String facebook,
	                          String email,
	                          String recommend1,
	                          String recommend2,
	                          String recommend3,
	                          String recommend4,
	                          String ownerComment) {
		
		UpdateOwnerCommentView view = null;
		OwnerComment owner = null;
		
		try{
			
			String date = year + "-" + month + "-" + day;
			String recommend = getRecommend(recommend1 , recommend2 , recommend3 ,recommend4);
			
			//Twitterアカウントの頭に@がある場合取り除く
			twitter=TwitterUtil.getTwitter(twitter);
			
			//入力チェック
			String strChk=inputCheck(date,recommend,ownerComment,bookId,ownerId,name,twitter,facebook,email);
			if(!strChk.equals("")){
				owner = new OwnerComment("","",ownerId,name,email,twitter,facebook,ownerComment);
				return new UpdateOwnerCommentView(year,month,day,bookId,title,owner,strChk);
			}

			// データベース接続を開始する
			transaction.begin();
			
			String today = DateUtil.getToday();
			
			//オーナーIDがない場合、ownerテーブルに寄贈者情報を挿入する
			if(ownerId.equals("")){
				
				String mailFlg = "0";
				if(email.equals("")){
					mailFlg = "1";					
				}

				bookDao.insertOwnerInfo(transaction,name,twitter,facebook,email,date,mailFlg,today);
				
				//ownerIDを検索する
				ownerId = ownerDao.findOwnerId(transaction,name,twitter,facebook,email,today);
			}
						
			// データベースにアクセスして、書籍情報を更新する
			ownerDao.updateComment(transaction, bookId,date,ownerId,name,recommend,ownerComment,today);
			
			//戻り値Viewの作成
			view = new UpdateOwnerCommentView("","","",bookId,title,new OwnerComment(),SUCCESS_MESSAGE);
			
			// データベース接続を正常終了する
			transaction.commit();
	
		}catch(IOException e){
			owner = new OwnerComment("","",ownerId,name,email,twitter,facebook,ownerComment);
			view = new UpdateOwnerCommentView(year,month,day,bookId,title,owner,FAIL_MESSAGE);
			// 異常終了
//			throw new IllegalStateException(e);
		}finally{
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
		
		return view;
	}

	/**
	 * 入力内容確認
	 * @param date 日付
	 * @param recommend おすすめ理由（"0010"）
	 * @param comment 感想
	 * @param bookId	書籍管理番号
	 * @param name	記入者名
	 * @param twitter	 Twitterアカウント
	 * @param facebook	Facebookアカウント
	 * @return エラーメッセージ（チェックが問題ない場合、""を返す）
	 */
	private String inputCheck(String date ,String recommend ,String comment,String bookId,String ownerId,String name, String twitter, String facebook, String email){
		
		//bookIdがデータベースに存在するか確認
		if(bookId.equals("")){
			return BOOKID_ERROR_MESSAGE;
		}
		
		//日付が正しいかチェック
		if(DateUtil.checkDate(date)!=true){
			return DATE_ERROR_MESSAGE;
		}
		
		//オーナーIDの文字数制限チェック
		if(ownerId.length()>=OWNERID_LIMIT ){
			return OWNERID_ERROR_MESSAGE;
		}
		
		//名前の文字数制限チェック
		if(name.length()>=NAME_LIMIT ){
			return NAME_ERROR_MESSAGE;
		}
				
		//Twitterの文字数制限チェック
		if(twitter.length()>=TWITTER_LIMIT ){
			return TWITTER_ERROR_MESSAGE;
		}
				
		//Facebookの文字数制限チェック
		if(facebook.length()>=FACEBOOK_LIMIT ){
			return FACEBOOK_ERROR_MESSAGE;
		}
		
		//オーナーIDの文字数制限チェック
		if(email.length()>=EMAIL_LIMIT ){
			return EMAIL_ERROR_MESSAGE;
		}
		
		//入力必須箇所が入力されているかチェック
		if(date.equals("--") || (recommend.equals("0000") && (comment == null||comment.equals("") ))){
			return BLANK_ERROR_MESSAGE;
		}
		
		return "";
	}
	/**
	 * おすすめ理由を一つのデータにまとめる
	 * @param recommend1 楽しかった本です。(チェックが入っていれば"1"そうでなければ"0")
	 * @param recommend2 感動した本です。(チェックが入っていれば"1"そうでなければ"0")
	 * @param recommend3 勉強になった本です。(チェックが入っていれば"1"そうでなければ"0")
	 * @param recommend4 気持ちが楽になった本です。(チェックが入っていれば"1"そうでなければ"0")
	 * @return　おすすめ理由("0110")
	 */
	private String getRecommend(String recommend1,String recommend2,String recommend3,String recommend4){
		
		if(recommend1 == null){
			recommend1="0";
		}
		if(recommend2 == null){
			recommend2="0";
		}
		if(recommend3 == null){
			recommend3="0";
		}
		if(recommend4 == null){
			recommend4="0";
		}
		return  recommend1 + recommend2 + recommend3 + recommend4;
	}
}