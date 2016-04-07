package com.flyfox.component.spring.service;

import java.io.Serializable;
import java.util.List;

import com.flyfox.component.hibernate.dao.BaseDao;
import com.flyfox.component.hibernate.dao.HibernateSessionManager;
import com.flyfox.component.hibernate.sql.Page;
import com.flyfox.component.hibernate.sql.Paginator;
import com.flyfox.component.hibernate.sql.SQLType;
import com.flyfox.component.spring.util.SpringContextUtils;
import com.flyfox.util.NumberUtils;

public class BaseService implements IBaseService {

	protected BaseDao mainDao;

	/**
	 * 需要初始化调用
	 */
	public void init() throws Exception {
		main();
		if (mainDao == null) {
			mainDao = new BaseDao();
		}
		HibernateSessionManager sessionManger = SpringContextUtils.getBean("dbsession");
		mainDao.setSession(sessionManger);
	}

	/**
	 * 初始化Dao
	 * 
	 * 2015年4月2日 下午5:14:50 flyfox 330627517@qq.com
	 * 
	 * @throws Exception
	 */
	public void main() throws Exception {

	}

	@SuppressWarnings("unchecked")
	public <T> T save(Object obj) throws Exception {
		return (T) mainDao.save(obj);
	}

	public boolean update(Object obj) throws Exception {
		return mainDao.update(obj);
	}

	public boolean delete(Object obj) throws Exception {
		return mainDao.delete(obj);
	}

	public <T> List<T> query(String sql, SQLType sqlType, Object... params) throws Exception {
		return mainDao.query(sql, sqlType, params);
	}

	@SuppressWarnings("unchecked")
	public <T> T queryFirst(String sql, SQLType sqlType, Object... params) throws Exception {
		return (T) mainDao.queryFirst(sql, sqlType, params);
	}

	public <T> List<T> queryPage(String sql, int firstResult, int maxResults, SQLType sqlType, Object... params)
			throws Exception {
		return mainDao.queryPage(sql, firstResult, maxResults, sqlType, params);
	}

	public int execute(String sql, SQLType sqlType, Object... params) throws Exception {
		return mainDao.execute(sql, sqlType, params);
	}

	@SuppressWarnings("unchecked")
	public <T> T findFirst(Class<?> tableClass, Serializable id) throws Exception {
		return (T) mainDao.findFirst(tableClass, id);
	}

	public <T> Page<T> paginate(Paginator paginator, String select, String from, SQLType sqlType, Object... params)
			throws Exception {
		List<T> list = mainDao.queryPage(select + from, paginator.getFirstResult(), paginator.getMaxResults(), sqlType,
				params);
		Object cnt = mainDao.queryFirst("select count(*) AS CNT " + from, sqlType, params);
		Page<T> page = new Page<T>();
		paginator.setTotalRecords(NumberUtils.parseInt(cnt));
		page.setPaginator(paginator);
		page.setList(list);
		return page;
	}

	public BaseDao getDao() {
		return mainDao;
	}

	public void setDao(BaseDao dao) {
		this.mainDao = dao;
	}

}
