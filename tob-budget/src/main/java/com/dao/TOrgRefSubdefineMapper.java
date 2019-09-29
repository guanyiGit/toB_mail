package com.dao;

import com.entities.TOrgRefSubdefine;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TOrgRefSubdefineMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(TOrgRefSubdefine record);

    int insertSelective(TOrgRefSubdefine record);

    TOrgRefSubdefine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TOrgRefSubdefine record);

    int updateByPrimaryKey(TOrgRefSubdefine record);

    int batchSave(List<TOrgRefSubdefine> orgRefSubdefines);

    int deleteBySubDefineId(Integer subDefineId);

}