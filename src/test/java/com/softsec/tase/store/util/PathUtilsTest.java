/**
 * 
 */
package com.softsec.tase.store.util;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * PathUtilsTest
 * <p> </p>
 * @author yanwei
 * @since 2013-8-21 上午11:47:33
 * @version
 */
public class PathUtilsTest extends TestCase {

	@Test
	public void testFormatPath4Ftp() {
		String ftpDirName = "default//apk/d2/21";
		System.out.println(PathUtils.formatPath4FTP(ftpDirName));
	}
	
	@Test
	public void testFormatPath4File() {
		String filePath = "//Default/kkk/";
		System.out.println(PathUtils.formatPath4File(filePath));
	}
}
