package com.flyfox.modules.dict.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_dict")
public class SysDict implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dict_id", length = 11)
	private Integer dictId;

	@Column(name = "dict_name")
	private String dictName;

	@Column(name = "dict_type")
	private String dictType;

	@Column(name = "dict_remark")
	private String dictRemark;

	public Integer getDictId() {
		return dictId;
	}

	public void setDictId(Integer dictId) {
		this.dictId = dictId;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getDictRemark() {
		return dictRemark;
	}

	public void setDictRemark(String dictRemark) {
		this.dictRemark = dictRemark;
	}

}
