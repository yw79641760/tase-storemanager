/**
 * 
 */
package com.softsec.tase.store.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softsec.tase.common.domain.schedule.Task;
import com.softsec.tase.common.rpc.domain.job.JobParameter;
import com.softsec.tase.common.rpc.domain.job.JobPhase;
import com.softsec.tase.common.rpc.domain.job.JobStatus;
import com.softsec.tase.store.dao.TaskDao;
import com.softsec.tase.store.exception.DbUtilsException;
import com.softsec.tase.store.util.db.SQLMapperFactory;
import com.softsec.tase.store.util.db.SessionUtils;

/**
 * TaskService.java
 * @author yanwei
 * @date 2013-3-22 上午9:17:03
 * @description
 */
public class TaskStorageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskStorageService.class);
	
	/**
	 * save new task
	 * @param task
	 * @return
	 * @throws DbUtilsException
	 */
	public int save(Task task) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			TaskDao taskDao = new TaskDao(session);
			retValue = taskDao.save(task);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to save task info : " + task.getTaskId() + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to save task : " + task.getTaskId() + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	
	/**
	 * get task list by task status
	 * @param taskStatus
	 * @param limit
	 * @return taskList
	 * @throws DbUtilsException
	 */
	public List<Task> getTaskListByTaskStatus(JobStatus taskStatus, int limit) throws DbUtilsException {
		List<Task> taskList = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			TaskDao taskDao = new TaskDao(session);
			taskList = taskDao.getTaskListByTaskStatus(taskStatus, 0, limit);
		} catch (Exception e) {
			LOGGER.error("Failed to get task list by status [ " + taskStatus + " ] : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get task list by status [ " + taskStatus + " ] : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return taskList;
	}
	
	/**
	 * get task list by status list
	 * @param taskStatusList
	 * @param limit
	 * @return taskList
	 * @throws DbUtilsExcpetion
	 */
	public List<Task> getTaskListByTaskStatusList(List<JobStatus> taskStatusList, int limit) throws DbUtilsException {
		List<Task> taskList = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			TaskDao taskDao = new TaskDao(session);
			taskList = taskDao.getTaskListByStatusList(taskStatusList, 0, limit);
		} catch (Exception e) {
			LOGGER.error("Failed to get task list by status list : " + taskStatusList + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get task list by status list : " + taskStatusList + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return taskList;
	}

	/**
	 * update task status by task id
	 * @param taskId
	 * @param scheduled
	 * @return
	 */
	public int updateTaskStatus(long taskId, JobPhase jobPhase, JobStatus taskStatus) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			TaskDao taskDao = new TaskDao(session);
			retValue = taskDao.updateTaskStatusByTaskIdAndPhase(taskId, jobPhase, taskStatus);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to update task status : " + taskId + " at phase : " + jobPhase + " to " + taskStatus, e);
			throw new DbUtilsException("Failed to update task status : " + taskId + " at phase : " + jobPhase + " to " + taskStatus, e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}

	/**
	 * save all tasks in task list
	 * @param taskList
	 * @return involved row number
	 */
	public int saveAll(List<Task> taskList) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			TaskDao taskDao = new TaskDao(session);
			retValue = taskDao.saveAll(taskList);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to save task list : " + taskList.size() + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to save task list : " + taskList.size() + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}

	/**
	 * save task scheduling info
	 * @param taskId
	 * @param jobPhase
	 * @param programId
	 * @param executorId
	 */
	public int saveTaskScheduling(long taskId, JobPhase jobPhase, long programId, String executorId)
		throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			TaskDao taskDao = new TaskDao(session);
			retValue = taskDao.saveTaskScheduling(taskId, jobPhase, programId, executorId);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to save task scheduling info : " + taskId + " : " + jobPhase
					+ " : " + programId + " : " + executorId + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to save task scheduling info : " + taskId + " : " + jobPhase
					+ " : " + programId + " : " + executorId + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}

	/**
	 * get task parameter by task id and job phase
	 * @param taskId
	 * @param jobPhase
	 * @return
	 * @throws DbUtilsException
	 */
	public JobParameter getTaskParameter(long taskId, JobPhase jobPhase) throws DbUtilsException {
		JobParameter taskParameter = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			TaskDao taskDao = new TaskDao(session);
			taskParameter = taskDao.getTaskParameter(taskId, jobPhase);
		} catch (Exception e) {
			LOGGER.error("Failed to get task parameter [ " + taskId + " : " + jobPhase + " ] : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get task parameter [ " + taskId + " : " + jobPhase + " ] : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return taskParameter;
	}
	
	public Task getTaskByTaskIdAndJobPhase(Long taskId, JobPhase jobPhase) throws DbUtilsException {
		Task task = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			TaskDao taskDao = new TaskDao(session);
			task = taskDao.getTaskByTaskIdAndJobPhase(taskId, jobPhase);
		} catch (Exception e) {
			LOGGER.error("Failed to get task [ " + taskId + " : " + "jobPhase" + " ] : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get task [ " + taskId + " : " + "jobPhase" + " ] : " + e.getMessage(), e);
		}
		return task;
	}
}
