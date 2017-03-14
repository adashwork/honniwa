package jp.or.adash.honniwa.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * DataSourceを保持するクラス
 * @author T.Kawasaki
 *
 */
final class DataSourceHolder {
	private final DataSource ds;

	/**
	 * コンストラクタ
	 * DataSourceを作成する
	 */
	public DataSourceHolder() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/bookcomment");
		} catch (NamingException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * DataSourceを取得する
	 * @return DataSource
	 */
	public DataSource getDataSource() {
		return ds;
	}
}
