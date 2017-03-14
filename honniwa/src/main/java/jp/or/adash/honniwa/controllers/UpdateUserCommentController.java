package jp.or.adash.honniwa.controllers;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jp.or.adash.honniwa.services.UpdateUserCommentService;
import jp.or.adash.honniwa.view.UpdateUserCommentView;

/**
 * ユーザー感想入力機能コントローラ
 * @author wanikawa
 *
 */
@Path("/web/admin/usercomment")
@Produces(MediaType.TEXT_HTML)
public class UpdateUserCommentController {

	/**
	 * 画面に情報を表示する。
	 * commentIdが""のとき新規登録画面、""ではないとき既存情報修正画面につながる。
	 * @param bookId	書籍管理番号
	 * @param commentId	ユーザーコメント管理番号
	 * @return 感想編集画面
	 */
	@GET
	public Response show(@QueryParam("bookId") String bookId,@QueryParam("commentId") String commentId){

		UpdateUserCommentView view = new UpdateUserCommentService().getCommentView(bookId,commentId);

		return ResponseUtil.view("/usercomment.jsp", view);
	}

	/**
	 * 画面に入力した情報をデータベースに登録する。
	 * commentIdが""のとき新規登録、""ではないとき既存情報修正になる。
	 * @param bookId	書籍管理番号
	 * @param commentId	ユーザーコメント管理番号
	 * @param title		書籍タイトル
	 * @param date		感想日付
	 * @param isbn		ISBN
	 * @param ownerId	オーナー番号
	 * @param name		感想記入者名
	 * @param twitter	Twitterアカウント
	 * @param facebook	FaceBookアカウント
	 * @param comment	ユーザー感想
	 * @return　感想編集画面
	 */
	@POST
	public Response editUpdate(@FormParam("bookId") String bookId,
							   @FormParam("commentId") String commentId,
						   	   @FormParam("title") String title,
						       @FormParam("year") String year,
						       @FormParam("month") String month,
						       @FormParam("day") String day,
						       @FormParam("isbn") String isbn,
						       @FormParam("ownerId") String ownerId,
						       @FormParam("username") String name,
						       @FormParam("twitter") String twitter,
						       @FormParam("facebook") String facebook,
						       @FormParam("comment") String comment){

		UpdateUserCommentView view = new UpdateUserCommentService().updateComment(year,month,day,bookId,commentId,title,isbn,ownerId,name,twitter,facebook,comment);

		return ResponseUtil.view("/usercomment.jsp", view);
	}
}
