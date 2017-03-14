/**
 * 
 */
package jp.or.adash.honniwa.controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jp.or.adash.honniwa.dto.Book;
import jp.or.adash.honniwa.dto.OwnerComment;
import jp.or.adash.honniwa.dto.UserComment;
import jp.or.adash.honniwa.services.CommentService;

/**
 * @author kimura,wanikawa
 * 書籍情報,ユーザー情報,オーナー情報取得
 * 
 */
@Path("/web/detail")
@Produces(MediaType.TEXT_HTML)
public class CommentController {
	@GET
	
	public Response search(@QueryParam("bookid") String bookid) {
		// 	bookidでメゾッと
		CommentService service = new CommentService();
		Book book = service.getBook(bookid);
		// Book book = null;
		
		if(book != null){
			
			List<UserComment> userCommentList = service.getUserComment(bookid);
			OwnerComment ownerComment = service.getOwnerComment(bookid);

			book.setUserCommentList(userCommentList);
			book.setOwnerComment(ownerComment);
		}
		
		/*
		if (book == null){
			return ResponseUtil.view("/message.jsp", new String("該当書籍がありません。"));
		}else{
			// JSPに遷移する
			return ResponseUtil.view("/message.jsp", book);
		}
		*/
		// JSPに遷移する
					return ResponseUtil.view("/message.jsp", book);
	}

	

}
