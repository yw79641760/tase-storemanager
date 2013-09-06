/**
 * 
 */
package com.softsec.tase.store.util;

import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;

import com.mongodb.DB;
import com.softsec.tase.store.util.db.MongodbConnFactory;

/**
 *@author yanwei
 *@time: 2012-11-6 下午10:16:04
 *@description 
 *
 */
/**
 *+
 */
public class MongodbFactoryTest extends TestCase{
	
	@Test
	public void testMongodbConn() {
		DB tase = MongodbConnFactory.getDB();
		Set<String> collections = tase.getCollectionNames();
		for (String collect : collections) {
			System.out.println(collect);
		}
	}
	
}
