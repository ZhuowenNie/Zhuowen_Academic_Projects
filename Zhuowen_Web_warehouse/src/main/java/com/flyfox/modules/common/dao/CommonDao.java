package com.flyfox.modules.common.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.flyfox.component.hibernate.dao.BaseDao;

/**
 * 通用Dao 保证现场安全，不要用单例
 * 
 * 2015年4月3日 上午9:43:53 flyfox 330627517@qq.com
 */
@Service
@Scope("prototype")
public class CommonDao extends BaseDao {

	private static final long serialVersionUID = 1L;

}
