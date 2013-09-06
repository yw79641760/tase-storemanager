/**
 * 
 */
package com.softsec.tase.store.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softsec.tase.common.domain.schedule.Node;
import com.softsec.tase.common.rpc.domain.node.NodeInfo;
import com.softsec.tase.common.rpc.domain.node.NodePayload;
import com.softsec.tase.common.rpc.domain.node.NodeType;
import com.softsec.tase.store.dao.NodeDao;
import com.softsec.tase.store.exception.DbUtilsException;
import com.softsec.tase.store.util.db.SQLMapperFactory;
import com.softsec.tase.store.util.db.SessionUtils;

/**
 * Node存储服务类
 * @author yanwei
 * @date 2013-1-7 下午2:51:42
 * 
 */
public class NodeStorageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(NodeStorageService.class);
	
	/**
	 * register new master
	 * @param nodeId
	 * @return
	 * @throws DbUtilsException
	 */
	public int registerMaster(String nodeId) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			NodeDao nodeDao = new NodeDao(session);
			retValue = nodeDao.registerMaster(nodeId);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to register master : " + nodeId + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to register master : " + nodeId + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	
	/**
	 * get manager id by manager name
	 * @param nodeId
	 * @return
	 * @throws DbUtilsException
	 */
	public int getMasterId(String nodeId) throws DbUtilsException {
		int masterId = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			NodeDao nodeDao = new NodeDao(session);
			masterId = nodeDao.getMasterId(nodeId);
		} catch (NullPointerException npe) {
			
			// no such manager
			return masterId;
			
		} catch (Exception e) {
			LOGGER.error("Failed to get manager id : " + nodeId + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get manager id : " + nodeId + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return masterId;
	}
	
	/**
	 * get master identifier
	 * @param nodeId
	 * @return
	 * @throws DbUtilsException
	 */
	public int generateMasterIdentifier(String nodeId) throws DbUtilsException {
		int managerId = 0;
		if ((managerId = getMasterId(nodeId)) == 0) {
			registerMaster(nodeId);
			managerId = getMasterId(nodeId);
		}
		return managerId;
	}
	
	/**
	 * Node registration
	 * @param nodeInfo
	 */
	public int register(NodeInfo nodeInfo) throws DbUtilsException{
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			NodeDao nodeDao = new NodeDao(session);
			if (nodeDao.getNodeIdExistence(nodeInfo.getNodeId()) != null) {
				retValue = nodeDao.updateNodeInfo(nodeInfo);
			} else {
				retValue = nodeDao.register(nodeInfo);
			}
			session.commit();
		} catch (Exception e) {
			LOGGER.error("Failed to register node : " + nodeInfo.getNodeId() + " : " + e.getMessage(), e);
			SessionUtils.rollback(session);
			throw new DbUtilsException("Failed to register node : " + nodeInfo.getNodeId() + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}

	/**
	 * update node heart beat
	 * @param nodePayload
	 * @return
	 */
	public int updateHeartbeat(NodePayload nodePayload) throws DbUtilsException{
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			NodeDao nodeDao = new NodeDao(session);
			if (nodeDao.getNodeIdExistence(nodePayload.getNodeId()) != null) {
				retValue = nodeDao.updateNodePayload(nodePayload);
				session.commit();
			} else {
				retValue = -1;
				LOGGER.error("Node [ " + nodePayload.getNodeId() + " ] has not registered yet.");
				throw new DbUtilsException("Node [ " + nodePayload.getNodeId() + " ] has not registered yet.");
			}
		} catch (Exception e) {
			LOGGER.error("Failed to update node payload info : " + nodePayload.getNodeId() + " : " + e.getMessage(), e);
			SessionUtils.rollback(session);
			throw new DbUtilsException("Failed to update node payload info : " + nodePayload.getNodeId() + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	
	/**
	 * get nodes by node type
	 * @param nodeTypeList
	 * @return
	 * @throws DbUtilsException
	 */
	public List<Node> getNodesByNodeType(List<NodeType> nodeTypeList) throws DbUtilsException {
		List<Node> nodeList = null;
		List<Integer> nodeTypeValueList = new ArrayList<Integer>();
		
		if (nodeTypeList != null && nodeTypeList.size() != 0) {
			
			for (NodeType nodeType : nodeTypeList) {
				nodeTypeValueList.add(nodeType.getValue());
			}
			
			SqlSession session = null;
			try {
				session = SQLMapperFactory.openSession();
				NodeDao nodeDao = new NodeDao(session);
				nodeList = nodeDao.getNodesByNodeType(nodeTypeValueList);
			} catch (Exception e) {
				LOGGER.error("Failed to get nodes by node type : " + nodeTypeList + " : " + e.getMessage(), e);
				SessionUtils.rollback(session);
				throw new DbUtilsException("Failed to get nodes by node type : " + nodeTypeList + " : " + e.getMessage(), e);
			} finally {
				SessionUtils.close(session);
			}
		}
		return nodeList;
	}
	
	/**
	 * get all nodes
	 * @return
	 * @throws DbUtilsException
	 */
	public List<Node> getAllNodes() throws DbUtilsException {
		List<Node> nodeList = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			NodeDao nodeDao = new NodeDao(session);
			nodeList = nodeDao.getAllNodes();
		} catch (Exception e) {
			LOGGER.error("Failed to get all nodes : " + e.getMessage(), e);
			SessionUtils.rollback(session);
			throw new DbUtilsException("Failed to get all nodes : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return nodeList;
	}

	/**
	 * update node load by node id
	 * @param nodeId
	 * @return
	 * @throws DbUtilsException
	 */
	public int updateNodeQueueNum(String nodeId) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			NodeDao nodeDao = new NodeDao(session);
			retValue = nodeDao.updateNodeQueueNum(nodeId);
			session.commit();
		} catch (Exception e) {
			LOGGER.error("Failed to update node queue num : " + nodeId + " : " + e.getMessage(), e);
			SessionUtils.rollback(session);
			throw new DbUtilsException("Failed to update node queue num : " + nodeId + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
}
