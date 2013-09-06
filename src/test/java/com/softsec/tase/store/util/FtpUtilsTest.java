/**
 * 
 */
package com.softsec.tase.store.util;

import junit.framework.TestCase;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.softsec.tase.store.exception.FtpUtilsException;
import com.softsec.tase.store.util.net.FtpConnFactory;
import com.softsec.tase.store.util.net.FtpUtils;

/**
 * 
 * @author yanwei
 * @date 2013-1-1 下午5:10:43
 * 
 */
public class FtpUtilsTest extends TestCase {
	
	@Test
	public void testConnect() {
		FTPClient ftpClient = null;
		String ftpHost = null;
		try {
			ftpHost = FtpConnFactory.getRandomFtpHost();
			ftpClient = FtpConnFactory.connect(ftpHost);
			System.out.println(FtpUtils.listFtpDirectory(ftpClient, "/image/apk/00/00"));
		} catch (FtpUtilsException e) {
			e.printStackTrace();
		} finally {
			try {
				assertTrue(FtpUtils.disconnect(ftpClient));
			} catch (FtpUtilsException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testUpload() {
		String localFilePath = "E://Downloads/iTunes64Setup_10.7.0.21_10.7.0.21.exe";
		String ftpHost = null;
		FTPClient ftpClient = null;
		try {
			ftpHost = FtpConnFactory.getRandomFtpHost();
			ftpClient = FtpConnFactory.connect(ftpHost);
		} catch (FtpUtilsException e1) {
			e1.printStackTrace();
		}
		try {
			assertTrue(FtpUtils.upload(ftpClient, localFilePath, "/test1/"));
		} catch (FtpUtilsException e) {
			e.printStackTrace();
		} finally {
			try {
				FtpUtils.disconnect(ftpClient);
			} catch (FtpUtilsException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testDownload() {
		String remoteFileCanonicalPath = "/image/apk/00/00/";
		String ftpHost = null;
		FTPClient ftpClient = null;
		try {
			ftpHost = FtpConnFactory.getRandomFtpHost();
			ftpClient = FtpConnFactory.connect(ftpHost);
		} catch (FtpUtilsException e1) {
			e1.printStackTrace();
		}
		try {
			assertNotNull(FtpUtils.download(ftpClient, remoteFileCanonicalPath, "/Users/yanwei/Desktop/"));
		} catch (FtpUtilsException e) {
			e.printStackTrace();
		} finally {
			try {
				FtpUtils.disconnect(ftpClient);
			} catch (FtpUtilsException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Test
	public void testValidateDirectory() {
		String ftpDirPath = "default/apk/d2/21";
		String ftpServer = null;
		FTPClient ftpClient  = null;
		try {
			ftpServer = FtpConnFactory.getRandomFtpHost();
			ftpClient = FtpConnFactory.connect(ftpServer);
		} catch (FtpUtilsException fue) {
			fue.printStackTrace();
		}
		try {
			assertNotNull(FtpUtils.validateFtpDirectory(ftpClient, ftpDirPath));
		} catch (FtpUtilsException fue) {
			fue.printStackTrace();
		} finally {
			try {
				FtpUtils.disconnect(ftpClient);
			} catch (FtpUtilsException fue) {
				fue.printStackTrace();
			}
		}
	}
}
