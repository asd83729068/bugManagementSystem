package com.cqj.project.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqj.project.dao.entity.Amaldar;
import com.cqj.project.dao.entity.Developer;

public interface DeveloperMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Developer record);

    int insertSelective(Developer record);

    Developer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Developer record);

    int updateByPrimaryKey(Developer record);

	List<Developer> selectAllDevelopers();
	
	Developer check(@Param("username")String username);
	
	Developer login(@Param("username")String username,@Param("password")String password);

	int deleteSomeDevelopers(List<Integer> list);
	
	List<Developer> selectLikeByUsername(@Param("username")String username);

	int changePassword(@Param("id")Integer id,@Param("newPassword")String newPassword);
	
	Developer getOne(@Param("id")Integer id);

}