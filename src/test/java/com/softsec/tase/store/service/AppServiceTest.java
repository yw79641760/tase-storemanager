/**
 * 
 */
package com.softsec.tase.store.service;

import junit.framework.TestCase;

import org.junit.Test;

import com.softsec.tase.common.rpc.domain.app.AppType;
import com.softsec.tase.common.rpc.domain.app.OriginType;
import com.softsec.tase.store.exception.DbUtilsException;
import com.softsec.tase.store.service.AppStorageService;

/**
 * AppServiceTest.java
 * @author yanwei
 * @date 2013-2-1 上午2:31:35
 * @description
 */
public class AppServiceTest extends TestCase {
	
	@Test
	public void testAddStore() {
		AppStorageService service = new AppStorageService();
		service.addStore(AppType.APK, OriginType.UNOFFICIAL_STORE, "test", "test", "test", 1);
	}

//	@Test
//	public void testGenerateAppId() {
//		String storeName = "appChina1";
//		String storeUrl = "apk.china.com";
//		int storeType = 1;
//		String category = "工具";
//		AppStorageService service = new AppStorageService();
//		try {
//			System.out.println(service.generateAppId(storeName, storeUrl, storeType, category));
//		} catch (DbUtilsException due) {
//			due.printStackTrace();
//		}
//	}
	
//	@Test
//	public void testCheckMd5Duplication() {
//		String md5 = "lalala";
//		AppStorageService service = new AppStorageService();
//		try {
//			System.out.println(service.checkFileDuplication(md5));
//		} catch (NullPointerException npe) {
//			System.out.println("New app downloaded .");
//			npe.printStackTrace();
//		} catch (DbUtilsException due) {
//			System.out.println("Query failed .");
//			due.printStackTrace();
//		}
//	}
}
