/**
 * 
 */
package com.softsec.tase.store.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softsec.tase.common.domain.auth.Program;
import com.softsec.tase.store.dao.ProgramDao;
import com.softsec.tase.store.domain.ProgramItem;
import com.softsec.tase.store.domain.ProgramTypeItem;
import com.softsec.tase.store.exception.DbUtilsException;
import com.softsec.tase.store.util.db.SQLMapperFactory;
import com.softsec.tase.store.util.db.SessionUtils;

/**
 * Program存储服务类
 * @author yanwei
 * @date 2013-1-1 下午1:26:09
 * 
 */
public class ProgramStorageService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProgramStorageService.class);
	
	/**
	 * 
	 */
	public ProgramStorageService() {
	}
	
//	/**
//	 * register and save program
//	 * @param programme
//	 * @return programId
//	 * @throws FileUtilsException
//	 * @throws FtpUtilsException
//	 * @throws DbUtilsException
//	 */
//	public long register(Programme programme)  throws FileUtilsException, FtpUtilsException, DbUtilsException {
//
//		// parameter validation
//		if (!App.isAppTypeMember(programme.getAppType())
//				|| !Job.isJobLifecycleMember(programme.getJobLifecycle())
//				|| !Job.isJobPhaseMember(programme.getJobPhase())) {
//			return 0L;
//		}
//		
//		// check for duplication
//		long programDuplicatedId = checkForDuplication(programme.getScriptMd5(), programme.getExecutableMd5());
//		if (programDuplicatedId != 0L) {
//			return programDuplicatedId;
//		}
//		
//		// generate new program id
//		long programId = ProgramMapper.getInstance().generateProgramId(programme.getAppType(), 
//				programme.getJobLifecycle(), programme.getJobPhase());
//		
//		//save file using ftp
//		File scriptFile = FileUtils.saveByteArrayToFile(programme.getScriptName(), programme.getScript());
//		File executableFile = FileUtils.saveByteArrayToFile(programme.getExecutableName(), programme.getExecutable());
//		String ftpHost = FtpConnFactory.getRandomFtpHost();
//		String programPath = ftpHost + PROGRAM_REPO + "/" + String.valueOf(programId) + "/";
//		FTPClient ftpClient = FtpConnFactory.connect(ftpHost);
//		if (ftpClient != null) {
//			if (FtpUtils.upload(ftpClient, programme.getScriptName(), programPath)) {
//				FileUtils.deleteFile(scriptFile);
//			}
//			if (FtpUtils.upload(ftpClient, programme.getExecutableName(), programPath)) {
//				FileUtils.deleteFile(executableFile);
//			}
//			FtpUtils.disconnect(ftpClient);
//		}
//		String scriptPath = programPath + programme.getScriptName();
//		String executablePath = programPath + programme.getExecutableName();
//		
//		//save database record
//		Program program = new Program();
//		program.setProgramId(programId);
//		program.setProgramName(programme.getProgrammeName());
//		program.setNodeType(programme.getNodeType());
//		program.setScriptName(programme.getScriptName());
//		program.setScriptPath(scriptPath);
//		program.setScriptMd5(programme.getScriptMd5());
//		program.setExecutableName(programme.getExecutableName());
//		program.setExecutablePath(executablePath);
//		program.setExecutableMd5(programme.getExecutableMd5());
//		program.setEnvVariables(programme.getEnvVariables());
//		program.setRegisterTime(System.currentTimeMillis());
//		if(save(program) != 0){
//			LOGGER.info("Failed to save program info correctly.");
//			throw new DbUtilsException("Failed to save program info correctly.");
//		}
//		
//		return programId;
//	}
	
//	/**
//	 * list programs by program type
//	 * @param programTypeList
//	 * @return
//	 * @throws DbUtilsException
//	 */
//	public List<ProgramVisual> list(List<ProgramType> programTypeList) throws DbUtilsException {
//		SqlSession session = null;
//		List<ProgramVisual> programVisualList = null;
//		List<Integer> programTypeValueList = new ArrayList<Integer>();
//		try {
//			session = SQLMapperFactory.openSession();
//			ProgramDao programDao = new ProgramDao(session);
//			if (programTypeList != null && programTypeList.size() != 0) {
//				for (ProgramType programType : programTypeList) {
//					programTypeValueList.add(programType.getValue());
//				}
//				programVisualList = programDao.listProgramVisualsByProgramType(programTypeValueList);
//			}
//		} catch (Exception e) {
//			SessionUtils.rollback(session);
//			throw new DbUtilsException("Failed to query all program info : "+ e.getMessage());
//		} finally {
//			SessionUtils.close(session);
//		}
//		return programVisualList;
//	}
	
	/**
	 * check for program duplication
	 * @param scriptMd5
	 * @param executableMd5
	 * @return programId
	 * @throws DbUtilsException
	 */
	public long checkForDuplication(String scriptMd5, String executableMd5) throws DbUtilsException{
		SqlSession session = null;
		long programId = 0L;
		try {
			session = SQLMapperFactory.openSession();
			ProgramDao programDao = new ProgramDao(session);
			programId = programDao.getProgramIdByScriptMd5OrExecutableMd5(scriptMd5, executableMd5);
		} catch (NullPointerException npe) {
		
			// no such program found, just return 0
			return programId;
			
		} catch (Exception e) {
			SessionUtils.rollback(session);
			throw new DbUtilsException("Failed to query script [ " + scriptMd5 + " ] or executable md5 [ " + executableMd5 + " ].", e);
		} finally {
			SessionUtils.close(session);
		}
		return programId;
	}
	
//	/**
//	 * add new program type
//	 * @param programType
//	 * @return
//	 * @throws DbUtilsException
//	 */
//	private int addProgramType(ProgramType programType) throws DbUtilsException {
//		int retValue = 0;
//		SqlSession session = null;
//		try {
//			session = SQLMapperFactory.openSession();
//			ProgramDao programDao = new ProgramDao(session);
//			retValue = programDao.addProgramType(programType.getValue());
//			session.commit();
//		} catch (Exception e) {
//			LOGGER.error("Failed to add program type : " + programType.name() + " : " + e.getMessage(), e);
//			SessionUtils.rollback(session);
//			throw new DbUtilsException("Failed to add program type : " + programType.name() + " : " + e.getMessage(), e);
//		} finally {
//			SessionUtils.close(session);
//		}
//		return retValue;
//	}
	
//	/**
//	 * get specific program type count
//	 * @param programType
//	 * @return
//	 * @throws NullPointerException
//	 * @throws DbUtilsException
//	 */
//	private int getCountByProgramType(ProgramType programType) throws NullPointerException, DbUtilsException {
//		int count = 0;
//		SqlSession session = null;
//		try {
//			session = SQLMapperFactory.openSession();
//			ProgramDao programDao = new ProgramDao(session);
//			count = programDao.getCountByProgramType(programType.getValue());
//		} catch (NullPointerException npe) {
//			
//			// no such program type found
//			throw new NullPointerException("No such program type found : " + programType.name() + " : " + npe.getMessage());
//			
//		} catch (Exception e) {
//			LOGGER.error("Failed to get count by program type : " + programType.name() + " : " + e.getMessage(), e);
//		} finally {
//			SessionUtils.close(session);
//		}
//		return count;
//	}
	
//	/**
//	 * generate new program id for specific program type
//	 * @param programType
//	 * @return
//	 * @throws DbUtilsException
//	 */
//	private long generateProgramId(ProgramType programType) throws DbUtilsException {
//		StringBuilder sbuilder = new StringBuilder();
//		sbuilder.append(String.valueOf(programType.getValue()));
//		
//		int programCount = 0;
//		try {
//			programCount = getCountByProgramType(programType);
//		} catch (NullPointerException npe) {
//			
//			// Null Pointer Exception means new program type
//			addProgramType(programType);
//		} 
//		sbuilder.append(new DecimalFormat("0000").format(programCount + 1));
//		return Long.parseLong(sbuilder.toString());
//	}
	
	/**
	 * save program info
	 * @param program
	 * @return succeed or not
	 * @throws DbUtilsException
	 */
	@SuppressWarnings("unused")
	private int save(Program program) throws DbUtilsException {
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			ProgramDao programDao = new ProgramDao(session);
			retValue = programDao.register(program);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			throw new DbUtilsException("Failed to save program info into database : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}

	/**
	 * get latest program by program type
	 * @param programType
	 * @return latestProgramId
	 */
	public long getLastestProgramId(int programType) throws DbUtilsException{
		SqlSession session = null;
		long latestProgramId = 0L;
		try {
			session = SQLMapperFactory.openSession();
			ProgramDao programDao = new ProgramDao(session);
			latestProgramId = programDao.getLatestProgramId(programType);
		} catch (Exception e) {
			SessionUtils.rollback(session);
			throw new DbUtilsException("Failed to get latest program id : " + e.getMessage());
		} finally {
			SessionUtils.close(session);
		}
		return latestProgramId;
	}

	/**
	 * add new program type
	 * @param programIdPrefix
	 * @return succeed or not
	 */
	public int addProgramType(int programIdPrefix) throws DbUtilsException{
		SqlSession session = null;
		int retValue = 0;
		try {
			session = SQLMapperFactory.openSession();
			ProgramDao programDao = new ProgramDao(session);
			retValue = programDao.addProgramType(programIdPrefix);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			throw new DbUtilsException("Failed to add new program type : " + programIdPrefix + " : " + e.getMessage());
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
		
	}

	/**
	 * update program count by program type
	 * @param programIdPrefix
	 * @param programCount
	 */
	public int updateProgramCount(int programIdPrefix, int programCount) throws DbUtilsException{
		SqlSession session = null;
		int retValue = 0;
		try {
			session = SQLMapperFactory.openSession();
			ProgramDao programDao = new ProgramDao(session);
			retValue = programDao.updateProgramCount(programIdPrefix, programCount);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			LOGGER.error("Failed to update program count : " + programIdPrefix + " : " 
					+ programCount + " : " + e.getMessage());
			throw new DbUtilsException("Failed to update program count : " + programIdPrefix + " : " 
					+ programCount + " : " + e.getMessage());
		} finally {
			SessionUtils.close(session);
		}
		return retValue;
	}

	/**
	 * get program type list
	 * @return programTypeItemList
	 */
	public List<ProgramTypeItem> getProgramTypeList() throws DbUtilsException {
		List<ProgramTypeItem> programTypeItemList = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			ProgramDao programDao = new ProgramDao(session);
			programTypeItemList = programDao.getProgramTypeList();
		} catch (Exception e) {
			LOGGER.error("Failed to get program type list : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get program type list : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return programTypeItemList;
	}

	/**
	 * get program item list
	 * @return programItemList
	 */
	public List<ProgramItem> getProgramItemList() throws DbUtilsException {
		List<ProgramItem> programItemList = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			ProgramDao programDao = new ProgramDao(session);
			programItemList = programDao.getProgramItemList();
		} catch (Exception e) {
			LOGGER.error("Failed to get program item list : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get program item list : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return programItemList;
	}

	/**
	 * @param programId
	 * @return
	 */
	public ProgramItem getProgramItem(long programId) throws DbUtilsException {
		ProgramItem programItem = null;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			ProgramDao programDao = new ProgramDao(session);
			programItem = programDao.getProgramItemByProgramId(programId);
		} catch (Exception e) {
			LOGGER.error("Failed to get program item : " + programId + " : " + e.getMessage(), e);
			throw new DbUtilsException("Failed to get program item : " + programId + " : " + e.getMessage(), e);
		} finally {
			SessionUtils.close(session);
		}
		return programItem;
	}
	
//	/**
//	 * get context by program id
//	 * @param programId
//	 * @return
//	 * @throws DbUtilsException
//	 */
//	public Context getContext(long programId) throws DbUtilsException {
//		Context context = null;
//		SqlSession session = null;
//		try {
//			session = SQLMapperFactory.openSession();
//			ProgramDao programDao = new ProgramDao(session);
//			context = programDao.getContext(programId);
//		} catch (Exception e) {
//			LOGGER.error("Failed to get context from program : " + programId + " : " + e.getMessage(), e);
//			SessionUtils.rollback(session);
//			throw new DbUtilsException("Failed to get context from program : " + programId + " : " + e.getMessage(), e);
//		} finally {
//			SessionUtils.close(session);
//		}
//		return context;
//	}
}
