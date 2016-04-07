package com.flyfox.modules.goods.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.flyfox.component.spring.service.BaseService;
import com.flyfox.modules.goods.dao.GoodsDetailDao;

/**
 * 通用Service 保证现场安全，不要用单例
 * 
 * 2015年4月3日 上午9:43:53 flyfox 330627517@qq.com
 */
@Service
@Scope("prototype")
public class GoodsDetailService extends BaseService {

	@Resource
	private GoodsDetailDao dao;
	
	/**
	 * spring 创建后调用
	 */
	@PostConstruct
	@Override
	public void init() throws Exception {
		super.init();
	}
	
	public void main() throws Exception {
		mainDao = dao;
	}
	
}
