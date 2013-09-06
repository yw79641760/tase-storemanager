/**
 * 
 */
package com.softsec.tase.store.service;

import junit.framework.TestCase;

import org.junit.Test;

import com.softsec.tase.common.rpc.domain.app.AppType;

/**
 * FileStorageServiceTest
 * <p> </p>
 * @author yanwei
 * @since 2013-6-3 下午1:34:17
 * @version
 */
public class FileStorageServiceTest extends TestCase {
	
	
	@Test
	public void testCheckBundleDuplication() {
		FileStorageService service = new FileStorageService();
		System.out.println(service.checkBundleDuplication(AppType.APK, "12e47ec77f04d22a9d0249bfefc53c12"));
	}
	
	@Test
	public void testCheckFileDuplication() {
		FileStorageService service = new FileStorageService();
		System.out.println(service.checkFileDuplication(AppType.APK, "39476e58195c7410286f4839e6015245"));
	}
	
	@Test
	public void testGetBundleChecksumByFileStatus() {
		FileStorageService service = new FileStorageService();
		System.out.println(service.getBundleChecksumListByFileStatus(AppType.APK, 1, 0, 0, 3));
	}
	
	@Test
	public void testGetFileChecksumByBundleChecksum() {
		FileStorageService service = new FileStorageService();
		System.out.println(service.getFileChecksumByBundleChecksum(AppType.APK, "40023d55b7c7b10deb7795870f41871e"));
	}
}
