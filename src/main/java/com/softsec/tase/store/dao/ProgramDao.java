/**
 * 
 */
package com.softsec.tase.store.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.softsec.tase.common.domain.auth.Program;
import com.softsec.tase.store.domain.ProgramItem;
import com.softsec.tase.store.domain.ProgramTypeItem;

/**
 * program data access object
 * @author yanwei
 * @date 2012-12-31 下午9:16:15
 * 
 */
public class ProgramDao {
	
	private static final String NAMESPACE = "com.softsec.tase.store.dao.Program";
	
	private SqlSession session;
	
	/**
	 * 
	 */
	public ProgramDao(SqlSession session) {
		this.session = session;
	}
	/**
	 * get program id by script and executable md5
	 * @param scriptMd5
	 * @param executableMd5
	 * @return
	 */
	public long getProgramIdByScriptMd5OrExecutableMd5(final String scriptMd5, final String executableMd5) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("scriptMd5", scriptMd5);
		condition.put("executableMd5", executableMd5);
		return (Long) session.selectOne(NAMESPACE + ".selectProgramIdByScriptMd5OrExecutableMd5", condition);
	}
	
	/**
	 * get program count by program type
	 * @param programType
	 * @return
	 */
	public int getCountByProgramType(int programType) {
		return (Integer) session.selectOne(NAMESPACE + ".selectCountByProgramType", programType);
	}
	
	/**
	 * add new program type
	 * @param programType
	 * @return
	 */
	public int addProgramType(int programType) {
		return session.insert(NAMESPACE + ".insertProgramType", programType);
	}
	/**
	 * update program count by program type
	 * @param programIdPrefix
	 * @param programCount
	 * @return
	 */
	public int updateProgramCount(int programIdPrefix, int programCount) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("programIdPrefix", programIdPrefix);
		condition.put("programCount", programCount);
		return session.update(NAMESPACE + ".updateProgramCount", condition);
	}
	/**
	 * program registration
	 * @param program
	 */
	public int register(Program program) {
		
		int retValue;
		
		//insert into program_basic
		retValue = session.insert(NAMESPACE + ".insertBasic", program);
		
//		//insert into program_env
//		Map<String, Object> condition = new HashMap<String, Object>();
//		for (Entry<String, String> entry : program.getEnvVariableMap().entrySet()) {
//			condition.put("programId", program.getProgramId());
//			condition.put("envName", entry.getKey());
//			condition.put("envValue", entry.getValue());
//			retValue += session.insert(NAMESPACE + ".insertEnv", condition);
//			condition.clear();
//		}
		
		//insert into program_storage
		retValue += session.insert(NAMESPACE + ".insertStorage", program);
		
		//update program count
//		retValue += session.update(NAMESPACE + ".updateProgramTypeCount", program.getProgramType());
		
		return retValue;
	}
	
	/**
	 * get programs by program type
	 * @param programType
	 * @return
	 */
	public List<Program> getProgramsByProgramType(int programType) {
		return session.selectList(NAMESPACE + ".selectProgramsByProgramType", programType);
	}

	/**
	 * get latest program by program type
	 * @param programType
	 * @return
	 */
	public long getLatestProgramId(int programType) {
		return (Long) session.selectOne(NAMESPACE + ".selectLatestProgramIdByProgramType", programType);
	}
	/**
	 * get all program type and count
	 * @return
	 */
	public List<ProgramTypeItem> getProgramTypeList() {
		return session.selectList(NAMESPACE + ".selectAllProgramType");
	}
	/**
	 * get program item list 
	 * get all program item
	 * @return
	 */
	public List<ProgramItem> getProgramItemList() {
		return session.selectList(NAMESPACE + ".selectAllProgramItem");
	}
	/**
	 * get program item by program id
	 * @param programId
	 * @return programItem
	 */
	public ProgramItem getProgramItemByProgramId(long programId) {
		return (ProgramItem) session.selectOne(NAMESPACE + ".selectProgramItemByProgramId", programId);
	}
}
