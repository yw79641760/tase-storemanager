/**
 * 
 */
package com.softsec.tase.store.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.softsec.tase.common.domain.auth.User;

/**
 * UserDao.java
 * @author yanwei
 * @date 2013-3-4 下午3:32:59
 * @description
 */
public class UserDao {
	
	private static final String NAMESPACE = "com.softsec.tase.store.dao.User";
	
	private SqlSession session;
	
	public UserDao(SqlSession session) {
		this.session = session;
	}
	
	/**
	 * get user id by username
	 * @param username
	 * @return
	 */
	public int getUserIdByUsername(String username) {
		return (Integer) session.selectOne(NAMESPACE + ".selectUserIdByUsername", username);
	}

	public int addUser(String username, String password) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("username", username);
		condition.put("password", password);
		return session.insert(NAMESPACE + ".insertUser", condition);
	}

	/**
	 * update user app count by user id
	 * @param userId
	 * @param userAppCount
	 * @return
	 */
	public int updateUserAppCount(int userId, int userAppCount) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("userId", userId);
		condition.put("userAppCount", userAppCount);
		return session.update(NAMESPACE + ".updateUserAppCountByUserId", condition);
	}

	/**
	 * get all users
	 * @return
	 */
	public List<User> getUserList() {
		return session.selectList(NAMESPACE + ".selectAllUsers");
	}
}
