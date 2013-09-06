/**
 * 
 */
package com.softsec.tase.store.util.db;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.softsec.tase.common.util.StringUtils;
import com.softsec.tase.store.Configuration;
import com.softsec.tase.store.Constants;

/**
 *@author yanwei
 *@time: 2012-11-6 上午11:01:01
 *@description Mongodb连接工厂类
 *
 */
/**
 *
 */
public class MongodbConnFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(MongodbConnFactory.class);
	
	private static final String MONGODB_URIS = Configuration.get(Constants.MONGODB_URIS, "localhost:27017");
	
	private static final String DBNAME = Configuration.get(Constants.MONGODB_NAME, "tase");
	
	private static final String USERNAME = Configuration.get(Constants.MONGODB_USERNAME, "softsec");
	
	private static final String PASSWORD = Configuration.get(Constants.MONGODB_PASSWORD, "62283748");
	
	private static final DB mongodb;
	
	static {
		
		// parse mongodb server uri
		String[] serverArray = MONGODB_URIS.trim().split(";");
		if (serverArray.length > 1) {
			List<ServerAddress> serverAddrList = new ArrayList<ServerAddress>();
			for (String server : serverArray) {
				if (!StringUtils.isEmpty(server)) {
					try {
						serverAddrList.add(new ServerAddress(server.split(":")[0].trim(), Integer.parseInt(server.split(":")[1])));
						System.out.println(serverAddrList);
					} catch (NumberFormatException nfe) {
						LOGGER.error("Failed to parse mongodb server configuration : " + MONGODB_URIS + " : " + nfe.getMessage(), nfe);
						throw new RuntimeException("Failed to parse mongodb server configuration : " + MONGODB_URIS + " : " + nfe.getMessage(), nfe);
					} catch (UnknownHostException uhe) {
						LOGGER.error("Failed to connect mongodb server : " + MONGODB_URIS + " : " + uhe.getMessage(), uhe);
						throw new RuntimeException("Failed to connect mongodb server : " + MONGODB_URIS + " : " + uhe.getMessage(), uhe);
					}
				}
			}
			// mongodb connection configuration
			
			// initialize mongo client
			MongoClient client = new MongoClient(serverAddrList);
			mongodb = client.getDB(DBNAME);
			// Exceptions are raised for network issues, and server errors; waits for at least 2 servers for the write operation
			// for production use
			mongodb.setWriteConcern(WriteConcern.REPLICAS_SAFE);
		} else {
			try {
				MongoClient client = new MongoClient(serverArray[0].split(":")[0].trim(), Integer.parseInt(serverArray[0].split(":")[1]));
				mongodb = client.getDB(DBNAME);
				// Exceptions are raised for network issues, and server errors; waits on a server for the write operation
				// for development use
				mongodb.setWriteConcern(WriteConcern.SAFE);
			} catch (NumberFormatException nfe) {
				LOGGER.error("Failed to parse mongodb server configuration : " + MONGODB_URIS + " : " + nfe.getMessage(), nfe);
				throw new RuntimeException("Failed to parse mongodb server configuration : " + MONGODB_URIS + " : " + nfe.getMessage(), nfe);
			} catch (UnknownHostException uhe) {
				LOGGER.error("Failed to connect mongodb server : " + MONGODB_URIS + " : " + uhe.getMessage(), uhe);
				throw new RuntimeException("Failed to connect mongodb server : " + MONGODB_URIS + " : " + uhe.getMessage(), uhe);
			}
		}
		
		if (!mongodb.authenticate(USERNAME, PASSWORD.toCharArray())) {
			LOGGER.error("Invalid username or password to login mongodb : " + MONGODB_URIS);
			throw new RuntimeException("Invalid username or password to login mongodb : " + MONGODB_URIS);
		}
	}
	
	/**
	 * 
	 */
	public MongodbConnFactory() {
	}
	
	public static final DB getDB () {
		return mongodb;
	}
	
	public static final DBCollection getCollection (String collectionName) {
		return mongodb.getCollection(collectionName);
	}
}
