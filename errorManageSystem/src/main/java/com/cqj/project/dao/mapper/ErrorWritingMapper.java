package com.cqj.project.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqj.project.dao.entity.ErrorWriting;

public interface ErrorWritingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ErrorWriting record);

    int insertSelective(ErrorWriting record);

    ErrorWriting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ErrorWriting record);

    int updateByPrimaryKey(ErrorWriting record);
    
    List<ErrorWriting> selectAllErrorWritings();
    
    List<ErrorWriting> selectErrorWritingsByStatus(@Param("status")Integer status);

    List<ErrorWriting> selectErrorWritingsByStatusAndCondition(@Param("status")Integer status, @Param("condition")String condition);

    List<ErrorWriting> selectErrorWritingsByStatusAndDealerIdAndCondition(@Param("status")Integer status, @Param("dealerId")Integer dealerId, @Param("condition")String condition);
}