package com.dao;

import com.entities.TSubject;
import com.entities.TSubjectExample;
import com.utils.mallQuery;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TSubjectMapper {
    int countByExample(TSubjectExample example);

    int deleteByExample(TSubjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSubject record);

    int insertSelective(TSubject record);

    List<TSubject> selectByExample(TSubjectExample example);

    TSubject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSubject record, @Param("example") TSubjectExample example);

    int updateByExample(@Param("record") TSubject record, @Param("example") TSubjectExample example);

    int updateByPrimaryKeySelective(TSubject record);

    int updateByPrimaryKey(TSubject record);

	void updateOrg(int budSubId);
	
	/**
	 * 页面信息
	 * @param query
	 * @return
	 */
	List<TSubject> list(mallQuery query);

	
	/**
	 * 科目记录数
	 * @param query
	 * @return
	 */
	int count(mallQuery query);

	List<TSubject> subListByOrgId(@Param("orgId") Integer orgId);

	/**
	 * 科目删除
	 * @return
	 */
	int deleteById(Integer subId);

	/**
	 * 查询所有科目(当前组织)
	 * @return
	 */
	List<TSubject> getAll();

	int deleteBySubDefineId(Integer subDefineId);

	List<TSubject> getSubs(@Param("subDefineId") Integer subDefineId);

	List<TSubject> getSubsByOrgIdAndYear(@Param("orgId") Integer orgId, @Param("year") String year);

}