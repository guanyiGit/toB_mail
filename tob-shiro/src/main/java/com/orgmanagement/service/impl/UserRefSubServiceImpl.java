package com.orgmanagement.service.impl;

import com.orgmanagement.dao.UserRefSubDao;
import com.orgmanagement.domain.UserRefSubDO;
import com.orgmanagement.service.UserRefSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserRefSubServiceImpl implements UserRefSubService {
	@Autowired
	private UserRefSubDao userRefSubDao;
	
	@Override
	public UserRefSubDO get(Integer id){
		return userRefSubDao.get(id);
	}
	
	@Override
	public List<UserRefSubDO> list(Map<String, Object> map){
		return userRefSubDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userRefSubDao.count(map);
	}
	
	@Override
	public int save(UserRefSubDO userRefSub){
		return userRefSubDao.save(userRefSub);
	}
	
	@Override
	public int update(UserRefSubDO userRefSub){
		return userRefSubDao.update(userRefSub);
	}
	
	@Override
	public int remove(Integer id){
		return userRefSubDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return userRefSubDao.batchRemove(ids);
	}
	
}
