/**
 * 
 */
package com.softsec.tase.store.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softsec.tase.common.domain.schedule.Job;
import com.softsec.tase.common.rpc.domain.job.JobDistributionMode;
import com.softsec.tase.common.rpc.domain.job.JobOperationRequirement;
import com.softsec.tase.common.rpc.domain.job.JobPhase;
import com.softsec.tase.common.rpc.domain.job.JobPriority;
import com.softsec.tase.common.rpc.domain.job.JobResourceRequirement;
import com.softsec.tase.store.dao.JobDao;
import com.softsec.tase.store.domain.JobTypeItem;
import com.softsec.tase.store.exception.DbUtilsException;
import com.softsec.tase.store.util.db.SQLMapperFactory;
import com.softsec.tase.store.util.db.SessionUtils;

/**
 * Job存储服务类
 * @author yanwei
 * @date 2013-1-4 下午3:17:16
 * 
 */
public class JobStorageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobStorageService.class);
	
	/**
	 * add new job type
	 * @param jobType
	 * @return succeed or not
	 */
	public int addJobType(long jobType, int masterId) throws DbUtilsException{
		SqlSession session = null;
		int retValue = 0;
		try {
			session = SQLMapperFactory.openSession();
			JobDao jobDao = new JobDao(session);
			retValue = jobDao.addJobType(jobType, masterId);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to add new job type : " + jobType + " : " + e.getMessage());
			throw new DbUtilsException("Failed to add new job type : " + jobType + " : " + e.getMessage());
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}

	/**
	 * update job count by job type
	 * @param jobType
	 * @param jobCount
	 */
	public int updateJobCount(long jobType, int masterId, int jobCount) throws DbUtilsException{
		SqlSession session = null;
		int retValue = 0;
		try {
			session = SQLMapperFactory.openSession();
			JobDao jobDao = new JobDao(session);
			retValue = jobDao.updateJobCount(jobType, masterId, jobCount);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to update job type : " + jobType + " 's job count : " + e.getMessage());
			throw new DbUtilsException("Failed to update job type : " + jobType + " 's job count : " + e.getMessage());
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}

	/**
	 * get job type by master id
	 * @param masterId
	 * @return
	 */
	public List<JobTypeItem> getJobTypeList(int masterId) throws DbUtilsException{
		List<JobTypeItem> jobTypeItemList = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			JobDao jobDao = new JobDao(session);
			jobTypeItemList = jobDao.getJobTypeList(masterId);
		} catch (Exception e) {
			LOGGER.error("Failed to get job type list by master id : " + masterId + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get job type list by master id : " + masterId + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return jobTypeItemList;
	}

	/**
	 * save job into database
	 * @param job
	 */
	public int save(Job job) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			JobDao jobDao = new JobDao(session);
			retValue += jobDao.saveJobBasic(job.getJobId(), job.getJobPriority(), job.getJobDistributionMode());
			retValue += jobDao.saveJobOperationList(job.getJobId(), job.getJobOperationRequirementList());
			retValue += jobDao.saveJobParameterList(job.getJobId(), job.getJobParameterList());
			retValue += jobDao.saveJobPossession(job.getJobId(), job.getSubmitterId());
			retValue += jobDao.saveJobResourceList(job.getJobId(), job.getJobResourceRequirementList());
			retValue += jobDao.saveJobStatus(job.getJobId(), job.getImpatienceTime(), job.getCommittedTime(), job.getJobStatus());
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to save job into database : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to save job into database : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	/**
	 * get job operation requirement by job id and job phase
	 * @param taskId
	 * @return
	 */
	public JobOperationRequirement getJobOperationRequirementByJobIdAndJobPhase(long jobId, JobPhase jobPhase) throws DbUtilsException {
		JobOperationRequirement jobOperationRequirement = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			JobDao jobDao = new JobDao(session);
			jobOperationRequirement = jobDao.getJobOperationRequirementByJobIdAndJobPhase(jobId, jobPhase);
		} catch (Exception e) {
			LOGGER.error("Failed to get job operation requirement by job id : " 
					+ jobId + " and job phase : " + jobPhase + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get job operation requirement by job id : " 
					+ jobId + " and job phase : " + jobPhase + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return jobOperationRequirement;
	}
	
	/**
	 * get job resource requirement by job id and job phase
	 * @param jobId
	 * @param jobPhase
	 * @return
	 * @throws DbUtilsException
	 */
	public JobResourceRequirement getJobResourceRequirementByJobIdAndJobPhase(long jobId, JobPhase jobPhase) throws DbUtilsException {
		JobResourceRequirement jobResourceRequirement = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			JobDao jobDao = new JobDao(session);
			jobResourceRequirement = jobDao.getJobResourceRequirementByJobIdAndJobPhase(jobId, jobPhase);
		} catch (Exception e) {
			LOGGER.error("Failed to get job resource requirement by job id : "
					+ jobId + " and job phase : " + jobPhase + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get job resource requirement by job id : "
					+ jobId + " and job phase : " + jobPhase + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return jobResourceRequirement;
	}

	/**
	 * get job priority by job id
	 * @param jobId
	 * @return jobPriority
	 */
	public JobPriority getJobPriorityByJobId(long jobId) throws DbUtilsException {
		JobPriority jobPriority = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			JobDao jobDao = new JobDao(session);
			jobPriority = jobDao.getJobPriorityByJobId(jobId);
		} catch (Exception e) {
			LOGGER.error("Failed to get job priority by job id : " + jobId + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get job priority by job id : " + jobId + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return jobPriority;
	}
	/**
	 * get job distribution mode by job id
	 * @param jobId
	 * @return
	 * @throws DbUtilsException
	 */
	public JobDistributionMode getJobDistributionModeByJobId(long jobId) throws DbUtilsException {
		JobDistributionMode jobDistributionMode = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			JobDao jobDao = new JobDao(session);
			jobDistributionMode = jobDao.getJobDistributionModeByJobId(jobId);
		} catch (Exception e) {
			LOGGER.error("Failed to get job distribution mode by job id : " + jobId + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get job distribution mode by job id : " + jobId + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return jobDistributionMode;
	}
}
