/**
 * 
 */
package com.softsec.tase.store.util;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoURI;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteConcern;
import com.mongodb.util.JSON;
import com.softsec.tase.common.domain.app.XmlResultData;
import com.softsec.tase.common.util.JsonUtils;
import com.softsec.tase.store.mongo.AndroidStaticResultService;
import com.softsec.tase.store.mongo.MongodbService;
import com.softsec.tase.store.util.db.MongodbConnFactory;
import com.softsec.tase.store.util.db.MongodbUtils;


/**
 * @author yanwei
 * @time 2012-10-25 上午11:25:03
 * @description
 *
 */
public class MongoDbTest extends TestCase{

	private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbTest.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {

			MongoClient client = new MongoClient();
			
			MongoURI uri = new MongoURI("mongodb://localhost");
			Mongo mongo = new Mongo(uri);
			mongo.setWriteConcern(WriteConcern.SAFE);

			DB db = mongo.getDB("test");
			DBCollection collect = db.getCollection("names");

			System.out.println("Collection name : " + collect.getName());

			insertRecord(collect);
//			insertRecordAgain(collect);

			iteratorCollection(collect);

			System.out.println(findOneRecord(collect).toString() + "lalala~ hehe");
			customQuery(collect);

			findSpecRecord(collect, "info", "id", "20062883", "$elemMatch");


		} catch (UnknownHostException uhe) {
			LOGGER.error("Failed to connect host : " + uhe.getMessage(), uhe);
			uhe.printStackTrace();
		} finally {
		}
	}

	/**
	 * @param collect
	 */
	private static void iteratorCollection(DBCollection collect) {
		DBCursor cursor = collect.find(null, new BasicDBObject("_id", 0));
		System.out.println("Records are : ");
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		cursor.close();
	}

	/**
	 * @param collect
	 */
	private static void insertRecord(DBCollection collect) {
		if (collect.getName().equals("names")) {
			BasicDBObject newRecord = new BasicDBObject();
			newRecord.put("name", "lalala");
			newRecord.put("city", "Jinan");
			newRecord.put("fav_color", "Green");
			BasicDBObject nestedRecord = new BasicDBObject();
			nestedRecord.put("id", "20062883");
			nestedRecord.put("school", "qau");
			BasicDBObject nestedRecord2 = new BasicDBObject();
			nestedRecord2.put("id", "2011110588");
			nestedRecord2.put("school", "bupt");
			BasicDBList list = new BasicDBList();
			list.add(nestedRecord);
			list.add(nestedRecord2);
			newRecord.put("info", list);
			newRecord.put("date", new Date(System.currentTimeMillis() - 100000));
			collect.insert(newRecord);
		}
	}

	/**
	 * @param collect
	 */
	private static DBObject findOneRecord(DBCollection collect) {
		DBObject record = collect.findOne();
		return record;
	}

	/**
	 * 
	 * @param collect
	 * @param root
	 * @param key
	 * @param value
	 * @param condition
	 */
	private static void findSpecRecord(DBCollection collect, String root ,String key, String value, String condition) {
		DBObject ref = new BasicDBObject();
		if (condition != null) {
			ref.put(root, new BasicDBObject(condition, new BasicDBObject(key, value)));
		} else {
			ref.put(key, value);
		}
		DBObject dbObj = collect.findOne(new BasicDBObject("now", BasicDBObjectBuilder.start("$lte", (DBObject)JSON.parse(JsonUtils.serialize(new Date()))).get()), new BasicDBObject("_id", 0));
		if (dbObj == null) {
			System.out.println("result is null");
		} else {
//			Map<String, Object> classMap = new HashMap<String, Object>();
//			classMap.put("innerBean", TestBean.InnerBean.class);
//			System.out.println(((TestBean)JsonUtil.deserialize(JSON.serialize(dbObj), TestBean.class, classMap)) + " query result hahaha...");
			System.out.println(dbObj + " query result ...");
		}
//		Pattern pattern = Pattern.compile("bupt");
//		DBCursor cursor = collect.find(new BasicDBObject("innerBean.password", "passwd"));
//		if (!cursor.hasNext()) {
//			System.out.println("cursor.next() is null.");
//		} else {
//			while (cursor.hasNext()) {
//				System.out.println(cursor.next() + " query result ...");
//			}
//		}
//		cursor.close();
	}
	private static void customQuery(DBCollection collect) {
		QueryBuilder basicQuery = new QueryBuilder();
		Date fromDate = new Date(System.currentTimeMillis() - 1000000000);
		Date toDate = new Date();
		basicQuery.put("now").greaterThanEquals((DBObject)JSON.parse(JsonUtils.serialize(fromDate))).lessThanEquals((DBObject)JSON.parse(JsonUtils.serialize(toDate)));
		DBObject query = basicQuery.get();
		System.out.println(query + "... kekeke ...");
		DBCursor cursor = collect.find(query);
		if (!cursor.hasNext()) {
			System.out.println(" ... query result ... is null ...");
		}
		while (cursor.hasNext()) {
			System.out.println(cursor.next() + " ... query result ---");
		}
	}

	@SuppressWarnings("unused")
	private static Map<String, String> addElement(Map<String, String> refMap, String key, String value) {
		refMap.put(key, value);
		return refMap;
	}
	
//	public static void insertRecordAgain(DBCollection collection) {
//		TestBean testBean = new TestBean();
//		testBean.setName("lallalala");
//		testBean.setTestList(new ArrayList<String>());
//		testBean.setTestMap(new HashMap<String, String>());
//		TestBean.InnerBean innerBean = new TestBean.InnerBean();
//		innerBean.setUsername("user");
//		innerBean.setPassword("passwd");
//		testBean.setInnerBean(innerBean);
//		DBObject newRecord = (DBObject)JSON.parse(JsonUtil.serialize(testBean));
//		collection.save(newRecord);
//	}
	
	
	@Test
	public void testInsertItem() {
		XmlResultData androidStaticResult = new XmlResultData();
		androidStaticResult.setApkName("test");
		DBCollection collection = MongodbConnFactory.getCollection("android.static.result");
		MongodbService mongodbService = new MongodbService();
		mongodbService.insertItem(collection, MongodbUtils.beanToDBObj(androidStaticResult));
	}
	
	@Test
	public void testFindOne() {
		String bundleChecksum = "53d57d63a89e002e750a057f3d3396de";
		AndroidStaticResultService service = new AndroidStaticResultService();
		System.out.println(service.getByBundleChecksum(bundleChecksum));
	}
	
	@Test
	public void testModifyOne() {
		String newBundleChecksum = "53d57d63a89e002e750a057f3d3396de";
		String oldBundleChecksum = "942811b355809510cd09cc94c51f7d2d";
		AndroidStaticResultService service = new AndroidStaticResultService();
		System.out.println(service.updateBundleChecksumByBundleChecksum(oldBundleChecksum, newBundleChecksum));
	}
}
