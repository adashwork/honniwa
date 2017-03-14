package jp.or.adash.honniwa.services;

import java.io.IOException;
import java.util.List;

import jp.or.adash.honniwa.dao.OwnerCommentDao;
import jp.or.adash.honniwa.dao.Transaction;
import jp.or.adash.honniwa.dto.OwnerComment;

public class OwnerInfoService {
	
	private final OwnerCommentDao ownerDao;
	private final Transaction transaction;
		
	/**
	 * コンストラクタ
	 */
	public OwnerInfoService() {
		this.ownerDao = new OwnerCommentDao();
		this.transaction = new Transaction();
	}
	
	public List<OwnerComment> getOwnerList(String ownerId,String name,String twitter,String facebook,String email) {

		List<OwnerComment> ownerList = null;

		try {
			// データベース接続を開始する
			transaction.begin();

			// データベースにアクセスし、オーナー情報リストを取得する
			ownerList = ownerDao.findOwnerList(transaction, ownerId,name,twitter,facebook,email);
						
			// データベース接続を正常終了する
			transaction.commit();

		}catch(IOException e) {
			// 異常終了
//			throw new IllegalStateException(e);
			} finally {
				// データベース接続がある場合
				if (transaction.isActive()){
					// データベース接続をエラー終了する
					transaction.rollback();
				}
			}

		return ownerList;
	}
	
	public OwnerComment getOwnerInfo(String ownerId,String name,String twitter,String facebook,String email)  {
	
		return new OwnerComment("","",ownerId,name,email,twitter,facebook,"");
	}
}