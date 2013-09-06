/**
 * 
 */
package com.softsec.tase.store.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.softsec.tase.common.rpc.domain.app.AppAdvertiser;
import com.softsec.tase.common.rpc.domain.app.AppExternalLink;
import com.softsec.tase.common.rpc.domain.app.AppType;
import com.softsec.tase.common.rpc.domain.app.OriginType;
import com.softsec.tase.store.util.db.SQLMapperFactory;
import com.softsec.tase.store.util.db.SessionUtils;

/**
 * AppDaoTest.java
 * @author yanwei
 * @date 2013-1-31 下午11:12:44
 * @description
 */
public class AppDaoTest extends TestCase {
	
	@Test
	public void testStore() {
		String storeName = "apkChina";
		String storeUrl = "apk.china.cn";
		@SuppressWarnings("unused")
		int storeType = 1;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
//			System.out.println(appDao.addStore(storeName, storeUrl, storeType));
//			session.commit();
			System.out.println(appDao.getStoreId(AppType.APK, OriginType.UNOFFICIAL_STORE, storeName));
		} catch (Exception e) {
			e.printStackTrace();
			SessionUtils.rollback(session);
		} finally {
			SessionUtils.close(session);
		}
	}
	
//	@Test
//	public void testCategory() {
//		String storeName = "apkChina";
//		String category = "工具";
//		SqlSession session = null;
//		try {
//			session = SQLMapperFactory.openSession();
//			AppDao appDao = new AppDao(session);
//			System.out.println(appDao.addCategory(storeName, category));
//			session.commit();
//			int categoryId = appDao.getCategoryId(storeName, category);
//			System.out.println(categoryId);
//			System.out.println(appDao.getCountByCategoryId(categoryId));
//		} catch (Exception e) {
//			e.printStackTrace();
//			SessionUtils.rollback(session);
//		} finally {
//			SessionUtils.close(session);
//		}
//	}
	
//	@SuppressWarnings("deprecation")
//	@Test
//	public void testCheckAppDuplication() {
//		String storeName = "gfan";
//		String appName = "gfantest";
//		String appVersion = "1.7.1";
//		String url = "apk.gfan.com/gfantest_1.7.1";
//		
//		SqlSession session = null;
//		try {
//			session = SQLMapperFactory.openSession();
//			AppDao appDao = new AppDao(session);
//			System.out.println(appDao.getAppIdByBriefInfo(storeName, appName, appVersion, url));
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			SessionUtils.close(session);
//		}
//	}
	
	@Test
	public void testAppAdvertiser() {
		AppAdvertiser ad1 = new AppAdvertiser();
		ad1.setAdvertiserEmail("xxx");
		ad1.setAdvertiserName("ooo");
		AppAdvertiser ad2 = new AppAdvertiser();
		ad2.setAdvertiserName("ppp");
		ad2.setAdvertiserWebsite("www");
		List<AppAdvertiser> appAdvertiserList = new ArrayList<AppAdvertiser>();
		appAdvertiserList.add(ad1);
		appAdvertiserList.add(ad2);
		System.out.println(appAdvertiserList);
		
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			retValue = appDao.saveAppAdvertisement(10000700000045L, appAdvertiserList);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			e.printStackTrace();
		} finally {
			SessionUtils.close(session);
		}
		System.out.println(retValue);
	}
	
	public void testAppStatus() {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			retValue = appDao.saveAppStatus(10000700000045L, 0, 1, 0, 0);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			e.printStackTrace();
		} finally {
			SessionUtils.close(session);
		}
		System.out.println(retValue);
	}
	
	public void testInsertAppExternal() {
		AppExternalLink link1 = new AppExternalLink();
		link1.setExternalStoreName("xxx");
		link1.setExternalUrl("www");
		AppExternalLink link2 = new AppExternalLink();
		link2.setExternalStoreName("lll");
		link2.setExternalUrl("000");
		List<AppExternalLink> linkList = new ArrayList<AppExternalLink>();
		linkList.add(link1);
		linkList.add(link2);
		
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			retValue = appDao.saveAppExternal(10000700000045L, linkList);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			e.printStackTrace();
		} finally {
			SessionUtils.close(session);
		}
		System.out.println(retValue);
	}
	
	public void testAddNewStore() {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			retValue = appDao.saveStore(AppType.APK, OriginType.UNOFFICIAL_STORE, "test1", "test1", "test1", 1);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			e.printStackTrace();
		} finally {
			SessionUtils.close(session);
		}
		System.out.println(retValue);
	}
	@Test
	public void testUpdateCollectedCount() {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			retValue = appDao.updateAppCollectedCount(AppType.APK, OriginType.OFFICIAL_STORE, 55, 50);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			e.printStackTrace();
		} finally {
			SessionUtils.close(session);
		}
		System.out.println(retValue);
	}
	@Test
	public void testUpdateDownloadedCount() {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			retValue = appDao.updateAppDownloadedCount(AppType.APK, OriginType.OFFICIAL_STORE, 55);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			e.printStackTrace();
		} finally {
			SessionUtils.close(session);
		}
		System.out.println(retValue);
	}
	
	@Test
	public void testUpdateAppVersion() {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			retValue = appDao.saveAppVersion(1102000100000099L, "1392", System.currentTimeMillis(), null, 1L, 1L, 1L, 1L, 1L);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			e.printStackTrace();
		} finally {
			SessionUtils.close(session);
		}
		System.out.println(retValue);
	}
	
	@Test
	public void testUpdateAppStatus() {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			retValue = appDao.updateAppStatus(1102000100000013L, 0, 0, 1, 1);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			e.printStackTrace();
		} finally {
			SessionUtils.close(session);
		}
		System.out.println(retValue);
	}
	
	@Test
	public void testSaveAppMisc() {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			retValue = appDao.saveAppMisc(1102000103030014L, "China", "English", "'lalala'".replace("'", "\""), "hahaha", "USD", 0.0);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			e.printStackTrace();
		} finally {
			SessionUtils.close(session);
		}
		System.out.println(retValue);
	}
	
	@Test
	public void testSaveDownloadUrl() {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			retValue = appDao.saveAppResource(2101009000001869L, null, "http://l1.leaderhero.com/apple/r0027/It\\'sJustaThought_v1.2(os3.0).ipa".replaceAll("'", "\""), null);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			e.printStackTrace();
		} finally {
			SessionUtils.close(session);
		}
		System.out.println(retValue);	
	}
}
