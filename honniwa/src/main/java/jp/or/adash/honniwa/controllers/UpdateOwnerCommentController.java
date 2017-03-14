package jp.or.adash.honniwa.controllers;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jp.or.adash.honniwa.services.UpdateOwnerCommentService;
import jp.or.adash.honniwa.view.UpdateOwnerCommentView;

/**
 * オーナー感想入力機能コントローラ
 * @author kise
 *
 */

@Path("/web/admin/ownercomment")
@Produces(MediaType.TEXT_HTML)
public class UpdateOwnerCommentController {
	/**
	 * 画面に情報を表示する。
	 * @param bookId 書籍管理番号
	 * @return　感想編集画面
	 */
	@GET
	public Response show(@QueryParam("bookId") String bookId){
			
		UpdateOwnerCommentView view = new UpdateOwnerCommentService().getTitle(bookId);
		
		return ResponseUtil.view("/ownercomment.jsp", view);
	
	}
	/**
	 * 画面に入力した情報をデータベースに登録する。
	 * @param bookId	書籍管理番号
	 * @param title	書籍タイトル
	 * @param year	感想記入年
	 * @param month	感想記入月
	 * @param day		感想記入日
	 * @param ownerId	　オーナー管理番号
	 * @param name	感想記入者名
	 * @param twitter		Twitterアカウント
	 * @param facebook	Facebookアカウント
	 * @param email		Emailアドレス
	 * @param recommend1 楽しかった本です。(チェックが入っていれば"1"そうでなければ"0")
	 * @param recommend2 感動した本です。(チェックが入っていれば"1"そうでなければ"0")
	 * @param recommend3 勉強になった本です。(チェックが入っていれば"1"そうでなければ"0")
	 * @param recommend4 気持ちが楽になった本です。(チェックが入っていれば"1"そうでなければ"0")
	 * @param ownerComment　オーナー感想
	 * @return	　感想編集画面
	 */
	@POST
	public Response insert(@FormParam("bookId") String bookId,
						   @FormParam("title") String title,
						   @FormParam("year") String year,
						   @FormParam("month") String month,
						   @FormParam("day") String day,
						   @FormParam("ownerId") String ownerId,
						   @FormParam("name") String name,
						   @FormParam("twitter") String twitter,
						   @FormParam("facebook") String facebook,
						   @FormParam("email") String email,
						   @FormParam("recommend1") String recommend1,
						   @FormParam("recommend2") String recommend2,
						   @FormParam("recommend3") String recommend3,
						   @FormParam("recommend4") String recommend4,
						   @FormParam("ownerComment") String ownerComment){
		
		UpdateOwnerCommentView view = new UpdateOwnerCommentService().updateComment(bookId,title,year,month,day,ownerId,name,twitter,facebook,email,recommend1,recommend2,recommend3,recommend4,ownerComment);		
			
		return ResponseUtil.view("/ownercomment.jsp", view);
	}
	
}
