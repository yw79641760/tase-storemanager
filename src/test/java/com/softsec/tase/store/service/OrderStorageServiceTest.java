/**
 * 
 */
package com.softsec.tase.store.service;

import junit.framework.TestCase;

import org.junit.Test;

import com.softsec.tase.common.dto.app.FileMetadata;
import com.softsec.tase.common.dto.app.apk.Apk;

/**
 * OrderStorageServiceTest
 * <p> </p>
 * @author yanwei
 * @since 2013-9-3 下午5:06:13
 * @version
 */
public class OrderStorageServiceTest extends TestCase {
	
	@Test 
	public void testAddOrderAndJobId() {
		String orderId = "test";
		Long jobId = 1000L;
		try {
			System.out.println(new OrderStorageService().saveOrderAndJob(orderId, jobId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSaveInputApk() {
		Apk apk = new Apk();
		FileMetadata fileMetadata = new FileMetadata();
		fileMetadata.setCreatedTime(System.currentTimeMillis());
		fileMetadata.setExtension("apk");
		fileMetadata.setFileChecksum("test");
		fileMetadata.setFileName("test");
		fileMetadata.setFilePath("/path/to/source.apk");
		apk.setFileMetadata(fileMetadata);
		try {
			System.out.println(new OrderStorageService().saveInputApkInfo(apk));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateAppStatus() {
		String sourceFileChecksum = "source";
		String targetFileChecksum = "target";
		try {
			System.out.println(new OrderStorageService().updateFileStatus(sourceFileChecksum, targetFileChecksum, 1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
