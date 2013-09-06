/**
 * 2011-7-25 SessionUtil.java
 */
package com.softsec.tase.store.util.db;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author long.ou
 * 
 */
public class SessionUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(SessionUtils.class);

	/**
	 * roll backe safely.
	 * 
	 * @param session
	 * @return failed or success.
	 */
	public static boolean rollback(SqlSession session) {
		if (session == null) {
			LOGGER.info("SESSION is NULL.");
			return false;
		} else {
			session.rollback();
			return true;
		}
	}

	/**
	 * close safely.
	 * 
	 * @param session
	 * @return failed or success.
	 */
	public static boolean close(SqlSession session) {
		if (session == null) {
			LOGGER.info("SESSION is NULL.");
			return false;
		} else {
			session.close();
			return true;
		}
	}
}
