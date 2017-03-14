/**
 * 
 */
package jp.or.adash.honniwa.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.or.adash.honniwa.dto.UserComment;


/**
 * UserCommentDtoへの書き込み、UserCommentテーブルへの追加、更新
 * @author kimura
 *
 */
public class UserCommentDao {
	
	private static final String SQL_FIND_USERCOMMENT = "select commentid ,name, twitter, facebook, comment, commentdate from comment where bookid = ? order by commentdate desc";
	private static final String SQL_FIND_USERCOMMENT_BY_COMMENTID = "select name, twitter, facebook, comment, commentdate from comment where bookid = ? and commentid = ?";
	private static final String SQL_INSERT
	= "insert into comment (bookid,isbn,ownerid,name,twitter,facebook,comment,commentdate,sendmailflg,regdate,regstaffno,modifydate,modifystaffno) "
				 + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE
	= "update comment set name = ?,commentdate = ?,twitter = ?,facebook = ?,comment = ? , modifydate = ? ,modifystaffno = ? where commentid = ? ";
	
	private static final String MAIL_FLG = "0";
	private static final String USER_ID = "0";
	
	/**
	 * ユーザーコメント情報をすべてを取得する
	 * @param transaction トランザクション
	 * @return ユーザーコメントリスト
	 * @throws IOException
	 */

	public List<UserComment> find(Transaction transaction,String bookid) throws IOException {
		Connection con = transaction.getResource(Connection.class);

		// SQLを設定する（PrepareedStatementとして）
		try (PreparedStatement ps = con.prepareStatement(SQL_FIND_USERCOMMENT)) {
			ps.setString(1, bookid);

			try (ResultSet rs = ps.executeQuery()) {

				List<UserComment> userComment = new ArrayList<>();
				String str;
				
				while (rs.next()) {
					
					str = rs.getString("commentdate");
					str = str.substring(0,10);
					
					userComment.add ( new UserComment (rs.getString("commentid") ,str ,rs.getString("name"), rs.getString("facebook"), rs.getString("twitter"),
										rs.getString("comment")));
				}

				return userComment;
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
	/**
	 * コメントIDを使ってユーザー感想一件を取得する
	 * @param transaction
	 * @param bookid	書籍管理番号
	 * @param commentId	ユーザーコメント管理番号
	 * @return
	 * @throws IOException
	 */
	public UserComment find(Transaction transaction,String bookid,String commentId) throws IOException {
		Connection con = transaction.getResource(Connection.class);

		// SQLを設定する（PrepareedStatementとして）
		try (PreparedStatement ps = con.prepareStatement(SQL_FIND_USERCOMMENT_BY_COMMENTID)) {
			
			ps.setString(1, bookid);
			ps.setString(2, commentId);
			
			try (ResultSet rs = ps.executeQuery()) {

				UserComment userComment = null;
				String str;
				
				if (rs.next()) {
					
					str = rs.getString("commentdate");
					str = str.substring(0,10);
					
					userComment= new UserComment (str ,rs.getString("name"), rs.getString("facebook"), rs.getString("twitter"), rs.getString("comment"));
				}

				return userComment;
				
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	/**
	 * commentテーブルに行の追加
	 * @param transaction
	 * @param date		感想日付
	 * @param bookId	書籍管理番号
	 * @param isbn		ISBN
	 * @param ownerId	オーナー番号
	 * @param name		入力者名
	 * @param twitter	Twitterアカウント
	 * @param facebook	Facebookアカウント
	 * @param comment	感想
	 * @param today		今日の日付
	 * @return
	 * @throws IOException
	 */
	public int insert(Transaction transaction,
					  String date,
					  String bookId,
					  String isbn,
					  String ownerId,
					  String name,
					  String twitter,
					  String facebook,
					  String comment,
					  String today) throws IOException{
		
		// Connectionオブジェクトを取得する
		Connection con = transaction.getResource(Connection.class);
	
		// SQL文をPreparedStatementに格納する
		try(PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {
			// PreparedStatementにパラメータ値を格納する
			//コメントID(オートインクリメント)
			ps.setString(1, bookId); 		//書籍管理番号
			ps.setString(2, isbn);			//ISBN
			ps.setString(3, ownerId); 		//オーナーID
			ps.setString(4, name);			//記入者名
			ps.setString(5, twitter);		//Twitter
			ps.setString(6, facebook);		//facebook
			ps.setString(7, comment);		//感想
			ps.setString(8, date);			//感想記入日
			ps.setString(9, MAIL_FLG);			//メール送信フラグ
			ps.setString(10, today);		//登録日時
			ps.setString(11, USER_ID );			//登録職員
			ps.setString(12, today);		//更新日時
			ps.setString(13, USER_ID );			//更新職員
			
			// SQLを実行する
			return ps.executeUpdate();
	
		} catch(SQLException e) {
			throw new IOException(e);
		}
	
	}

	/**
	 * commentテーブルの指定行の修正
	 * @param transaction
	 * @param commentId	ユーザーコメント管理番号
	 * @param date		感想日付
	 * @param name		感想記入者名
	 * @param twitter	twitterアカウント
	 * @param facebook	facebookアカウント
	 * @param comment	ユーザーコメント
	 * @param today		今日の日付
	 * @return
	 * @throws IOException
	 */
	public int update(Transaction transaction,
					  String commentId, 
					  String date, 
					  String name, 
					  String twitter, 
					  String facebook,
					  String comment, 
					  String today) throws IOException {
		
		// Connectionオブジェクトを取得する
		Connection con = transaction.getResource(Connection.class);
	
		// SQL文をPreparedStatementに格納する
		try(PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {
			// PreparedStatementにパラメータ値を格納する
			//コメントID(オートインクリメント)
			ps.setString(1, name); 			//記入者名
			ps.setString(2, date);			//感想記入日
			ps.setString(3, twitter); 		//Twitterアカウント
			ps.setString(4, facebook);		//Facebookアカウント
			ps.setString(5, comment);		//ユーザー感想
			ps.setString(6, today);			//更新日
			ps.setString(7, USER_ID );		//更新職員
			ps.setString(8, commentId);		//コメントID
					
			// SQLを実行する
			return ps.executeUpdate();
	
		} catch(SQLException e) {
			throw new IOException(e);
		}
		
	}

}
