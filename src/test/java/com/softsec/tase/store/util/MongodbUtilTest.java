/**
 * 
 */
package com.softsec.tase.store.util;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.softsec.tase.common.domain.CodeRef;
import com.softsec.tase.common.domain.rule.AndroidStaticRule;
import com.softsec.tase.common.util.JsonUtils;

/**
 *@author yanwei
 *@time: 2012-11-13 上午8:36:33
 *@description 
 *
 */
/**
 *
 */
public class MongodbUtilTest extends TestCase{

	@Test
	public void testJsonStringToBean() {
		AndroidStaticRule androidStaticRule = new AndroidStaticRule();
		androidStaticRule.setCodeRef(new CodeRef());
		androidStaticRule.setRuleBrief(new AndroidStaticRule.RuleBrief());
		DBObject recordBean = (DBObject)(JSON.parse(JsonUtils.serialize(androidStaticRule)));
		Map<String, Object> classMap = new HashMap<String, Object>();
		classMap.put("codeRef", CodeRef.class);
		classMap.put("ruleBrief", AndroidStaticRule.RuleBrief.class);
		System.out.println(JsonUtils.deserialize(JSON.serialize(recordBean), AndroidStaticRule.class, classMap).toString());
	}
	
	@Test
	public void testJsonSerialize() {
		DBObject recordBean = null;
		System.out.println(JSON.serialize(recordBean));
	}
}
