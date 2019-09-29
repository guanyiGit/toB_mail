package com.orgmanagement.service;

import com.orgmanagement.domain.RoleDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 16:21:13
 */
public interface RoleInfoService {

	RoleDO get(Long id);

	int save(RoleDO role);

	int update(RoleDO role);

	int remove(Long id);

	List<RoleDO> list(Map<String, Object> params);

	int listCount(Map<String, Object> params);

	int batchremove(Long[] ids);

	List<RoleDO> initRoleCheckBox(Integer roleId);
}
