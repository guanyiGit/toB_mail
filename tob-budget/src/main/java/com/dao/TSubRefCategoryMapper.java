package com.dao;

import com.entities.TSubRefCategory;
import com.entities.TSubRefCategoryExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TSubRefCategoryMapper {
    int countByExample(TSubRefCategoryExample example);

    int deleteByExample(TSubRefCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSubRefCategory record);

    int insertSelective(TSubRefCategory record);

    List<TSubRefCategory> selectByExample(TSubRefCategoryExample example);

    TSubRefCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSubRefCategory record, @Param("example") TSubRefCategoryExample example);

    int updateByExample(@Param("record") TSubRefCategory record, @Param("example") TSubRefCategoryExample example);

    int updateByPrimaryKeySelective(TSubRefCategory record);

    int updateByPrimaryKey(TSubRefCategory record);
    //插入关联信息
	void add(List<TSubRefCategory> list);

	//

}