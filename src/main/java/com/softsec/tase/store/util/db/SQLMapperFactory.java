package com.softsec.tase.store.util.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SQLMapperFactory {

	private static final SqlSessionFactory sqlMapper;

	static {
		try {
			InputStream input = SQLMapperFactory.class.getClassLoader().getResourceAsStream("jdbc.properties");
			if (input == null) {
				input = SQLMapperFactory.class.getResourceAsStream("jdbc.properties");
			}
			Properties properties = new Properties();
			properties.load(input);
			Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlMapper = new SqlSessionFactoryBuilder().build(reader, null, properties);
		} catch (IOException e) {
			throw new RuntimeException("Initialize sql mapper failed : " + e.getMessage(), e);
		}
	}

	private SQLMapperFactory() {
	}

	public static final SqlSessionFactory getSqlMapper() {
		return sqlMapper;
	}

	public static final SqlSession openSession() {
		return getSqlMapper().openSession();
	}
}
