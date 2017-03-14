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

import jp.or.adash.honniwa.dao.Transaction;
import jp.or.adash.honniwa.dto.Book;
import jp.or.adash.honniwa.services.BookImageService;

 /**
  * @author kimura
  *
  */
 public class BookDao {

	 // 書籍名、著者名、オーナー感想、ユーザー感想のいずれかに検索語句の文字列を含む書籍情報をデータベースから取得するSQL文
         private static final String SQL_FIND_SEARCHRESULT = "select distinct book.bookid, book.title, book.author, book.isbn "
        		 + "from book left join comment on book.bookid = comment.bookid "
        		 + "where title collate utf8_unicode_ci like concat('%', ?, '%') "
        		 + "or author collate utf8_unicode_ci like concat('%', ?, '%') "
        		 + "or ownercomment collate utf8_unicode_ci like concat('%', ?, '%') "
        		 + "or comment collate utf8_unicode_ci like concat('%', ?, '%') ";


        		 
         // 検索一覧画面からの遷移時にURLのクエリパラメータに指定された書籍idに該当する書籍情報を取得するSQL文
         private static final String SQL_FIND_BOOK = "select bookid, title, author, isbn from book where bookid = ? ";



		private static final String SQL_INSERT_TO_OWNER
		= "insert into owner (ownername,ownermail,ownertwitter,ownerfacebook,getMailFlg,regDate,regstaffno,modifydate,modifystaffno) "
					 + "values (?,?,?,?,?,?,?,?,?)";



		private static final String SQL_INSERT = 
				"insert into book() values()";
                 
         /**
          * 検索結果一覧情報を取得する
         * @param transaction トランザクション
          * @param searchCondition 検索語句
         * @return 検索結果リスト
         * @throws IOException
          */
         
         public List<Book> selectByCondition(Transaction transaction, String searchCondition) throws IOException {
                 Connection con = transaction.getResource(Connection.class);

                 // SQLを設定する（PrepareedStatementとして）
                try (PreparedStatement ps = con.prepareStatement(SQL_FIND_SEARCHRESULT)) {
                	
                         ps.setString(1, searchCondition);
                         ps.setString(2, searchCondition);
                         ps.setString(3, searchCondition);
                         ps.setString(4, searchCondition);
         
                         try (ResultSet rs = ps.executeQuery()) {

                                 List<Book> bookList = new ArrayList<>();
                                 
                                 // isbnから画像urlを取得するためのオブジェクト
                                 BookImageService bookImage = new BookImageService();
                                 
                                 //書籍情報を格納する
                                 while (rs.next()) {

                                        bookList.add ( new Book(rs.getString("bookid"), rs.getString("isbn"), rs.getString("title"),
                                                       rs.getString("author"), bookImage.getBookImage(rs.getString("isbn")), null, null,""));
                                 }

                                 
                                 return bookList;
                         } catch (SQLException e) {
                                 throw new IOException(e);
                         }
                 } catch (SQLException e) {
                         throw new IOException(e);
                 }
         }
         

         /**
          * ユーザーコメント情報をすべてを取得する.
          * @param transaction トランザクション
         * @return ユーザーコメントリスト
         * @throws IOException
          */

         public Book find(Transaction transaction, String bookid) throws IOException {
                 Connection con = transaction.getResource(Connection.class);

                 // SQLを設定する（PrepareedStatementとして）
                try (PreparedStatement ps = con.prepareStatement(SQL_FIND_BOOK)) {
                         ps.setString(1, bookid);
                         try (ResultSet rs = ps.executeQuery()) {
                                 
                                 
                                 Book book = null;
                              // isbnから画像urlを取得するためのオブジェクト
                                 BookImageService bookImage = new BookImageService();
                                 // 書籍情報を格納する
                                 if (rs.next()) {

                                        book = new Book(rs.getString("bookid"), rs.getString("isbn"), rs.getString("title"),rs.getString("author"), 
                                        		bookImage.getBookImage(rs.getString("isbn")), null, null,"");
                                 }

                                 return book;
                         } catch (SQLException e) {
                                 throw new IOException(e);
                         }
                 } catch (SQLException e) {
                         throw new IOException(e);
                 }
         }


		public int insertOwnerInfo(Transaction transaction, String name, String twitter, String facebook, String email,
				String date,String mailFlg,String today) throws IOException{
			
			// Connectionオブジェクトを取得する
			Connection con = transaction.getResource(Connection.class);
		
			// SQL文をPreparedStatementに格納する
			try(PreparedStatement ps = con.prepareStatement(SQL_INSERT_TO_OWNER)) {
				// PreparedStatementにパラメータ値を格納する
				//コメントID(オートインクリメント)
				ps.setString(1, name); 	//おすすめ理由
				ps.setString(2, email);	//メッセージ
				ps.setString(3, twitter); 			//記入日付
				ps.setString(4, facebook);			//書籍管理番号
				ps.setString(5, mailFlg);
				ps.setString(6, today);
				ps.setString(7, "0");
				ps.setString(8, today);
				ps.setString(9, "0");
				
				// SQLを実行する
				return ps.executeUpdate();
		
			} catch(SQLException e) {
				throw new IOException(e);
			}
			
		}

	/**
	 * 書籍を登録する
	 * @param transaction トランザクションオブジェクト
	 * @param book 書籍データ
	 * @return 処理件数
	 * @throws IOException 登録処理に失敗した際に発生 
	 */
	public int insert(Transaction transaction, Book book) throws IOException {
		// Connectionオブジェクトを取得する
		Connection con = transaction.getResource(Connection.class);
		
		// SQL文をPreparedStatementに格納する
		try (PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {
			// パラメータを設定する
			
			
			// 登録処理を行う
			return ps.executeUpdate();
			
		} catch(SQLException e) {
			throw new IOException(e);
		}
	}

}
