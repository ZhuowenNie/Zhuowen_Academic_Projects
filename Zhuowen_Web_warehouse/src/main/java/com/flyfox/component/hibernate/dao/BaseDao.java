package com.flyfox.component.hibernate.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.flyfox.component.hibernate.sql.SQLType;
import com.flyfox.util.DateUtils;
import com.flyfox.util.NumberUtils;

/**
 * @author Jerry Sun
 * @Date 2011-02-18
 * @declare HIBERNATE DAO
 */

public class BaseDao implements ISessionManager, IBaseDao {

	private static final long serialVersionUID = 1L;
	protected ISessionManager sessionManager;
	protected boolean isTran = false;

	public void setSession(ISessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	/**
	 * 独立事务start
	 * 
	 * 2015年4月2日 下午9:02:42 flyfox 330627517@qq.com
	 */
	public void start() throws Exception {
		isTran = true;
		sessionManager.begin();
	}

	/**
	 * 独立事务,与start公用
	 * 
	 * 2015年4月2日 下午9:02:42 flyfox 330627517@qq.com
	 */
	public void stop(boolean isCommit) throws Exception {
		sessionManager.end(isCommit);
		isTran = false;
	}

	public Session getSession() {
		return sessionManager.getSession();
	}

	public void openSession() {
		if (!isTrans()) {
			sessionManager.openSession();
		}
	}

	public void begin() throws Exception {
		if (!isTrans()) {
			sessionManager.begin();
		}
	}

	public void end(boolean isCommit) throws Exception {
		if (!isTrans()) {
			sessionManager.end(isCommit);
		}
	}

	public boolean isTrans() {
		return isTran;
	}

	public void closeSession() {
		sessionManager.closeSession();
	}

	@SuppressWarnings("unchecked")
	public <T> T save(Object obj) throws Exception {
		boolean flag = true;
		try {
			begin();
			Object result = getSession().save(obj);
			return result == null ? null : (T) result;
		} catch (Exception e) {
			flag = false;
			throw e;
		} finally {
			end(flag);
		}
	}

	public boolean update(Object obj) throws Exception {
		boolean flag = true;
		try {
			begin();
			getSession().update(obj);
			return true;
		} catch (Exception e) {
			flag = false;
			throw e;
		} finally {
			end(flag);
		}
	}

	public boolean delete(Object obj) throws Exception {
		boolean flag = true;
		try {
			begin();
			getSession().delete(obj);
			return true;
		} catch (Exception e) {
			flag = false;
			throw e;
		} finally {
			end(flag);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T findFirst(Class<?> tableClass, Serializable id) throws Exception {
		boolean flag = true;
		try {
			openSession();
			Object object = getSession().get(tableClass, id);
			return object == null ? null : (T) object;
		} catch (Exception e) {
			flag = false;
			throw e;
		} finally {
			end(flag);
		}
	}

	/**
	 * query list by sql or hql
	 * 
	 * @param sql
	 * @param sqlType
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T> T queryFirst(String sql, SQLType sqlType, Object... params) throws Exception {
		boolean flag = true;
		try {
			openSession();
			Query query;
			if (sqlType == SQLType.SQL) {
				query = getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			} else {
				query = getSession().createQuery(sql);
			}
			setParams(query, params);
			List<T> list = query.list();
			return (list == null || list.size() <= 0) ? null : (T) list.get(0);
		} catch (Exception e) {
			flag = false;
			throw e;
		} finally {
			end(flag);
		}
	}

	/**
	 * query list by sql or hql
	 * 
	 * @param sql
	 * @param sqlType
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> query(String sql, SQLType sqlType, Object... params) throws Exception {
		boolean flag = true;
		try {
			openSession();
			Query query;
			if (sqlType == SQLType.SQL) {
				query = getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			} else {
				query = getSession().createQuery(sql);
			}
			setParams(query, params);
			List<T> list = query.list();
			return list;
		} catch (Exception e) {
			flag = false;
			throw e;
		} finally {
			end(flag);
		}
	}

	/**
	 * query list by sql or hql
	 * 
	 * @param sql
	 * @param sqlType
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryPage(String sql, int firstResult, int maxResults, SQLType sqlType, Object... params)
			throws Exception {
		boolean flag = true;
		try {
			openSession();
			Query query;
			if (sqlType == SQLType.SQL) {
				query = getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			} else {
				query = getSession().createQuery(sql);
			}
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResults);
			setParams(query, params);
			List<T> list = query.list();
			return list;
		} catch (Exception e) {
			flag = false;
			throw e;
		} finally {
			end(flag);
		}
	}

	/**
	 * executeUpdate list by sql or hql
	 * 
	 * @param sql
	 * @param sqlType
	 * @return
	 * @throws Exception
	 */
	public int execute(String sql, SQLType sqlType, Object... params) throws Exception {
		boolean flag = true;
		try {
			begin();
			Query query;
			if (sqlType == SQLType.SQL) {
				query = getSession().createSQLQuery(sql);
			} else {
				query = getSession().createQuery(sql);
			}
			setParams(query, params);
			int i = query.executeUpdate();
			return i;
		} catch (Exception e) {
			flag = false;
			throw e;
		} finally {
			end(flag);
		}

	}

	/**
	 * 设置Query查询 参数
	 * 
	 * @param query
	 * @param params
	 * @author zb 2013-11-19
	 */
	private void setParams(Query query, Object... params) {
		for (int i = 0; i < params.length; i++) {
			if (params[i] instanceof String) {
				query.setString(i, String.valueOf(params[i]));
			} else if (params[i] instanceof Integer) {
				query.setInteger(i, NumberUtils.parseInt(params[i]));
			} else if (params[i] instanceof BigDecimal) {
				query.setBigDecimal(i, NumberUtils.parseBigDecimal(params[i]));
			} else if (params[i] instanceof Date) {
				query.setDate(i, DateUtils.parse(String.valueOf(params[i])));
			}
		}
	}

}
