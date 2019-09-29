package com.service;


import com.entities.RecipientDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-12-25 14:45:27
 */
public interface RecipientService {
	
	RecipientDO get(Long id);
	
	List<RecipientDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RecipientDO recipient);
	
	int update(RecipientDO recipient);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
