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

import jp.or.adash.honniwa.dto.OwnerComment;

/**
 * OwnerCommentDtoへの書き込み、UserCommentテーブルへの追加、更新
 * @author wanikawa,koyama
 *
 */
public class OwnerCommentDao {
	
	private static final String SQL_FIND_OWNERCOMMENT = "select book.bookid bookid,"
															 + "owner.ownerid ownerid, "
															 + "book.ownername namebook, "
															 + "owner.ownername nameowner, "
															 + "owner.ownermail mail, "
															 + "owner.ownertwitter twitter, "
															 + "owner.ownerfacebook facebook, "
															 + "book.ownercomment comment,"
															 + "book.ownerdate date "
															 + "from owner "
															 + "left join book "
															 + "on owner.ownerid = book.ownerid "
															 + "where book.bookid = ? ";
	
	private static final String SQL_FIND_OWNERID = "select ownerid "
														+ "from owner "
			 											+ "where ownername = ? "
			 											+ "and ownermail = ? "
			 											+ "and ownertwitter = ? "
			 											+ "and ownerfacebook = ? "
			 											+ "and regDate = ? ";
	
	private static final String SQL_FIND_OWNERLIST = "select ownerid,ownername,ownertwitter,ownerfacebook,ownermail "
			                                      + "from owner ";
	
	//where句を付け足す処理
	int setFlag = 0;
	int setFlagOwnerId = 0;
	int setFlagName = 0;
	int setFlagTwitter = 0;
	int setFlagFacebook = 0;
	int setFlagEmail = 0;

	private static final String SQL_UPDATE = "update book set "
	 + "ownerid = ? , "
	 + "ownername = ? , "
	 + "recommend = ? , "
	 + "ownercomment = ? , "
	 + "ownerdate = ? , "
	 + "modifydate = ? , "
	 + "modifystaffno = ? "
	 + "where bookid = ? ";
	
	/**
	 * 寄贈者コメント情報を取得する
	 * @param transaction トランザクション
	 * @bookId	 書籍管理番号
	 * @return 寄贈者コメント
	 * @throws IOException
	 */
	public OwnerComment find(Transaction transaction, String bookid) throws IOException {
		Connection con = transaction.getResource(Connection.class);

		// SQLを設定する（PrepareedStatementとして）
		try (PreparedStatement ps = con.prepareStatement(SQL_FIND_OWNERCOMMENT)) {
			
			ps.setString(1, bookid);

			try (ResultSet rs = ps.executeQuery()) {

				OwnerComment ownerComment = null;
				
				if (rs.next()) {
					
					String str = rs.getString("date");
					str = str.substring(0,10);
					
					ownerComment = new OwnerComment (str,rs.getString("bookid"), rs.getString("ownerid"), rs.getString("namebook"),
					rs.getString("mail"),rs.getString("twitter"),rs.getString("facebook"),rs.getString("comment"));
				}

				return ownerComment;
				
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	/**
	 * 寄贈者管理番号を取得する
	 * @param transaction トランザクション
	 * @param name	寄贈者名
	 * @param twitter	　Twitterアカウント
	 * @param facebook	Facebookアカウント
	 * @param email	Emailアドレス
	 * @param today	今日の日付
	 * @return 寄贈者コメント
	 * @throws IOException
	 */
	public String findOwnerId(Transaction transaction, 
								 String name,
								 String twitter,
								 String facebook,
								 String email,
								 String today) throws IOException {
		
		
		
		Connection con = transaction.getResource(Connection.class);

		// SQLを設定する（PrepareedStatementとして）
		try (PreparedStatement ps = con.prepareStatement(SQL_FIND_OWNERID)) {
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, twitter);
			ps.setString(4, facebook);
			ps.setString(5, today);
	
			try (ResultSet rs = ps.executeQuery()) {

				String str = null;
				
				if (rs.next()) {
					
					str = rs.getString("ownerid");
					
				}

				return str;
				
			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
	/**
	 * 寄贈者コメント情報を取得する
	 * @param transaction
	 * @param ownerId 寄贈者管理番号
	 * @param name	寄贈者名
	 * @param twitter	　Twitterアカウント
	 * @param facebook	Facebookアカウント
	 * @param email	Emailアドレス
	 * @return	 寄贈者コメントリスト
	 * @throws IOException
	 */
	public List<OwnerComment> findOwnerList(Transaction transaction,
				                             String ownerId,
				                             String name,
				                             String twitter,
				                             String facebook,
				                             String email) throws IOException {

		Connection con = transaction.getResource(Connection.class);
		
		//SQL_FIND_OWNERLISTに条件分岐文を追加 
		List<String> whereStr = new ArrayList<String>();
		
		if(ownerId != null && !"".equals(ownerId)) {
			whereStr.add("ownerid = ?");
			setFlagOwnerId=++setFlag;
		}
		if(name != null && !"".equals(name)) {
			whereStr.add("ownername like concat('%', ? ,'%')");
			setFlagName=++setFlag;
		}
		if(twitter != null && !"".equals(twitter)) {
			whereStr.add("ownertwitter like concat('%', ? ,'%')");
			setFlagTwitter=++setFlag;
		}
		if(facebook != null && !"".equals(facebook)) {
			whereStr.add("ownerfacebook like concat('%', ? ,'%')");
			setFlagFacebook=++setFlag;
		}
		if(email != null && !"".equals(email)) {
			whereStr.add("ownermail like concat('%', ? ,'%')");
			setFlagEmail=++setFlag;
		}
		StringBuilder sqlFindBookListSb = new StringBuilder();
		sqlFindBookListSb.append(SQL_FIND_OWNERLIST);
		if (setFlag !=0){
			sqlFindBookListSb.append("where ");
			sqlFindBookListSb.append(String.join(" and ", whereStr));
		}

		// SQLを設定する（PrepareedStatementとして）
		try (PreparedStatement ps = con.prepareStatement(sqlFindBookListSb.toString())) {
			
		 if(setFlag !=0){
			if(setFlagOwnerId != 0){
				ps.setString(setFlagOwnerId, ownerId);
			}

			if(setFlagName != 0){
				ps.setString(setFlagName, name);
			}

			if(setFlagTwitter != 0){
				ps.setString(setFlagTwitter, twitter);
			}

			if(setFlagFacebook != 0){
				ps.setString(setFlagFacebook, facebook);
			}

			if(setFlagEmail != 0){
				ps.setString(setFlagEmail, email);
			}
		 }

			try (ResultSet rs = ps.executeQuery()) {
				
				List<OwnerComment> ownerComment = new ArrayList<>();
				
				while (rs.next()) {
					
					ownerComment.add ( new OwnerComment ("",
															  "",
															  rs.getString("ownerid"),
															  rs.getString("ownername"),
															  rs.getString("ownermail"),
															  rs.getString("ownertwitter"),
															  rs.getString("ownerfacebook"),
															  ""));
				}
								
				return ownerComment;
			

			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	/**
	 * 寄贈者情報を更新する
	 * @param transaction
	 * @param bookId　書籍管理番号
	 * @param title	書籍タイトル
	 * @param date	感想日付
	 * @param ownerId	オーナー管理場号
	 * @param name	記入者名
	 * @param twitter	　Twitterアカウント
	 * @param facebook　Facebookアカウント
	 * @param email	Emailアドレス
	 * @param recommend	おすすめ理由
	 * @param ownerComment	寄贈者感想
	 * @return	更新件数
	 * @throws IOException
	 */
		public int updateComment(Transaction transaction,
	                          String bookId,
							     String date,
							     String ownerId,
							     String name,
							     String recommend,
							     String ownerComment,
							     String today) throws IOException{
			
			// Connectionオブジェクトを取得する
			Connection con = transaction.getResource(Connection.class);
	
			// SQL文をPreparedStatementに格納する
			try(PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {
				// PreparedStatementにパラメータ値を格納する
				ps.setString(1, ownerId); 		//寄贈者ID
				ps.setString(2, name); 			//寄贈者名
				ps.setString(3, recommend); 	//おすすめ理由
				ps.setString(4, ownerComment);	//メッセージ
				ps.setString(5, date); 			//記入日付
				ps.setString(6, today); 			//更新日付
				ps.setString(7, "0"); 			//更新者ID
				ps.setString(8, bookId);			//書籍管理番号
				
				// SQLを実行する			 
				return ps.executeUpdate();
	
			} catch(SQLException e) {
				throw new IOException(e);
			}
	
		}
	
}
