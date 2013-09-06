/**
 * 
 */
package com.softsec.tase.store.util.fs;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.softsec.tase.common.util.StringUtils;
import com.softsec.tase.store.exception.FileUtilsException;

/**
 * 
 * @author yanwei
 * @date 2013-1-2 上午9:12:11
 * 
 */
public class FileUtils {
	
	/**
	 * Get the FileInputStream 's MD5
	 * @param fis
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * 			Failed to read FileInputStream
	 */
	public static String getFileMd5(FileInputStream fis) throws NoSuchAlgorithmException, IOException {
		byte[] dataBytes = new byte[1024];
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        int nread = 0; 
		while ((nread = fis.read(dataBytes)) != -1) {
			messageDigest.update(dataBytes, 0, nread);
		}
		fis.close();
		return StringUtils.byteArrayToHexString(messageDigest.digest());
	}
	
	/**
	 * write file from stream
	 * @param fileName
	 * @param is
	 * @return
	 * @throws FileUtilsException
	 */
	public static String writeFileFromStream(String fileName, InputStream is) throws FileUtilsException {
		File file = new File(fileName);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException fnfe) {
			throw new FileUtilsException("Failed to create file : " + fileName + " : " + fnfe.getMessage(), fnfe);
		}
		byte[] buffer = new byte[10240];
		int nread = 0;
		try {
			while ((nread = is.read(buffer)) != -1) {
				fos.write(buffer, 0, nread);
			}
		} catch (IOException ioe) {
			throw new FileUtilsException("Failed to read stream or write file : " + fileName + " : " + ioe.getMessage(), ioe);
		}
		try {
			is.close();
			fos.close();
		} catch (IOException ioe) {
			throw new FileUtilsException("Failed to close stream : " + ioe.getMessage(), ioe);
		}
		return file.getAbsolutePath();
	}
	
	/**
	 * read file to byte array
	 * @param fileName
	 * @return
	 * @throws FileUtilsException
	 */
	public static byte[] readFileToByteArray(String fileName) throws FileUtilsException {
		File file = new File(fileName);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException fnfe) {
			throw new FileUtilsException("Failed to open file : " + fileName + " : " + fnfe.getMessage(), fnfe);
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream(10240);
		byte[] buffer = new byte[10240];
		int nread = 0;
		try {
			while ((nread = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, nread);
			}
		} catch (IOException ioe) {
			throw new FileUtilsException("Failed to read file : " + fileName + " : " + ioe.getMessage(), ioe);
		}
		try {
			fis.close();
			baos.close();
		} catch (IOException ioe) {
			throw new FileUtilsException("Failed to close stream : " + ioe.getMessage(), ioe);
		}
		
		return baos.toByteArray();
	}

	/**
	 * save byte array to file
	 * @param fileName
	 * @param content
	 * @return
	 * @throws FileUtilsException
	 */
	@SuppressWarnings("resource")
	public static File saveByteArrayToFile(String fileName, byte[] content) throws FileUtilsException {
		File file = new File(fileName);
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException fnfe) {
			throw new FileUtilsException("Failed to create file : " + fileName + " : " + fnfe.getMessage(), fnfe);
		}
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		try {
			bos.write(content);
		} catch (IOException ioe) {
			throw new FileUtilsException("Failed to write file : " + fileName + " : " + ioe.getMessage(), ioe);
		}
		try {
			fos.close();
			bos.close();
		} catch (IOException ioe) {
			throw new FileUtilsException("Failed to close stream : " + ioe.getMessage(), ioe);
		}
		
		return file;
	}
	
	/**
	 * rename file in the original folder
	 * @param oldName
	 * @param newName
	 * @return
	 */
	public static boolean rename(String oldName, String newName) {
		boolean isSucceed = false;
		File oldFile = new File(oldName);
		if (oldFile != null && oldFile.exists() && !oldFile.getName().equals(newName)) {
			String dir = oldFile.getParent();
			File newFile = new File(dir + "/" + newName);
			isSucceed = oldFile.renameTo(newFile) && deleteFile(oldFile);
		}
		return isSucceed;
	}
	
	/**
	 * delete file
	 * @param file
	 */
	public static boolean deleteFile(File file) {
		boolean isSucceed = false;
		if (file != null) {
			isSucceed = file.delete();
		}
		return isSucceed;
	}
	
	/**
	 * delete whole directory recursively
	 * @param file
	 * @return
	 */
	public static boolean deleteDirectory(File file) {
		if (file != null && file.isDirectory()) {
			for (File entry : file.listFiles()) {
				if (!deleteFile(entry)) {
					return false;
				}
			}
		}
		return true;
	}
}
