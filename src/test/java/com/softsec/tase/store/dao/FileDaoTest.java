/**
 * 
 */
package com.softsec.tase.store.dao;

import junit.framework.TestCase;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.softsec.tase.common.dto.app.FileMetadata;
import com.softsec.tase.store.util.db.SQLMapperFactory;
import com.softsec.tase.store.util.db.SessionUtils;

/**
 * FileDaoTest
 * <p> </p>
 * @author yanwei
 * @since 2013-6-3 下午2:31:58
 * @version
 */
public class FileDaoTest extends TestCase {

	@Test
	public void testSaveFileMetadata() {
		Long appId = 1102000100303092L;
		String bundleChecksum = "12e47ec77f04d22a9d0249efef353c31";
		FileMetadata fileMetadata = new FileMetadata();
		fileMetadata.setFileChecksum("12e47ec77f04d22a9d0e49bfe3c53c31");
		fileMetadata.setFileName("test");
		fileMetadata.setFilePath("test");
		fileMetadata.setLength(System.currentTimeMillis());
		fileMetadata.setCreatedTime(System.currentTimeMillis());
		fileMetadata.setModifiedTime(System.currentTimeMillis());
		fileMetadata.setExtension("apk");
		int retValue = 0;
		SqlSession session = null;
		try {
			session = SQLMapperFactory.openSession();
			FileDao fileDao = new FileDao(session);
			retValue = fileDao.saveFileMetadata(appId, bundleChecksum, fileMetadata);
			session.commit();
		} catch (Exception e) {
			SessionUtils.rollback(session);
			e.printStackTrace();
		} finally {
			SessionUtils.close(session);
		}
		System.out.println(retValue);
	}
}
