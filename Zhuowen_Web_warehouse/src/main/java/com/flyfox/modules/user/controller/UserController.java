package com.flyfox.modules.user.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flyfox.component.hibernate.sql.Page;
import com.flyfox.component.hibernate.sql.SQLType;
import com.flyfox.component.spring.controller.BaseController;
import com.flyfox.modules.common.form.CommonForm;
import com.flyfox.modules.user.model.SysUser;
import com.flyfox.modules.user.service.UserService;
import com.flyfox.util.StrUtils;
import com.flyfox.util.extend.BeanUtils;

/**
 * 用户管理
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	private static final String path = "/user/";

	@Resource
	private UserService svc;

	@RequestMapping("list")
	public ModelAndView list(CommonForm form) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		String sql = " from SysUser t where 1 = 1 ";

		if (StrUtils.isNotEmpty(form.get("username"))) {
			sql += " and username like  '%" + form.get("username") + "%'";
		}
		if (StrUtils.isNotEmpty(form.get("realname"))) {
			sql += " and realname like  '%" + form.get("realname") + "%'";
		}

		sql += " order by userid ";
		Page<SysUser> page = svc.paginate(form.getPaginator(), " ", sql.toString(), SQLType.HQL);
		// 下拉框
		modelAndView.setViewName(path + "list");
		modelAndView.addObject("attr", form.getAttr());
		modelAndView.addObject("page", page);
		return modelAndView;
	}

	@RequestMapping("add")
	public String add() {
		return path + "add";
	}

	@RequestMapping("view")
	public ModelAndView view(Integer id) throws Exception {
		SysUser model = svc.findFirst(SysUser.class, id);
		return new ModelAndView(path + "view", "model", model);
	}

	@RequestMapping("delete")
	public ModelAndView delete(Integer id, CommonForm form) throws Exception {
		SysUser model = svc.findFirst(SysUser.class, id);
		svc.delete(model);
		return list(form);
	}

	@RequestMapping("edit")
	public ModelAndView edit(Integer id) throws Exception {
		SysUser model = svc.findFirst(SysUser.class, id);
		return new ModelAndView(path + "edit", "model", model);
	}

	@RequestMapping("save")
	public ModelAndView save(SysUser model) throws Exception {
		if (model.getUserid() != null && model.getUserid() > 0) {
			SysUser user = svc.findFirst(SysUser.class, model.getUserid());
			BeanUtils.copy(model, user);

			svc.update(user);
		} else { // 新增
			if (StrUtils.isEmpty(model.getPassword())) {
				model.setPassword("123456");
			}
			model.setCreateId(1);
			model.setCreateTime(getNow());
			svc.save(model);
		}
		// 更新session
		// setSessionUser(model);
		ModelAndView modelAndView = new ModelAndView();
		renderMessage(modelAndView, "保存成功");
		return modelAndView;
	}

	@RequestMapping("pwd")
	public ModelAndView pwd(Integer id) throws Exception {
		SysUser model = svc.findFirst(SysUser.class, id);
		return new ModelAndView(path + "pwd", "model", model);
	}

	@RequestMapping("save_pwd")
	public ModelAndView save_pwd(Integer id, String old_password, String new_password, String new_password2)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		SysUser model = svc.findFirst(SysUser.class, id);
		if (model == null || model.getUserid() <= 0) {
			renderMessageByFailed(modelAndView, "保存失败，稍后再试");
			return modelAndView;
		}
		if (!model.getPassword().equals(old_password)) {
			renderMessageByFailed(modelAndView, "原始密码输入错误，请重新输入");
			return modelAndView;
		}
		if (!new_password.equals(new_password2)) {
			renderMessageByFailed(modelAndView, "两次新密码不匹配，请重新输入");
			return modelAndView;
		}
		model.setPassword(new_password);
		svc.update(model);

		renderMessage(modelAndView, "保存成功");
		return modelAndView;
	}
}
