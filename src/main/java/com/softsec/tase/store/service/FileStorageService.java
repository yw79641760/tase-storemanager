/**
 * 
 */
package com.softsec.tase.store.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softsec.tase.common.domain.app.App;
import com.softsec.tase.common.dto.app.FileMetadata;
import com.softsec.tase.common.dto.app.apk.Apk;
import com.softsec.tase.common.rpc.domain.app.AppType;
import com.softsec.tase.common.util.domain.AppUtils;
import com.softsec.tase.store.dao.FileDao;
import com.softsec.tase.store.exception.DbUtilsException;
import com.softsec.tase.store.util.db.SQLMapperFactory;
import com.softsec.tase.store.util.db.SessionUtils;

/**
 * FileStorageService
 * <p> </p>
 * @author yanwei
 * @since 2013-5-16 下午10:12:07
 * @version
 */
public class FileStorageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileStorageService.class);
	
	/**
	 * check app bundle duplication
	 * @param bundleChecksum
	 * @return
	 * @throws NullPointerException
	 * @throws DbUtilsException
	 */
	public String checkBundleDuplication(AppType appType, String bundleChecksum) throws NullPointerException, DbUtilsException {
		String duplicatedBundleChecksum = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			FileDao fileDao = new FileDao(session);
			duplicatedBundleChecksum = fileDao.checkBundleDuplication(appType, bundleChecksum);
		} catch (NullPointerException npe) {
			// if not exist bundleChecksum, then return null
			return duplicatedBundleChecksum;
		} catch (Exception e) {
			LOGGER.error("Failed to check app bundle duplication : " + bundleChecksum + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to check app bundle duplication : " + bundleChecksum + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return duplicatedBundleChecksum;
	}
	/**
	 * check app file duplication
	 * @param fileChecksum
	 * @return
	 * @throws NullPointerException
	 * @throws DbUtilsException
	 */
	public String checkFileDuplication(AppType appType, String fileChecksum) throws NullPointerException, DbUtilsException {
		String duplicatedFileChecksum = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			FileDao fileDao = new FileDao(session);
			duplicatedFileChecksum = fileDao.checkFileDuplication(appType, fileChecksum);
		} catch (NullPointerException npe) {
			// if not exist fileChecksum, then return null
			return duplicatedFileChecksum;
		} catch (Exception e) {
			LOGGER.error("Failed to check app file duplication : " + fileChecksum + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to check app file duplication : " + fileChecksum + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return duplicatedFileChecksum;
	}
	
	/**
	 * save apk info
	 * @param appId
	 * @param apk
	 * @return
	 */
	public int saveApk(Long appId, Apk apk) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			FileDao fileDao = new FileDao(session);
			
			// save bundle
			if (StringUtils.isEmpty(checkBundleDuplication(AppUtils.getAppTypeByAppId(appId), apk.getBundleChecksum()))) {
				retValue +=	fileDao.saveBundleChecksum(appId, apk.getBundleChecksum(), apk.getBundleMagicNumber());
				retValue +=	fileDao.saveBundleResource(appId, apk.getBundleChecksum(), apk.getFileMetadata().getFilePath());
				retValue += fileDao.saveBundleStatus(appId, apk.getBundleChecksum(), 0, 0);
			}

			// save apk
			if (StringUtils.isEmpty(checkFileDuplication(AppUtils.getAppTypeByAppId(appId), apk.getFileMetadata().getFileChecksum()))) {
				retValue +=	fileDao.saveFileApkManifest(apk.getFileMetadata().getFileChecksum(), 
														apk.getApkManifest());
				retValue += fileDao.saveFileApkManifestActivity(apk.getFileMetadata().getFileChecksum(), 
																apk.getApkManifest().getApkActivityList());
				retValue += fileDao.saveFileApkManifestIntentFilterAction(apk.getFileMetadata().getFileChecksum(), 
																			apk.getApkManifest().getApkIntentFilterActionList());
				retValue += fileDao.saveFileApkManifestPermission(apk.getFileMetadata().getFileChecksum(), 
																	apk.getApkManifest().getApkPermissionList());
				retValue += fileDao.saveFileApkManifestUsesFeature(apk.getFileMetadata().getFileChecksum(), 
																	apk.getApkManifest().getApkUsesFeatureList());
				retValue += fileDao.saveFileApkManifestUsesLibrary(apk.getFileMetadata().getFileChecksum(), 
																	apk.getApkManifest().getApkUsesLibraryList());
				retValue += fileDao.saveFileApkManifestUsesPermission(apk.getFileMetadata().getFileChecksum(), 
																		apk.getApkManifest().getApkUsesPermissionList());
				retValue += fileDao.saveFileApkManifestUsesSdk(apk.getFileMetadata().getFileChecksum(), 
																apk.getApkManifest().getApkUsesSdk());
				retValue += fileDao.saveFileApkSignature(apk.getFileMetadata().getFileChecksum(), 
															apk.getApkSignature());
				retValue += fileDao.saveFileChecksum(appId, 
														apk.getFileMetadata().getFileChecksum());
				retValue += fileDao.saveFileLabel(appId, 
													apk.getFileMetadata().getFileChecksum(), 0);
				retValue += fileDao.saveFileMetadata(appId, 
													apk.getBundleChecksum(), 
													apk.getFileMetadata());
				retValue += fileDao.saveFileResource(appId, 
														apk.getFileMetadata().getFileChecksum(), 
														apk.getFileMetadata().getFilePath());
				retValue += fileDao.saveFileStatus(appId, 
													apk.getFileMetadata().getFileChecksum(), 0, 0);
			}
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to save apk : " + appId + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to save apk : " + appId + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	
	/**
	 * save logo & snapshot list
	 * @param appId
	 * @param logoMetadata
	 * @param snapshotMetadataList
	 * @return
	 * @throws DbUtilsException
	 */
	public int saveImage(Long appId, FileMetadata logoMetadata, List<FileMetadata> snapshotMetadataList) 
		throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			FileDao fileDao = new FileDao(session);
			
			if (logoMetadata != null) {
				retValue += fileDao.saveFileLogoChecksum(appId, logoMetadata.getFileChecksum());
				retValue += fileDao.saveFileLogoResource(appId, logoMetadata.getFileChecksum(), logoMetadata.getFilePath());
			}
			
			if (snapshotMetadataList != null && snapshotMetadataList.size() != 0) {
				retValue += fileDao.saveFileSnapshotChecksum(appId, snapshotMetadataList);
				retValue += fileDao.saveFileSnapshotResource(appId, snapshotMetadataList);
			}
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to save app images info : " + appId + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to save app images info : " + appId + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	
	/**
	 * update file status
	 * @param bundleChecksum
	 * @param fileChecksum
	 * @param staticStatus
	 * @param dynamicStatus
	 * @return
	 * @throws DbUtilsException
	 */
	public int updateFileStatus(Long appId, String bundleChecksum, String fileChecksum, int staticStatus, int dynamicStatus) 
		throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			FileDao fileDao = new FileDao(session);
			retValue += fileDao.updateBundleStatus(appId, bundleChecksum, staticStatus, dynamicStatus);
			retValue += fileDao.updateFileStatus(appId, fileChecksum, staticStatus, dynamicStatus);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to update bundle or file status : " + bundleChecksum + " : " + fileChecksum + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to update bundle or file status : " + bundleChecksum + " : " + fileChecksum + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
		
	}
	
	/**
	 * get bundle checksum by file status
	 * @param appType
	 * @param staticStatus
	 * @param dynamicStatus
	 * @param offset
	 * @param limit
	 * @return
	 * @throws DbUtilsException
	 */
	public List<String> getBundleChecksumListByFileStatus(AppType appType, int staticStatus, int dynamicStatus, int offset, int limit)
		throws DbUtilsException {
		List<String> bundleChecksumList = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			FileDao fileDao = new FileDao(session);
			bundleChecksumList = fileDao.getBundleChecksumListByFileStatus(appType, staticStatus, dynamicStatus, offset, limit);
		} catch (Exception e) {
			LOGGER.error("Failed to get bundle checksum by file status : " + staticStatus + " : " + dynamicStatus + " : "+ e.getMessage(), e);
			throw new DbUtilsException("Failed to get bundle checksum by file status : " + staticStatus + " : " + dynamicStatus + " : "+ e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return bundleChecksumList;
	}
	
	/**
	 * get file checksum by bundle checksum
	 * @param appType
	 * @param bundleChecksum
	 * @return
	 * @throws DbUtilsException
	 */
	public List<String> getFileChecksumByBundleChecksum(AppType appType, String bundleChecksum) throws DbUtilsException {
		List<String> fileChecksumList = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			FileDao fileDao = new FileDao(session);
			fileChecksumList = fileDao.getFileChecksumByBundleChecksum(appType, bundleChecksum);
		} catch (Exception e) {
			LOGGER.error("Failed to get file checksum by bundle checksum : " + bundleChecksum + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get file checksum by bundle checksum : " + bundleChecksum + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return fileChecksumList;
	}
	
	/**
	 * update file status by file checksum list
	 * @param appType
	 * @param fileChecksumList
	 * @param staticStatus
	 * @param dynamicStatus
	 * @return
	 * @throws DbUtilsException
	 */
	public int updateFileStatusByFileChecksum(AppType appType, List<String> fileChecksumList, int staticStatus, int dynamicStatus) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			FileDao fileDao = new FileDao(session);
			retValue = fileDao.updateFileStatusByFileChecksum(appType, fileChecksumList, staticStatus, dynamicStatus);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to update file status in file checksum list : " + fileChecksumList + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to update file status in file checksum list : " + fileChecksumList + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
}
