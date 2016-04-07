package com.flyfox.modules.goods.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_goods")
public class TbGoods implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", length = 11)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private Integer type;

	@Column(name = "content")
	private String content;

	@Column(name = "status")
	private Integer status;

	@Column(name = "goods_count_in")
	private Integer goodsCountIn;

	@Column(name = "goods_count_out")
	private Integer goodsCountOut;

	@Column(name = "create_time")
	private String createTime;

	@Column(name = "create_id")
	private Integer createId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getGoodsCountIn() {
		return goodsCountIn;
	}

	public void setGoodsCountIn(Integer goodsCountIn) {
		this.goodsCountIn = goodsCountIn;
	}

	public Integer getGoodsCountOut() {
		return goodsCountOut;
	}

	public void setGoodsCountOut(Integer goodsCountOut) {
		this.goodsCountOut = goodsCountOut;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

}
