/**
 * 
 */
package com.softsec.tase.store.util;

import java.io.File;

import junit.framework.TestCase;

import org.junit.Test;

import com.softsec.tase.store.exception.FileUtilsException;
import com.softsec.tase.store.exception.IOUtilsException;
import com.softsec.tase.store.exception.ZipUtilsException;
import com.softsec.tase.store.util.fs.FileUtils;
import com.softsec.tase.store.util.fs.IOUtils;
import com.softsec.tase.store.util.fs.ZipUtils;

/**
 * FileUtilsTest.java
 * @author yanwei
 * @date 2013-1-23 下午2:51:00
 * @description
 */
public class FileUtilsTest extends TestCase{
	
//	@Test
//	public void testWriteFileFromStream() {
//		String oleFile = "E://Documents/Tencent/QQPhoneManager/Application/PDF阅读器.zip";
//		String newFile = "E://Documents/Tencent/QQPhoneManager/Application/PDF阅读器.apk";
//		InputStream inputStream = null;
//		try {
//			inputStream = ZipUtils.getApkFromZip(oleFile);
//		} catch (ZipUtilsException zue) {
//			zue.printStackTrace();
//		}
//		try {
//			FileUtils.writeFileFromStream(newFile, inputStream);
//		} catch (FileUtilsException fue) {
//			fue.printStackTrace();
//		}
//	}

	@Test
	public void testSaveByteArrayToFile() {
		byte[] content = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		try {
			assertNotNull(FileUtils.saveByteArrayToFile("Test.txt", content));
		} catch (FileUtilsException fue) {
			fue.printStackTrace();
		}
	}
	

	@Test
	public void testRename() {
		String apkFileName = "E://Documents/Tencent/QQPhoneManager/Application/PDF阅读器.apk";
		try {
			FileUtils.rename(apkFileName, 
					IOUtils.getInputStreamMd5(ZipUtils.getApkExecutable(001, 
							FileUtils.readFileToByteArray(apkFileName))) + ".apk");
		} catch (IOUtilsException ioue) {
			ioue.printStackTrace();
		} catch (FileUtilsException fue) {
			fue.printStackTrace();
		} catch (ZipUtilsException zue) {
			zue.printStackTrace();
		}
	}
	
	@Test
	public void testRenameRaw() {
		String rawFile = "/Users/yanwei/Downloads/2.3.release.zip";
		String targetFile = "2.4.release.zip";
		FileUtils.rename(rawFile, targetFile);
	}
}
