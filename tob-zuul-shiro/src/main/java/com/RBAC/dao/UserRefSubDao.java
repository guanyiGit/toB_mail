package com.RBAC.dao;

import com.RBAC.domain.UserRefSubDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-27 19:00:43
 */
@Mapper
public interface UserRefSubDao {

	UserRefSubDO get(Integer id);
	
	List<UserRefSubDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserRefSubDO userRefSub);
	
	int update(UserRefSubDO userRefSub);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	int removeByUserId(Long userId);

	int batchSave(List<UserRefSubDO> list);
}
