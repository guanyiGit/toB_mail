package com.sys.controller;

import com.RBAC.service.ResourceInfoService;
import com.utils.R;
import com.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller()
@RequestMapping("/biz")
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ResourceInfoService resourceInfoService;

//	@Autowired
//	FileService fileService;
//	@GetMapping({ "/", "" })
//	String welcome() {
//		return "redirect:/blog";
//	}

	@RequestMapping(value = "/login")
	@ResponseBody
	public R login(HttpServletResponse httpServletResponse) {
		httpServletResponse.setHeader("statusCode", "401");
		return R.ok();
	}


	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password,HttpServletResponse httpServletResponse) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()){
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			try {
				subject.login(token);
				String Authorization = subject.getSession().getId().toString();
				httpServletResponse.setHeader("Authorization",Authorization);
				Map<String,Object> map = new HashMap<>();
				map.put("userInfo",getUser());
				return R.ok(map);
			} catch (UnknownAccountException e) {
				logger.error("账号不存在：{}", e);
				return R.error(511, "账号不存在!!!");
			} catch (DisabledAccountException e) {
				logger.error("账号未启用：{}", e);
				return R.error(512, "账号未启用!!!");
			} catch (IncorrectCredentialsException e) {
				logger.error("密码错误：{}", e);
				return R.error(513, "账号或密码错误!!!");
			} catch (Exception e) {
				logger.error("未知错误,请联系管理员：{}", e);
				return R.error(513, "账号或密码错误!!!");
			}
		}else {
			return R.ok();
		}
	}


	@GetMapping("/logout")
	@ResponseBody
	R logout() {
		ShiroUtils.logout();
		return R.ok("退出成功");
	}

}
