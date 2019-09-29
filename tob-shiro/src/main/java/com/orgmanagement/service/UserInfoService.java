package com.orgmanagement.service;

import com.entities.Tree;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.domain.UserVO;
import com.orgmanagement.vo.UserRefSubVo;
import com.orgmanagement.vo.UserSelVO;
import com.utils.R;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 11:05:22
 */
public interface UserInfoService {
	
//	Map<String,String> get(Integer id);
//
//	List<Map<String,Object>> list(Map<String, Object> map);
//
//	int count(Map<String, Object> map);
//
//	int save(UserInfo userInfo);
//
//	int update(UserInfo userInfo);
//
//	int remove(Integer id);
//
//	int batchRemove(Integer[] ids);
//
//	Map<String,String> selectByUserNoOrName(Map<String,Object> map);
//
//	List<UserInfo> userList (Map<String,Object> map);
//
	List<UserSelVO> userSelList();

	UserDO get(Long id);

	int count(Map<String, Object> map);

//	int save(UserDO user);

	int update(UserDO user);

	int remove(Long userId);

	int batchremove(Long[] userIds);

	boolean exit(Map<String, Object> params);

	Set<String> listRoles(Long userId);

	int resetPwd(UserVO userVO, UserDO userDO) throws Exception;
	int adminResetPwd(UserVO userVO) throws Exception;

	R resetPassword(UserDO userDO,String oldPassWord, String newPassword)throws Exception;

//	Tree<DeptDO> getTree();

	/**
	 * 更新个人信息
	 * @param userDO
	 * @return
	 */
	int updatePersonal(UserDO userDO);

	/**
	 * 更新个人图片
	 * @param file 图片
	 * @param avatar_data 裁剪信息
	 * @param userId 用户ID
	 * @throws Exception
	 */
	Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception;

	List<UserDO> list(Map<String, Object> map);

	List<UserDO> pageList(Map<String, Object> map);

	/**
	 * @param userRefSubVo
	 * @return
	 */
	int save(UserRefSubVo userRefSubVo);

	/**
	 * 禁用用户
	 * @param userId
	 * @return
	 */
	int forbiddenUser(Integer userId);

	/**
	 * 启用用户
	 * @param userId
	 * @return
	 */
	int enableUser(Integer userId);
}
