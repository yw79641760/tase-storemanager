/**
 * 
 */
package com.softsec.tase.store.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.softsec.tase.common.domain.schedule.Node;
import com.softsec.tase.common.rpc.domain.node.NodeInfo;
import com.softsec.tase.common.rpc.domain.node.NodePayload;

/**
 * node data access object
 * @author yanwei
 * @date 2013-1-7 下午2:57:32
 * 
 */
public class NodeDao {

	private static final String NAMESPACE = "com.softsec.tase.store.dao.Node";
	
	private SqlSession session = null;
	
	public NodeDao(SqlSession session) {
		this.session = session;
	}
	/**
	 * register master node
	 * @param nodeId
	 * @return
	 */
	public int registerMaster(String nodeId) {
		return session.insert(NAMESPACE + ".insertMasterNode", nodeId);
	}
	/**
	 * get master id by master node id
	 * @param nodeId
	 * @return
	 */
	public int getMasterId(String nodeId) {
		return (Integer)session.selectOne(NAMESPACE + ".selectMasterIdByNodeId", nodeId);
	}
	/**
	 * is node registered
	 * @param nodeId
	 * @return
	 */
	public String getNodeIdExistence(String nodeId) {
		return (String) session.selectOne(NAMESPACE + ".selectNodeIdByNodeId", nodeId);
	}

	/**
	 * node registration
	 * @param nodeInfo
	 * @return
	 */
	public int register(NodeInfo nodeInfo) {
		int retValue = 0;
		retValue = session.insert(NAMESPACE + ".insertHardware", nodeInfo);
		retValue += session.insert(NAMESPACE + ".insertSoftware", nodeInfo);
		retValue += session.insert(NAMESPACE + ".insertNetwork", nodeInfo);
		retValue += session.insert(NAMESPACE + ".insertProcess", nodeInfo);
		retValue += session.insert(NAMESPACE + ".insertPayload", nodeInfo);
		retValue += session.insert(NAMESPACE + ".insertAggregation", nodeInfo);
		return retValue;
	}
	
	/**
	 * update node
	 * @param nodeInfo
	 * @return
	 */
	public int updateNodeInfo(NodeInfo nodeInfo) {
		int retValue = 0;
		retValue = session.update(NAMESPACE + ".updateHardware", nodeInfo);
		retValue += session.update(NAMESPACE + ".updateSoftware", nodeInfo);
		retValue += session.update(NAMESPACE + ".updateNetwork", nodeInfo);
		retValue += session.update(NAMESPACE + ".updateProcess", nodeInfo);
		retValue += session.update(NAMESPACE + ".updatePayload", nodeInfo);
		retValue += session.update(NAMESPACE + ".updateAggregation", nodeInfo);
		return retValue;
	}
	
	/**
	 * update node heart beat
	 * @param nodePayload
	 * @return
	 */
	public int updateNodePayload(NodePayload nodePayload) {
		return session.update(NAMESPACE, nodePayload);
	}

	/**
	 * get nodes by node type
	 * @param nodeType
	 * @return
	 */
	public List<Node> getNodesByNodeType(List<Integer> nodeTypeList) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("nodeTypeList", nodeTypeList);
		return session.selectList(NAMESPACE + ".selectNodesByNodeType", condition);
	}
	
	/**	
	 * get all nodes
	 * @return
	 */
	public List<Node> getAllNodes() {
		return session.selectList(NAMESPACE + ".selectAllNodes");
	}

	/**
	 * update node load by node id
	 * @param nodeId
	 * @return
	 */
	public int updateNodeQueueNum(String nodeId) {
		return session.update(NAMESPACE + ".updateNodeQueueNum", nodeId);
	}

}
