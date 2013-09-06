/**
 * 
 */
package com.softsec.tase.store.mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.softsec.tase.common.domain.CodeRef;
import com.softsec.tase.common.domain.app.ResultData;
import com.softsec.tase.common.domain.app.XmlResultData;
import com.softsec.tase.common.domain.result.AndroidStaticResult;
import com.softsec.tase.common.domain.rule.AndroidStaticRule;
import com.softsec.tase.common.util.JsonUtils;
import com.softsec.tase.store.util.db.MongodbConnFactory;
import com.softsec.tase.store.util.db.MongodbUtils;

/**
 *@author yanwei
 *@time: 2012-11-6 上午10:09:23
 *@description 
 *
 */
/**
 *
 */
public class AndroidStaticResultService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AndroidStaticResultService.class);

	private static final DBCollection results = MongodbConnFactory.getCollection("android.static.result");
	
	private static MongodbService mongodbService = new MongodbService();
	
	/**
	 * save android static analysis result to db
	 * @param androidStaticResult
	 */
	public void insert(AndroidStaticResult androidStaticResult) {
		mongodbService.insertItem(results, MongodbUtils.beanToDBObj(androidStaticResult));
		LOGGER.info("Received android static result : " + androidStaticResult.getAppBrief().getMd5());
	}
	
	/**
	 * get single result by bundle checksum
	 * @param bundleChecksum
	 * @return
	 */
	public XmlResultData getByBundleChecksum(String bundleChecksum) {
		DBObject query = new BasicDBObject("MD5", bundleChecksum);
		return getSingleAndroidStaticResultByField(query);
	}
	
	public int updateBundleChecksumByBundleChecksum(String oldBundleChecksum, String newBundleChecksum) {
		DBObject query = new BasicDBObject("MD5", oldBundleChecksum);
		DBObject condition = new BasicDBObject().append("$set", new BasicDBObject("MD5", newBundleChecksum));
		return updateSingleAndroidStaticResultByField(query, condition).getN();
	}
	
	/**
	 * update android static result
	 * @param query
	 * @param object
	 * @return
	 */
	private static WriteResult updateSingleAndroidStaticResultByField(DBObject query, DBObject object) {
		return mongodbService.updateItem(results, query, object, true, true);
	}

	/**
	 * get single result by app id
	 * @param appId
	 * @return
	 */
	public AndroidStaticResult getByAppId (String appId) {
		DBObject query = new BasicDBObject("appBrief.appId", appId);
		return getSingleResultByField(query);
	}
	
	/**
	 * get result list by app name
	 * @param appName
	 * @return
	 */
	public List<AndroidStaticResult> getByAppName(String appName) {
		DBObject query = new BasicDBObject("appBrief.appName", appName);
		return getMultipleResultsByField(query);
	}
	
	/**
	 * get single result by mission id
	 * @param missionId
	 * @return
	 */
	public AndroidStaticResult getByMissionId(String missionId) {
		DBObject query = new BasicDBObject("appBrief.missionId", missionId);
		return getSingleResultByField(query);
	}
	
	/**
	 * get single result by app md5
	 * @param md5
	 * @return
	 */
	public AndroidStaticResult getByMd5(String md5) {
		DBObject query = new BasicDBObject("appBrief.md5", md5);
		return getSingleResultByField(query);
	}
	
	/**
	 * get result list by mission date and elapse time
	 * @param commitFromDate
	 * @param commitToDate
	 * @param finishFromDate
	 * @param finishToDate
	 * @param option
	 * 		0(do nothing), 1(==), 2(>), 3(>=), 4(<), 5(<=)
	 * @param elapseTime
	 * @return
	 */
	public List<AndroidStaticResult> getByCommitAndFinishTime(Date commitFromDate, 
																	 Date commitToDate, 
																	 Date finishFromDate, 
																	 Date finishToDate,
																	 int option, 
																	 long elapseTime) {
		QueryBuilder query = new QueryBuilder();
		if (commitFromDate != null || commitToDate != null) {
			query.put("appBrief.commitTime");
			if (commitFromDate != null) {
				query.greaterThanEquals(MongodbUtils.beanToDBObj(commitFromDate));
			}
			if (commitToDate != null) {
				query.lessThanEquals(MongodbUtils.beanToDBObj(commitToDate));
			}
		}
		if (finishFromDate != null || finishToDate != null) {
			query.put("appBrief.finishTime");
			if (finishFromDate != null) {
				query.greaterThanEquals(MongodbUtils.beanToDBObj(finishFromDate));
			}
			if (finishToDate != null) {
				query.lessThanEquals(MongodbUtils.beanToDBObj(finishToDate));
			}
		}
		if (option != 0) {
			query.put("appBrief.elapseTime");
			if (option == 1) {
				query.is(MongodbUtils.beanToDBObj(String.valueOf(elapseTime)));
			} else if (option == 2) {
				query.greaterThan(MongodbUtils.beanToDBObj(String.valueOf(elapseTime)));
			} else if (option == 3) {
				query.greaterThanEquals(MongodbUtils.beanToDBObj(String.valueOf(elapseTime)));
			} else if (option == 4) {
				query.lessThan(MongodbUtils.beanToDBObj(String.valueOf(elapseTime)));
			} else if (option == 5) {
				query.lessThanEquals(MongodbUtils.beanToDBObj(String.valueOf(elapseTime)));
			}
		}
		return getMultipleResultsByField(query.get());
	}
	
	/**
	 * get result list by rule id
	 * @param ruleId
	 * @return
	 */
	public List<AndroidStaticResult> getByRuleId(String ruleId) {
		DBObject query = new BasicDBObject("resultDetailList.ruleInfo.ruleBrief.ruleId", ruleId);
		return getMultipleResultsByField(query);
	}
	
	/**
	 * get result list by rule name regex
	 * @param ruleNameRegex
	 * @return
	 */
	public List<AndroidStaticResult> getByRuleName(String ruleNameRegex) {
		Pattern pattern = Pattern.compile(ruleNameRegex, Pattern.CASE_INSENSITIVE);
		DBObject query = new BasicDBObject("resultDetailList.ruleInfo.ruleBrief.ruleName", pattern);
		return getMultipleResultsByField(query);
	}
	
	/**
	 * get result list by rule desc regex
	 * @param ruleDescRegex
	 * @return
	 */
	public List<AndroidStaticResult> getByRuleDesc(String ruleDescRegex) {
		Pattern pattern = Pattern.compile(ruleDescRegex, Pattern.CASE_INSENSITIVE);
		DBObject query = new BasicDBObject("resultDetailList.ruleInfo.ruleBrief.ruleDesc", pattern);
		return getMultipleResultsByField(query);
	}
	
	/**
	 * get rule list by rule type
	 * @param ruleType
	 * @return
	 */
	public List<AndroidStaticResult> getByRuleType (String ruleType) {
		DBObject query = new BasicDBObject("resultDetailList.ruleInfo.ruleBrief.ruleType", ruleType);
		return getMultipleResultsByField(query);
	}
	
	/**
	 * get result list by method name
	 * @param methodName
	 * @return
	 */
	public List<AndroidStaticResult> getByMethodName(String methodName) {
		DBObject query = new BasicDBObject("resultDetailList.ruleInfo.codeRef.methodName", methodName);
		return getMultipleResultsByField(query);
	}
	
	/**
	 * get result list by parameter regex
	 * @param paramRegex
	 * @return
	 */
	public List<AndroidStaticResult> getByParameter(String paramRegex) {
		Pattern pattern = Pattern.compile(paramRegex, Pattern.CASE_INSENSITIVE);
		DBObject query = new BasicDBObject("resultDetailList.ruleInfo.codeRef.paramList.paramRegex", pattern);
		return getMultipleResultsByField(query);
	}
	
	/**
	 * get result list by field or regex
	 * @param index
	 * @param field
	 * @return
	 */
	private static List<AndroidStaticResult> getMultipleResultsByField(DBObject query) {
		List<AndroidStaticResult> resultList = new ArrayList<AndroidStaticResult>();
		DBCursor cursor = mongodbService.getMultipleItems(results, query);
		while (cursor.hasNext()) {
			Map<String, Object> classMap = new HashMap<String, Object>();
			classMap.put("appBrief", AndroidStaticResult.AppBrief.class);
			classMap.put("resultDetail", AndroidStaticResult.ResultDetail.class);
			classMap.put("ruleInfo", AndroidStaticRule.class);
			classMap.put("ruleBrief", AndroidStaticRule.RuleBrief.class);
			classMap.put("codeRef", CodeRef.class);
			classMap.put("param", CodeRef.Param.class);
			resultList.add((AndroidStaticResult)(JsonUtils.deserialize(JSON.serialize(cursor.next()), AndroidStaticResult.class, classMap)));
		}
		cursor.close();
		return resultList;
	}
	
	/**
	 * get single result by field or regex
	 * @param index
	 * @param field
	 * @return
	 */
	private static AndroidStaticResult getSingleResultByField(DBObject query) {
		DBObject dbObj = mongodbService.getSingleItem(results, query);
		if (dbObj == null) {
			return null;
		} else {
			Map<String, Object> classMap = new HashMap<String, Object>();
			classMap.put("appBrief", AndroidStaticResult.AppBrief.class);
			classMap.put("resultDetail", AndroidStaticResult.ResultDetail.class);
			classMap.put("ruleInfo", AndroidStaticRule.class);
			classMap.put("ruleBrief", AndroidStaticRule.RuleBrief.class);
			classMap.put("codeRef", CodeRef.class);
			classMap.put("param", CodeRef.Param.class);
			return (AndroidStaticResult)(JsonUtils.deserialize(JSON.serialize(dbObj), AndroidStaticResult.class, classMap));
		}
	}
	
	private static XmlResultData getSingleAndroidStaticResultByField(DBObject query) {
		DBObject dbObj = mongodbService.getSingleItem(results, query);
		if (dbObj == null) {
			return null;
		} else {
			Map<String, Object> classMap = new HashMap<String, Object>();
			classMap.put("resultData", ResultData.class);
			return (XmlResultData) (JsonUtils.deserialize(JSON.serialize(dbObj), XmlResultData.class, classMap));
		}
	}
}
