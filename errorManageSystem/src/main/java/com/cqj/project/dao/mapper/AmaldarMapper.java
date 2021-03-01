package com.cqj.project.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqj.project.dao.entity.Amaldar;

public interface AmaldarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Amaldar record);

    int insertSelective(Amaldar record);

    Amaldar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Amaldar record);

    int updateByPrimaryKey(Amaldar record);
    
    //////////////////////////////////////////
    List<Amaldar> selectAllAmaldars();
    
    Amaldar check(@Param("username")String username);
    
    Amaldar getOne(@Param("id")Integer id);
    
    Amaldar login(@Param("username")String username,@Param("password")String password);
    
    int changeManagerPassword(@Param("id")Integer id,@Param("newPassword")String newPassword);
    
    int deleteSomeAmaldars(List<Integer> list);

	List<Amaldar> selectLikeByUsername(@Param("username")String username);
}