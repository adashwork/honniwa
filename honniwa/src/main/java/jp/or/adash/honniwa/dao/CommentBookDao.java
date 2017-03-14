
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
 * コメントの入った書籍情報を取得する。
 * @return ブックリストコメント
 * @throws IOException
 * @author morii 
 * @param transaction トランザクション
 */
public class CommentBookDao {

	
	private static final String SQL_FIND_COMMENTBOOK = "select book.bookid, book.isbn,count(commentid) as count ,book.regdate "
			+"from bookcomment.book " 
			+"inner JOIN bookcomment.comment ON bookcomment.book.bookid = bookcomment.comment.bookid "
			+"group by book.bookid ORDER BY book.regdate DESC limit 10";
		
	
	public  List<Book> getBookList(Transaction transaction) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		Connection con = transaction.getResource(Connection.class);

		// SQLを設定する（PrepareedStatementとして）
		try (PreparedStatement ps = con.prepareStatement(SQL_FIND_COMMENTBOOK)) {
			try (ResultSet rs = ps.executeQuery()) {
				
				List<Book> bookList = new ArrayList<>();
				BookImageService bookImage = new BookImageService();

				while (rs.next()) {

					bookList.add ( new Book(rs.getString("bookid"), rs.getString("isbn"),"",
							"",bookImage.getBookImage(rs.getString("isbn")),null,null,rs.getString("count")));
					}

				return bookList;
			} catch (SQLException e) {
				throw new IOException(e);
			}
			
		} catch (SQLException e) {
			throw new IOException(e);
		}

	}

}
