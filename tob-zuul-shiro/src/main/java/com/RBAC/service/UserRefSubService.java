package com.RBAC.service;


import com.RBAC.domain.UserRefSubDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-27 19:00:43
 */
public interface UserRefSubService {
	
	UserRefSubDO get(Integer id);
	
	List<UserRefSubDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserRefSubDO userRefSub);
	
	int update(UserRefSubDO userRefSub);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
