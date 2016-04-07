package com.flyfox.modules.dict.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flyfox.component.hibernate.sql.Page;
import com.flyfox.component.hibernate.sql.SQLType;
import com.flyfox.component.spring.controller.BaseController;
import com.flyfox.modules.common.form.CommonForm;
import com.flyfox.modules.dict.model.SysDict;
import com.flyfox.modules.dict.model.SysDictDetail;
import com.flyfox.modules.dict.service.DictService;
import com.flyfox.util.StrUtils;
import com.flyfox.util.extend.BeanUtils;

/**
 * 数据字典
 * 
 */
@Controller
@RequestMapping("dict")
public class DictController extends BaseController {

	private static final String path = "/dict/";
	@Resource
	private DictService svc;

	@RequestMapping("list")
	public ModelAndView list(CommonForm form) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		StringBuffer sql = new StringBuffer(" from sys_dict_detail t,sys_dict d where t.dict_type = d.dict_type ");
		String attrVal = form.get("dict_type");
		if (StrUtils.isNotEmpty(attrVal)) {
			sql.append(" AND t.dict_type = '").append(attrVal).append("'");
		}
		Page<List<Map<String, Object>>> page = svc.paginate(form.getPaginator(), "select t.*,d.dict_name ", sql.toString(),
				SQLType.SQL);

		// 下拉框
		modelAndView.addObject("optionList", svc.selectDictType(form.get("dict_type")));
		modelAndView.setViewName(path + "list");
		modelAndView.addObject("attr", form.getAttr());
		modelAndView.addObject("page", page);
		return modelAndView;
	}

	@RequestMapping("add")
	public ModelAndView add(String dictType) throws Exception {
		return new ModelAndView(path + "add", "optionList", svc.selectDictType(dictType));
	}

	@RequestMapping("view")
	public ModelAndView view(Integer id) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		SysDictDetail item = svc.findFirst(SysDictDetail.class, id);
		modelAndView.addObject("optionList", svc.selectDictType(item.getDictType()));
		modelAndView.addObject("item", item);
		modelAndView.setViewName(path + "view");
		return modelAndView;
	}

	@RequestMapping("delete")
	public ModelAndView delete(Integer id, CommonForm form) throws Exception {
		svc.deleteDetail(id);
		return list(form);
	}

	@RequestMapping("edit")
	public ModelAndView edit(Integer id) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		SysDictDetail item = svc.findFirst(SysDictDetail.class, id);
		modelAndView.addObject("optionList", svc.selectDictType(item.getDictType()));
		modelAndView.addObject("item", item);
		modelAndView.setViewName(path + "edit");
		return modelAndView;
	}

	@RequestMapping("save")
	public ModelAndView save(SysDictDetail model) throws Exception {
		if (model != null && model.getDetailId() > 0) { // 更新
			SysDictDetail detail = svc.findFirst(SysDictDetail.class, model.getDetailId());
			BeanUtils.copy(model, detail);
			svc.updateDetail(detail);
		} else { // 新增
			model.setCreateId(1);
			model.setCreateTime(getNow());
			svc.saveDetail(model);
		}
		ModelAndView modelAndView = new ModelAndView();
		renderMessage(modelAndView, "保存成功");
		return modelAndView;
	}

	@RequestMapping("edit_dict")
	public ModelAndView edit_dict(String dict_type) throws Exception {
		SysDict item = svc.queryFirst(" from SysDict where dictType = ? ", SQLType.HQL, dict_type);
		return new ModelAndView(path + "edit_dict", "item", item);
	}

	@RequestMapping("save_dict")
	public ModelAndView save_dict(SysDict model) throws Exception {
		if (model != null && model.getDictId() > 0) { // 更新
			SysDict dict = svc.findFirst(SysDict.class, model.getDictId());
			BeanUtils.copy(model, dict);
			svc.update(dict);
		} else { // 新增
			svc.save(model);
		}
		ModelAndView modelAndView = new ModelAndView();
		renderMessage(modelAndView, "保存成功");
		return modelAndView;
	}

	@RequestMapping("delete_dict")
	public ModelAndView delete_dict(Integer id) throws Exception {
		SysDict dict = svc.findFirst(SysDict.class, id);
		svc.delete(dict);
		ModelAndView modelAndView = new ModelAndView();
		renderMessage(modelAndView, "删除成功");
		return modelAndView;
	}

}
