package com.flyfox.modules.goods.controller;

import java.io.PrintWriter;
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
import com.flyfox.modules.dict.service.DictService;
import com.flyfox.modules.goods.model.TbGoods;
import com.flyfox.modules.goods.service.GoodsService;
import com.flyfox.util.StrUtils;
import com.flyfox.util.extend.BeanUtils;

@Controller
@RequestMapping("goods")
public class GoodsController extends BaseController {

	private static final String path = "/goods/";

	@Resource
	private GoodsService svc;
	@Resource
	private DictService dictSvc;

	@RequestMapping("list")
	public ModelAndView list(CommonForm form) throws Exception {
		String sql = " from tb_goods t left join sys_dict_detail d on t.type=d.detail_id  where 1 = 1 ";

		if (StrUtils.isNotEmpty(form.get("name"))) {
			sql += " and name like  '%" + form.get("name") + "%'";
		}
		if (form.getInt("type") > 0) {
			sql += " and type =  " + form.getInt("type");
		}
		sql += " order by t.create_time desc ";
		Page<List<Map<String, Object>>> page = svc.paginate(form.getPaginator(), //
				" select t.*,d.detail_name as typeName ", sql.toString(), SQLType.SQL);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(path + "list");
		modelAndView.addObject("goodsType", dictSvc.getSelect("objectType", form.getInt("type")));
		modelAndView.addObject("attr", form.getAttr());
		modelAndView.addObject("page", page);
		return modelAndView;
	}

	@RequestMapping("add")
	public ModelAndView add() throws Exception {
		// 物资种类
		return new ModelAndView(path + "add", "goodsType", dictSvc.getSelect("objectType", 0));
	}

	/**
	 * 获取物资数量
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping("count")
	public void count(Integer id, PrintWriter printWriter) throws Exception {
		String sql = "select (goods_count_in - goods_count_out) as cnt from tb_goods where id = ? ";
		Map<String, Object> record = svc.queryFirst(sql, SQLType.SQL, id);
		Object value = record.get("cnt");
		value = value == null ? "0" : value;

		printWriter.write(value.toString());
		printWriter.flush();
		printWriter.close();
	}

	@RequestMapping("view")
	public ModelAndView view(Integer id) throws Exception {
		TbGoods model = svc.findFirst(TbGoods.class, id);
		// 物资种类
		String typeName = dictSvc.getDictName(model.getType());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(path + "view");
		modelAndView.addObject("typeName", typeName);
		modelAndView.addObject("model", model);
		return modelAndView;
	}

	@RequestMapping("delete")
	public ModelAndView delete(Integer id, CommonForm form) throws Exception {
		TbGoods model = svc.findFirst(TbGoods.class, id);
		svc.delete(model);

		// 删除物品出库入库记录
		svc.execute("delete from tb_goods_detail where goods_id = ? ", SQLType.SQL, id);
		return list(form);
	}

	@RequestMapping("edit")
	public ModelAndView edit(Integer id) throws Exception {
		TbGoods model = svc.findFirst(TbGoods.class, id);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(path + "edit");
		modelAndView.addObject("goodsType", dictSvc.getSelect("objectType", model.getType()));
		modelAndView.addObject("model", model);
		return modelAndView;
	}

	@RequestMapping("save")
	public ModelAndView save(TbGoods model) throws Exception {
		if (model.getId() != null && model.getId() > 0) { // 更新
			TbGoods goods = svc.findFirst(TbGoods.class, model.getId());
			BeanUtils.copy(model, goods);

			svc.update(goods);
		} else { // 新增
			model.setGoodsCountIn(0);
			model.setGoodsCountOut(0);
			model.setCreateId(1);
			model.setCreateTime(getNow());
			svc.save(model);
		}
		// 更新session
		// setSessionUser(model);
		ModelAndView modelAndView = new ModelAndView();
		renderMessage(modelAndView, "保存成功");
		return modelAndView;
	}

	/**
	 * 盘库管理
	 * @throws Exception 
	 * 
	 */
	@RequestMapping("check")
	public ModelAndView check(CommonForm form) throws Exception {

		String sql = " from tb_goods t left join sys_dict_detail d on t.type=d.detail_id  where 1 = 1 ";

		if (StrUtils.isNotEmpty(form.get("name"))) {
			sql += " and name like  '%" + form.get("name") + "%'";
		}
		if (form.getInt("type") > 0) {
			sql += " and type =  " + form.getInt("type");
		}
		sql += " order by t.create_time desc ";
		Page<List<Map<String, Object>>> page = svc.paginate(form.getPaginator(), "select t.*,d.detail_name as typeName ",
				sql.toString(),SQLType.SQL);

		// 下拉框
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(path + "list_check");
		modelAndView.addObject("goodsType", dictSvc.getSelect("objectType", form.getInt("type")));
		modelAndView.addObject("attr", form.getAttr());
		modelAndView.addObject("page", page);
		return modelAndView;
	}
}
