/**
 * 
 */
package com.softsec.tase.store.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.softsec.tase.common.dto.app.FileMetadata;
import com.softsec.tase.common.dto.app.apk.ApkActivity;
import com.softsec.tase.common.dto.app.apk.ApkIntentFilterAction;
import com.softsec.tase.common.dto.app.apk.ApkManifest;
import com.softsec.tase.common.dto.app.apk.ApkPermission;
import com.softsec.tase.common.dto.app.apk.ApkSignature;
import com.softsec.tase.common.dto.app.apk.ApkUsesFeature;
import com.softsec.tase.common.dto.app.apk.ApkUsesLibrary;
import com.softsec.tase.common.dto.app.apk.ApkUsesPermission;
import com.softsec.tase.common.dto.app.apk.ApkUsesSdk;
import com.softsec.tase.common.dto.app.ipa.IpaInfo;
import com.softsec.tase.common.rpc.domain.app.AppType;
import com.softsec.tase.common.util.StringUtils;
import com.softsec.tase.store.util.db.DbUtils;

/**
 * FileDao
 * <p> </p>
 * @author yanwei
 * @since 2013-5-16 下午10:15:07
 * @version
 */
public class FileDao {

	private static final String NAMESPACE = "com.softsec.tase.store.dao.File";
	
	private SqlSession session = null;
	
	public FileDao(SqlSession session) {
		this.session = session;
	}
	/**
	 * save app bundle checksum
	 * @param appId
	 * @param bundleChecksum
	 * @return
	 */
	public int saveBundleChecksum(Long appId, String bundleChecksum, String bundleMagicNumber) {
		int retValue = 0;
		if (appId != 0
				&&	!StringUtils.isEmpty(bundleChecksum)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getBundleTablePrefix(appId) + "checksum");
			condition.put("appId", appId);
			condition.put("bundleChecksum", bundleChecksum);
			condition.put("bundleMagicNumber", bundleMagicNumber);
			retValue = session.insert(NAMESPACE + ".insertBundleChecksum", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save bundle resource
	 * @param bundleChecksum
	 * @param filePath
	 * @param imagePath
	 * @param logoPath
	 * @return
	 */
	public int saveBundleResource(Long appId, String bundleChecksum, String filePath) {
		int retValue = 0;
		if (!StringUtils.isEmpty(bundleChecksum)
				&& !StringUtils.isEmpty(filePath)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getBundleTablePrefix(appId) + "resource");
			condition.put("bundleChecksum", bundleChecksum);
			condition.put("filePath", filePath);
			retValue = session.insert(NAMESPACE + ".insertBundleResource", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save bundle status
	 * @param bundleChecksum
	 * @param staticStatus
	 * @param dynamicStatus
	 * @return
	 */
	public int saveBundleStatus(Long appId, String bundleChecksum, int staticStatus, int dynamicStatus) {
		int retValue = 0;
		if (!StringUtils.isEmpty(bundleChecksum)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getBundleTablePrefix(appId) + "status");
			condition.put("bundleChecksum", bundleChecksum);
			condition.put("staticStatus", staticStatus);
			condition.put("dynamicStatus", dynamicStatus);
			retValue = session.insert(NAMESPACE + ".insertBundleStatus", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save file antivirus
	 * @param fileChecksum
	 * @param antivirusVendor
	 * @param antivirusUrl
	 * @param antivirusEmail
	 * @param scanningTime
	 * @param maliciousType
	 * @param antivirusResult
	 * @return
	 */
	public int saveFileAntivirus(Long appId, String fileChecksum, String antivirusVendor, String antivirusUrl,
			String antivirusEmail, long scanningTime, String maliciousType, String antivirusResult) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum)
				&& !StringUtils.isEmpty(antivirusVendor)
				&& !StringUtils.isEmpty(antivirusResult)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getFileTablePrefix(appId) + "antivirus");
			condition.put("fileChecksum", fileChecksum);
			condition.put("antivirusVendor", antivirusVendor);
			condition.put("antivirusUrl", antivirusUrl);
			condition.put("antivirusEmail", antivirusEmail);
			condition.put("scanningTime", scanningTime);
			condition.put("maliciousType", maliciousType);
			condition.put("antivirusResult", antivirusResult);
			retValue = session.insert(NAMESPACE + ".insertFileAntivirus", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save apk manifest
	 * @param fileChecksum
	 * @param apkManifest
	 * @return
	 */
	public int saveFileApkManifest(String fileChecksum, ApkManifest apkManifest) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum)
				&& apkManifest != null) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("fileChecksum", fileChecksum);
			condition.put("apkManifest", apkManifest);
			retValue = session.insert(NAMESPACE + ".insertFileAndroidApkManifest", condition);
			condition = null;
		}
		return retValue;		
	}
	/**
	 * save apk manifest activity
	 * @param fileChecksum
	 * @param apkActivity
	 * @return
	 */
	public int saveFileApkManifestActivity(String fileChecksum, List<ApkActivity> apkActivityList) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum)
				&& apkActivityList != null
				&& apkActivityList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("fileChecksum", fileChecksum);
			condition.put("apkActivityList", apkActivityList);
			retValue = session.insert(NAMESPACE + ".insertFileAndroidApkManifestActivity", condition);
			condition = null;
		}
		return retValue;		
	}
	/**
	 * save apk manifest intent filter action
	 * @param fileChecksum
	 * @param apkIntentFilterActionList
	 * @return
	 */
	public int saveFileApkManifestIntentFilterAction(String fileChecksum, List<ApkIntentFilterAction> apkIntentFilterActionList) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum)
				&& apkIntentFilterActionList != null
				&& apkIntentFilterActionList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("fileChecksum", fileChecksum);
			condition.put("apkIntentFilterActionList", apkIntentFilterActionList);
			retValue = session.insert(NAMESPACE + ".insertFileAndroidApkManifestIntentFilterAction", condition);
			condition = null;
		}
		return retValue;		
	}
	/**
	 * save apk manifest permission
	 * @param fileChecksum
	 * @param apkPermissionList
	 * @return
	 */
	public int saveFileApkManifestPermission(String fileChecksum, List<ApkPermission> apkPermissionList) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum)
				&& apkPermissionList != null
				&& apkPermissionList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("fileChecksum", fileChecksum);
			condition.put("apkPermissionList", apkPermissionList);
			retValue = session.insert(NAMESPACE + ".insertFileAndroidApkManifestPermission", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save apk manifest uses sdk
	 * @param fileChecksum
	 * @param apkUsesSdk
	 * @return
	 */
	public int saveFileApkManifestUsesSdk(String fileChecksum, ApkUsesSdk apkUsesSdk) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum)
				&& apkUsesSdk != null) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("fileChecksum", fileChecksum);
			condition.put("apkUsesSdk", apkUsesSdk);
			retValue = session.insert(NAMESPACE + ".insertFileAndroidApkManifestUsesSdk", condition);
			condition = null;
		}
		return retValue;		
	}
	/**
	 * save apk manifest uses feature
	 * @param fileChecksum
	 * @param apkUsesFeature
	 * @return
	 */
	public int saveFileApkManifestUsesFeature(String fileChecksum, List<ApkUsesFeature> apkUsesFeatureList) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum)
				&& apkUsesFeatureList != null
				&& apkUsesFeatureList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("fileChecksum", fileChecksum);
			condition.put("apkUsesFeatureList", apkUsesFeatureList);
			retValue = session.insert(NAMESPACE + ".insertFileAndroidApkManifestUsesFeature", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save apk manifest uses library
	 * @param fileChecksum
	 * @param apkUsesLibraryList
	 * @return
	 */
	public int saveFileApkManifestUsesLibrary(String fileChecksum, List<ApkUsesLibrary> apkUsesLibraryList) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum)
				&& apkUsesLibraryList != null
				&& apkUsesLibraryList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("fileChecksum", fileChecksum);
			condition.put("apkUsesLibraryList", apkUsesLibraryList);
			retValue = session.insert(NAMESPACE + ".insertFileAndroidApkManifestUsesLibrary", condition);
			condition = null;
		}
		return retValue;		
	}
	/**
	 * save apk manifest uses permission
	 * @param fileChecksum
	 * @param apkUsesPermissionList
	 * @return
	 */
	public int saveFileApkManifestUsesPermission(String fileChecksum, List<ApkUsesPermission> apkUsesPermissionList) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum)
				&& apkUsesPermissionList != null
				&& apkUsesPermissionList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("fileChecksum", fileChecksum);
			condition.put("apkUsesPermissionList", apkUsesPermissionList);
			retValue = session.insert(NAMESPACE + ".insertFileAndroidApkManifestUsesPermission", condition);
			condition = null;
		}
		return retValue;		
	}
	/**
	 * save apk signature
	 * @param fileChecksum
	 * @param apkSignature
	 * @return
	 */
	public int saveFileApkSignature(String fileChecksum, ApkSignature apkSignature) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum)
				&& apkSignature != null) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("fileChecksum", fileChecksum);
			condition.put("apkSignature", apkSignature);
			retValue = session.insert(NAMESPACE + ".insertFileAndroidApkSignature", condition);
			condition = null;
		}
		return retValue;		
	}
	/**
	 * save file checksum
	 * @param appId
	 * @param fileChecksum
	 * @return
	 */
	public int saveFileChecksum(Long appId, String fileChecksum) {
		int retValue = 0;
		if (appId != 0
				&& !StringUtils.isEmpty(fileChecksum)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getFileTablePrefix(appId) + "checksum");
			condition.put("appId", appId);
			condition.put("fileChecksum", fileChecksum);
			retValue = session.insert(NAMESPACE + ".insertFileChecksum", condition);
			condition = null;
		}
		return retValue;		
	}
	/**
	 * save file label
	 * @param fileChecksum
	 * @param virus
	 * @return
	 */
	public int saveFileLabel(Long appId, String fileChecksum, int hasVirus) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getFileTablePrefix(appId) + "label");
			condition.put("fileChecksum", fileChecksum);
			condition.put("hasVirus", hasVirus);
			retValue = session.insert(NAMESPACE + ".insertFileLabel", condition);
			condition = null;
		}
		return retValue;		
	}
	/**
	 * save logo checksum
	 * @param appId
	 * @param logoChecksum
	 * @return
	 */
	public int saveFileLogoChecksum(Long appId, String logoChecksum) {
		int retValue = 0;
		if (appId != 0L
				&& !StringUtils.isEmpty(logoChecksum)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getFileTablePrefix(appId) + "logo_checksum");
			condition.put("appId", appId);
			condition.put("logoChecksum", logoChecksum);
			retValue = session.insert(NAMESPACE + ".insertFileLogoChecksum", condition);
			condition = null;
		}
		return retValue;		
	}
	/**
	 * save logo resource
	 * @param logoChecksum
	 * @param logoPath
	 * @return
	 */
	public int saveFileLogoResource(Long appId, String logoChecksum, String logoPath) {
		int retValue = 0;
		if (!StringUtils.isEmpty(logoChecksum)
				&& !StringUtils.isEmpty(logoPath)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getFileTablePrefix(appId) + "logo_resource");
			condition.put("logoChecksum", logoChecksum);
			condition.put("logoPath", logoPath);
			retValue = session.insert(NAMESPACE + ".insertFileLogoResource", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save file metadata
	 * @param appId 
	 * @param fileMetadata
	 * @return
	 */
	public int saveFileMetadata(Long appId, String bundleChecksum, FileMetadata fileMetadata) {
		int retValue = 0;
		if (!StringUtils.isEmpty(bundleChecksum)
				&& fileMetadata != null) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getFileTablePrefix(appId) + "metadata");
			condition.put("bundleChecksum", bundleChecksum);
			condition.put("fileMetadata", fileMetadata);
			retValue = session.insert(NAMESPACE + ".insertFileMetadata", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save file resource
	 * @param fileChecksum
	 * @param filePath
	 * @param imagePath
	 * @param logoPath
	 * @return
	 */
	public int saveFileResource(Long appId, String fileChecksum, String filePath) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum)
				&& !StringUtils.isEmpty(filePath)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getFileTablePrefix(appId) + "resource");
			condition.put("fileChecksum", fileChecksum);
			condition.put("filePath", filePath);
			retValue = session.insert(NAMESPACE + ".insertFileResource", condition);
			condition = null;
		}
		return retValue;		
	}
	/**
	 * save file status
	 * @param fileChecksum
	 * @param staticStatus
	 * @param dynamicStatus
	 * @return
	 */
	public int saveFileStatus(Long appId, String fileChecksum, int staticStatus, int dynamicStatus) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getFileTablePrefix(appId) + "status");
			condition.put("fileChecksum", fileChecksum);
			condition.put("staticStatus", staticStatus);
			condition.put("dynamicStatus", dynamicStatus);
			retValue = session.insert(NAMESPACE + ".insertFileStatus", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save file snapshot checksum
	 * @param appId
	 * @param snapshotMetadataList
	 * @return
	 */
	public int saveFileSnapshotChecksum(Long appId, List<FileMetadata> snapshotMetadataList) {
		int retValue = 0;
		if (appId != 0L
				&& snapshotMetadataList != null
				&& snapshotMetadataList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getFileTablePrefix(appId) + "snapshot_checksum");
			condition.put("appId", appId);
			condition.put("snapshotMetadataList", snapshotMetadataList);
			retValue = session.insert(NAMESPACE + ".insertFileSnapshotChecksum", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save file snapshot resource
	 * @param snapshotMetadataList
	 * @return
	 */
	public int saveFileSnapshotResource(Long appId, List<FileMetadata> snapshotMetadataList) {
		int retValue = 0;
		if (snapshotMetadataList != null
				&& snapshotMetadataList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getFileTablePrefix(appId) + "snapshot_resource");
			condition.put("appId", appId);
			condition.put("snapshotMetadataList", snapshotMetadataList);
			retValue = session.insert(NAMESPACE + ".insertFileSnapshotResource", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * check for bundle duplication
	 * @param bundleChecksum
	 * @return
	 */
	public String checkBundleDuplication(AppType appType, String bundleChecksum) {
		String duplicatedBundleChecksum = null;
		if (!StringUtils.isEmpty(bundleChecksum)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getBundleTablePrefix(appType) + "checksum");
			condition.put("bundleChecksum", bundleChecksum);
			duplicatedBundleChecksum = (String) session.selectOne(NAMESPACE + ".selectBundleChecksum", condition);
			condition = null;
		}
		return duplicatedBundleChecksum;
	}
	/**
	 * check for file duplication
	 * @param fileChecksum
	 * @return	null if not duplicate
	 * 			not null if duplicate
	 */
	public String checkFileDuplication(AppType appType, String fileChecksum) {
		String duplicatedFileChecksum = null;
		if (!StringUtils.isEmpty(fileChecksum)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getFileTablePrefix(appType) + "checksum");
			condition.put("fileChecksum", fileChecksum);
			duplicatedFileChecksum = (String) session.selectOne(NAMESPACE + ".selectFileChecksum", condition);
			condition = null;
		}
		return duplicatedFileChecksum;
	}
	/**
	 * save iTunesMetadataPlist and InfoPlist
	 * @param md5
	 * @param ipaInfo
	 * @return
	 */
	public int addIpaInfo(String md5, IpaInfo ipaInfo) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("md5", md5);
		condition.put("bundleName", ipaInfo.getCfBundleName());
		condition.put("bundleDisplayName", ipaInfo.getCfBundleDisplayName());
		condition.put("bundleIdentifier", ipaInfo.getCfBundleIdentifier());
		condition.put("bundleVersion", ipaInfo.getCfBundleVersion());
		return session.insert(NAMESPACE + ".insertIpaInfo", condition);
	}
	/**
	 * @param bundleChecksum
	 * @param staticStatus
	 * @param dynamicStatus
	 * @return
	 */
	public int updateBundleStatus(Long appId, String bundleChecksum, int staticStatus,
			int dynamicStatus) {
		int retValue = 0;
		if (!StringUtils.isEmpty(bundleChecksum)
				&& (staticStatus != 0
				|| dynamicStatus != 0)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getBundleTablePrefix(appId) + "status");
			condition.put("bundleChecksum", bundleChecksum);
			condition.put("staticStatus", staticStatus);
			condition.put("dynamicStatus", dynamicStatus);
			retValue = session.insert(NAMESPACE + ".updateBundleStatus", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * @param fileChecksum
	 * @param staticStatus
	 * @param dynamicStatus
	 * @return
	 */
	public int updateFileStatus(Long appId, String fileChecksum, int staticStatus,
			int dynamicStatus) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum)
				&& (staticStatus != 0
				|| dynamicStatus != 0)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getFileTablePrefix(appId) + "status");
			condition.put("fileChecksum", fileChecksum);
			condition.put("staticStatus", staticStatus);
			condition.put("dynamicStatus", dynamicStatus);
			retValue = session.insert(NAMESPACE + ".updateFileStatus", condition);
			condition = null;
		}
		return retValue;
	}
	
	/**
	 * get bundle checksum list by file status
	 * @param appType
	 * @param staticStatus
	 * @param dynamicStatus
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<String> getBundleChecksumListByFileStatus(AppType appType, int staticStatus, int dynamicStatus, int offset, int limit) {
		List<String> bundleChecksumList = null;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("bundleChecksumTableName", DbUtils.getBundleTablePrefix(appType) + "checksum");
		condition.put("fileMetadataTableName", DbUtils.getFileTablePrefix(appType) + "metadata");
		condition.put("fileStatusTableName", DbUtils.getFileTablePrefix(appType) + "status");
		condition.put("staticStatus", staticStatus);
		condition.put("dynamicStatus", dynamicStatus);
		bundleChecksumList = session.selectList(NAMESPACE + ".selectBundleChecksumByFileStatus", condition, new RowBounds(offset, limit));
		condition = null;
		return bundleChecksumList;
	}
	
	/**
	 * get file checksum by bundle checksum
	 * @param appType
	 * @param bundleChecksum
	 * @return
	 */
	public List<String> getFileChecksumByBundleChecksum(AppType appType, String bundleChecksum) {
		List<String> fileChecksumList = null;
		if (!StringUtils.isEmpty(bundleChecksum)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getFileTablePrefix(appType) + "metadata");
			condition.put("bundleChecksum", bundleChecksum);
			fileChecksumList = session.selectList(NAMESPACE + ".selectFileChecksumByBundleChecksum", condition);
			condition = null;
		}
		return fileChecksumList;
	}
	/**
	 * @param appType
	 * @param fileChecksumList
	 * @return
	 */
	public int updateFileStatusByFileChecksum(AppType appType, List<String> fileChecksumList, int staticStatus, int dynamicStatus) {
		int retValue = 0;
		if (fileChecksumList != null && fileChecksumList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("tableName", DbUtils.getFileTablePrefix(appType) + "status");
			condition.put("fileChecksumList", fileChecksumList);
			condition.put("staticStatus", staticStatus);
			condition.put("dynamicStatus", dynamicStatus);
			retValue = session.insert(NAMESPACE + ".updateFileStatusByFileChecksum", condition);
			condition = null;
		}
		return retValue;
	}
}
