/**
 * 
 */
package com.softsec.tase.store.util;

import junit.framework.TestCase;

import org.junit.Test;

import com.softsec.tase.store.exception.IOUtilsException;
import com.softsec.tase.store.exception.ZipUtilsException;
import com.softsec.tase.store.util.fs.IOUtils;
import com.softsec.tase.store.util.fs.ZipUtils;

/**
 * IOUtilsTest.java
 * @author yanwei
 * @date 2013-1-23 下午3:04:51
 * @description
 */
public class IOUtilsTest extends TestCase {

	@Test
	public void testGetByteArrayMd5() {
		byte[] content = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		String supposedValue = "c56bd5480f6e5413cb62a0ad9666613a";
		try {
			assertEquals(IOUtils.getByteArrayMd5(content), supposedValue);
		} catch (IOUtilsException ioue) {
			ioue.printStackTrace();
		}
	}
	
//	@Test
//	public void testGetAppExecutableMd5() {
//		String apkFile = "E://Documents/Tencent/QQPhoneManager/Application/PDF阅读器.apk";
//		String apkValue = "0ba5be1894b18fb26ddd8ddd2fd157ab";
//		String ipaFile = "E://Documents/Tongbu/PublicStaging/8684地铁_v1.6.8.ipa";
//		String ipaValue = "78235c6a815cf1695dee2d378fa68e37";
//
//		try {
//			assertEquals(IOUtils.getInputStreamMd5(ZipUtils.getInputStreamByEntryName(apkFile, "classes.dex")), apkValue);
//			assertEquals(IOUtils.getInputStreamMd5(ZipUtils.getIpaExecutable(ipaFile)), ipaValue);
//		} catch (IOUtilsException ioue) {
//			ioue.printStackTrace();
//		} catch (ZipUtilsException zue) {
//			zue.printStackTrace();
//		}
//	}
	
//	@Test
//	public void testIsMagicValid() {
//		String apkFile = "E://Documents/Tencent/QQPhoneManager/Application/PDF阅读器.apk";
//		String ipaFile = "E://Documents/Tongbu/PublicStaging/8684地铁_v1.6.8.ipa";
//		InputStream apkInputStream = null;
//		InputStream ipaInputStream = null;
//		try {
//			apkInputStream = ZipUtils.getApkExecutable(apkFile);
//			ipaInputStream = ZipUtils.getIpaExecutable(ipaFile);
//		} catch (ZipUtilsException zue) {
//			zue.printStackTrace();
//		}
//		try {
//			assertTrue(IOUtils.isMagicValid("APK", apkInputStream));
//			assertTrue(IOUtils.isMagicValid("IPA", ipaInputStream));
//		} catch (IOUtilsException ioue) {
//			ioue.printStackTrace();
//		}
//	}
	
//	@Test
//	public void testValidateSingleApp() {
//		String apkFile = "E://Documents/Tencent/QQPhoneManager/Application/PDF阅读器.apk";
//		String apkValue = "0ba5be1894b18fb26ddd8ddd2fd157ab";
//		InputStream apkInputStream = null;
//		try {
//			apkInputStream = ZipUtils.getApkExecutable(apkFile);
//			if (apkInputStream.markSupported()) {
//				apkInputStream.mark(0);
//			}
//		} catch (ZipException ze) {
//			ze.printStackTrace();
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//		}
//		try {
//			assertEquals(IOUtils.getAppExecutableMd5(apkInputStream), apkValue);
//		} catch (NoSuchAlgorithmException nsae) {
//			nsae.printStackTrace();
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//		}
//		try {
//			if (apkInputStream.markSupported()) {
//				apkInputStream.reset();
//			}
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//		}
//		try {
//			assertTrue(IOUtils.isMagicValid("APK", apkInputStream));
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//		}
//		try {
//			apkInputStream.close();
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//		}
//	}
}
