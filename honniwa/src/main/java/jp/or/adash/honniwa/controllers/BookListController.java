package jp.or.adash.honniwa.controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jp.or.adash.honniwa.dto.Book;
import jp.or.adash.honniwa.services.BookListService;
import jp.or.adash.honniwa.view.BookListView;

/**
 * 検索結果一覧画面の動作に関するコントローラークラス
 * @author T.kajihara
 *
 */
@Path("/web/list")
@Produces(MediaType.TEXT_HTML)
public class BookListController {
	
	/**
	 * 入力された検索語句に該当する書籍情報をjspに遷移するメソッド
	 * @param searchCondition 検索窓に入力された検索語句
	 * @return
	 */
	@GET
	public Response search(@QueryParam("searchCondition") String searchCondition) {
		// データベースの書籍テーブルから一覧を取得する
		BookListService service = new BookListService();
		List<Book> bookList = service.getBookList(searchCondition);
		
		// 受け渡すデータを作成する
		BookListView view = new BookListView(searchCondition, bookList);
		
		// JSPに遷移する
		return ResponseUtil.view("/booklist.jsp", view);
	}
	
	
}
