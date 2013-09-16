/**
 * 
 */
package com.softsec.tase.store.domain;

import java.util.List;
import java.util.Set;

import com.softsec.tase.common.rpc.domain.node.ClusterType;
import com.softsec.tase.common.rpc.domain.node.NodePayload;
import com.softsec.tase.common.rpc.domain.node.NodeType;

/**
 * NodeItem.java
 * @author yanwei
 * @date 2013-3-15 下午3:12:46
 * @description node for scheduling
 */
public class NodeItem implements Comparable<NodeItem>{

	private String nodeId;
	
	private ClusterType clusterType;
	
	private Set<NodeType> nodeTypeSet;
	
	private int cpuCores;
	
	private int cpuMhz;
	
	private double cpuUsedPerc;
	
	private long freeMem;
	
	private long jvmFreeMem;

	private int queueLimit;
	
	private int queueNum;
	
	private long expectedDelay;
	
	private List<Integer> preferredProgramTypeList;
	
	private List<Long> preferredProgramIdList;
	
	private int grade;
	
	private long updatedTime;
	
	public NodeItem(int grade) {
		this.grade = grade;
	}
	
	public NodeItem(NodePayload nodePayload) {
		
		this.nodeId = nodePayload.getNodeId();
		this.clusterType = nodePayload.getClusterType();
		this.nodeTypeSet = nodePayload.getNodeTypeList();
		
		this.cpuCores = nodePayload.getNodeRuntime().getCpuCores();
		this.cpuMhz = nodePayload.getNodeRuntime().getCpuMhz();
		this.cpuUsedPerc = nodePayload.getNodeRuntime().getCpuUsedPerc();
		this.freeMem = nodePayload.getNodeRuntime().getFreeMem();
		this.jvmFreeMem = nodePayload.getNodeRuntime().getJvmFreeMem();
		
		this.queueLimit = nodePayload.getQueueLimit();
		this.queueNum = nodePayload.getQueueNum();
		this.expectedDelay = nodePayload.getExpectedDelay();
		this.preferredProgramTypeList = nodePayload.getPreferredProgramTypeList();
		this.preferredProgramIdList = nodePayload.getPreferredProgramIdList();
		
		this.updatedTime = System.currentTimeMillis();
		
		getGrade(this.cpuCores,
				this.cpuMhz,
				this.cpuUsedPerc,
				this.freeMem,
				this.jvmFreeMem,
				this.queueLimit,
				this.queueNum,
				this.expectedDelay,
				this.preferredProgramTypeList,
				this.preferredProgramIdList);
	}
	
	/**
	 * node performance evaluate equation
	 * @param cpuCores
	 * @param cpuMhz
	 * @param cpuUsedPerc
	 * @param freeMem
	 * @param jvmFreeMem
	 * @param queueLimit
	 * @param queueNum
	 * @param expectedDelay
	 * @param preferredProgramTypeList
	 */
	private void getGrade(int cpuCores, 
							int cpuMhz, 
							double cpuUsedPerc,
							long freeMem,
							long jvmFreeMem,
							int queueLimit,
							int queueNum,
							long expectedDelay,
							List<Integer> preferredProgramTypeList,
							List<Long> preferredProgramIdList) {
		
		/**
		 * node performance evaluate equation
		 * 
		 * NODE_LOAD = ( int ) ( CPU_SCORE + MEM_SCORE ) * ( QUEUE_LIMIT - QUEUE_NUM) / QUEUE_LIMIT
		 */
		int cpuScore = (int) (cpuCores * cpuMhz * (1 - cpuUsedPerc / 100) / 100);
		int memScore = (int) (freeMem / 100);
		this.grade = (int) ((cpuScore + memScore) * (queueLimit - queueNum) / queueLimit);
	}
	
	/**
	 * update node performance grade
	 */
	public void updateGrade() {
		getGrade(this.cpuCores, 
					this.cpuMhz, 
					this.cpuUsedPerc, 
					this.freeMem, 
					this.jvmFreeMem, 
					this.queueLimit, 
					this.queueNum,
					this.expectedDelay,
					this.preferredProgramTypeList,
					this.preferredProgramIdList);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(NodeItem nodeItem) {
		if (nodeItem != null && nodeItem.getNodeId() != null && nodeItem.getNodeId().equals(nodeId)) {
			return 0;
		}
		return Integer.valueOf(nodeItem.getGrade()).compareTo(grade);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeItem other = (NodeItem) obj;
		if (nodeId == null) {
			if (other.nodeId != null)
				return false;
		} else if (!nodeId.equals(other.nodeId))
			return false;
		return true;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public ClusterType getClusterType() {
		return clusterType;
	}

	public void setClusterType(ClusterType clusterType) {
		this.clusterType = clusterType;
	}

	public Set<NodeType> getNodeTypeSet() {
		return nodeTypeSet;
	}

	public void setNodeTypeSet(Set<NodeType> nodeTypeSet) {
		this.nodeTypeSet = nodeTypeSet;
	}

	public int getQueueLimit() {
		return queueLimit;
	}

	public void setQueueLimit(int queueLimit) {
		this.queueLimit = queueLimit;
	}

	public int getCpuCores() {
		return cpuCores;
	}

	public void setCpuCores(int cpuCores) {
		this.cpuCores = cpuCores;
	}

	public int getCpuMhz() {
		return cpuMhz;
	}

	public void setCpuMhz(int cpuMhz) {
		this.cpuMhz = cpuMhz;
	}

	public double getCpuUsedPerc() {
		return cpuUsedPerc;
	}

	public void setCpuUsedPerc(double cpuUsedPerc) {
		this.cpuUsedPerc = cpuUsedPerc;
	}

	public long getFreeMem() {
		return freeMem;
	}

	public void setFreeMem(long freeMem) {
		this.freeMem = freeMem;
	}

	public long getJvmFreeMem() {
		return jvmFreeMem;
	}

	public void setJvmFreeMem(long jvmFreeMem) {
		this.jvmFreeMem = jvmFreeMem;
	}

	public int getQueueNum() {
		return queueNum;
	}

	public void setQueueNum(int queueNum) {
		this.queueNum = queueNum;
	}

	public long getExpectedDelay() {
		return expectedDelay;
	}

	public void setExpectedDelay(long expectedDelay) {
		this.expectedDelay = expectedDelay;
	}

	public List<Integer> getPreferredProgramTypeList() {
		return preferredProgramTypeList;
	}

	public void setPreferredProgramTypeList(List<Integer> preferredProgramTypeList) {
		this.preferredProgramTypeList = preferredProgramTypeList;
	}

	public List<Long> getPreferredProgramIdList() {
		return preferredProgramIdList;
	}

	public void setPreferredProgramIdList(List<Long> preferredProgramIdList) {
		this.preferredProgramIdList = preferredProgramIdList;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NodeItem [nodeId=" + nodeId + ", clusterType=" + clusterType
				+ ", nodeTypeSet=" + nodeTypeSet + ", cpuCores=" + cpuCores
				+ ", cpuMhz=" + cpuMhz + ", cpuUsedPerc=" + cpuUsedPerc
				+ ", freeMem=" + freeMem + ", jvmFreeMem=" + jvmFreeMem
				+ ", queueLimit=" + queueLimit + ", queueNum=" + queueNum
				+ ", expectedDelay=" + expectedDelay
				+ ", preferredProgramTypeList=" + preferredProgramTypeList
				+ ", preferredProgramIdList=" + preferredProgramIdList
				+ ", grade=" + grade + ", updatedTime=" + updatedTime + "]";
	}
	
}
