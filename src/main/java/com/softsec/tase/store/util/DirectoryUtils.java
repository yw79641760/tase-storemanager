/**
 * 
 */
package com.softsec.tase.store.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * DirectoryUtils.java
 * @description
 * @todo
 * @author yanwei
 * @date 2013-4-2 下午10:04:52
 */
public class DirectoryUtils {
	
	public static List<String> listFiles(File dirName) {
		List<String> fileList = new ArrayList<String>();
		File[] fileArray = dirName.listFiles();
		for (File file : fileArray) {
			if (file.isFile()) {
				fileList.add(file.getName());
			}
		}
		return fileList;
	}

	public static List<String> listDirs(File dirName) {
		List<String> fileList = new ArrayList<String>();
		File[] fileArray = dirName.listFiles();
		for (File file : fileArray) {
			if (file.isDirectory()) {
				fileList.add(file.getName());
			}
		}
		return fileList;
	}
}
