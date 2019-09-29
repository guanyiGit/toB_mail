package com.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.entities.OrderDetailDO;
import com.vo.*;
import org.apache.ibatis.annotations.MapKey;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.entities.TBudgetAllocations;
import com.entities.TBudgetAllocationsExample;
import com.utils.mallQuery;

@Mapper
public interface TBudgetAllocationsMapper {
    int countByExample(TBudgetAllocationsExample example);

    int deleteByExample(TBudgetAllocationsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TBudgetAllocations record);

    int insertSelective(TBudgetAllocations record);

    List<TBudgetAllocations> selectByExample(TBudgetAllocationsExample example);

    TBudgetAllocations selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TBudgetAllocations record, @Param("example") TBudgetAllocationsExample example);

    int updateByExample(@Param("record") TBudgetAllocations record, @Param("example") TBudgetAllocationsExample example);

    int updateByPrimaryKeySelective(TBudgetAllocations record);

    int updateByPrimaryKey(TBudgetAllocations record);
    /**
     * 插入预算分配表
     * @param list
     */
	void add(List<TBudgetAllocations2> list);
	
	/**
     * 插入预算分配表(总额)
     * @param list
     */
	void add1(List<UpBudget>list);
	
	/**
     * 插入预算分配表2(科目)
     * @param list
     */
	void add2(ChildSub c);
	
	/**
	 * 检查部门科目是否被分配
	 * @param query
	 * @return
	 */
	int checkAllocation(@Param("orgId")Integer orgId,@Param("year")Integer year);
	
	/**
	 * 检查本部门是否已经分配预算
	 * @param query
	 * @return
	 */
	int checkAllocation2(@Param("orgId")Integer orgId,@Param("year")Integer year);
	//分配列表信息
	List<TBudgetAllocationsVo> allocationList(mallQuery query);
	//分配总记录数
	int countAllocation(mallQuery query);
	
	//接收列表信息
	List<TBudgetAllocationsVo> receiveList(mallQuery query);
	//接收总记录数
	int countReceive(mallQuery query);
	
	//使用列表信息
	List<TBudgetAllocationsVo> useList(mallQuery query);
	
	//使用总记录数
	int countUse(mallQuery query);
	
	//根据年份查询已用预算额
	Double actualUseByYear(Map<String,Object>map);
	
	//按年统计科目已用预算额(无执行计划) 
	List<BudgetDetailVo> budgetPublicByYear(@Param("orgId")Integer orgId,@Param("year")Integer year,@Param("date")Date date);


	 Double budgetAccumulative(@Param("orgId")Integer orgId,@Param("year")Integer year,@Param("date")Date date,@Param("subjectId")Integer subjectId);
	//根据部门，年份，科目查出预算
	TBudgetAllocationsVo findAllocation(@Param("orgId")Integer orgId,
										@Param("year")int year,
										@Param("subjectId")Integer subjectId);
	//根据部门，年份查出预算
	TBudgetAllocations findAllocation2(Map<String, Object> map);
	//预算使用详细
	List<OrderVo> budgetDetailUseList(mallQuery query);

	//预算使用详细总记录数
	int countBudgetDetailUse(mallQuery query);
	//查看每个月所用预算
	Double actualUseByMonth(Map<String, Object> map);
	//查看每个季度所用预算
	Double actualUseByQuarter(Map<String, Object> map);
	
	/**
	 * 下拉框科目
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findSubjectList(Integer currOrgId);
	
	/**
	 * 根据部门，科目，下单时间查出本科目年度已用预算额
	 * @param orgId
	 * @param subjectId
	 * @param orderDate
	 * @return
	 */
	BudgetDetailVo budgetOrder(@Param("orgId")Integer orgId,
							   @Param("subjectId")Integer subjectId, 
							   @Param("orderDate")Date orderDate);


	// 查出当前年度部门情况
	TBudgetAllocations findSubjetIdByOrgAndYear(@Param("orgId")Integer orgId,@Param("year")Integer year);
	// 查出当前年度部门情况
	TBudgetAllocations findSubjetIdByOrgAndYearAndMonth(@Param("orgId")Integer orgId,@Param("year")Integer year,@Param("month")Integer month);
	//查看当前部门年度使用多少钱
	Double findUsedAmount(@Param("orgId")Integer orgId,@Param("year")Integer year,@Param("date")Date date);//查看当前部门使用多少钱
	//查看当前部门月度使用多少钱
	Double findUsedAmountMonth(@Param("orgId")Integer orgId,@Param("year")Integer year,@Param("month")Integer month);	//查看当前部门月度使用多少钱
	//查看当前部门当前科目月度使用多少钱
	Double findUsedAmountMonthSub(@Param("orgId")Integer orgId,@Param("year")Integer year,@Param("month")Integer month,@Param("subjectId") Integer subjectId);
	//查看当前部门年度是否按科目分配
	int findSubjetCount(@Param("orgId")Integer orgId,@Param("year")Integer year);
	//查看当前部门当前科目月度预算额
	 Double  findSubMonthAmount(@Param("orgId")Integer orgId,@Param("year")Integer year,@Param("month")Integer month,@Param("subjectId") Integer subjectId);
	//查看当前部门月度是否按科目分配
	int findSubjetMonthCount(@Param("orgId")Integer orgId,@Param("year")Integer year,@Param("month")Integer month);
	//查看当前科目是否有执行计划
	TBudgetAllocations findPlan(@Param("orgId")Integer orgId,@Param("year")Integer year,@Param("type")Integer type,@Param("subjectId") Integer subjectId);
	/**
	 * 查询预算明细里面的所有采购人员
	 * @param map
	 *           key:subjectId 可null
	 *           key:userId 可用
	 * @return 所有采购员:userID,userName
	 */
	@MapKey("id")
	List<Map<Long,String>> findPrucUsers(Map<String,String> map);

	/**
	 * py
	 * 根据部门，科目，年份查出本科目年度已用预算额
	 * @param orgId
	 * @param subjectId  (可能为空)
	 * @param year
	 * @return
	 */
	List<TBudgetAllocationsVo> getBudgetAllocation(@Param("orgId")Integer orgId,
												   @Param("subjectId")Integer subjectId,
												   @Param("year")Integer year);


	void insertBudgetMonth(List<TBudgetAllocations> TBudgetAllocations );


	Double findBudgetAmount(@Param("orgId")Integer orgId,
							@Param("month")Integer month,
							@Param("year")Integer year
							);

}