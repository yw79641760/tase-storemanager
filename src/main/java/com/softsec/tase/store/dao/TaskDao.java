/**
 * 
 */
package com.softsec.tase.store.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.softsec.tase.common.domain.schedule.Task;
import com.softsec.tase.common.rpc.domain.job.JobParameter;
import com.softsec.tase.common.rpc.domain.job.JobPhase;
import com.softsec.tase.common.rpc.domain.job.JobStatus;

/**
 * TaskDao.java
 * @author yanwei
 * @date 2013-3-18 上午8:49:18
 * @description
 */
public class TaskDao {

	private static final String NAMESPACE = "com.softsec.tase.store.dao.Task";
	
	private SqlSession session = null;
	
	public TaskDao(SqlSession session) {
		this.session = session;
	}
	
	/**
	 * save task basic
	 * @param task
	 * @return
	 */
	public int saveTaskBasic(Task task) {
		int retValue = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("taskId", task.getTaskId());
		condition.put("taskPriority", task.getTaskPriority());
		retValue = session.insert(NAMESPACE + ".insertTaskBasic", condition);
		condition = null;
		return retValue;
	}
	
	/**
	 * save task parameter
	 * @param task
	 * @return
	 */
	public int saveTaskParameter(Task task) {
		int retValue = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("taskId", task.getTaskId());
		condition.put("jobPhase", task.getJobPhase());
		condition.put("contextParameterList", task.getTaskParameter().getContextParameterList());
		retValue = session.insert(NAMESPACE + ".insertTaskParameter", condition);
		condition = null;
		return retValue;
	}
	
	/**
	 * save task scheduling
	 * @param task
	 * @return
	 */
	public int saveTaskScheduling(long taskId, JobPhase jobPhase, long programId, String executorId) {
		int retValue = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("taskId", taskId);
		condition.put("jobPhase", jobPhase);
		condition.put("programId", programId);
		condition.put("executorId", executorId);
		retValue = session.insert(NAMESPACE + ".insertTaskScheduling", condition);
		condition = null;
		return retValue;
	}
	
	/**
	 * save task status
	 * @param task
	 * @return
	 */
	public int saveTaskStatus(Task task) {
		return session.insert(NAMESPACE + ".insertTaskStatus", task);
	}
	
	/**
	 * save task time stamp
	 * @param taskId
	 * @param jobPhase
	 * @param taskStatus
	 * @param timestamp
	 * @return
	 */
	public int saveTaskTimestamp(long taskId, JobPhase jobPhase, long loadedTime, long issuedTime, long startedTime, long finishedTime) {
		int retValue = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("taskId", taskId);
		condition.put("jobPhase", jobPhase);
		condition.put("loadedTime", loadedTime);
		condition.put("issuedTime", issuedTime);
		condition.put("startedTime", startedTime);
		condition.put("finishedTime", finishedTime);
		retValue = session.insert(NAMESPACE + ".insertTaskTimestamp", condition);
		condition = null;
		return retValue;
	}
	
	/**
	 * update task phase and status
	 * @param taskId
	 * @param jobPhase
	 * @param taskStatus
	 * @return
	 */
	public int updateTaskStatusByTaskIdAndPhase(long taskId, JobPhase jobPhase, JobStatus taskStatus) {
		int retValue = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("taskId", taskId);
		condition.put("jobPhase", jobPhase);
		condition.put("taskStatus", taskStatus);
		retValue = session.update(NAMESPACE + ".updateTaskStatusByTaskIdAndPhase", condition);
		condition = null;
		return retValue;
	}

	/**
	 * save new single task
	 * @param task
	 * @return
	 */
	public int save(Task task) {
		int retValue = 0;
		retValue = saveTaskBasic(task);
		retValue += saveTaskParameter(task);
		retValue += saveTaskStatus(task);
		retValue += saveTaskTimestamp(task.getTaskId(), task.getJobPhase(), task.getLoadedTime(), 0, 0, 0);
		return retValue;
	}

	/**
	 * get task list by task status
	 * @param taskStatus
	 * @param limit
	 * @return
	 */
	public List<Task> getTaskListByTaskStatus(JobStatus taskStatus, int offset, int limit) {
		return session.selectList(NAMESPACE + ".selectTaskListByTaskStatus", taskStatus, new RowBounds(offset, limit));
	}
	
	/**
	 * get task list by status list
	 * @param taskStatusList
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<Task> getTaskListByStatusList(List<JobStatus> taskStatusList, int offset, int limit) {
		return session.selectList(NAMESPACE + ".selectTaskListByTaskStatusList", taskStatusList, new RowBounds(offset, limit));
	}

	/**
	 * save task list
	 * @param taskList
	 * @return
	 */
	public int saveAll(List<Task> taskList) {
		int retValue = 0;
		for (Task task : taskList) {
			retValue += save(task);
		}
		return retValue;
	}

	/**
	 * @param taskId
	 * @param jobPhase
	 * @return
	 */
	public JobParameter getTaskParameter(long taskId, JobPhase jobPhase) {
		JobParameter taskParameter = null;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("taskId", taskId);
		condition.put("jobPhase", jobPhase);
		taskParameter = (JobParameter) session.selectOne(NAMESPACE + ".selectTaskParameterByTaskIdAndJobPhase", condition);
		condition = null;
		return taskParameter;
	}
	/**
	 * 
	 * @param taskId
	 * @param jobPhase
	 * @return
	 */
	public Task getTaskByTaskIdAndJobPhase(Long taskId, JobPhase jobPhase) {
		Task task = null;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("taskId", taskId);
		condition.put("jobPhase", jobPhase);
		task = session.selectOne(NAMESPACE + ".selectTaskByTaskIdByJobPhase", condition);
		return task;
	}

	/**
	 * @param taskId
	 * @param jobPhase
	 * @param timestamp
	 * @return
	 */
	public int updateTaskTimestamp(long taskId, JobPhase jobPhase, long loadedTime, long issuedTime, long startedTime, long finishedTime) {
		int retValue = 0;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("taskId", taskId);
		condition.put("jobPhase", jobPhase);
		condition.put("loadedTime", loadedTime);
		condition.put("issuedTime", issuedTime);
		condition.put("startedTime", startedTime);
		condition.put("finishedTime", finishedTime);
		retValue = session.update(NAMESPACE + ".updateTaskTimestampByTaskIdAndJobPhase", condition);
		condition = null;
		return retValue;
	}
}
