/**
 * 
 */
package com.softsec.tase.store.util.net;

import java.io.IOException;
import java.net.SocketException;
import java.util.Random;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.softsec.tase.store.Configuration;
import com.softsec.tase.store.exception.FtpUtilsException;

/**
 * FtpConnFactory.java
 * @author yanwei
 * @date 2013-2-9 下午2:52:14
 * @description
 */
public class FtpConnFactory {
	
	/**
	 * get random ftp server url
	 * @return ftpHost
	 * 			ftp://username:password@hostname:port/
	 */
	public static String getRandomFtpHost() {
		String[] ftpHosts = Configuration.get("ftp.server.urls", null).split(",");
		return ftpHosts[new Random().nextInt(ftpHosts.length)];
	}
	
	/**
	 * connect and login
	 * @param 
	 * @return Map< ftpClient, ftpHost >
	 * @throws FtpUtilsException
	 */
	public static FTPClient connect(String ftpHost) throws FtpUtilsException{
		
		// parse ftp server url
		FtpUrlParser parser = null;
		try {
			parser = new FtpUrlParser(ftpHost);
		} catch (FtpUtilsException fe) {
			throw new FtpUtilsException("Invalid ftp address format : " + ftpHost, fe);
		}
		
		String domain = parser.getDomain();
		int port = parser.getPort();
		String username = parser.getUsername();
		String password = parser.getPassword();
		
		// connect ftp server and login
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(domain, port);
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				ftpClient.disconnect();
				throw new FtpUtilsException("Failed to connect ftp server : " + ftpHost);
			}
			
			ftpClient.login(username, password);
			// initialization
			ftpClient.enterLocalPassiveMode();
			ftpClient.setControlEncoding("UTF-8");
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.setBufferSize(10240);
			
		} catch (SocketException se) {
			throw new FtpUtilsException("Failed to establish ftp socket connection : " + se.getMessage(), se);
		} catch (IOException ioe) {
			throw new FtpUtilsException("Failed to read/write from ftp connection : " + ioe.getMessage(), ioe);
		}
		
		return ftpClient;
	}
}
