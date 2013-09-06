/**
 * 
 */
package com.softsec.tase.store.domain;

/**
 * TaskItem.java
 * @description
 * @todo
 * @author yanwei
 * @date 2013-4-9 下午7:13:05
 */
public class TaskItem {

	private long taskId;
	
	private int taskPriority;
	
	private String taskOperationRequirement;
	
	private String taskResourceRequirement;
	
	private String parameter;
	
	private int taskStatus;
	
	private long programId;
	
	private String executorId;
	
	private long committedTime;

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public int getTaskPriority() {
		return taskPriority;
	}

	public void setTaskPriority(int taskPriority) {
		this.taskPriority = taskPriority;
	}

	public String getTaskOperationRequirement() {
		return taskOperationRequirement;
	}

	public void setTaskOperationRequirement(String taskOperationRequirement) {
		this.taskOperationRequirement = taskOperationRequirement;
	}

	public String getTaskResourceRequirement() {
		return taskResourceRequirement;
	}

	public void setTaskResourceRequirement(String taskResourceRequirement) {
		this.taskResourceRequirement = taskResourceRequirement;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}

	public long getProgramId() {
		return programId;
	}

	public void setProgramId(long programId) {
		this.programId = programId;
	}

	public String getExecutorId() {
		return executorId;
	}

	public void setExecutorId(String executorId) {
		this.executorId = executorId;
	}

	public long getCommittedTime() {
		return committedTime;
	}

	public void setCommittedTime(long committedTime) {
		this.committedTime = committedTime;
	}
}
