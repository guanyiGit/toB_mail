package com.orgmanagement.dao;

import java.util.List;
import java.util.Map;

import com.orgmanagement.domain.OrgInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-28 15:35:45
 */
@Mapper
public interface OrgInfoDao {

	List<OrgInfoDO> findAll();

	OrgInfoDO get(Integer id);
	
	List<OrgInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);

	int topOrgCount(Map<String, Object> map);

	int save(OrgInfoDO orgInfo);
	
	int update(OrgInfoDO orgInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<OrgInfoDO> findChildrenOrg(Integer id);

	List<OrgInfoDO> findTopParentOrgList(Map<String, Object> map);

	//获取直接下级组织
	List<OrgInfoDO> getOrgSubList(Integer orgId);
}
