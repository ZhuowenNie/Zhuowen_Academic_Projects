package com.flyfox.modules.dict.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.flyfox.component.hibernate.sql.SQLType;
import com.flyfox.component.spring.service.BaseService;
import com.flyfox.modules.dict.dao.DictDao;
import com.flyfox.modules.dict.model.SysDict;
import com.flyfox.modules.dict.model.SysDictDetail;

/**
 * 通用Service 保证现场安全，不要用单例
 * 
 * 2015年4月3日 上午9:43:53 flyfox 330627517@qq.com
 */
@Service
@Scope("prototype")
public class DictService extends BaseService {

	@Resource
	private DictDao dao;

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
	 * 通过Key获取数据字典名称
	 * 
	 * @param key
	 * @return
	 * @author flyfox 2013-11-19
	 * @throws Exception
	 */
	public SysDictDetail getDictDetail(String key) throws Exception {
		return dao.findFirst(SysDictDetail.class, key);
	}

	/**
	 * 通过Key获取数据字典名称
	 * 
	 * @param key
	 * @return
	 * @author flyfox 2013-11-19
	 */
	public String getDictName(Integer key) throws Exception {
		SysDictDetail detail = dao.findFirst(SysDictDetail.class, key);
		return detail == null ? null : detail.getDetailName();
	}

	public String selectDictType(String selected) throws Exception {
		List<SysDict> list = dao.query(" from SysDict", SQLType.HQL);
		StringBuffer sb = new StringBuffer();
		for (SysDict dict : list) {
			sb.append("<option value=\"");
			sb.append(dict.getDictType());
			sb.append("\" ");
			sb.append(dict.getDictType().equals(selected) ? "selected" : "");
			sb.append(">");
			sb.append(dict.getDictName());
			sb.append("</option>");
		}
		return sb.toString();
	}

	public void updateDetail(SysDictDetail model) throws Exception {
		dao.update(model);

	}

	public void saveDetail(SysDictDetail model) throws Exception {
		dao.save(model);
	}

	public void deleteDetail(Integer detailId) throws Exception {
		SysDictDetail detail = dao.findFirst(SysDictDetail.class, detailId);
		dao.delete(detail);
	}

	/**
	 * 获取下拉菜单
	 * 
	 * 2014年1月22日 下午10:08:38 flyfox 330627517@qq.com
	 * 
	 * @param type
	 * @param selected_value
	 * @return
	 */
	public String getSelect(String type, Integer selected_value) throws Exception {
		List<SysDictDetail> list = dao.query(" from SysDictDetail where dictType = '" //
				+ type + "'", SQLType.HQL);
		if (list == null || list.size() <= 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (SysDictDetail dict : list) {
			sb.append("<option value=\"");
			sb.append(dict.getDetailId());
			sb.append("\" ");
			sb.append(dict.getDetailId().equals(selected_value) ? "selected" : "");
			sb.append(">");
			sb.append(dict.getDetailName());
			sb.append("</option>");
		}
		return sb.toString();
	}

}
