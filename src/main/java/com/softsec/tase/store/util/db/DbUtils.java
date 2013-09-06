/**
 * 
 */
package com.softsec.tase.store.util.db;

import com.softsec.tase.common.rpc.domain.app.AppType;
import com.softsec.tase.common.rpc.domain.app.OriginType;
import com.softsec.tase.common.rpc.domain.job.JobLifecycle;
import com.softsec.tase.common.util.domain.AppUtils;
import com.softsec.tase.common.util.domain.JobUtils;

/**
 * DbTableUtils
 * <p> </p>
 * @author yanwei
 * @since 2013-5-22 下午7:45:43
 * @version
 */
public class DbUtils {
	
	/**
	 * get database table name prefix according to appId
	 * @param appId
	 * @return
	 */
	public static String getAppTablePrefix(Long appId) {
		AppType appType = AppUtils.getAppTypeByAppId(appId);
		OriginType originType = AppUtils.getOriginTypeByAppId(appId);
		return getAppTablePrefix(appType, originType);
	}
	
	/**
	 * get database table name prefix according to appType and originType
	 * @param appType
	 * @param originType
	 * @return
	 */
	public static String getAppTablePrefix(AppType appType, OriginType originType) {
		StringBuilder sbuilder = new StringBuilder("tase_app_");
		switch(appType.getValue()) {
		// AppType.APK
		case(1) :
			sbuilder.append("android_");
			break;
		// AppType.IPA
		case(2) :
			sbuilder.append("ios_");
			break;
		}
		switch(originType.getValue()) {
		case(0) :
			sbuilder.append("official_");
			break;
		case(1) :
			sbuilder.append("unofficial_");
			break;
		case(2)	:
			sbuilder.append("user_");
		case(3)	:
			sbuilder.append("device_");
		}
		return sbuilder.toString();
	}
	
	/**
	 * get file table prefix by app id
	 * @param appId
	 * @return
	 */
	public static String getFileTablePrefix(Long appId) {
		AppType appType = AppUtils.getAppTypeByAppId(appId);
		return getFileTablePrefix(appType);
	}
	
	/**
	 * get file table prefix by app type
	 * @param appType
	 * @return
	 */
	public static String getFileTablePrefix(AppType appType) {
		StringBuilder sbuilder = new StringBuilder("tase_file_");
		switch(appType.getValue()) {
		// AppType.APK
		case(1) :
			sbuilder.append("android_");
			break;
		case(2)	:
			sbuilder.append("ios_");
			break;
		}
		return sbuilder.toString();
	}
	
	/**
	 * get bundle table prefix by app id
	 * @param appId
	 * @return
	 */
	public static String getBundleTablePrefix(Long appId) {
		AppType appType = AppUtils.getAppTypeByAppId(appId);
		return getBundleTablePrefix(appType);
	}
	
	/**
	 * get bundle table prefix by app type
	 * @param appType
	 * @return
	 */
	public static String getBundleTablePrefix(AppType appType) {
		StringBuilder sbuilder = new StringBuilder("tase_bundle_");
		switch(appType.getValue()) {
		// AppType.APK
		case(1) :
			sbuilder.append("android_");
			break;
		case(2)	:
			sbuilder.append("ios_");
			break;
		}
		return sbuilder.toString();
	}
	
	/**
	 * get job table prefix by job id
	 * @param jobId
	 * @return
	 */
	public static String getJobTablePrefix(Long jobId) {
		JobLifecycle jobLifecycle = JobUtils.getJobLifecycle(jobId);
		return "tase_" + jobLifecycle.toString().toLowerCase();
	}

}
