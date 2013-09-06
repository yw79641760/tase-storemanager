/**
 * 
 */
package com.softsec.tase.store.domain;

import com.softsec.tase.common.rpc.domain.app.AppType;
import com.softsec.tase.common.rpc.domain.job.JobLifecycle;

/**
 * JobTypeItem.java
 * @author yanwei
 * @date 2013-3-13 上午10:22:12
 * @description
 */
public class JobTypeItem {

	private long jobType;
	
	private int masterId;
	
	private int jobCount;
	
	public static AppType getAppType(long jobType) {
		return AppType.findByValue((int)(jobType / 10000000));
	}
	
	public static JobLifecycle getJobLifecycle(long jobType) {
		return JobLifecycle.findByValue((int)(jobType % 10000000 / 1000000));
	}
	
	public static int getJobPhaseCode(long jobType) {
		return (int)(jobType % 100000000);
	}
	
	public long getJobType() {
		return jobType;
	}
	
	public void setJobType(long jobType) {
		this.jobType = jobType;
	}
	
	public int getMasterId() {
		return masterId;
	}
	
	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	public int getJobCount() {
		return jobCount;
	}

	public void setJobCount(int jobCount) {
		this.jobCount = jobCount;
	}
}
