package jp.or.adash.honniwa.controllers;

import java.net.URI;

import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;

/**
 * Responseに関するユーティリティクラス
 * @author T.Kawasaki
 *
 */
class ResponseUtil {

	/**
	 * 画面遷移を行う
	 * @param jsp JSPファイルパス
	 * @param it JSPに受け渡すオブジェクト
	 * @return HTTPレスポンス
	 */
	public static Response view(String jsp, Object it) {
		return Response.ok().entity(new Viewable(jsp, it)).build();
	}

	/**
	 * リダイレクトを行う
	 * @param uri リダイレクト先URI
	 * @return HTTPレスポンス
	 */
	public static Response redirect(String uri) {
		return Response.seeOther(URI.create(uri)).build();
	}
}
