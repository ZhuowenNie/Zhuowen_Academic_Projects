package com.flyfox.component.spring.form;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.flyfox.component.hibernate.sql.Paginator;
import com.flyfox.util.NumberUtils;

public class BaseForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private Paginator paginator;
	private Map<String, String> attr = new LinkedHashMap<String, String>();

	public Paginator getPaginator() {
		if (paginator == null) {
			paginator = new Paginator();
		}
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	public Map<String, String> getAttr() {
		return attr;
	}

	public void setAttr(Map<String, String> attr) {
		this.attr = attr;
	}

	public String get(String key) {
		return attr.get(key);
	}

	public int getInt(String key) {
		return NumberUtils.parseInt(attr.get(key));
	}

	public long getLong(String key) {
		return NumberUtils.parseLong(attr.get(key));
	}

	public double getDbl(String key) {
		return NumberUtils.parseDbl(attr.get(key));
	}
}
