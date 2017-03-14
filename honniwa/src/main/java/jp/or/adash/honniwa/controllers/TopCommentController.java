package jp.or.adash.honniwa.controllers;



import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jp.or.adash.honniwa.controllers.ResponseUtil;
import jp.or.adash.honniwa.dto.Book;
import jp.or.adash.honniwa.services.TopCommentService;



@Path("/web")
@Produces(MediaType.TEXT_HTML)
public class TopCommentController {

		/**
		 * 初期表示を行うメソッド
		 * @return 遷移先画面(index.jsp)
		 */
		@GET
		public Response showList() {
			List<Book> books = (new TopCommentService()).getBookList();

			return ResponseUtil.view("/index.jsp",books);

		}
	
}
