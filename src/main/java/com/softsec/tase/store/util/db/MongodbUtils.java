/**
 * 
 */
package com.softsec.tase.store.util.db;

import net.sf.json.JSONObject;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.softsec.tase.common.util.JsonUtils;

/**
 *@author yanwei
 *@time: 2012-11-12 下午3:04:32
 *@description Mongodb工具类
 *
 */
/**
 *
 */
public class MongodbUtils {
	
	/**
	 * convert JSONObject to DBObject
	 * @param jsonObj
	 * @return
	 */
	public static DBObject jsonObjToDBObj (JSONObject jsonObj) {
		return (DBObject)JSON.parse(jsonObj.toString());
	}
	
	/**
	 * convert bean to DBObject
	 * @param bean
	 * @return
	 */
	public static DBObject beanToDBObj (Object bean) {
		return (DBObject)JSON.parse(JsonUtils.serialize(bean));
	}
}
