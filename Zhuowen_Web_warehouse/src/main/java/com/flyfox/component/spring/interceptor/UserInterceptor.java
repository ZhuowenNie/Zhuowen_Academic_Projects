package com.flyfox.component.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.flyfox.modules.user.model.SysUser;
import com.flyfox.util.Constants;
import com.flyfox.util.StrUtils;

public class UserInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// System.out.println("user_after");

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// System.out.println("user_post");

	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// System.out.println("user_pre");
		
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";

		String path_tmp  = request.getRequestURI().replace(path, ""); // 参数
		
		if (path_tmp.startsWith("/")) {
			path_tmp = path_tmp.substring(1, path_tmp.length());
		}
		if (path_tmp.endsWith("/")) {
			path_tmp = path_tmp.substring(0, path_tmp.length() - 1);
		}

		SysUser user = (SysUser) request.getSession().getAttribute(Constants.SESSION_NAME);

		if (isAuth(path_tmp) && (user == null || user.getUserid() <= 0)) {
			response.sendRedirect(basePath + "pages/error/trans.jsp");
			return false;
		}

		// 继续
		return true;
	}

	/**
	 * 认证方法
	 * 
	 * 2015年2月27日 上午11:38:37 flyfox 330627517@qq.com
	 * 
	 * @param path_tmp
	 * @return
	 */
	protected boolean isAuth(String path_tmp) {
		return StrUtils.isNotEmpty(path_tmp) //
				&& path_tmp.indexOf("login") < 0 // 登录
				&& !path_tmp.endsWith("trans.jsp") // 过期
				&& path_tmp.indexOf("logout") < 0  // 登出
				&& path_tmp.indexOf("admin") < 0  // 登录
		;
	}
}
