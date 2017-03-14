/**
 *
 */
package jp.or.adash.honniwa.view;

/**
 * 書籍データインポート画面に受け渡すオブジェクト
 * @author T.Kawasaki
 *
 */
public class BookImportView {

	private String message;

	/**
	 * コンストラクタ
	 * @param message 処理結果メッセージ
	 */
	public BookImportView(String message) {
		this.message = message;
	}

	/**
	 * 処理結果メッセージを取得する
	 * @return 処理結果メッセージ
	 */
	public String getMessage() {
		return message;
	}
}
