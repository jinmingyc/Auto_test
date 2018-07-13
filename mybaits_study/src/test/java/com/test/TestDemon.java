package com.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.dao.TestMapper;
import com.domain.Test;
import com.util.BatisUtil;

public class TestDemon {
	
	private Logger logger = Logger.getLogger(TestDemon.class);

@org.testng.annotations.Test

    public void getEmpListTest() {
       
        SqlSession sqlSession = null;
        //查询结果集
        List<Test> TestList = new  ArrayList<>();
        sqlSession =BatisUtil.createSqlSession();
        try {                        
            // 调用mapper文件进行数据操作(调用之前必须要把mapper文件加入到mybatis-config.xml)
           // TestList = sqlSession.selectList("com.dao.TestMapper.getAll");
            //调用getMapper传入接口类型，获取接口方法
        	TestMapper mapper=sqlSession.getMapper(TestMapper.class);        	
           TestList=mapper.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭会话
            BatisUtil.closeSession(sqlSession);
        }

        //遍历数据
        for (Test test : TestList) {
           logger.debug(test);
        }
    }

}
