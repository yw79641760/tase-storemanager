/**
 * 
 */
package com.softsec.tase.store.util.fs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.softsec.tase.common.util.StringUtils;
import com.softsec.tase.store.exception.IOUtilsException;

/**
 * 
 * @author yanwei
 * @date 2012-12-21 下午5:07:42
 * 
 */
public class IOUtils {
	
	/**
	 * Get the byte array 's MD5
	 * @param content
	 * @return
	 * @throws IOUtilsException
	 */
	public static String getByteArrayMd5(byte[] content) throws IOUtilsException {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsae) {
			throw new IOUtilsException("Failed to find such algorithm : " + nsae.getMessage(), nsae);
		}
		messageDigest.update(content);
		return StringUtils.byteArrayToHexString(messageDigest.digest());
	}

	/**
	 * Get the FileInputStream 's MD5
	 * @param fis
	 * @return
	 * @throws IOUtilsException
	 */
	public static String getFileMd5(FileInputStream fis) throws IOUtilsException {
		byte[] dataBytes = new byte[10240];
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsae) {
			throw new IOUtilsException("Failed to find such algorithm : " + nsae.getMessage(), nsae);
		}
        int nread = 0; 
		try {
			while ((nread = fis.read(dataBytes)) != -1) {
				messageDigest.update(dataBytes, 0, nread);
			}
		} catch (IOException ioe) {
			throw new IOUtilsException("Failed to read stream : " + ioe.getMessage(), ioe);
		}
		try {
			fis.close();
		} catch (IOException ioe) {
			throw new IOUtilsException("Failed to close stream : " + ioe.getMessage(), ioe);
		}
		return StringUtils.byteArrayToHexString(messageDigest.digest());
	}
	
	/**
	 * object serialization
	 * @param obj
	 * @return
	 * @throws IOUtilsException
	 */
	public static byte[] getBytes(Serializable obj) throws IOUtilsException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(baos);
		} catch (IOException ioe) {
			throw new IOUtilsException("Failed to create stream : " + ioe.getMessage(), ioe);
		}
		try {
			oos.writeObject(obj);
		} catch (IOException ioe) {
			throw new IOUtilsException("Failed to write object : " + ioe.getMessage(), ioe);
			
		}
		try {
			oos.flush();
		} catch (IOException ioe) {
			throw new IOUtilsException("Failed to flush stream : " + ioe.getMessage(), ioe);
		}
		byte[] bytes = baos.toByteArray();
		try {
			baos.close();
			oos.close();
		} catch (IOException ioe) {
			throw new IOUtilsException("Failed to close stream : " + ioe.getMessage(), ioe);
		}
		return bytes;
	}
	
	/**
	 * object de-serialization
	 * @param bytes
	 * @return
	 * @throws IOUtilsException
	 */
	public static Object getObject(byte[] bytes) throws IOUtilsException {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(bais);
		} catch (IOException ioe) {
			throw new IOUtilsException("Failed to open stream : " + ioe.getMessage(), ioe);
		}
		Object obj = null;
		try {
			obj = ois.readObject();
		} catch (IOException ioe) {
			throw new IOUtilsException("Failed to read object : " + ioe.getMessage(), ioe);
		} catch (ClassNotFoundException cnfe) {
			throw new IOUtilsException("Failed to find class : " + cnfe.getMessage(), cnfe);
		}
		try {
			bais.close();
			ois.close();
		} catch (IOException ioe) {
			throw new IOUtilsException("Failed to close stream : " + ioe.getMessage(), ioe);
		}
		return obj;
	}
	
	/**
	 * get input stream 's md5
	 * @param is
	 * @return
	 * @throws IOUtilsException
	 */
	public static String getInputStreamMd5(InputStream is) throws IOUtilsException {
		byte[] dataBytes = new byte[10240];
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsae) {
			throw new IOUtilsException("Failed to find such algorithm : " + nsae.getMessage(), nsae);
		}
        int nread = 0; 
		try {
			while ((nread = is.read(dataBytes)) != -1) {
				messageDigest.update(dataBytes, 0, nread);
			}
		} catch (IOException ioe) {
			throw new IOUtilsException("Failed to read stream : " + ioe.getMessage(), ioe);
		}
		try {
			is.close();
		} catch (IOException ioe) {
			throw new IOUtilsException("Failed to close stream : " + ioe.getMessage(), ioe);
		}
		return StringUtils.byteArrayToHexString(messageDigest.digest());
	}
	
	/**
	 * get app file 's magic number
	 * @param extension
	 * @param is
	 * 			classes.dex or Apple Executable File 's input stream
	 * @return
	 * @throws IOUtilsException
	 */
	public static String getMagicNumber(String extension, InputStream is) throws IOUtilsException {
		
		int magicDigits = 0;
		String supposedMagic = null;
		if (extension.toUpperCase().equals("APK")) {
			magicDigits = 3;
			supposedMagic = "646578";
		} else if (extension.toUpperCase().equals("IPA")) {
			magicDigits = 4;
			supposedMagic = "cafebabe";
		} else if (extension.toUpperCase().equals("ZIP")) {
			magicDigits = 2;
			supposedMagic = "504b";
		}
		
		int magicNumber = 0;
		for (int i = 0; i < magicDigits; i++) {
			try {
				magicNumber = (magicNumber << 8) ^ is.read();
			} catch (IOException ioe) {
				throw new IOUtilsException("Failed to read stream : " + ioe.getMessage(), ioe);
			}
		}
		try {
			is.close();
		} catch (IOException ioe) {
			throw new IOUtilsException("Failed to close stream : " + ioe.getMessage(), ioe);
		}
		String magicNumberString = Integer.toHexString(magicNumber);
		
		if (!StringUtils.isEmpty(supposedMagic)) {
			if (magicNumberString.startsWith(supposedMagic)) {
				return magicNumberString;
			} else if (magicNumberString.endsWith(supposedMagic)) {
				return magicNumberString;
			} else {
				return null;
			}
		}
		return magicNumberString;
	}
}
