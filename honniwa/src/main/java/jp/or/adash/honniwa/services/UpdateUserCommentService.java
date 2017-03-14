package jp.or.adash.honniwa.services;

import java.io.IOException;
import java.util.List;

import jp.or.adash.honniwa.dao.BookDao;
import jp.or.adash.honniwa.dao.Transaction;
import jp.or.adash.honniwa.dao.UserCommentDao;
import jp.or.adash.honniwa.dto.Book;
import jp.or.adash.honniwa.dto.UserComment;
import jp.or.adash.honniwa.util.DateUtil;
import jp.or.adash.honniwa.util.TwitterUtil;
import jp.or.adash.honniwa.view.UpdateUserCommentView;

/**
 * ユーザー感想入力機能サービス
 * @author wanikawa
 *
 */
public class UpdateUserCommentService {
	
	private final BookDao bookDao;
	private final UserCommentDao commentDao;
	private final Transaction transaction;
	
	private static final String BOOKID_NULL_MESSAGE = "URLが間違っています。システム担当者に連絡してください。";
	private static final String SUCCESS_MESSAGE = "更新が完了しました。";
	private static final String ERROR_MESSAGE = "更新に失敗しました。";
	private static final String BLANK_ERROR_MESSAGE = "感想日付を正しく入力してください。";
	private static final String DATE_ERROR_MESSAGE = "感想日付を正しく入力してください。";
	private static final String EDIT_MESSAGE = "感想を編集します。";
	private static final String EDIT_FINISH_MESSAGE = "編集が完了しました。";
	private static final String NAME_ERROR_MESSAGE = "名前は２００文字以上は入力不可です。";
	private static final String TWITTER_ERROR_MESSAGE = "Twitterは５０文字以上は入力不可です。";
	private static final String FACEBOOK_ERROR_MESSAGE = "Facebookは５０文字以上は入力不可です。";
	
	private static final int NAME_LIMIT = 200;
	private static final int TWITTER_LIMIT = 50;
	private static final int FACEBOOK_LIMIT = 50;
	/**
	 * コンストラクタ
	 */
	public UpdateUserCommentService() {
		this.bookDao = new BookDao();
		this.commentDao = new UserCommentDao();
 		this.transaction = new Transaction();
	}
		
	/**
	 * 書籍のユーザーコメント一覧を取得する
	 * @param bookId	書籍管理番号
	 * @param commentId	ユーザーコメント管理番号
	 * @return 感想編集画面に渡すエンティティ
	 */
	public UpdateUserCommentView getCommentView(String bookId,String commentId) {
		
		UpdateUserCommentView view = null;
		UserComment userComment = null;
		List<UserComment> userCommentList = null;
		Book book = null;
		
		try{

			transaction.begin();
			
			//URLのbookIdが未入力の時のエラー回避
			book = bookDao.find(transaction,bookId);
			if(book ==null){
				return new UpdateUserCommentView(book,BOOKID_NULL_MESSAGE);
			}
			
			book = bookDao.find(transaction,bookId);
		
			//コメントIDの記入の有無で分岐する
			if(commentId==null ||commentId.equals("")){
				//新規登録画面
				userComment=null;
				userCommentList = commentDao.find(transaction, bookId);
				book.setUserCommentList(userCommentList);
				view = new UpdateUserCommentView(book,commentId,"","","","","","","","");
			}else{
				//既存データ編集画面
				userComment = commentDao.find(transaction, bookId,commentId);
				String date = userComment.getDate();
				view = new UpdateUserCommentView(book,
						                         commentId,
						                         userComment.getName(),
						                         DateUtil.getDateInfo(date,"yyyy"),
						                         DateUtil.getDateInfo(date,"M"),
						                         DateUtil.getDateInfo(date,"d"),
						                         userComment.getTwitter(),
						                         userComment.getFacebook(),
						                         userComment.getComment(),
						                         EDIT_MESSAGE);
			}
	
			transaction.commit();
						
		}catch(IOException e){
		//更新が失敗した時の処理
			userComment = new UserComment();
			String date = userComment.getDate();
			view = new UpdateUserCommentView(book,
					                         commentId,
					                         userComment.getName(),
					                         DateUtil.getDateInfo(date,"yyyy"),
					                         DateUtil.getDateInfo(date,"m"),
					                         DateUtil.getDateInfo(date,"d"),
					                         userComment.getTwitter(),
					                         userComment.getFacebook(),
					                         userComment.getComment(),
					                         ERROR_MESSAGE);
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
	 * commentIdが""のとき新規登録、""ではないとき既存情報修正になる。
	 * @param date		感想日付
	 * @param bookId	書籍管理番号
	 * @param commentId	ユーザーコメント管理番号
	 * @param title		書籍タイトル
	 * @param isbn		ISBN
	 * @param ownerId	オーナー番号
	 * @param name		感想記入者名
	 * @param twitter	Twitterアカウント
	 * @param facebook	FaceBookアカウント
	 * @param comment	ユーザー感想
	 * @return 感想編集画面に渡すエンティティ
	 */
	public UpdateUserCommentView updateComment(String year,String month,String day, String bookId, String commentId, String title, String isbn,
			String ownerId, String name, String twitter, String facebook, String comment) {
				
		Book book = null;
		String message = "";
		List<UserComment> commentList = null;
		UpdateUserCommentView view = null;
		
		try{
			
			// データベース接続を開始する
			transaction.begin();
			
			//twitterアカウントの頭文字(@)チェック
			twitter=TwitterUtil.getTwitter(twitter);
			
			//URLのbookIdが未入力の時のエラー回避
			if(bookId==null||bookId.equals("")){
				return new UpdateUserCommentView(book,BOOKID_NULL_MESSAGE);
			}
			
			//今日の日付を取得
			String today = DateUtil.getToday();
			
			//感想日付
			String date = DateUtil.toHankaku(year) + "-" + DateUtil.toHankaku(month)  + "-" + DateUtil.toHankaku(day);
			
			//入力チェック
			String strChk = inputCheck(date,name,twitter,facebook);
			if(!strChk.equals("")){
				book= new Book(bookId,isbn,title,"","",commentDao.find(transaction, bookId),null,"");
				return new UpdateUserCommentView(book,"",name,year,month,day,twitter,facebook,comment,strChk);
			}
								
			//コメントIDの記入の有無で分岐する
			if(commentId==null ||commentId.equals("")){
				//新規登録画面
				// データベースにアクセスして、書籍情報を更新する
				commentDao.insert(transaction,date,bookId,isbn,ownerId,name,twitter,facebook,comment,today);
				
				message = SUCCESS_MESSAGE;
			}else{
				//既存データ編集画面
				// データベースにアクセスして、書籍情報を更新する
				commentDao.update(transaction,commentId,date,name,twitter,facebook,comment,today);
				
				message = EDIT_FINISH_MESSAGE;
			}
			
			// コメントの一覧を取得
			commentList=commentDao.find(transaction, bookId);
			
			//戻り値データ作成
			view = new UpdateUserCommentView(new Book(bookId,isbn,title,"","",commentList,null,""),message);
			
			// データベース接続を正常終了する
			transaction.commit();
			
		}catch(IOException e){
			
			//失敗時のメッセージ
			message = ERROR_MESSAGE ;
			
			//戻り値データ作成
			view = new UpdateUserCommentView(new Book(bookId,isbn,title,"","",commentList,null,""),message);
			
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
	 * 引数の値が記入されているかチェックする
	 * @param date 記入日付
	 * @return エラーメッセージ（チェックが問題ない場合、""を返す）
	 */
	private String inputCheck(String date,String name,String twitter,String facebook){
		
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
		
		//入力必須箇所が入力されているかチェック
		if(date.equals("")){
			return BLANK_ERROR_MESSAGE;
		};
		
		//日付が正しいかチェック
		if(!DateUtil.checkDate(date)){;
			return DATE_ERROR_MESSAGE;
		};
		
		return "";
	}
	
}
