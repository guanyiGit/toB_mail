package com.dao;

import com.entities.TExecutionPlan;
import com.entities.TExecutionPlanExample;
import com.utils.mallQuery;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TExecutionPlanMapper {
    int countByExample(TExecutionPlanExample example);

    int deleteByExample(TExecutionPlanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TExecutionPlan record);

    int insertSelective(TExecutionPlan record);

    List<TExecutionPlan> selectByExample(TExecutionPlanExample example);

    TExecutionPlan selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TExecutionPlan record, @Param("example") TExecutionPlanExample example);

    int updateByExample(@Param("record") TExecutionPlan record, @Param("example") TExecutionPlanExample example);

    int updateByPrimaryKeySelective(TExecutionPlan record);

    int updateByPrimaryKey(TExecutionPlan record);
    //计划列表
	List<TExecutionPlan> list(mallQuery query);
	//执行计划总计录数
	int count(mallQuery query);
}