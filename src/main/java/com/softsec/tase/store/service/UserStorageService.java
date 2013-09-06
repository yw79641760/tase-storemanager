/**
 * 
 */
package com.softsec.tase.store.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softsec.tase.common.domain.auth.User;
import com.softsec.tase.store.dao.UserDao;
import com.softsec.tase.store.exception.DbUtilsException;
import com.softsec.tase.store.util.db.SQLMapperFactory;
import com.softsec.tase.store.util.db.SessionUtils;

/**
 * user management
 * @author yanwei
 * @date 2013-1-11 上午11:30:10
 * 
 */
public class UserStorageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserStorageService.class);
	
	/**
	 * get user id by specified username
	 * @param username
	 * @return
	 * @throws NullPointerException
	 * @throws DbUtilsException
	 */
	public int getUserId(String username) throws NullPointerException, DbUtilsException{
		int userId = 0;
		SqlSession session = null;
		UserDao userDao = null;
		try {
			session = SQLMapperFactory.openSession();
			userDao = new UserDao(session);
			userId = userDao.getUserIdByUsername(username);
		} catch (NullPointerException npe) {
			
			// no such user, just return
			return userId;
		} catch (Exception e) {
			LOGGER.error("Failed to get user id : " + username + " : " + e.getMessage());
			throw new DbUtilsException("Failed to get user id : " + username + " : " + e.getMessage());
		} finally {
			SessionUtils.close(session);
		}
		return userId;
	}
	
	/**
	 * add new user
	 * @param username
	 * @param password
	 * @return
	 * @throws DbUtilsException
	 */
	public int addUser(String username, String password) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		UserDao userDao = null;
		try {
			session = SQLMapperFactory.openSession();
			userDao = new UserDao(session);
			retValue = userDao.addUser(username, password);
			session.commit();
		} catch (Exception e) {
			LOGGER.error("Failed to add : " + username + " into the database : " + e.getMessage());
			SessionUtils.rollback(session);
			throw new DbUtilsException("Failed to add : " + username + " into the database : " + e.getMessage());
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}
	
	/**
	 * generate new user id
	 * @param username
	 * @param password
	 * @return
	 * @throws DbUtilsException
	 */
	public int generateUserId(String username, String password) throws DbUtilsException {
		int userId = 0; ;
		if ((userId = getUserId(username)) == 0) {
			addUser(username, password);
			userId = getUserId(username);
		}
		return userId;
	}

	/**
	 * update user app count
	 * @param userId
	 * @param userAppCount
	 */
	public int updateUserAppCount(int userId, int userAppCount) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			UserDao userDao = new UserDao(session);
			retValue = userDao.updateUserAppCount(userId, userAppCount);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to update user count : " + userId + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to update user count : " + userId + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}

	/**
	 * get user list
	 * @return
	 */
	public List<User> getUserList() throws DbUtilsException {
		List<User> userList = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			UserDao userDao = new UserDao(session);
			userList = userDao.getUserList();
		} catch (Exception e) {
			LOGGER.error("Failed to get user list : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get user list : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return userList;
	}
}
