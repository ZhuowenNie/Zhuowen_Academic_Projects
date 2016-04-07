package com.flyfox.modules.goods.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.flyfox.component.hibernate.sql.SQLType;
import com.flyfox.component.spring.service.BaseService;
import com.flyfox.modules.goods.dao.GoodsDao;
import com.flyfox.modules.goods.model.TbGoods;

/**
 * 通用Service 保证现场安全，不要用单例
 * 
 * 2015年4月3日 上午9:43:53 flyfox 330627517@qq.com
 */
@Service
@Scope("prototype")
public class GoodsService extends BaseService {

	@Resource
	private GoodsDao dao;
	
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
	
	
	/**
	 * 获取下拉菜单
	 * 
	 * @param type
	 * @param selected_value
	 * @return
	 * @throws Exception 
	 */
	public String getGoods(Integer selected_value) throws Exception {
		List<TbGoods> list = query(" from TbGoods ", SQLType.HQL);
		if (list == null || list.size() <= 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (TbGoods goods : list) {
			sb.append("<option value=\"");
			sb.append(goods.getId());
			sb.append("\" ");
			sb.append(goods.getId().equals(selected_value) ? "selected" : "");
			sb.append(">");
			sb.append(goods.getName());
			sb.append("</option>");
		}
		return sb.toString();
	}

	/**
	 * 获取物资名称
	 * 
	 * @param key
	 * @return
	 * @throws Exception 
	 */
	public String getGoodsName(Integer key) throws Exception {
		TbGoods goods = findFirst(TbGoods.class, key);
		if (goods == null) {
			return null;
		}

		return goods.getName();
	}

	/**
	 * 更新数量
	 * 
	 * @param key
	 * @param type
	 * @return
	 * @throws Exception 
	 */
	public boolean updateGoodsCount(Integer type) throws Exception {
		String sql = "update tb_goods set goods_count_" + (type == 1 ? "in" : "out") + " = "
				+ "(select IFNULL(sum(oper_count),0) from tb_goods_detail where type = ? " //
				+ "and tb_goods.id = tb_goods_detail.goods_id )";
		int goods = execute(sql, SQLType.SQL, type);
		return goods > 0;
	}
	
}
