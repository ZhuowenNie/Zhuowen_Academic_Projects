package com.flyfox.modules.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flyfox.component.hibernate.sql.SQLType;
import com.flyfox.component.spring.controller.BaseController;
import com.flyfox.modules.common.service.CommonService;
import com.flyfox.modules.user.model.SysUser;
import com.flyfox.util.Constants;
import com.flyfox.util.StrUtils;

@Controller
public class CommonController extends BaseController {

	@Resource
	private CommonService svc;

	@RequestMapping("/")
	public String index() {
		return "login";
	}
	
	@RequestMapping("/test")
	public String test() {
		return "test";
	}

	/**
	 * 登陆
	 * 
	 * @author flyfox 2013-11-11
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public ModelAndView login(String username, String password, HttpServletRequest request) throws Exception {
		// 初始化数据字典Map
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		if (StrUtils.isEmpty(username)) {
			mav.addObject("msg", "用户名不能为空");
			return mav;
		} else if (StrUtils.isEmpty(password)) {
			mav.addObject("msg", "密码不能为空");
			return mav;
		}
		SysUser user = svc.queryFirst(" from SysUser where username = ? and password = ? ", SQLType.HQL, username,
				password);
		if (user == null || user.getUserid() <= 0) {
			mav.addObject("msg", "认证失败，请您重新输入。");
			return mav;
		} else {
			request.getSession().setAttribute(Constants.SESSION_NAME, user);
		}
		mav.setViewName("redirect:goods/check.htm");
		return mav;
	}

	/**
	 * 登出
	 * 
	 * @author flyfox 2013-11-13
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().removeAttribute(Constants.SESSION_NAME);
		return new ModelAndView("login", "msg", "您已退出");
	}

	@RequestMapping("/login_reg")
	public String login_reg() {
		return "reg";
	}

	@RequestMapping("/login_reg_save")
	public ModelAndView login_reg_save(SysUser user) throws Exception {
		ModelAndView mav = new ModelAndView();
		SysUser tmp = svc.queryFirst(" from SysUser  where username = ? ", SQLType.HQL, user.getUsername());
		if (tmp != null) {
			renderMessageByFailed(mav, "用户名已存在，请重新输入。");
			return mav;
		}

		if (user.getUserid() == 0) { // 更新
			if (StrUtils.isEmpty(user.getPassword())) {
				user.setPassword("123456");
			}
			user.setCreateId(0);
			user.setCreateTime(getNow());
			svc.save(user);
		}

		renderMessage(mav, "保存成功");
		return mav;
	}

}
