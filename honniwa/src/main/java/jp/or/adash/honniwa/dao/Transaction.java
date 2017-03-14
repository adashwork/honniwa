package jp.or.adash.honniwa.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * トランザクションクラス
 * @author T.Kawasaki
 *
 */
public class Transaction {
	// ---------- JUnitテスト時は、以下1行をコメントアウト  ----------
	private final DataSourceHolder dataSourceHolder = new DataSourceHolder();
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private Connection connection;

	/**
	 * トランザクション開始
	 * @throws TransactionException
	 */
	public void begin() throws TransactionException {
		if (connection == null) {
			try {
				// ---------- JUnitテスト時は、切り替え必要(start)  ----------
				connection = dataSourceHolder.getDataSource().getConnection();
//				connection = DriverManager.getConnection(
//						"jdbc:mysql://127.0.0.1:3306/biblio?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull",
//						"root", "password");
				// ---------- JUnitテスト時は、切り替え必要( end ) ----------
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				throw new TransactionException(e);
			}
		}
	}

	/**
	 * コミット
	 * @throws TransactionException
	 */
	public void commit() throws TransactionException {
		if (connection != null) {
			try {
				connection.commit();
			} catch (SQLException e) {
				throw new TransactionException(e);
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
//					logger.warn(e.getMessage(),e);
				}

				connection = null;
			}
		}
	}

	/**
	 * ロールバック
	 * @throws TransactionException
	 */
	public void rollback() throws TransactionException {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				throw new TransactionException(e);
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
//					logger.warn(e.getMessage(),e);
				}

				connection = null;
			}
		}
	}

	/**
	 * リソースオブジェクトを取得する
	 * @param klass クラスのデータ型（現状はConnectionクラスのみ対応）
	 * @return リソースオブジェクト
	 */
	public <T> T getResource(Class<T> klass) {
		if (klass.equals(Connection.class)) {
			@SuppressWarnings("unchecked")
			T con = (T) connection;

			return con;
		}

		throw new IllegalArgumentException();
	}

	/**
	 * トランザクションが開始されているかどうかを返す
	 * @return トランザクションが開始されているかどうか(true or false)
	 * @throws TransactionException
	 */
	public boolean isActive() throws TransactionException {
		try {
			if (connection != null && connection.isClosed()) {
				connection = null;
				return false;
			}
		} catch (SQLException e) {
			throw new TransactionException(e);
		}

		return true;
	}

	/**
	 * トランザクションに関する例外
	 * @author T.Kawasaki
	 *
	 */
	public static final class TransactionException extends RuntimeException{
		private static final long serialVersionUID = 1L;

		/**
		 * コンストラクタ
		 * @param e 例外オブジェクト
		 */
		public TransactionException(Exception e) {
			super(e);
		}
	}
}
