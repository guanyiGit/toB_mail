package com.orgmanagement.controller;

import com.annotation.Log;
import com.entities.Tree;
import com.orgmanagement.domain.RoleDO;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.domain.UserVO;
import com.orgmanagement.service.OrgInfoService;
import com.orgmanagement.service.RoleInfoService;
import com.orgmanagement.service.UserInfoService;
import com.orgmanagement.vo.UserRefSubVo;
import com.orgmanagement.vo.UserSelVO;
import com.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 11:05:22
 */
 
@Controller
@RequestMapping("/system/userInfo")
public class UserInfoController extends BaseController {
	@Autowired
	private UserInfoService userInfoService;

	@GetMapping("/initHandlerSel")
    @ResponseBody
	public List<UserSelVO> initHandlerSel() {
        List<UserSelVO> userSelList = userInfoService.userSelList();
        return userSelList;
    }
	private String prefix="orgmanagement"  ;
	@Autowired
	UserInfoService userService;
	@Autowired
	RoleInfoService roleService;
	@Autowired
    private  OrgInfoService orgInfoService;



	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		mallQuery mallQuery = new mallQuery(params);
        List<UserDO> sysUserList = userService.list(mallQuery);
		int total = userService.count(mallQuery);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	//	@Log("保存用户")
//	@RequiresPermissions("sys:user:add")
	@PostMapping("/save")
	@ResponseBody
	R save(@RequestBody UserRefSubVo userRefSubVo) {
		userRefSubVo.setPassword(Md5Encode.getCredentials(userRefSubVo.getUsername(),"123456").toString().trim());
		if (userService.save(userRefSubVo) > 0) {
			return R.ok();
		}
		return R.error();
	}

	//	@RequiresPermissions("sys:user:edit")
//	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	R update(UserDO user) {
		if (userService.update(user) > 0) {
			return R.ok();
		}
		return R.error();
	}


	//	@RequiresPermissions("sys:user:edit")
	@Log("更新用户")
	@PostMapping("/updatePeronal")
	@ResponseBody
	R updatePeronal(UserDO user) {
		if (userService.updatePersonal(user) > 0) {
			return R.ok();
		}
		return R.error();
	}


	//	@RequiresPermissions("sys:user:remove")
	@Log("删除用户")
	@PostMapping("/remove")
	@ResponseBody
	R remove(Long id) {
//		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
//			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
//		}
		if (userService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 禁用用户
	 * @param userId
	 * @return
	 */
	@PostMapping("/forbiddenUser")
	@ResponseBody
	public R forbiddenUser(Integer userId){
		int forbiddenUser = userService.forbiddenUser(userId);
		return R.ok(forbiddenUser);
	}

	/**
	 * 启用用户
	 * @param userId
	 * @return
	 */
	@PostMapping("/enableUser")
	@ResponseBody
	public R enableUser(Integer userId){
		int enableUser = userService.enableUser(userId);
		return R.ok(enableUser);
	}

	//	@RequiresPermissions("sys:user:batchRemove")
	@Log("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] userIds) {
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exit(params);
	}

	@RequestMapping("/resetPassword")
	@ResponseBody
	R resetPassword(@RequestParam Map<String, Object> params){
		Integer userId = Integer.parseInt(params.get("userId").toString());
		String oldPassWord = params.get("oldPassword").toString();
		String newPassword = params.get("newPassword").toString();
		try {
			return userService.resetPassword(userId, oldPassWord, newPassword);
		} catch (Exception e){
			return R.error(e.getMessage());
		}
	}


	@Log("提交更改用户密码")
	@PostMapping("/resetPwd")
	@ResponseBody
	R resetPwd(UserVO userVO) {
		try{
			userService.resetPwd(userVO,getUser());
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}

	//	@RequiresPermissions("sys:user:resetPwd")
	@Log("admin提交更改用户密码")
	@PostMapping("/adminResetPwd")
	@ResponseBody
	R adminResetPwd(UserVO userVO) {
		try{
			userService.adminResetPwd(userVO);
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}


	@ResponseBody
	@PostMapping("/uploadImg")
	R uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		Map<String, Object> result = new HashMap<>();
		try {
			result = userService.updatePersonalImg(file, avatar_data, getUserId());
		} catch (Exception e) {
			return R.error("更新图像失败！");
		}
		if(result!=null && result.size()>0){
			return R.ok(result);
		}else {
			return R.error("更新图像失败！");
		}
	}
	
}
