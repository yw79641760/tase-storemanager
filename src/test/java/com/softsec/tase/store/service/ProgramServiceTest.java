/**
 * 
 */
package com.softsec.tase.store.service;

import junit.framework.TestCase;

import org.junit.Test;

import com.softsec.tase.store.exception.DbUtilsException;
import com.softsec.tase.store.service.ProgramStorageService;

/**
 * ProgramServiceTest.java
 * @author yanwei
 * @date 2013-2-6 下午5:33:14
 * @description
 */
public class ProgramServiceTest extends TestCase {
	
	@Test
	public void testCheckForDuplication() {
		String scriptMd5 = "lalal";
		String executableMd5 = "adsfasd";
		ProgramStorageService service = new ProgramStorageService();
		try {
			System.out.println(service.checkForDuplication(scriptMd5, executableMd5));
		} catch (DbUtilsException due) {
			due.printStackTrace();
		}
	}
}
