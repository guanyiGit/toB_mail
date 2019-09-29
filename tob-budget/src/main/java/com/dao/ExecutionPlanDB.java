package com.dao;

import com.entities.ExecutionPlanDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ExecutionPlanDB {

    /**
     * 查询我的（上级）默认执行计划
     *
     * @param orgId
     * @return
     */
    @Select({"SELECT ep.* FROM t_budget_allocations ba, t_execution_plan ep WHERE ep.id = ba.plan_id AND ba.org_id = #{orgId} AND ba.budget_year = #{year} AND ba.type=0"})
    ExecutionPlanDO findDefaultPlan(@Param("orgId") long orgId, @Param("year") int year);

    
    @Select({"SELECT ep.* FROM t_budget_allocations ba, t_execution_plan ep WHERE ep.id = ba.plan_id AND ba.org_id = #{orgId} AND ba.budget_year = #{year} AND ba.type=1 AND ba.subject_id=#{subjectId}" })
    ExecutionPlanDO findDefaultPlan2(@Param("orgId") long orgId, @Param("year") int year, @Param("subjectId") int subjectId);
    
    /**
     * 查询我的默认执行计划
     *
     * @param orgId
     * @return
     */
    @Select({"SELECT ep.* FROM t_budget_allocations ba, t_execution_plan ep WHERE ep.id = ba.plan_id AND ba.org_id = #{orgId} AND ba.budget_year = #{year} AND ba.subject_id=#{subjectId} AND ba.type=1"})
    ExecutionPlanDO findMyDefaultPlan(@Param("orgId") long orgId,@Param("subjectId")Integer subjectId,@Param("year") int year);
    
    /**
     * 查询我的可选执行计划
     *
     * @param orgId
     * @return
     */
    @Select("SELECT * FROM t_execution_plan ep WHERE ep.org_id = #{orgId} ORDER BY ep.create_date DESC")
    List<ExecutionPlanDO> findPlans(@Param("orgId") long orgId);

    /**
     * 修改默认执行计划
     *
     * @param orgId
     * @param planId
     * @param year
     * @return
     */
    @Update("UPDATE t_budget_allocations ba SET ba.plan_id = #{planId} WHERE ba.org_id = #{orgId} AND ba.budget_year = #{year}")
    int modifyBudgetDefaultPlanByOrgId(@Param("orgId")long orgId, @Param("planId") long planId, @Param("year") int year);
    
    @Update("UPDATE t_budget_allocations ba SET ba.plan_id = #{planId} WHERE ba.org_id = #{orgId} AND ba.subject_id=#{subjectId} AND ba.budget_year = #{year}")
    int modifyBudgetMyPlanByOrgId(@Param("orgId")Integer orgId, @Param("subjectId")Integer subjectId, @Param("planId")Integer planId,@Param("year") Integer year);

}
