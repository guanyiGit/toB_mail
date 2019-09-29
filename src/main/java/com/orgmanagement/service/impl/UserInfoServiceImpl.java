package com.orgmanagement.service.impl;

import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.dao.UserInfoDao;
import com.orgmanagement.dao.UserRefRoleDao;
import com.orgmanagement.dao.UserRefSubDao;
import com.orgmanagement.domain.*;
import com.orgmanagement.service.OrgInfoService;
import com.orgmanagement.service.UserInfoService;
import com.orgmanagement.vo.UserRefSubVo;
import com.orgmanagement.vo.UserSelVO;
import com.utils.MD5Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	UserInfoDao userMapper;
	@Autowired
	UserRefRoleDao userRoleMapper;
	@Autowired
	OrgInfoDao deptMapper;

	@Autowired
	UserRefSubDao userRefSubMapper;

	@Autowired
	private OrgInfoService orgInfoService;

//	@Autowired
//	private BootdoConfig bootdoConfig;
	private static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);

	@Override
	public List<UserSelVO> userSelList() {
		return userMapper.userSelList();
	}
	@Override
//    @Cacheable(key = "#id")
	public UserDO get(Long id) {
		List<Long> roleIds = userRoleMapper.listRoleId(id);
            UserDO user = userMapper.get(id);
//		user.setDeptName(deptMapper.get(user.getDeptId()).getName());
		user.setRoleIds(roleIds);
		return user;
	}

	@Override
	public List<UserDO> list(Map<String, Object> map) {
		//获取所有当前组织下的子组织
		List<OrgInfoDO> list = orgInfoService.list(map);
		List<Integer> orgIds = new ArrayList<>();
		list.forEach( org -> {
			orgIds.add(org.getId());
		});
		map.put("orgIds",orgIds);
		return userMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return userMapper.count(map);
	}

	@Override
	public int update(UserDO user) {
		int r = userMapper.update(user);
		Long userId = user.getUserId();
		List<Long> roles = user.getRoleIds();
		userRoleMapper.removeByUserId(userId);
		List<UserRoleDO> list = new ArrayList<>();
		for (Long roleId : roles) {
			UserRoleDO ur = new UserRoleDO();
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			list.add(ur);
		}
		if (list.size() > 0) {
			userRoleMapper.batchSave(list);
		}
		return r;
	}

	@Override
	public int remove(Long userId) {
		userRoleMapper.removeByUserId(userId);
		return userMapper.remove(userId);
	}

	@Override
	public boolean exit(Map<String, Object> params) {
		boolean exit;
		exit = userMapper.list(params).size() > 0;
		return exit;
	}

	@Override
	public Set<String> listRoles(Long userId) {
		return null;
	}

	@Override
	public int resetPwd(UserVO userVO, UserDO userDO) throws Exception {
		if (Objects.equals(userVO.getUserDO().getUserId(), userDO.getUserId())) {
			if (Objects.equals(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdOld()), userDO.getPassword())) {
				userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
				return userMapper.update(userDO);
			} else {
				throw new Exception("输入的旧密码有误！");
			}
		} else {
			throw new Exception("你修改的不是你登录的账号！");
		}
	}

	@Override
	public int adminResetPwd(UserVO userVO) throws Exception {
		UserDO userDO = get(userVO.getUserDO().getUserId());
		if ("admin".equals(userDO.getUsername())) {
			throw new Exception("超级管理员的账号不允许直接重置！");
		}
		userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
		return userMapper.update(userDO);
	}

	@Transactional
	@Override
	public int batchremove(Long[] userIds) {
		int count = userMapper.batchRemove(userIds);
		userRoleMapper.batchRemoveByUserId(userIds);
		return count;
	}

//	@Override
//	public Tree<DeptDO> getTree() {
//		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
//		List<DeptDO> depts = deptMapper.list(new HashMap<String, Object>(16));
//		Long[] pDepts = deptMapper.listParentDept();
//		Long[] uDepts = userMapper.listAllDept();
//		Long[] allDepts = (Long[]) ArrayUtils.addAll(pDepts, uDepts);
//		for (DeptDO dept : depts) {
//			if (!ArrayUtils.contains(allDepts, dept.getDeptId())) {
//				continue;
//			}
//			Tree<DeptDO> tree = new Tree<DeptDO>();
//			tree.setId(dept.getDeptId().toString());
//			tree.setParentId(dept.getParentId().toString());
//			tree.setText(dept.getName());
//			Map<String, Object> state = new HashMap<>(16);
//			state.put("opened", true);
//			state.put("mType", "dept");
//			tree.setState(state);
//			trees.add(tree);
//		}
//		List<UserDO> users = userMapper.list(new HashMap<String, Object>(16));
//		for (UserDO user : users) {
//			Tree<DeptDO> tree = new Tree<DeptDO>();
//			tree.setId(user.getUserId().toString());
//			tree.setParentId(user.getDeptId().toString());
//			tree.setText(user.getName());
//			Map<String, Object> state = new HashMap<>(16);
//			state.put("opened", true);
//			state.put("mType", "user");
//			tree.setState(state);
//			trees.add(tree);
//		}
//		// 默认顶级菜单为０，根据数据库实际情况调整
//		Tree<DeptDO> t = BuildTree.build(trees);
//		return t;
//	}

	@Override
	public int updatePersonal(UserDO userDO) {
		return userMapper.update(userDO);
	}

	@Override
	public Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception {
		Map<String, Object> result = new HashMap<>();
		return result;
	}

	@Override
	public List<UserDO> pageList(Map<String, Object> map) {
        List<UserDO> list = userMapper.pageList(map);
        list.stream().forEach(user ->{
            List<RoleDO> roles = userRoleMapper.listRolesByUserId(user.getUserId());
            user.setRoles(roles);
        });
        return list;
	}

	@Override
	public int save(UserRefSubVo userRefSubVo){
		UserDO user = new UserDO();
        try {
            BeanUtils.copyProperties(user,userRefSubVo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        user.setOrgId(userRefSubVo.getOrgInfoDO().getId());
        user.setGmtCreate(new Date());
        user.setGmtModified(new Date());
        //默认启用
        user.setStatus(0);
		int count = userMapper.save(user);
		Long userId = user.getUserId();
		//获取所有roleIds，先删除
		List<Long> roles = user.getRoleIds();
		userRoleMapper.removeByUserId(userId);
		List<UserRoleDO> list = new ArrayList<>();
		for (Long roleId : roles) {
			UserRoleDO ur = new UserRoleDO();
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			ur.setCreateDate(new Date());
			ur.setUpdateDate(new Date());
			list.add(ur);
		}
		if (list.size() > 0) {
			userRoleMapper.batchSave(list);
		}
		//获取所有的UserRefSubs，先删除
		List<UserRefSubDO> userRefSubDOList = userRefSubVo.getUserRefSubDO();
		userRefSubMapper.removeByUserId(userId);
		if(userRefSubDOList != null && userRefSubDOList.size() > 0){
			userRefSubDOList.stream().forEach(item -> item.setCreateTime(new Date()));
			userRefSubMapper.batchSave(userRefSubDOList);
		}
		return count;
	}

    @Override
    public int forbiddenUser(Integer userId) {
        Integer forbiddenUser = userMapper.forbiddenUser(userId);
        return forbiddenUser;
    }

    @Override
    public int enableUser(Integer userId) {
        Integer enableUser = userMapper.enableUser(userId);
        return enableUser;
    }
}
