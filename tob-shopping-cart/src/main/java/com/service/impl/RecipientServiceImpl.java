package com.service.impl;

import com.dao.RecipientDao;
import com.entities.RecipientDO;
import com.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class RecipientServiceImpl implements RecipientService {
	@Autowired
	private RecipientDao recipientDao;
	
	@Override
	public RecipientDO get(Long id){
		return recipientDao.get(id);
	}
	
	@Override
	public List<RecipientDO> list(Map<String, Object> map){
		return recipientDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return recipientDao.count(map);
	}
	
	@Override
	public int save(RecipientDO recipient){
		return recipientDao.save(recipient);
	}
	
	@Override
	public int update(RecipientDO recipient){
		return recipientDao.update(recipient);
	}
	
	@Override
	public int remove(Long id){
		return recipientDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return recipientDao.batchRemove(ids);
	}
	
}
