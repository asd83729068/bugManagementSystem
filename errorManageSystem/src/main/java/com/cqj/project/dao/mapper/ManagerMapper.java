package com.cqj.project.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqj.project.dao.entity.Amaldar;
import com.cqj.project.dao.entity.Manager;

public interface ManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
    
    /////////////以上是系统自动生成//////////////////
    
    Manager login(@Param("username")String username,@Param("password")String password);

    Manager check(@Param("username")String username);

    List<Manager> selectAllManagers();
    
    int deleteSomeManagers(List<Integer> list);

    int changeManagerPassword(@Param("id")Integer id,@Param("newPassword")String newPassword);
    
    List<Manager> selectLikeByUsername(@Param("username")String username);
}