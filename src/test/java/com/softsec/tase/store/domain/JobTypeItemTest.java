/**
 * 
 */
package com.softsec.tase.store.domain;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * JobTypeTest.java
 * @author yanwei
 * @date 2013-3-21 下午1:51:44
 * @description
 */
public class JobTypeItemTest extends TestCase {

	@Test
	public void testGetAppType() {
		int jobType = 1034567801;
		System.out.println(JobTypeItem.getAppType(jobType));
		System.out.println(JobTypeItem.getJobLifecycle(jobType));
		System.out.println(JobTypeItem.getJobPhaseCode(jobType));
	}
}
