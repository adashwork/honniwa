package jp.or.adash.honniwa.dto;

import java.util.ArrayList;
import java.util.List;
/**
 * 書籍
 * @author wanikawa
 *
 */
public class Book {
	private final String id;		//書籍管理番号
	private final String isbn;		//ISBN
	private final String title;		//本タイトル
	private final String author;	//著者
	private final String bookImage; // 書籍画像
	private List<UserComment> userCommentList; //使用者コメント情報
	private OwnerComment ownerComment; //寄贈者コメント情報
	private final String count;      //感想件数

	public Book(){
		this.id="";
		this.isbn="";
		this.title="";
		this.author="";
		this.bookImage="";
		this.userCommentList= new ArrayList<UserComment>();
		this.ownerComment=new OwnerComment();
		this.count = "";
	}
	
	/**
	 * コンストラクタ
	 * @param id	書籍管理番号
	 * @param isbn 	ISBN
	 * @param title 本タイトル
	 * @param author 著者
	 * @param bookImage 書籍画像
	 * @param userComment ユーザー感想情報
	 * @param ownerComment 寄贈者感想情報
	 */
	public Book(String id,
				String isbn,
				String title,
				String author,
				String bookImage,
				List<UserComment> userCommentList,
				OwnerComment ownerComment,
				String count){
		this.id=id;
		this.isbn=isbn;
		this.title=title;
		this.author=author;
		this.bookImage=bookImage;
		this.userCommentList=userCommentList;
		this.ownerComment=ownerComment;
		this.count = count;
	}

	/**
	 * 書籍管理番号を取得する。
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * ISBNを取得する。
	 * @return isbn
	 */
	public String getIsbn() {
		return isbn;
	}
	/**
	 * 本タイトルを取得する。
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 著者を取得する。
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}
	/** 
	 * 書籍画像を取得する
	 *  @return bookImage
	 */
	public String getBookImage(){
		return bookImage;
	}

	/**
	 * ユーザー感想情報を取得する。
	 * @return userCommentList
	 */
	public List<UserComment> getUserCommentList() {
		return userCommentList;
	}
	/**
	 * 寄贈者感想情報を取得する。
	 * @return ownerComment
	 */
	public OwnerComment getOwnerComment() {
		return ownerComment;
	}
	public String getCount(){
		return count;
	}

	/**
	 * @param userCommentList セットする userCommentList
	 */
	public void setUserCommentList(List<UserComment> userCommentList) {
		this.userCommentList = userCommentList;
	}

	/**
	 * @param ownerComment セットする ownerComment
	 */
	public void setOwnerComment(OwnerComment ownerComment) {
		this.ownerComment = ownerComment;
	}

	
}
