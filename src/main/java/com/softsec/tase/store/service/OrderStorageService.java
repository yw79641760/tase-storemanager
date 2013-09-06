/**
 * 
 */
package com.softsec.tase.store.service;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softsec.tase.common.dto.app.FileMetadata;
import com.softsec.tase.common.dto.app.apk.Apk;
import com.softsec.tase.common.dto.app.apk.ApkManifest;
import com.softsec.tase.common.dto.app.apk.ApkSignature;
import com.softsec.tase.store.dao.OrderDao;
import com.softsec.tase.store.exception.DbUtilsException;
import com.softsec.tase.store.util.db.SQLMapperFactory;
import com.softsec.tase.store.util.db.SessionUtils;

/**
 * OrderStorageService
 * <p> </p>
 * @author yanwei
 * @since 2013-8-29 下午3:05:42
 * @version
 */
public class OrderStorageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderStorageService.class);
	
	/**
	 * save order and job info
	 * @param orderId
	 * @param jobId
	 * @return
	 * @throws DbUtilsException
	 */
	public int saveOrderAndJob(String orderId, Long jobId) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			OrderDao orderDao = new OrderDao(session);
			retValue = orderDao.saveOrderAndJob(orderId, jobId);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to insert order [ " + orderId + " ] and job [ " + jobId + " ] : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to insert order [ " + orderId + " ] and job [ " + jobId + " ] : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	/**
	 * save input apk manifest
	 * @param fileChecksum
	 * @param apkManifest
	 * @return
	 * @throws DbUtilsException
	 */
	public int saveInputApkManifest(String fileChecksum, ApkManifest apkManifest) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			OrderDao orderDao = new OrderDao(session);
			retValue = orderDao.saveInputApkManifest(fileChecksum, apkManifest);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to insert apk manifest [ " + fileChecksum + " ] : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to insert apk manifest [ " + fileChecksum + " ] : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	/**
	 * save input apk signature
	 * @param fileChecksum
	 * @param apkSignature
	 * @return
	 * @throws DbUtilsException
	 */
	public int saveInputApkSignature(String fileChecksum, ApkSignature apkSignature) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			OrderDao orderDao = new OrderDao(session);
			retValue = orderDao.saveInputApkSignature(fileChecksum, apkSignature);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to insert apk signature [ " + fileChecksum + " ] : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to insert apk signature [ " + fileChecksum + " ] : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	/**
	 * save input apk info
	 * @param fileMetadata
	 * @return
	 * @throws DbUtilsException
	 */
	public int saveInputApkResourceInfo(FileMetadata fileMetadata, String bundleChecksum) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			OrderDao orderDao = new OrderDao(session);
			retValue = orderDao.saveInputApkResourceInfo(fileMetadata, bundleChecksum);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to insert input apk file metadata [ " + fileMetadata.getFileChecksum() + " ] : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to insert input apk file metadata [ " + fileMetadata.getFileChecksum() + " ] : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	/**
	 * save input apk info
	 * @param apk
	 * @return
	 * @throws DbUtilsException
	 */
	public int saveInputApkInfo(Apk apk) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			OrderDao orderDao = new OrderDao(session);
			retValue = orderDao.saveInputApkInfo(apk);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to save apk info [ " + apk.getFileMetadata().getFileName() + " ] : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to save apk info [ " + apk.getFileMetadata().getFileName() + " ] : " + e.getMessage(), e);
		}
		return retValue;
	}
	/**
	 * save output apk info
	 * @param fileMetadata
	 * @return
	 * @throws DbUtilsException
	 */
	public int saveOutputApkResourceInfo(FileMetadata fileMetadata) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			OrderDao orderDao = new OrderDao(session);
			retValue = orderDao.saveOutputApkResourceInfo(fileMetadata);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to insert output apk file metadata [ " + fileMetadata.getFileChecksum() + " ] : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to insert output apk file metadata [ " + fileMetadata.getFileChecksum() + " ] : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	/**
	 * update file status
	 * @param sourceFileChecksum
	 * @param targetFileChecksum
	 * @param status
	 * @return
	 */
	public int updateFileStatus(String sourceFileChecksum, String targetFileChecksum, int status) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			OrderDao orderDao = new OrderDao(session);
			retValue += orderDao.updateApkStatus(sourceFileChecksum, targetFileChecksum, status);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to update file status [ " + sourceFileChecksum + " : " + targetFileChecksum + " : " + status + " ] : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to update file status [ " + sourceFileChecksum + " : " + targetFileChecksum + " : " + status + " ] : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
}
