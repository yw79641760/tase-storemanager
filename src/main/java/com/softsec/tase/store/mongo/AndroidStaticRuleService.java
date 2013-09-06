/**
 * 
 */
package com.softsec.tase.store.mongo;

import java.util.ArrayList;
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
import com.mongodb.util.JSON;
import com.softsec.tase.common.domain.CodeRef;
import com.softsec.tase.common.domain.rule.AndroidStaticRule;
import com.softsec.tase.common.util.JsonUtils;
import com.softsec.tase.store.util.db.MongodbConnFactory;
import com.softsec.tase.store.util.db.MongodbUtils;

/**
 *@author yanwei
 *@time: 2012-11-6 上午10:01:14
 *@description 规则服务类
 *
 */
/**
 *
 */
public class AndroidStaticRuleService {

	private final static Logger LOGGER = LoggerFactory.getLogger(AndroidStaticRuleService.class);
	
	private final static DBCollection rules = MongodbConnFactory.getCollection("android.static.rule");
	
	private static MongodbService mongodbService = new MongodbService();
	
	/**
	 * save android static analysis rule to db
	 * @param androidStaticRule
	 */
	public void insert(AndroidStaticRule androidStaticRule) {
		mongodbService.insertItem(rules, MongodbUtils.beanToDBObj(androidStaticRule));
		LOGGER.info("Received android static rule : " + androidStaticRule.getRuleBrief().getRuleName());
	}
	
	/**
	 * get all rules
	 * @return
	 */
	public List<AndroidStaticRule> getAllRules() {
		DBObject query = new BasicDBObject();
		return getMultipleRulesByField(query);
	}
	
	/**
	 * get rule list by rule id
	 * @param ruleId
	 * @return
	 */
	public AndroidStaticRule getByRuleId(String ruleId) {
		DBObject query = new BasicDBObject("ruleBrief.ruleId", ruleId);
		return getSingleRuleByField(query);
	}
	
	/**
	 * get rule list by rule desc regex
	 * @param ruleDescRegex
	 * @return
	 */
	public List<AndroidStaticRule> getByRuleDesc(String ruleDescRegex) {
		Pattern pattern = Pattern.compile(ruleDescRegex, Pattern.CASE_INSENSITIVE);
		DBObject query = new BasicDBObject("ruleBrief.ruleDesc", pattern);
		return getMultipleRulesByField(query);
	}
	
	/**
	 * get rule list by rule name
	 * @param ruleName
	 * @return
	 */
	public AndroidStaticRule getByRuleName(String ruleName) {
		DBObject query = new BasicDBObject("ruleBrief.ruleName", ruleName);
		return getSingleRuleByField(query);
	}
	
	/**
	 * get rule list by risk level
	 * @param riskLevel
	 * @return
	 */
	public List<AndroidStaticRule> getByRiskLevel(int riskLevel) {
		DBObject query = new BasicDBObject("ruleBrief.riskLevel", String.valueOf(riskLevel));
		return getMultipleRulesByField(query);
	}
	
	/**
	 * get rule list by rule type
	 * @param ruleType
	 * @return
	 */
	public List<AndroidStaticRule> getByRuleType(String ruleType) {
		DBObject query = new BasicDBObject("ruleBrief.ruleType", ruleType);
		return getMultipleRulesByField(query);
	}
	
	/**
	 * get rule list by class name
	 * @param className
	 * @return
	 */
	public List<AndroidStaticRule> getByClassName(String className) {
		DBObject query = new BasicDBObject("codeRef.className", className);
		return getMultipleRulesByField(query);
	}
	
	/**
	 * get rule list by method name
	 * @param methodName
	 * @return
	 */
	public AndroidStaticRule getByMethodName(String methodName) {
		DBObject query = new BasicDBObject("codeRef.methodName", methodName);
		return getSingleRuleByField(query);
	}
	
	/**
	 * get rule list by parameter regex
	 * @param paramRegex
	 * @return
	 */
	public AndroidStaticRule getByParameter(String paramRegex) {
		Pattern pattern = Pattern.compile(paramRegex, Pattern.CASE_INSENSITIVE);
		DBObject query = new BasicDBObject("codeRef.paramList.paramRegex", pattern);
		return getSingleRuleByField(query);
	}
	
	/**
	 * get rule list by field
	 * @param query
	 * @return
	 */
	private static List<AndroidStaticRule> getMultipleRulesByField(DBObject query) {
		List<AndroidStaticRule> ruleList = new ArrayList<AndroidStaticRule>();
		DBCursor cursor = mongodbService.getMultipleItems(rules, query);
		while (cursor.hasNext()) {
			Map<String, Object> classMap = new HashMap<String, Object>();
			classMap.put("ruleBrief", AndroidStaticRule.RuleBrief.class);
			classMap.put("codeRef", CodeRef.class);
			ruleList.add((AndroidStaticRule)JsonUtils.deserialize(JSON.serialize(cursor.next()), AndroidStaticRule.class, classMap));
		}
		cursor.close();
		return ruleList;
	}
	
	/**
	 * get single rule by field
	 * @param querye
	 * @return
	 */
	private static AndroidStaticRule getSingleRuleByField(DBObject query) {
		DBObject dbObj = mongodbService.getSingleItem(rules, query);
		if (dbObj == null) {
			return null;
		} else {
			Map<String, Object> classMap = new HashMap<String, Object>();
			classMap.put("ruleBrief", AndroidStaticRule.RuleBrief.class);
			classMap.put("codeRef", CodeRef.class);
			return (AndroidStaticRule)JsonUtils.deserialize(JSON.serialize(dbObj), AndroidStaticRule.class, classMap);
		}
	}
}
