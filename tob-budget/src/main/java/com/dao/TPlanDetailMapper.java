package com.dao;

import com.entities.TPlanDetail;
import com.entities.TPlanDetailExample;
import java.util.List;
import java.util.Map;

import com.vo.BudgetVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TPlanDetailMapper {
    int countByExample(TPlanDetailExample example);

    int deleteByExample(TPlanDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TPlanDetail record);

    int insertSelective(TPlanDetail record);

    List<TPlanDetail> selectByExample(TPlanDetailExample example);

    TPlanDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TPlanDetail record, @Param("example") TPlanDetailExample example);

    int updateByExample(@Param("record") TPlanDetail record, @Param("example") TPlanDetailExample example);

    int updateByPrimaryKeySelective(TPlanDetail record);

    int updateByPrimaryKey(TPlanDetail record);
    
    //月计划
	void addMonthPlan(List<TPlanDetail> list);
	
	//季度计划
	void addQuarterPlan(List<TPlanDetail> list);


	void addPlan(List<TPlanDetail> list);

    void deleteByExample();
    //根据id得到执行计划详情
    List<Map> getPlanDetail(Integer id);

    //根据planId获取执行计划详情
    List<TPlanDetail> getPlanDetailByPlanId(@Param("planId") Integer planId);

}