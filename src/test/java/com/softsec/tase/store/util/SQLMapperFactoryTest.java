/**
 * 
 */
package com.softsec.tase.store.util;

import junit.framework.TestCase;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.softsec.tase.store.util.db.SQLMapperFactory;
import com.softsec.tase.store.util.db.SessionUtils;

/**
 * 
 * @author yanwei
 * @date 2012-12-28 上午8:25:42
 * 
 */
public class SQLMapperFactoryTest extends TestCase {

	@Test
	public void testSQLMapperFactory() {
		SqlSession session = SQLMapperFactory.openSession();
		assertNotNull(session);
		SessionUtils.close(session);
	}
}