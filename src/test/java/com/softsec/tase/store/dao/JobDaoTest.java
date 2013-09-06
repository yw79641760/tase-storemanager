/**
 * 
 */
package com.softsec.tase.store.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.softsec.tase.common.domain.schedule.Job;
import com.softsec.tase.common.rpc.domain.job.ContextParameter;
import com.softsec.tase.common.rpc.domain.job.JobDistributionMode;
import com.softsec.tase.common.rpc.domain.job.JobExecutionMode;
import com.softsec.tase.common.rpc.domain.job.JobOperationRequirement;
import com.softsec.tase.common.rpc.domain.job.JobParameter;
import com.softsec.tase.common.rpc.domain.job.JobPhase;
import com.softsec.tase.common.rpc.domain.job.JobPriority;
import com.softsec.tase.common.rpc.domain.job.JobResourceRequirement;
import com.softsec.tase.common.rpc.domain.job.JobReturnMode;
import com.softsec.tase.common.rpc.domain.job.JobStatus;
import com.softsec.tase.common.rpc.domain.node.ClusterType;
import com.softsec.tase.common.rpc.domain.node.NodeType;
import com.softsec.tase.common.util.domain.JobUtils;
import com.softsec.tase.store.util.db.SQLMapperFactory;
import com.softsec.tase.store.util.db.SessionUtils;

/**
 * JobDaoTest.java
 * <p></p>
 * @author yanwei
 * @since 2013-4-17 上午9:17:07
 * @version
 */
public class JobDaoTest extends TestCase {
	
	@Test
	public void testSaveJob() {
		Job job = new Job();
		job.setJobId(14);
		job.setJobPriority(JobPriority.MEDIUM);
		job.setJobDistributionMode(JobDistributionMode.SERIAL);
		System.out.println(System.currentTimeMillis());
		
		List<JobOperationRequirement> jobOperationRequirementList = new ArrayList<JobOperationRequirement>();
		JobOperationRequirement item1 = new JobOperationRequirement();
		item1.setJobPhase(JobPhase.INITIALIZE);
		item1.setJobExecutionMode(JobExecutionMode.EXCLUSIVE);
		item1.setTimeout(100000);
		JobOperationRequirement item2 = new JobOperationRequirement();
		item2.setJobPhase(JobPhase.GENERATE);
		item2.setJobReturnMode(JobReturnMode.ACTIVE);
		jobOperationRequirementList.add(item1);
		jobOperationRequirementList.add(item2);
		job.setJobOperationRequirementList(jobOperationRequirementList);
		
		List<JobResourceRequirement> jobResourceRequirementList = new ArrayList<JobResourceRequirement>();
		JobResourceRequirement item3 = new JobResourceRequirement();
		item3.setJobPhase(JobPhase.ON_STATIC);
		item3.setClusterType(ClusterType.DEDICATED);
		item3.setExecutorId("193centos:5455");
		jobResourceRequirementList.add(item3);
		JobResourceRequirement item4 = new JobResourceRequirement();
		item4.setJobPhase(JobPhase.EVALUATE);
		item4.setNodeType(NodeType.INTERNET_ACCESSIBLE);
		jobResourceRequirementList.add(item4);
		job.setJobResourceRequirementList(jobResourceRequirementList);
		
		
		job.setCommittedTime(System.currentTimeMillis());
		job.setImpatienceTime(30040);
		job.setJobStatus(JobUtils.getJobStatusCode(JobPhase.INITIALIZE, JobStatus.COMMITTED));
		job.setSubmitterId(11);
		System.out.println(job.getJobDistributionMode());
		
	}
	
	@Test
	public void testInsertJobParameter() {
		long jobId = 100002L;
		
		List<JobParameter> jobParameterList = new ArrayList<JobParameter>();
		
		JobParameter jobParameter1 = new JobParameter();
		jobParameter1.setJobPhase(JobPhase.GENERATE);
		List<ContextParameter> parameterList = new ArrayList<ContextParameter>();
		ContextParameter parameter1 = new ContextParameter();
		parameter1.setSequenceNum(1);
		parameter1.setOpt("-f");
		parameter1.setContent("./00001.apk");
		parameter1.setNeedDownload(true);
		parameterList.add(parameter1);
		ContextParameter parameter2 = new ContextParameter();
		parameter2.setSequenceNum(2);
		parameter2.setOpt("-o");
		parameter2.setContent("./00002.apk");
		parameter2.setNeedDownload(false);
		parameterList.add(parameter2);
		jobParameter1.setContextParameterList(parameterList);
		
		JobParameter jobParameter2 = new JobParameter();
		jobParameter2.setJobPhase(JobPhase.GENERATE);
		List<ContextParameter> parameterList1 = new ArrayList<ContextParameter>();
		ContextParameter parameter3 = new ContextParameter();
		parameter3.setSequenceNum(1);
		parameterList1.add(parameter3);
		ContextParameter parameter4 = new ContextParameter();
		parameter4.setSequenceNum(2);
		parameter4.setContent("./00002.apk");
		parameterList1.add(parameter4);
		jobParameter2.setContextParameterList(parameterList1);
		
		jobParameterList.add(jobParameter1);
		jobParameterList.add(jobParameter2);
		
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			JobDao jobDao = new JobDao(session);
			jobDao.saveJobParameterList(jobId, jobParameterList);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			e.printStackTrace();
		} finally {
			SessionUtils.close(session);
		}
	}
}
