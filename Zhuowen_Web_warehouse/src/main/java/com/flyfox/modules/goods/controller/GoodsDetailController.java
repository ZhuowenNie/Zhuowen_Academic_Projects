package com.flyfox.modules.goods.controller;

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
import com.flyfox.modules.goods.model.TbGoodsDetail;
import com.flyfox.modules.goods.service.GoodsDetailService;
import com.flyfox.modules.goods.service.GoodsService;
import com.flyfox.util.StrUtils;
import com.flyfox.util.extend.BeanUtils;

@Controller
@RequestMapping("goodsdetail")
public class GoodsDetailController extends BaseController {

	private static final String path = "/goodsdetail/";

	@Resource
	private GoodsDetailService svc;
	@Resource
	private GoodsService goodsSvc;

	@Resource
	private DictService dictSvc;

	@RequestMapping("list")
	public ModelAndView list(CommonForm form) throws Exception {
		String sql = " from tb_goods_detail t " + "  left join sys_dict_detail com on t.company=com.detail_id "
				+ "  left join sys_dict_detail oper on t.oper_id=oper.detail_id "
				+ "  left join tb_goods good on t.goods_id=good.id " + "where 1 = 1 ";

		// 类型 入库 出库
		if (form.getInt("type") > 0) {
			sql += " and t.type =  " + form.getInt("type");
		}
		// 查询
		if (StrUtils.isNotEmpty(form.get("name"))) {
			sql += " and good.name like  '%" + form.get("name") + "%'";
		}

		sql += " order by t.create_time desc ";
		Page<List<Map<String, Object>>> page = svc.paginate(
				form.getPaginator(), //
				"select t.*,com.detail_name as companyName,oper.detail_name as operName,good.name as goodsName ",
				sql.toString(), SQLType.SQL);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(path + "list");
		modelAndView.addObject("attr", form.getAttr());
		modelAndView.addObject("page", page);
		return modelAndView;
	}

	@RequestMapping("add")
	public ModelAndView add(TbGoodsDetail model) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(path + "add");
		modelAndView.addObject("model", model);
		// 单位
		modelAndView.addObject("companyType", dictSvc.getSelect("company", 0));
		modelAndView.addObject("operIdType", dictSvc.getSelect("handlerPerson", 0));
		modelAndView.addObject("goodsType", goodsSvc.getGoods(0));
		return modelAndView;
	}

	@RequestMapping("view")
	public ModelAndView view(Integer id) throws Exception {
		TbGoodsDetail model = svc.findFirst(TbGoodsDetail.class, id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(path + "view");
		modelAndView.addObject("model", model);
		// 单位
		modelAndView.addObject("companyName", dictSvc.getDictName(model.getCompany()));
		// 经手人
		modelAndView.addObject("operName", dictSvc.getDictName(model.getOperId()));
		// 物资
		modelAndView.addObject("goodsName", goodsSvc.getGoodsName(model.getGoodsId()));
		return modelAndView;
	}

	@RequestMapping("delete")
	public ModelAndView delete(Integer id, CommonForm form) throws Exception {
		TbGoodsDetail model = svc.findFirst(TbGoodsDetail.class, id);
		svc.delete(model);

		// 更新物资数量
		goodsSvc.updateGoodsCount(model.getType());
		return list(form);
	}

	@RequestMapping("edit")
	public ModelAndView edit(Integer id) throws Exception {
		TbGoodsDetail model = svc.findFirst(TbGoodsDetail.class, id);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(path + "edit");
		modelAndView.addObject("model", model);
		// 单位
		modelAndView.addObject("companyType", dictSvc.getSelect("company", model.getCompany()));
		modelAndView.addObject("operIdType", dictSvc.getSelect("handlerPerson", model.getOperId()));
		modelAndView.addObject("goodsType", goodsSvc.getGoods(model.getGoodsId()));
		return modelAndView;
	}

	@RequestMapping("save")
	public ModelAndView save(TbGoodsDetail model) throws Exception {
		if (model.getId() != null && model.getId() > 0) { // 更新
			TbGoodsDetail goods = svc.findFirst(TbGoodsDetail.class, model.getId());
			BeanUtils.copy(model, goods);

			svc.update(goods);
		} else { // 新增
			model.setCreateId(1);
			model.setCreateTime(getNow());
			svc.save(model);
		}
		// 更新物资数量
		goodsSvc.updateGoodsCount(model.getType());

		ModelAndView modelAndView = new ModelAndView();
		renderMessage(modelAndView, "保存成功");
		return modelAndView;

	}

	@RequestMapping("check")
	public ModelAndView check(Integer id) throws Exception {
		String sql = "select t.*,com.detail_name as companyName,oper.detail_name as operName,good.name as goodsName "
				+ " from tb_goods_detail t " //
				+ "  left join sys_dict_detail com on t.company=com.detail_id " //
				+ "  left join sys_dict_detail oper on t.oper_id=oper.detail_id " //
				+ "  left join tb_goods good on t.goods_id=good.id " //
				+ "where t.goods_id = ?";

		sql += " order by t.create_time desc ";
		List<List<Map<String, Object>>> list = svc.query(sql, SQLType.SQL, id);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(path + "list_check");
		modelAndView.addObject("list", list);
		return modelAndView;
	}
}
