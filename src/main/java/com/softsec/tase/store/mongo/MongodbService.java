/**
 * 
 */
package com.softsec.tase.store.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 *@author yanwei
 *@time: 2012-11-15 上午11:53:31
 *@description MongoDB服务类
 *
 */
/**
 *
 */
public class MongodbService {
	
	/**
	 * add new item to collection
	 * @param collection
	 * @param dbObj
	 */
	public void insertItem(DBCollection collection, DBObject dbObj) {
		collection.insert(dbObj);
	}
	
	/**
	 * get single record from collection by query
	 * @param collection
	 * @param query
	 * @return
	 */
	public DBObject getSingleItem(DBCollection collection, DBObject query) {
		DBObject dbObj = collection.findOne(query, new BasicDBObject("_id", 0));
		return dbObj;
	}
	
	/**
	 * get records from collection by query
	 * @param collection
	 * @param query
	 * @return
	 */
	public DBCursor getMultipleItems(DBCollection collection, DBObject query) {
		DBCursor cursor = collection.find(query, new BasicDBObject("_id", 0));
		return cursor;
	}
	
	/**
	 * update query to object
	 * @param collection
	 * @param query
	 * @param object
	 * @param upsert
	 * @param multi
	 * @return
	 */
	public WriteResult updateItem(DBCollection collection, DBObject query, DBObject object, boolean upsert, boolean multi) {
		return collection.update(query, object, upsert, multi);
	}
}
