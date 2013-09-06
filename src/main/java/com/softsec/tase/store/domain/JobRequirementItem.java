/**
 * 
 */
package com.softsec.tase.store.domain;

/**
 * TaskItem.java
 * @author yanwei
 * @date 2013-3-27 上午10:15:43
 * @description
 */
public class JobRequirementItem {

	private long jobId;
	
	private String jobOperationRequirement;
	
	private String jobResourceRequirement;
	
	private int priority;

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getJobOperationRequirement() {
		return jobOperationRequirement;
	}

	public void setJobOperationRequirement(String jobOperationRequirement) {
		this.jobOperationRequirement = jobOperationRequirement;
	}

	public String getJobResourceRequirement() {
		return jobResourceRequirement;
	}

	public void setJobResourceRequest(String jobResourceRequirement) {
		this.jobResourceRequirement = jobResourceRequirement;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
