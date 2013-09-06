/**
 * 
 */
package com.softsec.tase.store.util;

import org.junit.Test;

import com.softsec.tase.store.util.net.FtpConnFactory;
import com.softsec.tase.store.util.net.FtpUrlParser;

/**
 * FtpUrlParserTest
 * <p> </p>
 * @author yanwei
 * @since 2013-8-6 下午3:49:19
 * @version
 */
public class FtpUrlParserTest {

	@Test
	public void testFtpUrl() {
		String ftpHost = FtpConnFactory.getRandomFtpHost();
		FtpUrlParser parser = new FtpUrlParser(ftpHost);
		System.out.println(parser.getUsername());
		System.out.println(parser.getPassword());
		System.out.println(parser.getDomain());
		System.out.println(parser.getPort());
		System.out.println(parser.getPath());
		System.out.println(parser.getFileName());
	}
}
