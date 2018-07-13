package com.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BatisUtil {

	private static SqlSessionFactory factory;

	/**
	 * 静态代码块初始化 sqlSessionFactory
	 */
	static {
		try {
			InputStream is = Resources.getResourceAsStream("configure.xml");
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 创建sqlsession
	public static SqlSession createSqlSession() {
		return factory.openSession();
	}

	//自动commit
	public static SqlSession createSqlSession(boolean isAutoCommit) {
		return factory.openSession(isAutoCommit);
	}

	public static void closeSession(SqlSession sqlSession) {
		if (sqlSession != null) {
			sqlSession.close();
		}
	}
}
