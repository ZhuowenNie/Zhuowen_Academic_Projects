package com.flyfox.component.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.flyfox.component.hibernate.sql.SQLType;

/**
 * Dao接口
 * 
 * 2015年4月2日 下午3:50:14 flyfox 330627517@qq.com
 */
public interface IBaseDao extends Serializable {

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

	/**
	 * 
	 * 获取单个对象
	 * 
	 * 2015年4月2日 下午10:30:15 flyfox 330627517@qq.com
	 * 
	 * @param sql
	 * @param sqlType
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public <T> T queryFirst(String sql, SQLType sqlType, Object... params) throws Exception;

	/**
	 * 查询List
	 * 
	 * 2015年4月2日 下午8:37:32 flyfox 330627517@qq.com
	 * 
	 * @param sql
	 * @param sqlType
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> query(String sql, SQLType sqlType, Object... params) throws Exception;

	/**
	 * 查询分页List
	 * 
	 * 2015年4月2日 下午8:37:43 flyfox 330627517@qq.com
	 * 
	 * @param sql
	 * @param firstResult
	 * @param maxResults
	 * @param sqlType
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> queryPage(String sql, int firstResult, int maxResults, SQLType sqlType, Object... params)
			throws Exception;

	/**
	 * 执行sql
	 * 
	 * 2015年4月2日 下午8:37:50 flyfox 330627517@qq.com
	 * 
	 * @param sql
	 * @param sqlType
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int execute(String sql, SQLType sqlType, Object... params) throws Exception;
}
