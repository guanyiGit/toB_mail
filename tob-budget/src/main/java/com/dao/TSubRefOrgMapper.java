package com.dao;

import com.entities.TSubRefOrg;
import com.entities.TSubRefOrgExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TSubRefOrgMapper {
    int countByExample(TSubRefOrgExample example);

    int deleteByExample(TSubRefOrgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSubRefOrg record);

    int insertSelective(TSubRefOrg record);

    List<TSubRefOrg> selectByExample(TSubRefOrgExample example);

    TSubRefOrg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSubRefOrg record, @Param("example") TSubRefOrgExample example);

    int updateByExample(@Param("record") TSubRefOrg record, @Param("example") TSubRefOrgExample example);

    int updateByPrimaryKeySelective(TSubRefOrg record);

    int updateByPrimaryKey(TSubRefOrg record);

    int batchSave(List<TSubRefOrg> subRefOrgs);

    int deleteBySubId(Integer subId);

}