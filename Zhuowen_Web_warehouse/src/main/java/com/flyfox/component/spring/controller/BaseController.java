package com.flyfox.component.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.flyfox.component.hibernate.sql.Paginator;
import com.flyfox.util.Config;
import com.flyfox.util.DateUtils;
import com.flyfox.util.NumberUtils;
import com.flyfox.util.StrUtils;

public class BaseController {
	
	protected static final String page_message = Config.getStr("PAGES.MESSAGE");

	protected void renderMessage(ModelAndView modelAndView, String message) {
		renderMessage(modelAndView, message, "closeIframe();");
	}

	protected void renderMessageByFailed(ModelAndView modelAndView, String message) {
		renderMessage(modelAndView, message, "history.back();");
	}

	protected void renderMessage(ModelAndView modelAndView, String message, String obj) {
		String script = "";
		if (StrUtils.isEmpty(obj)) { // 关闭页面
			script = "closeIframe();";
		} else if (script.endsWith(".jsp")) { // 页面跳转
			script = "window.location.href = \"" + obj + "\"";
		} else { // 直接执行JS
			script = obj;
		}
		modelAndView.setViewName(page_message);
		modelAndView.addObject("msg", message);
		modelAndView.addObject("script", script);
	}

	/**
	 * 获取当前时间，保存创建时间使用
	 * 
	 * 2015年3月25日 下午3:48:02 flyfox 330627517@qq.com
	 * 
	 * @return
	 */
	protected String getNow() {
		return DateUtils.getNow(DateUtils.DEFAULT_REGEX_YYYY_MM_DD_HH_MIN_SS);
	}

	public Paginator getPaginator(HttpServletRequest request) {
		Paginator paginator = new Paginator();
		Integer pageNo = NumberUtils.parseInt(request.getParameter("pageNo"));
		if (pageNo != null && pageNo > 0) {
			paginator.setPageNo(pageNo);
		}
		Integer pageSize = NumberUtils.parseInt(request.getParameter("recordsperpage"));
		if (pageSize != null && pageSize > 0) {
			paginator.setPageSize(pageSize);
		}
		return paginator;
	}

}
