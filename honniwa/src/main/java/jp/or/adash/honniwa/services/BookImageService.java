package jp.or.adash.honniwa.services;

/**
 * 楽天ブックスから画像を取得するクラス
 * @author T.Kajihara
 *
 */
public class BookImageService {

	private String isbn;

/**
 * 楽天ブックスから画像のURLを取得
 * @param isbn 書籍のisbn
 * @return 書籍イメージのURL
 */
	public String getBookImage(String isbn){

		//isbnの桁数が13桁であるかどうか
		if(isbn == null || isbn.length() != 13){
			return "";
		}

		this.isbn = isbn;
		String underIsbn = this.isbn.substring(9);

		 String bookImageUrl = "http://thumbnail.image.rakuten.co.jp/@0_mall/book/cabinet/" + underIsbn + "/" + this.isbn + ".jpg";
		 return bookImageUrl;
	}

}
