/**
 * 
 */
package com.softsec.tase.store.util.fs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import com.softsec.tase.store.exception.FileUtilsException;
import com.softsec.tase.store.exception.ZipUtilsException;
import com.softsec.tase.store.util.StringUtils;

/**
 *@author yanwei
 *@time: 2012-7-19 下午4:22:28
 *@description Zip/Unzip工具类
 *
 */
/**
 *
 */
public class ZipUtils {

	/**
	 * zip source to file path
	 * @param source
	 * @param zipFilePath
	 * @throws ZipUtilsException
	 */
	public static void zip(String source, String zipFilePath) throws ZipUtilsException {
		
		OutputStream os = null;
		try {
			os = new FileOutputStream(zipFilePath);
		} catch (FileNotFoundException fnfe) {
			throw new ZipUtilsException("Failed to create zip file : " + zipFilePath + " : " + fnfe.getMessage(), fnfe);
		}
		BufferedOutputStream bos = new BufferedOutputStream(os);
		ZipOutputStream zos = new ZipOutputStream(bos);
		
		File file = new File(source);
		String basePath = null;
		
		if (file.isDirectory()) {
			basePath = file.getPath();
		} else {
			basePath = file.getParent();
		}
		zipFile(file, basePath, zos);
		try {
			zos.closeEntry();
			zos.close();
		} catch (IOException ioe) {
			throw new ZipUtilsException("Failed to close stream : " + ioe.getMessage(), ioe);
		}
	}
	
	/**
	 * zip file into outputstream
	 * @param source
	 * @param basePath
	 * @param zos
	 * @throws ZipUtilsException
	 */
	@SuppressWarnings("resource")
	private static void zipFile(File source, String basePath, ZipOutputStream zos) throws ZipUtilsException {
		File[] files = new File[0];
		
		if(source.isDirectory()) {
			files = source.listFiles();
		} else {
			files = new File[1];
			files[0] = source;
		}
		
		String pathName;
		byte[] buffer = new byte[1024];
		int length = 0;
		
		for (File file : files) {
			if (file.isDirectory()) {
				pathName = file.getPath().substring(basePath.length() + 1) + "/";
				try {
					zos.putNextEntry(new ZipEntry(pathName));
				} catch (IOException ioe) {
					throw new ZipUtilsException("Failed to assemble directory entries : " + ioe.getMessage(), ioe);
				}
				zipFile(file, basePath, zos);
			} else {
				pathName = file.getPath().substring(basePath.length() + 1);
				InputStream is;
				try {
					is = new FileInputStream(file);
				} catch (FileNotFoundException fnfe) {
					throw new ZipUtilsException("Failed to open file entry : " + fnfe.getMessage(), fnfe);
				}
				BufferedInputStream bis = new BufferedInputStream(is);
				try {
					zos.putNextEntry(new ZipEntry(pathName));
				} catch (IOException ioe) {
					throw new ZipUtilsException("Failed to assemble file entry : " + ioe.getMessage(), ioe);
				}
				
				try {
					while ((length = bis.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}
				} catch (IOException ioe) {
					throw new ZipUtilsException("Failed to read stream or write zip file : " + ioe.getMessage(), ioe);
				} finally {
					try {
						is.close();
						bis.close();
					} catch (IOException ioe) {
						throw new ZipUtilsException("Failed to close stream : " +  ioe.getMessage(), ioe);
					}
				}
			}
		}
	}

	/**
	 * unzip file to dest dir
	 * @param zipFilePath 压缩文件路径
	 * @param destDir 解压的目的文件夹
	 * @return executable file path or null 返回解压文件中的可执行文件的绝对路径，否则为空
	 * @throws ZipUtilsException
	 */
	@SuppressWarnings("resource")
	public static String unzip(String zipFilePath, String destDir) throws ZipUtilsException {
		
		String appName = null;
		
		destDir = destDir.endsWith("/") ? destDir : destDir + "/";
		byte[] buffer = new byte[10240];
		int length;
		
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(new File(zipFilePath));
		} catch (IOException ioe) {
			throw new ZipUtilsException("Failed to open zip file : " + ioe.getMessage(), ioe);
		}
		@SuppressWarnings("rawtypes")
		Enumeration enumeration = zipFile.getEntries();
		ZipEntry zipEntry = null;
			
		while (enumeration.hasMoreElements()) {
			zipEntry = (ZipEntry) enumeration.nextElement();
			File loadFile = new File(destDir + zipEntry.getName());
			
			String temp = StringUtils.parseAppExecutablePath(zipEntry.getName());
			if (temp != null) {
				appName = temp;
			}

			if (zipEntry.isDirectory()) {
				loadFile.mkdirs();
			} else {
				if (!loadFile.getParentFile().exists()) {
					loadFile.getParentFile().mkdirs();
				}
				
				OutputStream os = null;
				try {
					os = new FileOutputStream(loadFile);
				} catch (FileNotFoundException fnfe) {
					throw new ZipUtilsException("Failed to create file : " + loadFile + " : " + fnfe.getMessage(), fnfe);
				}
				InputStream is;
				try {
					is = zipFile.getInputStream(zipEntry);
				} catch (ZipException ze) {
					throw new ZipUtilsException("Failed to find compression method : " + " : " + ze.getMessage(), ze);
				} catch (IOException ioe) {
					throw new ZipUtilsException("Failed to close zip entry : " + zipEntry.getName() + " : " + ioe.getMessage(), ioe);
				}
				
				try {
					while ((length = is.read(buffer)) > 0) {
						os.write(buffer, 0, length);
					}
				} catch (IOException ioe) {
					throw new ZipUtilsException("Failed to read stream or write file : " + ioe.getMessage(), ioe);
				} finally {
					try {
						os.close();
					} catch (IOException e) {
						throw new ZipUtilsException("");
					}
				}
			}
		}
		return appName == null ? null : destDir + appName;
	}
	
	/**
	 * get specific file name 's input stream in zip file 
	 * @param zipFilePath
	 * @param targetEntryName
	 * @return
	 */
	public static InputStream getInputStreamByEntryName(ZipFile zipFile, String targetEntryName) throws ZipUtilsException {
		@SuppressWarnings("rawtypes")
		Enumeration enumeration = zipFile.getEntries();
		ZipEntry zipEntry = null;
		while (enumeration.hasMoreElements()) {
			zipEntry = (ZipEntry) enumeration.nextElement();
			if (zipEntry.getName().endsWith(targetEntryName)) {
				try {
					return zipFile.getInputStream(zipEntry);
				} catch (ZipException ze) {
					throw new ZipUtilsException("Failed to open zip file : " + zipFile.toString() + " : " + ze.getMessage(), ze);
				} catch (IOException ioe) {
					throw new ZipUtilsException("Failed to read zip file : " + zipFile.toString() + " : " + ioe.getMessage(), ioe);
				}
			}
		}
		return null;
	}

	/**
	 * get app executable file input stream
	 * @param appId
	 * @param appContent
	 * @return
	 * @throws FileUtilsException
	 * @throws ZipUtilsException
	 */
	public static InputStream getApkExecutable(long appId, byte[] appContent) throws FileUtilsException, ZipUtilsException {
		
		String targetFileName = "classes.dex";
		
		File tempFile = null;
		tempFile = FileUtils.saveByteArrayToFile(String.valueOf(appId) + ".apk", appContent);

		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(tempFile);
		} catch (IOException ioe) {
			throw new ZipUtilsException("Failed to open zip file : " + tempFile.getName() + " : " + ioe.getMessage(), ioe);
		}
		@SuppressWarnings("rawtypes")
		Enumeration enumeration = zipFile.getEntries();
		ZipEntry zipEntry = null;
		while (enumeration.hasMoreElements()) {
			zipEntry = (ZipEntry) enumeration.nextElement();
			if (zipEntry.getName().endsWith(targetFileName)) {
				try {
					return zipFile.getInputStream(zipEntry);
				} catch (ZipException ze) {
					throw new ZipUtilsException("Failed to find compression method : " + ze.getMessage(), ze);
				} catch (IOException ioe) {
					throw new ZipUtilsException("Failed to close zip entry : " + zipEntry.getName() + " : " + ioe.getMessage(), ioe);
				}
			}
		}
		return null;
	}
	
	/**
	 * get apk stream from zip file
	 * @param fileName
	 * @return
	 * @throws ZipUtilsException
	 */
	public static InputStream getApkFromZip(String fileName) throws ZipUtilsException {
		
		String targetFileSuffix = ".apk";
		File tempFile = new File(fileName);
		
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(tempFile);
		} catch (IOException ioe) {
			throw new ZipUtilsException("Failed to open zip file : " + fileName + " : " + ioe.getMessage(), ioe);
		}
		@SuppressWarnings("rawtypes")
		Enumeration enumeration = zipFile.getEntries();
		ZipEntry zipEntry = null;
		while (enumeration.hasMoreElements()) {
			zipEntry = (ZipEntry) enumeration.nextElement();
			if (zipEntry.getName().toLowerCase().endsWith(targetFileSuffix)) {
				try {
					return zipFile.getInputStream(zipEntry);
				} catch (ZipException ze) {
					throw new ZipUtilsException("Failed to find compression method : " + ze.getMessage(), ze);
				} catch (IOException ioe) {
					throw new ZipUtilsException("Failed to close zip entry : " + zipEntry.getName() + " : " + ioe.getMessage(), ioe);
				}
			}
		}
		return null;
	}
	
	/**
	 * get ipa executable file input stream
	 * @param appId
	 * @param appContent
	 * @return
	 * @throws ZipUtilsException
	 * @throws FileUtilsException
	 */
	public static InputStream getIpaExecutable(long appId, byte[] appContent) throws ZipUtilsException, FileUtilsException {
		
		File tempFile = FileUtils.saveByteArrayToFile(String.valueOf(appId) + ".ipa", appContent);
		
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(tempFile);
		} catch (IOException ioe) {
			throw new ZipUtilsException("Failed to open zip file : " + tempFile.getName() + " : " + ioe.getMessage(), ioe);
		}
		@SuppressWarnings("rawtypes")
		Enumeration enumeration = zipFile.getEntries();
		ZipEntry zipEntry = null;
		while (enumeration.hasMoreElements()) {
			zipEntry = (ZipEntry) enumeration.nextElement();
			if (StringUtils.parseAppExecutablePath(zipEntry.getName()) != null) {
				try {
					return zipFile.getInputStream(zipEntry);
				} catch (ZipException ze) {
					throw new ZipUtilsException("Failed to find compression method : " + ze.getMessage(), ze);
				} catch (IOException ioe) {
					throw new ZipUtilsException("Failed to close zip entry : " + zipEntry.getName() + " : " + ioe.getMessage(), ioe);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * get ipa executable stream by ipa file name
	 * @param fileName
	 * @return
	 * @throws ZipUtilsException
	 */
	public static InputStream getIpaExecutable(String fileName) throws ZipUtilsException {
		File tempFile = new File(fileName);
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(tempFile);
		} catch (IOException ioe) {
			throw new ZipUtilsException("Failed to open zip file : " + fileName + " : " + ioe.getMessage(), ioe);
		}
		@SuppressWarnings("rawtypes")
		Enumeration enumeration = zipFile.getEntries();
		ZipEntry zipEntry = null;
		while (enumeration.hasMoreElements()) {
			zipEntry = (ZipEntry) enumeration.nextElement();
			if (StringUtils.parseAppExecutablePath(zipEntry.getName()) != null) {
				try {
					return zipFile.getInputStream(zipEntry);
				} catch (ZipException ze) {
					throw new ZipUtilsException("Failed to find compression method : " + ze.getMessage(), ze);
				} catch (IOException ioe) {
					throw new ZipUtilsException("Failed to close zip entry : " + zipEntry.getName() + " : " + ioe.getMessage(), ioe);
				}
			}
		}
		
		return null;
	}
}
