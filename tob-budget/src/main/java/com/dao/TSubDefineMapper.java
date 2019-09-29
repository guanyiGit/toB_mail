package com.dao;

import com.entities.TSubDefine;
import java.util.List;
import java.util.Map;

import com.entities.TSubject;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TSubDefineMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TSubDefine record);

    int insertSelective(TSubDefine record);

    TSubDefine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TSubDefine record);

    int updateByPrimaryKey(TSubDefine record);

    List<TSubDefine> getSubDefines(Map<String,Object> map);


}