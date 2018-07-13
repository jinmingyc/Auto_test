package com.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.domain.TStudent;

public interface ITStudent {
	@Select("select * from TSTUDENT where ID =#{id}")
	public TStudent getById(@Param(value="id") int id);
	
	
}
