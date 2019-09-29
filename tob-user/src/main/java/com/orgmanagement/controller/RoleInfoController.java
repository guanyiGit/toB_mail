package com.orgmanagement.controller;

import com.annotation.Log;
import com.orgmanagement.domain.RoleDO;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.RoleInfoService;

import com.utils.PageUtils;
import com.utils.R;
import com.utils.mallQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 16:21:13
 */
 
@Controller
@RequestMapping("/system/roleInfo")
public class RoleInfoController  extends BaseController {

	@Autowired
	RoleInfoService roleService;

	@GetMapping("/list")
	@ResponseBody()
	public PageUtils list(@RequestParam Map<String, Object> params) {
        mallQuery mallQuery = new mallQuery(params);
		List<RoleDO> roles = roleService.list(mallQuery);
        int total = roleService.listCount(mallQuery);
        PageUtils pageUtils = new PageUtils(roles, total);
        return pageUtils;
	}

	@GetMapping("/initRoleCheckBox")
	@ResponseBody()
	public R initRoleCheckBox(Integer roleId){
		Map<String,Object> map = new HashMap<>();
		List<RoleDO> roleDOS = roleService.initRoleCheckBox(roleId);
		map.put("roles",roleDOS) ;
		return R.ok(map);
	}


	@Log("保存角色")
//	@RequiresPermissions("sys:role:add")
	@PostMapping("/save")
	@ResponseBody()
	R save(RoleDO role) {
		if (roleService.save(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@Log("更新角色")
//	@RequiresPermissions("sys:role:edit")
	@PostMapping("/update")
	@ResponseBody()
	R update(RoleDO role) {
		if (roleService.update(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@Log("删除角色")
//	@RequiresPermissions("sys:role:remove")
	@PostMapping("/remove")
	@ResponseBody()
	R save(Long id) {
		if (roleService.remove(id) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}

	//	@RequiresPermissions("sys:role:batchRemove")
	@Log("批量删除角色")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] ids) {
		int r = roleService.batchremove(ids);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
}
