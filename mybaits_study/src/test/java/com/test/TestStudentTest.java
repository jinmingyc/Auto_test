package com.test;

import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.dao.ITStudent;
import com.domain.TStudent;
import com.util.BatisUtil;

public class TestStudentTest {

	SqlSession sqlSession = null;

	@BeforeTest
	public void setup() {
		sqlSession = BatisUtil.createSqlSession();
		}

	@AfterTest
	public void tearDown() {
		BatisUtil.closeSession(sqlSession);
	}

	@Test
	public void testGetById() {
		TStudent tStudent = sqlSession.getMapper(ITStudent.class).getById(1);
		
		
		if (null == tStudent) {
			System.out.println("return null!");
		} else {
			System.out.println(tStudent);
		}
	}
}
