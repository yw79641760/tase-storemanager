/**
 * 
 */
package com.softsec.tase.store.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.softsec.tase.common.dto.app.FileMetadata;
import com.softsec.tase.common.dto.app.apk.Apk;
import com.softsec.tase.common.dto.app.apk.ApkManifest;
import com.softsec.tase.common.dto.app.apk.ApkSignature;
import com.softsec.tase.common.util.StringUtils;

/**
 * OrderDao
 * <p> </p>
 * @author yanwei
 * @since 2013-8-29 下午3:04:50
 * @version
 */
public class OrderDao {

	private static final String NAMESPACE = "com.softsec.tase.store.dao.Order";
	
	private SqlSession session = null;
	
	/**
	 * 
	 */
	public OrderDao(SqlSession session) {
		this.session = session;
	}
	/**
	 * save order & job relationship
	 * @param orderId
	 * @param jobId
	 * @return
	 */
	public int saveOrderAndJob(String orderId, Long jobId) {
		int retValue = 0;
		if (!StringUtils.isEmpty(orderId) && jobId != 0L) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("orderId", orderId);
			condition.put("jobId", jobId);
			retValue = session.insert(NAMESPACE + ".insertOrderAndJob", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save input apk manifest
	 * @param fileChecksum
	 * @param apkManifest
	 * @return
	 */
	public int saveInputApkManifest(String fileChecksum, ApkManifest apkManifest) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum) && apkManifest != null) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("fileChecksum", fileChecksum);
			condition.put("apkManifest", apkManifest);
			retValue = session.insert(NAMESPACE + ".insertInputApkManifest", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save input apk signatue
	 * @param fileChecksum
	 * @param apkSignature
	 * @return
	 */
	public int saveInputApkSignature(String fileChecksum, ApkSignature apkSignature) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum) && apkSignature != null) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("fileChecksum", fileChecksum);
			condition.put("apkSignature", apkSignature);
			retValue = session.insert(NAMESPACE + ".insertInputApkSignature", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save input apk file metadata
	 * @param fileMetadata
	 * @return
	 */
	public int saveInputApkFileMetadata(FileMetadata fileMetadata, String bundleChecksum) {
		int retValue = 0;
		if (fileMetadata != null
				|| !StringUtils.isEmpty(bundleChecksum)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("fileMetadata", fileMetadata);
			condition.put("bundleChecksum", bundleChecksum);
			retValue = session.insert(NAMESPACE + ".insertInputApkFileMetadata", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save input apk resource
	 * @param fileChecksum
	 * @param filePath
	 * @return
	 */
	public int saveInputApkResource(String fileChecksum, String filePath) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum) && !StringUtils.isEmpty(filePath)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("fileChecksum", fileChecksum);
			condition.put("filePath", filePath);
			retValue = session.insert(NAMESPACE + ".insertInputApkResource", condition);
			session = null;
		}
		return retValue;
	}
	/**
	 * save input apk info
	 * @param fileMetadata
	 * @return
	 */
	public int saveInputApkResourceInfo(FileMetadata fileMetadata, String bundleChecksum) {
		int retValue = 0;
		retValue += saveInputApkFileMetadata(fileMetadata, bundleChecksum);
		retValue += saveInputApkResource(fileMetadata.getFileChecksum(), fileMetadata.getFilePath());
		return retValue;
	}
	/**
	 * save output apk file metadata
	 * @param fileMetadata
	 * @return
	 */
	public int saveOutputApkFileMetadata(FileMetadata fileMetadata) {
		int retValue = 0;
		if (fileMetadata != null) {
			retValue = session.insert(NAMESPACE + ".insertOutputApkFileMetadata", fileMetadata);
		}
		return retValue;
	}
	/**
	 * save output apk resource
	 * @param fileChecksum
	 * @param filePath
	 * @return
	 */
	public int saveOutputApkResource(String fileChecksum, String filePath) {
		int retValue = 0;
		if (!StringUtils.isEmpty(fileChecksum) && !StringUtils.isEmpty(filePath)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("fileChecksum", fileChecksum);
			condition.put("filePath", filePath);
			retValue = session.insert(NAMESPACE + ".insertOutputApkResource", condition);
			session = null;
		}
		return retValue;
	}
	/**
	 * save output apk info
	 * @param fileMetadata
	 * @return
	 */
	public int saveOutputApkResourceInfo(FileMetadata fileMetadata) {
		int retValue = 0;
		retValue += saveOutputApkFileMetadata(fileMetadata);
		retValue += saveOutputApkResource(fileMetadata.getFileChecksum(), fileMetadata.getFilePath());
		return retValue;
	}
	/**
	 * update apk resource
	 * @param inputfilePath
	 * @param outputFilePath
	 * @param status
	 * @return
	 */
	public int updateApkStatus(String inputfileChecksum, String outputFileChecksum, int status) {
		int retValue = 0;
		if (!StringUtils.isEmpty(inputfileChecksum) && status != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("inputfileChecksum", inputfileChecksum);
			condition.put("outputFileChecksum", outputFileChecksum);
			condition.put("status", status);
			retValue = session.insert(NAMESPACE + ".updateApkStatus", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save input apk info
	 * @param apk
	 * @return
	 */
	public int saveInputApkInfo(Apk apk) {
		int retValue = 0;
		if (apk != null) {
			retValue += saveInputApkManifest(apk.getFileMetadata().getFileChecksum(), apk.getApkManifest());
			retValue += saveInputApkSignature(apk.getFileMetadata().getFileChecksum(), apk.getApkSignature());
			retValue += saveInputApkResourceInfo(apk.getFileMetadata(), apk.getBundleChecksum());
		}
		return retValue;
	}
}
