/**
 * 
 */
package com.softsec.tase.store.service;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.softsec.tase.common.domain.schedule.Task;
import com.softsec.tase.common.rpc.domain.job.JobParameter;
import com.softsec.tase.common.rpc.domain.job.JobPhase;
import com.softsec.tase.common.rpc.domain.job.JobStatus;

/**
 * TaskStorageServiceTest
 * <p> </p>
 * @author yanwei
 * @since 2013-9-4 下午10:25:59
 * @version
 */
public class TaskStorageServiceTest extends TestCase {

	@Test
	public void testGetTask() {
		List<Task> taskList = 	new TaskStorageService().getTaskListByTaskStatus(JobStatus.COMMITTED, 10);
		if (taskList != null && taskList.size() != 0) {
			for (Task task : taskList) {
				System.out.println(task);
			}
		}
	}
	
	@Test
	public void testGetTaskParameter() {
		JobParameter taskParameter = new TaskStorageService().getTaskParameter(100000680112346701L, JobPhase.GENERATE);
		System.out.println(taskParameter);
	}
	
	@Test
	public void testGetSingleTask() {
		Task task = new TaskStorageService().getTaskByTaskIdAndJobPhase(100000680112346701L, JobPhase.GENERATE);
		System.out.println(task);
	}
}
