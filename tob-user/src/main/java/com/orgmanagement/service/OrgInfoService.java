package com.orgmanagement.service;


import com.entities.OrgInfo;
import com.entities.Tree;
import com.orgmanagement.domain.OrgInfoDO;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-28 15:35:45
 */
public interface OrgInfoService {
	//获取当前组织下所有组织，不包含供应商
	PageUtils findAll(Map<String, Object> map);

	//获取当前组织下所有组织，包含供应商
	List<OrgInfoDO> findAll(Integer orgId);

	OrgInfoDO get(Integer id);
	
	List<OrgInfoDO> list(Integer orgId);
	
	int count(Map<String, Object> map);

	int topOrgCount(Map<String, Object> map);

	@Transactional
	R save(OrgInfoDO orgInfo) throws Exception;
	
	int update(OrgInfoDO orgInfo);

	//删除组织，同时删除所有子组织
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	//获取所有子组织
	List<OrgInfoDO> childList(Map<String, Object> map);

	List<OrgInfoDO> findTopParentOrgList(Map<String,Object> map);


	//查询最下级组织
	List<OrgInfoDO> findBottomOrgList(Integer orgId);

	//获取直接下级组织
	List<OrgInfoDO> getOrgSubList(Integer orgId);

	List<Tree<OrgInfoDO>> initOrgSelect (Map<String,Object> map);

}
