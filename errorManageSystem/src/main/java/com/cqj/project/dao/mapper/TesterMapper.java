package com.cqj.project.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqj.project.dao.entity.Amaldar;
import com.cqj.project.dao.entity.Manager;
import com.cqj.project.dao.entity.Tester;

public interface TesterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tester record);

    int insertSelective(Tester record);

    Tester selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tester record);

    int updateByPrimaryKey(Tester record);
    //////////////////////////////////////
    
    Tester check(@Param("username")String username);

	List<Tester> selectAllTesters();
	
	Tester login(@Param("username")String username,@Param("password")String password);
    
	int deleteSomeTesters(List<Integer> list);
	
	List<Tester> selectLikeByUsername(@Param("username")String username);

	int changePassword(@Param("id")Integer id,@Param("newPassword")String newPassword);
	
	Tester getOne(@Param("id")Integer id);
}