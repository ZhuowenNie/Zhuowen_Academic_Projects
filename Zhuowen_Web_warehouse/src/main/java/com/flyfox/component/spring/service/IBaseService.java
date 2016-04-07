package com.flyfox.component.spring.service;

import java.io.Serializable;
import java.util.List;

import com.flyfox.component.hibernate.sql.Page;
import com.flyfox.component.hibernate.sql.Paginator;
import com.flyfox.component.hibernate.sql.SQLType;

public interface IBaseService {

	/**
	 * 初始化方法
	 * 
	 * @throws Exception
	 */
	public void init() throws Exception;

	/**
	 * 添加对象
	 * 
	 * 2015年4月2日 下午3:51:29 flyfox 330627517@qq.com
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public <T> T save(Object obj) throws Exception;

	/**
	 * 更新对象
	 * 
	 * 2015年4月2日 下午3:51:24 flyfox 330627517@qq.com
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean update(Object obj) throws Exception;

	/**
	 * 删除对象
	 * 
	 * 2015年4月2日 下午3:51:15 flyfox 330627517@qq.com
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean delete(Object obj) throws Exception;

	/**
	 * 获取单个对象
	 * 
	 * 2015年4月2日 下午3:50:51 flyfox 330627517@qq.com
	 * 
	 * @param tableClass
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public <T> T findFirst(Class<?> tableClass, Serializable id) throws Exception;

	public <T> T queryFirst(String sql, SQLType sqlType, Object... params) throws Exception;

	public <T> List<T> query(String sql, SQLType sqlType, Object... params) throws Exception;

	public <T> List<T> queryPage(String sql, int firstResult, int maxResults, SQLType sqlType, Object... params)
			throws Exception;

	public int execute(String sql, SQLType sqlType, Object... params) throws Exception;

	public <T> Page<T> paginate(Paginator paginator, String select, String from, SQLType sqlType, Object... params)
			throws Exception;
}
