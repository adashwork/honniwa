/**
 *
 */
package jp.or.adash.honniwa.controllers;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jp.or.adash.honniwa.services.BookImportService;
import jp.or.adash.honniwa.view.BookImportView;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * 書籍データインポート画面のコントローラークラス
 * @author T.Kawasaki
 *
 */
@Path("/web/admin/bookimport")
public class BookImportController {

	/**
	 * 初期表示
	 * @return インポート画面
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response show() {
		return ResponseUtil.view("/bookimport.jsp", null);
	}

	/**
	 * CSVインポート実行
	 * @param file CSVファイルオブジェクト
	 * @param file2 CSVファイルの情報
	 * @return インポート画面
	 */
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response importBook(
			@FormDataParam("file") InputStream file,
			@FormDataParam("file") FormDataContentDisposition file2) {

		String message = "";
		BookImportService service = new BookImportService();

		// ファイルのチェックを行う
		message = service.checkFile(file, file2);
		if (message == null) {
			return ResponseUtil.view("/bookimport.jsp", new BookImportView(message));
		}

		// 書籍データのインポート処理
		message = service.execute(file);

		// インポート画面にフォワードする
		return ResponseUtil.view("/bookimport.jsp", new BookImportView(message));
	}
}
