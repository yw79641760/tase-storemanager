/**
 * 
 */
package com.softsec.tase.store.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.softsec.tase.common.rpc.domain.app.AppAdvertiser;
import com.softsec.tase.common.rpc.domain.app.AppAntivirus;
import com.softsec.tase.common.rpc.domain.app.AppComment;
import com.softsec.tase.common.rpc.domain.app.AppExternalLink;
import com.softsec.tase.common.rpc.domain.app.AppPermission;
import com.softsec.tase.common.rpc.domain.app.AppType;
import com.softsec.tase.common.rpc.domain.app.OriginType;
import com.softsec.tase.common.util.StringUtils;
import com.softsec.tase.store.domain.AppCategoryItem;
import com.softsec.tase.store.util.db.DbUtils;

/**
 * app data access object
 * @author yanwei
 * @date 2013-1-16 上午11:02:55
 * 
 */
public class AppDao {

	private static final String NAMESPACE = "com.softsec.tase.store.dao.App";
	
	private SqlSession session = null;
	
	public AppDao(SqlSession session) {
		this.session = session;
	}
	/**
	 * save app advertisement list
	 * @param appId
	 * @param appAdvertiserList
	 * @return
	 */
	public int saveAppAdvertisement(Long appId, List<AppAdvertiser> appAdvertiserList){
		int retValue = 0;
		if (appAdvertiserList != null
				&&	appAdvertiserList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "advertisement");
			condition.put("appId", appId);
			condition.put("appAdvertiserList", appAdvertiserList);
			retValue = session.insert(NAMESPACE + ".insertAppAdvertisement", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save app antivirus list
	 * @param appId
	 * @param appAntivirusList
	 * @return
	 */
	public int saveAppAntivirus(Long appId, List<AppAntivirus> appAntivirusList) {
		int retValue = 0;
		if (appAntivirusList != null
				&& appAntivirusList.size() != 0){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "antivirus");
			condition.put("appId", appId);
			condition.put("appAntivirusList", appAntivirusList);
			retValue = session.insert(NAMESPACE + ".insertAppAntivirus", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save app comment list
	 * @param appId
	 * @param appCommentList
	 * @return
	 */
	public int saveAppComment(Long appId, List<AppComment> appCommentList) {
		int retValue = 0;
		if (appCommentList != null
				&& appCommentList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "comment");
			condition.put("appId", appId);
			condition.put("appCommentList", appCommentList);
			retValue = session.insert(NAMESPACE + ".insertAppComment", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * add category by store name and category name
	 * @param storeName
	 * @param category
	 * @return
	 */
	public int saveCategory(AppType appType, OriginType originType, 
			String storeName, String category, int masterId) {
		int retValue = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tableName", DbUtils.getAppTablePrefix(appType, originType) + "category");
		condition.put("storeName", storeName);
		condition.put("category", category);
		condition.put("masterId", masterId);
		retValue = session.insert(NAMESPACE + ".insertAppCategory", condition);
		condition = null;
		return retValue;
	}
	/**
	 * save app checksum
	 * @param appId
	 * @param appChecksum
	 * @param loadedTime
	 * @return
	 */
	public int saveAppChecksum(Long appId, String appChecksum, Long loadedTime) {
		int retValue = 0;
		if (!StringUtils.isEmpty(appChecksum)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "checksum");
			condition.put("appId", appId);
			condition.put("appChecksum", appChecksum);
			condition.put("loadedTime", loadedTime);
			retValue = session.insert(NAMESPACE + ".insertAppChecksum", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save app developer
	 * @param appId
	 * @param developerName
	 * @param developerWebsite
	 * @param developerEmail
	 * @param privacyPolicy
	 * @return
	 */
	public int saveAppDeveloper(Long appId, String developerName, String developerWebsite, 
			String developerEmail, String privacyPolicy) {
		int retValue = 0;
		if (!StringUtils.isEmpty(developerName)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "developer");
			condition.put("appId", appId);
			condition.put("developerName", developerName);
			condition.put("developerWebsite", developerWebsite);
			condition.put("developerEmail", developerEmail);
			condition.put("privacyPolicy", privacyPolicy);
			retValue = session.insert(NAMESPACE + ".insertAppDeveloper", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save app external link list
	 * @param appId
	 * @param appExternalLinkList
	 * @return
	 */
	public int saveAppExternal(Long appId, List<AppExternalLink> appExternalLinkList) {
		int retValue = 0;
		if (appExternalLinkList != null
				&& appExternalLinkList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "external");
			condition.put("appId", appId);
			condition.put("appExternalLinkList", appExternalLinkList);
			retValue = session.insert(NAMESPACE + ".insertAppExternal", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save app image url
	 * @param appId
	 * @param appSnapshotUrlList
	 * @return
	 */
	public int saveAppSnapshot(Long appId, List<String> appSnapshotUrlList) {
		int retValue = 0;
		if (appSnapshotUrlList != null
				&& appSnapshotUrlList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "snapshot");
			condition.put("appId", appId);
			condition.put("appSnapshotUrlList", appSnapshotUrlList);
			retValue = session.insert(NAMESPACE + ".insertAppSnapshot", condition);
			condition = null;
		}
		return retValue; 
	}
	/**
	 * save app internal id
	 * @param appId
	 * @param innerId
	 * @return
	 */
	public int saveAppInternal(Long appId, String innerId) {
		int retValue = 0;
		if (!StringUtils.isEmpty(innerId)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "internal");
			condition.put("appId", appId);
			condition.put("innerId", innerId);
			retValue = session.insert(NAMESPACE + ".insertAppInternal", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save app label
	 * @param appId
	 * @param hasKeyword
	 * @return
	 */
	public int saveAppLabel(Long appId, int hasKeyword) {
		int retValue = 0;
		if (hasKeyword != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "label");
			condition.put("appId", appId);
			condition.put("hasKeyword", hasKeyword);
			retValue = session.insert(NAMESPACE + ".insertAppLabel", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save app misc
	 * @param appId
	 * @param country
	 * @param language
	 * @param description
	 * @param size
	 * @param currencyUnit
	 * @param price
	 * @return
	 */
	public int saveAppMisc(Long appId, String country, String language, 
			String description, String size, String currencyUnit, Double price) {
		int retValue = 0;
		if (!StringUtils.isEmpty(country)
				|| !StringUtils.isEmpty(language)
				|| !StringUtils.isEmpty(description)
				|| !StringUtils.isEmpty(size)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "misc");
			condition.put("appId", appId);
			condition.put("country", country);
			condition.put("language", language);
			condition.put("description", description);
			condition.put("size", size);
			condition.put("currencyUnit", currencyUnit);
			condition.put("price", price);
			retValue = session.insert(NAMESPACE + ".insertAppMisc", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save app permission list
	 * @param appId
	 * @param appPermissionList
	 * @return
	 */
	public int saveAppPermission(Long appId, List<AppPermission> appPermissionList) {
		int retValue = 0;
		if (appPermissionList != null
				&& appPermissionList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "permission");
			condition.put("appId", appId);
			condition.put("appPermissionList", 	appPermissionList);
			retValue = session.insert(NAMESPACE + ".insertAppPermission", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save app platform
	 * @param appId
	 * @param platformName
	 * @param osType
	 * @param osVersion
	 * @param device
	 * @return
	 */
	public int saveAppPlatform(Long appId, String platformName, String osType,
			String osVersion, String device) {
		int retValue = 0;
		if (!StringUtils.isEmpty(platformName)
				|| !StringUtils.isEmpty(osType)
				|| !StringUtils.isEmpty(osVersion)
				|| !StringUtils.isEmpty(device)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "platform");
			condition.put("appId", appId);
			condition.put("platformName", platformName);
			condition.put("osType", osType);
			condition.put("osVersion", osVersion);
			condition.put("device", device);
			retValue = session.insert(NAMESPACE + ".insertAppPlatform", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save app resource
	 * @param appId
	 * @param url
	 * @param downloadUrl
	 * @param logoUrl
	 * @return
	 */
	public int saveAppResource(Long appId, String url, String downloadUrl, String logoUrl) {
		int retValue = 0;
		if (!StringUtils.isEmpty(url)
				|| !StringUtils.isEmpty(downloadUrl)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "resource");
			condition.put("appId", appId);
			condition.put("url", url);
			condition.put("downloadUrl", downloadUrl);
			condition.put("logoUrl", logoUrl);
			retValue = session.insert(NAMESPACE + ".insertAppResource", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save app statistic
	 * @param appId
	 * @param downloadFloor
	 * @param downloadCeiling
	 * @param rating
	 * @param ratingCount
	 * @param review
	 * @param contentRating
	 * @return
	 */
	public int saveAppStat(Long appId, Long downloadFloor, Long downloadCeiling, 
			Double rating, Long ratingCount, String review, String contentRating) {
		int retValue = 0;
		if (downloadFloor != null
				&& downloadCeiling != null) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "statistic");
			condition.put("appId", appId);
			condition.put("downloadFloor", downloadFloor);
			condition.put("downloadCeiling", downloadCeiling);
			condition.put("rating", rating);
			condition.put("ratingCount", ratingCount);
			condition.put("review", review);
			condition.put("contentRating", contentRating);
			retValue = session.insert(NAMESPACE + ".insertAppStatistic", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save app status
	 * @param appId
	 * @param isDeprecated
	 * @param isFileDownloaded
	 * @param isImageDownloaded
	 * @param isLogoDownloaded
	 * @return
	 */
	public int saveAppStatus(Long appId, int isDeprecated, int isFileDownloaded, 
			int isImageDownloaded, int isLogoDownloaded) {
		int retValue = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "status");
		condition.put("appId", appId);
		condition.put("isDeprecated", isDeprecated);
		condition.put("isFileDownloaded", isFileDownloaded);
		condition.put("isImageDownloaded", isImageDownloaded);
		condition.put("isLogoDownloaded", isLogoDownloaded);
		retValue = session.insert(NAMESPACE + ".insertAppStatus", condition);
		condition = null;
		return retValue;
	}
	/**
	 * add store info
	 * @param storeName
	 * @param storeUrl
	 * @param storeType
	 * @return
	 */
	public int saveStore(AppType appType, OriginType originType, String storeName, 
			String storeDisplayName, String storeUrl, Integer storeType) {
		int retValue = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tableName", DbUtils.getAppTablePrefix(appType, originType) + "store");
		condition.put("storeName", storeName);
		condition.put("storeDisplayName", storeDisplayName);
		condition.put("storeUrl", storeUrl);
		condition.put("storeType", storeType);
		retValue = session.insert(NAMESPACE + ".insertAppStore", condition);
		condition = null;
		return retValue;
	}
	/**
	 * save app version
	 * @param appId
	 * @param appVersion
	 * @param updatedTime
	 * @param updateHistory
	 * @param majorVersion
	 * @param minorVersion
	 * @param revisionVersion
	 * @param buildVersion
	 * @param extraVersion
	 * @return
	 */
	public int saveAppVersion(Long appId, String appVersion, Long updatedTime,
			String updateHistory, Long majorVersion, Long minorVersion, 
			Long revisionVersion, Long buildVersion, Long extraVersion) {
		int retValue = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "version");
		condition.put("appId", appId);
		condition.put("appVersion", appVersion);
		condition.put("updatedTime", updatedTime);
		condition.put("updateHistory", updateHistory);
		condition.put("majorVersion", majorVersion);
		condition.put("minorVersion", minorVersion);
		condition.put("revisionVersion", revisionVersion);
		condition.put("buildVersion", buildVersion);
		condition.put("extraVersion", extraVersion);
		retValue = session.insert(NAMESPACE + ".insertAppVersion", condition);
		condition = null;
		return retValue;
	}
	/**
	 * save app visualization
	 * @param appId
	 * @param storeDisplayName
	 * @param appName
	 * @param version
	 * @param size
	 * @param url
	 * @return
	 */
	public int saveAppVisualization(Long appId, String storeDisplayName, String appName,
			String version, String size, String url) {
		int retValue = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "visualization");
		condition.put("appId", appId);
		condition.put("storeDisplayName", storeDisplayName);
		condition.put("appName", appName);
		condition.put("version", version);
		condition.put("size", size);
		condition.put("url", url);
		retValue = session.insert(NAMESPACE + ".insertAppVisualization", condition);
		condition = null;
		return retValue;
	}
	/**
	 * get category list by master id
	 * @param masterId
	 * @return
	 */
	public List<AppCategoryItem> getCategoryList(AppType appType, OriginType originType, int masterId) {
		List<AppCategoryItem> appCategoryItemList = null;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tableName", DbUtils.getAppTablePrefix(appType, originType) + "category");
		condition.put("masterId", masterId);
		appCategoryItemList = session.selectList(NAMESPACE + ".selectCategoryListByMasterId", condition);
		condition = null;
		return appCategoryItemList;
	}
	/**
	 * get store id by store name
	 * @param storeName
	 * @return
	 */
	public int getStoreId(AppType appType, OriginType originType, String storeName) {
		int storeId = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tableName", DbUtils.getAppTablePrefix(appType, originType) + "store");
		condition.put("storeName", storeName);
		storeId = session.selectOne(NAMESPACE + ".selectStoreIdByStoreName", condition);
		condition = null;
		return storeId;
	}
	/**
	 * get category id by store name and category
	 * @param storeName
	 * @param category
	 * @return
	 */
	public int getCategoryId(AppType appType, OriginType originType, String storeName, String category, int masterId) {
		int retValue = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tableName", DbUtils.getAppTablePrefix(appType, originType) + "category");
		condition.put("storeName", storeName);
		condition.put("category", category);
		condition.put("masterId", masterId);
		retValue = (Integer) session.selectOne(NAMESPACE + ".selectCategoryIdByStoreNameAndCategory", condition);
		condition = null;
		return retValue;
	}
	/**
	 * get app id by app checksum
	 * @param appChecksum
	 * @return
	 */
	public long getAppIdByAppChecksum(AppType appType, OriginType originType, String appChecksum) {
		long appId = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tableName", DbUtils.getAppTablePrefix(appType, originType) + "checksum");
		condition.put("appChecksum", appChecksum);
		appId = (Long) session.selectOne(NAMESPACE + ".selectAppIdByAppChecksum", condition);
		condition = null;
		return appId;
	}
	/**
	 * get app count by category id
	 * @param categoryId
	 * @return
	 */
	public long getAppCollectedCountByCategoryId(int categoryId) {
		return (Long) session.selectOne(NAMESPACE + ".selectAppCollectedCountByCategoryId", categoryId);
	}
	/**
	 * update app count by category id
	 * @param categoryId
	 * @param collectedCount
	 * @return
	 */
	public int updateAppCollectedCount(AppType appType, OriginType originType, Integer categoryId, Integer collectedCount) {
		int retValue = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tableName", DbUtils.getAppTablePrefix(appType, originType) + "category");
		condition.put("categoryId", categoryId);
		condition.put("collectedCount", collectedCount);
		retValue = session.update(NAMESPACE + ".updateAppCollectedCountByCategoryId", condition);
		condition = null;
		return retValue;
	}
	/**
	 * update download count
	 * @param categoryId
	 * @param downloadedCount
	 * @return
	 */
	public int updateAppDownloadedCount(AppType appType, OriginType originType, Integer categoryId) {
		int retValue = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tableName", DbUtils.getAppTablePrefix(appType, originType) + "category");
		condition.put("categoryId", categoryId);
		retValue =  session.update(NAMESPACE + ".updateAppDownloadedCountByCategoryId", condition);
		condition = null;
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
	 */
	public int updateAppStatus(Long appId, int isDeprecated, int isFileDownloaded, 
			int isImageDownloaded, int isLogoDownloaded) {
		int retValue = 0;
		if (appId != 0L
				&& (isDeprecated != 0
				|| isFileDownloaded != 0
				|| isImageDownloaded != 0
				|| isLogoDownloaded != 0)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getAppTablePrefix(appId) + "status");
			condition.put("appId", appId);
			condition.put("isDeprecated", isDeprecated);
			condition.put("isFileDownloaded", isFileDownloaded);
			condition.put("isImageDownloaded", isImageDownloaded);
			condition.put("isLogoDownloaded", isLogoDownloaded);
			retValue = session.update(NAMESPACE + ".updateAppStatus", condition);
			condition = null;
		}
		return retValue;		
	}
}
