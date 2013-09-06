/**
 * 
 */
package com.softsec.tase.store.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softsec.tase.common.domain.app.App;
import com.softsec.tase.common.rpc.domain.app.AppType;
import com.softsec.tase.common.rpc.domain.app.OriginType;
import com.softsec.tase.store.dao.AppDao;
import com.softsec.tase.store.domain.AppCategoryItem;
import com.softsec.tase.store.exception.DbUtilsException;
import com.softsec.tase.store.util.db.SQLMapperFactory;
import com.softsec.tase.store.util.db.SessionUtils;

/**
 * App存储服务类
 * @author yanwei
 * @date 2013-1-4 下午8:40:46
 * 
 */

public class AppStorageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppStorageService.class);
	/**
	 * add new store
	 * @param storeName
	 * @param storeUrl
	 * @param storeType
	 * @return
	 * @throws DbUtilsException
	 */
	public int addStore(AppType appType, OriginType originType, String storeName, 
			String storeDisplayName, String storeUrl, int storeType) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			retValue = appDao.saveStore(appType, originType, storeName, storeDisplayName, storeUrl, storeType);
			session.commit();
		} catch (Exception e) {
			LOGGER.error("Failed to add store info : " + storeName + " : " + e.getMessage(), e);
			SessionUtils.rollback(session);
			throw new DbUtilsException("Failed to insert store info : " + storeName + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	/**
	 * get store id
	 * @param storeName
	 * @param storeUrl
	 * @param storeType
	 * @return
	 * @throws DbUtilsException
	 */
	private int getStoreId(AppType appType, OriginType originType, String storeName) throws NullPointerException, DbUtilsException {
		int storeId = 0;
		SqlSession session = null;
		AppDao appDao = null;
		try {
			session = SQLMapperFactory.openSession();
			appDao = new AppDao(session);
			storeId = appDao.getStoreId(appType, originType, storeName);
		} catch (NullPointerException npe) {
			
			// No such store found, just return 0
			return storeId;
			
		} catch (Exception e) {
			LOGGER.error("Failed to get store id : " + storeName + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get store id : " + storeName + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return storeId;
	}
	/**
	 * add new category
	 * @param storeName
	 * @param category
	 * @return
	 * @throws DbUtilsException
	 */
	public int addCategory(AppType appType, OriginType originType, String storeName, String category, int masterId) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			retValue = appDao.saveCategory(appType, originType, storeName, category, masterId);
			session.commit();
		} catch (Exception e) {
			LOGGER.error("Failed to add new category : " + storeName + " : " + category + " : " + e.getMessage(), e);
			SessionUtils.rollback(session);
			throw new DbUtilsException("Failed to add new category : " + storeName + " : " + category + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	/**
	 * get category id
	 * @param storeName
	 * @param category
	 * @return
	 * @throws DbUtilsException
	 */
	public int getCategoryId(AppType appType, OriginType originType, String storeName, String category, int masterId) throws NullPointerException, DbUtilsException {
		int categoryId = 0;
		SqlSession session = null;
		AppDao appDao = null;
		try {
			session = SQLMapperFactory.openSession();
			appDao = new AppDao(session);
			categoryId = appDao.getCategoryId(appType, originType, storeName, category, masterId);
		} catch (NullPointerException npe) {
			
			// No such category found, just return 0
			return categoryId;
			
		} catch (Exception e) {
			LOGGER.error("Failed to get category id : " + storeName + " : " + category + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get category id : " + storeName + " : " + category + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return categoryId;
	}
	/**
	 * add & get category id
	 * @param storeName
	 * @param category
	 * @param masterId
	 * @return
	 * @throws NullPointerException
	 * @throws DbUtilsException
	 */
	public int addAndGetCategoryId(AppType appType, OriginType originType, String storeName, String category, int masterId) 
			throws NullPointerException, DbUtilsException {
		int categoryId = 0;
		SqlSession session = null;
		AppDao appDao = null;
		try {
			session = SQLMapperFactory.openSession();
			appDao = new AppDao(session);
			appDao.saveCategory(appType, originType, storeName, category, masterId);
			session.commit();
			categoryId = appDao.getCategoryId(appType, originType, storeName, category, masterId);
			session.commit();
		} catch (NullPointerException npe) {
			return categoryId;
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to add and get category id [ " + storeName + " : " 
					+ category + " : " + masterId + " ] : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to add and get category id [ " + storeName + " : " 
					+ category + " : " + masterId + " ] : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return categoryId;
	}
	/**
	 * get category count
	 * @param categoryId
	 * @return
	 * @throws DbUtilsException
	 */
	public long getAppCollectedCountByCategoryId(int categoryId) throws DbUtilsException {
		long count = 0L;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			count = appDao.getAppCollectedCountByCategoryId(categoryId);
		} catch (Exception e) {
			LOGGER.error("Failed to get category count : " + categoryId + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get category count : " + categoryId + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return count;
	}
	/**
	 * update collected count by category id
	 * @param categoryId
	 * @param collectedCount
	 */
	public int updateAppCollectedCount(AppType appType, OriginType originType, int categoryId, int collectedCount) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			retValue = appDao.updateAppCollectedCount(appType, originType, categoryId, collectedCount);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to update app count in category : " + categoryId  + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to update app count in category : " + categoryId + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	/**
	 * update app downloaded count
	 * @param appType
	 * @param originType
	 * @param categoryId
	 * @return
	 * @throws DbUtilsException
	 */
	public int updateAppDownloadedCount(AppType appType, OriginType originType, int categoryId) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			retValue = appDao.updateAppDownloadedCount(appType, originType, categoryId);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to update app count in category : " + categoryId  + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to update app count in category : " + categoryId + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	/**
	 * get app category list by master id
	 * @param masterId
	 * @return
	 */
	public List<AppCategoryItem> getCategoryList(AppType appType, OriginType originType, int masterId) throws DbUtilsException {
		List<AppCategoryItem> appCategoryItemList = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			appCategoryItemList = appDao.getCategoryList(appType, originType, masterId);
		} catch (Exception e) {
			LOGGER.error("Failed to get app category list in master node [ " + masterId + " ] : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get app category list in master node [ " + masterId + " ] : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return appCategoryItemList;
	}
	/**
	 * check app 's duplication using app checksum
	 * @param appChecksum <br />
	 * 		MD5(StoreName + URL + Version)
	 * @return app id
	 * @throws NullPointerException
	 * @throws DbUtilsException
	 */
	public long checkAppDuplication(AppType appType, OriginType originType, String appChecksum) throws NullPointerException, DbUtilsException {
		long appId = 0L;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			appId = appDao.getAppIdByAppChecksum(appType, originType, appChecksum);
		} catch (NullPointerException npe) {
			return appId;
		} catch (Exception e) {
			LOGGER.error("Failed to check app checksum duplication : " + appChecksum + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to check app checksum duplication : " + appChecksum + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return appId;
	}
	/**
	 * save app into database
	 * @param app
	 * @return
	 * @throws DbUtilsException
	 */
	public int save(App app) throws DbUtilsException {
		int retValue = 0;
		if (app == null
				|| app.getAppId() == 0L) {
			return retValue;
		}
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			retValue +=	appDao.saveAppAdvertisement(app.getAppId(), app.getAppAdvertiserList());
			retValue += appDao.saveAppAntivirus(app.getAppId(), app.getAppAntivirusList());
			retValue += appDao.saveAppComment(app.getAppId(), app.getAppCommentList());
			retValue += appDao.saveAppChecksum(app.getAppId(), app.getAppChecksum(), app.getCollectedTime());
			retValue += appDao.saveAppDeveloper(app.getAppId(), app.getDeveloperName(), app.getDeveloperWebsite(),
					app.getDeveloperEmail(), app.getPrivacyPolicy());
			retValue += appDao.saveAppExternal(app.getAppId(), app.getAppExternalLinkList());
			retValue += appDao.saveAppInternal(app.getAppId(), app.getInnerId());
			retValue += appDao.saveAppLabel(app.getAppId(), 0);
			retValue += appDao.saveAppMisc(app.getAppId(), app.getCountry(), app.getLanguage(), 
					app.getDescription(), app.getSize(), app.getCurrencyUnit(), app.getPrice());
			retValue += appDao.saveAppPermission(app.getAppId(), app.getAppPermissionList());
			retValue += appDao.saveAppPlatform(app.getAppId(), app.getPlatform(), app.getOsType(), 
					app.getOsVersion(), app.getDevice());
			retValue += appDao.saveAppResource(app.getAppId(), app.getUrl(), app.getDownloadUrl(), app.getLogoUrl());
			retValue += appDao.saveAppSnapshot(app.getAppId(), app.getSnapshotUrlList());
			retValue += appDao.saveAppStat(app.getAppId(), app.getDownloadFloor(), app.getDownloadCeiling(), 
					app.getRating(), app.getRatingCount(), app.getReview(), app.getContentRating());
			retValue += appDao.saveAppStatus(app.getAppId(), 0, 0, 0, 0);
			retValue += appDao.saveAppVersion(app.getAppId(), app.getAppVersion(), app.getUpdatedTime(), 
					app.getUpdateHistory(), app.getMajorVersion(), app.getMinorVersioin(),
					app.getRevisionVersion(), app.getBuildVersion(), app.getExtraVersion());
			retValue += appDao.saveAppVisualization(app.getAppId(), app.getStoreDisplayName(), app.getAppName(),
					app.getAppVersion(), app.getSize(), app.getUrl());
			session.commit();
		} catch (Exception e) {
			LOGGER.error("Failed to save app : " + app  + " : " + e.getMessage(), e);
			SessionUtils.rollback(session);
			throw new DbUtilsException("Failed to save app : " + app  + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	/**
	 * update app status
	 * @param appId
	 * @param isDeprecated
	 * @param isFileDownloaded
	 * @param isImageDownloaded
	 * @param isLogoDownloaded
	 * @return
	 * @throws DbUtilsException
	 */
	public int updateAppStatus(Long appId, int isDeprecated, int isFileDownloaded, 
			int isImageDownloaded, int isLogoDownloaded) throws DbUtilsException {
		int retValue = 0;
		if (appId == 0) {
			return retValue;
		}
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			AppDao appDao = new AppDao(session);
			retValue = appDao.updateAppStatus(appId, isDeprecated, isFileDownloaded, isImageDownloaded, isLogoDownloaded);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to update app status : " + appId + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to update app status : " + appId + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
}
