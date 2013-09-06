/**
 * 
 */
package com.softsec.tase.store.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.softsec.tase.common.rpc.domain.job.JobDistributionMode;
import com.softsec.tase.common.rpc.domain.job.JobOperationRequirement;
import com.softsec.tase.common.rpc.domain.job.JobParameter;
import com.softsec.tase.common.rpc.domain.job.JobPhase;
import com.softsec.tase.common.rpc.domain.job.JobPriority;
import com.softsec.tase.common.rpc.domain.job.JobResourceRequirement;
import com.softsec.tase.store.domain.JobRequirementItem;
import com.softsec.tase.store.domain.JobTypeItem;

/**
 * job data access object
 * @author yanwei
 * @date 2013-1-4 下午3:26:17
 * 
 */
public class JobDao {

	private static final String NAMESPACE = "com.softsec.tase.store.dao.Job";
	
	private SqlSession session = null;
	/**
	 * 
	 */
	public JobDao(SqlSession session) {
		this.session = session;
	}
	/**
	 * add mission type
	 * @param jobType
	 * @return
	 */
	public int addJobType(long jobType, int masterId) {
		int retValue = 0;
		if (jobType != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("jobType", jobType);
			condition.put("masterId", masterId);
			retValue = session.insert(NAMESPACE + ".insertJobType", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * update job count by job type
	 * @param jobType
	 * @param jobCount
	 * @return
	 */
	public int updateJobCount(long jobType, int masterId, int jobCount) {
		int retValue = 0;
		if (jobCount != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("jobType", jobType);
			condition.put("masterId", masterId);
			condition.put("jobCount", jobCount);
			retValue = session.update(NAMESPACE + ".updateJobCountByJobType", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * get job type list by master id
	 * @param masterId
	 * @return
	 */
	public List<JobTypeItem> getJobTypeList(int masterId) {
		return session.selectList(NAMESPACE + ".selectJobTypeListByMasterId", masterId);
	}
	/**
	 * save job basic
	 * @param job
	 * @return
	 */
	public int saveJobBasic(Long jobId, JobPriority jobPriority, JobDistributionMode jobDistributionMode) {
		int retValue = 0;
		if (jobId != 0L) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("jobId", jobId);
			condition.put("jobPriority", jobPriority);
			condition.put("jobDistributionMode", jobDistributionMode);
			retValue = session.insert(NAMESPACE + ".insertJobBasic", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save job operation requirement list
	 * @param job
	 * @return
	 */
	public int saveJobOperationList(Long jobId, List<JobOperationRequirement> jobOperationRequirementList) {
		int retValue = 0;
		if (jobOperationRequirementList != null 
				&& jobOperationRequirementList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("jobId", jobId);
			condition.put("jobOperationRequirementList", jobOperationRequirementList);
			retValue = session.insert(NAMESPACE + ".insertJobOperation", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save job parameter list
	 * @param job
	 * @return
	 */
	public int saveJobParameterList(Long jobId, List<JobParameter> jobParameterList) {
		int retValue = 0;
		if (jobParameterList != null 
				&& jobParameterList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			for (JobParameter jobParameter : jobParameterList) {
				condition.put("jobId", jobId);
				condition.put("jobPhase", jobParameter.getJobPhase());
				condition.put("contextParameterList", jobParameter.getContextParameterList());
				retValue = session.insert(NAMESPACE + ".insertJobParameter", condition);
				condition.clear();
			}
			condition = null;
		}
		return retValue;
	}
	/**
	 * save job possession
	 * @param job
	 * @return
	 */
	public int saveJobPossession(Long jobId, int submitterId) {
		int retValue = 0;
		if (submitterId != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("jobId", jobId);
			condition.put("submitterId", submitterId);			
			retValue = session.insert(NAMESPACE + ".insertJobPossession", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save job resource requirement list
	 * @param job
	 * @return
	 */
	public int saveJobResourceList(Long jobId, List<JobResourceRequirement> jobResourceRequirementList) {
		int retValue = 0;
		if (jobResourceRequirementList != null 
				&& jobResourceRequirementList.size() != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("jobId", jobId);
			condition.put("jobResourceRequirementList", jobResourceRequirementList);
			retValue = session.insert(NAMESPACE + ".insertJobResource", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * save job status
	 * @param job
	 * @return
	 */
	public int saveJobStatus(Long jobId, Long impatienceTime, Long committedTime, int jobStatus) {
		int retValue = 0;
		if (jobId != 0) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("jobId", jobId);
			condition.put("impatienceTime", impatienceTime);
			condition.put("committedTime", committedTime);
			condition.put("jobStatus", jobStatus);
			retValue = session.insert(NAMESPACE + ".insertJobStatus", condition);
			condition = null;
		}
		return retValue;
	}
	/**
	 * get job requirements by job id
	 * @param jobId
	 * @return
	 * @deprecated
	 */
	public JobRequirementItem getJobRequirementItemByJobId(long jobId) {
		return (JobRequirementItem) session.selectOne(NAMESPACE + ".selectJobRequirementItemByJobId", jobId);
	}
	/**
	 * get job operation requirement by job id and job phase
	 * @param jobId
	 * @param jobPhase
	 * @return jobOperationRequirement
	 */
	public JobOperationRequirement getJobOperationRequirementByJobIdAndJobPhase(long jobId, JobPhase jobPhase) {
		JobOperationRequirement jobOperationRequirement = null;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("jobId", jobId);
		condition.put("jobPhase", jobPhase);
		jobOperationRequirement = (JobOperationRequirement)session.selectOne(NAMESPACE + ".selectJobOperationRequirementByJobIdAndJobPhase", condition);
		condition = null;
		return jobOperationRequirement;
	}
	/**
	 * get job resource requirement by job id and job phase
	 * @param jobId
	 * @param jobPhase
	 * @return jobResourceRequirement
	 */
	public JobResourceRequirement getJobResourceRequirementByJobIdAndJobPhase(long jobId, JobPhase jobPhase) {
		JobResourceRequirement jobResourceRequirement = null;
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("jobId", jobId);
		condition.put("jobPhase", jobPhase);
		jobResourceRequirement = (JobResourceRequirement) session.selectOne(NAMESPACE + ".selectJobResourceRequirementByJobIdAndJobPhase", condition);
		condition = null;
		return jobResourceRequirement;
	}
	/**
	 * get job priority by job id
	 * @param jobId
	 * @return jobPriority
	 */
	public JobPriority getJobPriorityByJobId(long jobId) {
		return (JobPriority) session.selectOne(NAMESPACE + ".selectJobPriorityByJobId", jobId);
	}
	/**
	 * get job distribution mode by job id
	 * @param jobId
	 * @return
	 */
	public JobDistributionMode getJobDistributionModeByJobId(long jobId) {
		return (JobDistributionMode) session.selectOne(NAMESPACE + ".selectJobDistributionModeByJobId", jobId);
	}
}
