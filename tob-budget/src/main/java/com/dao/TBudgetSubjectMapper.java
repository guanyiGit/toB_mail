package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.entities.TBudgetSubject;
import com.entities.TBudgetSubjectExample;
@Mapper
public interface TBudgetSubjectMapper {
    int countByExample(TBudgetSubjectExample example);

    int deleteByExample(TBudgetSubjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TBudgetSubject record);

    int insertSelective(TBudgetSubject record);

    List<TBudgetSubject> selectByExample(TBudgetSubjectExample example);

    TBudgetSubject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TBudgetSubject record, @Param("example") TBudgetSubjectExample example);

    int updateByExample(@Param("record") TBudgetSubject record, @Param("example") TBudgetSubjectExample example);

    int updateByPrimaryKeySelective(TBudgetSubject record);

    int updateByPrimaryKey(TBudgetSubject record);
}