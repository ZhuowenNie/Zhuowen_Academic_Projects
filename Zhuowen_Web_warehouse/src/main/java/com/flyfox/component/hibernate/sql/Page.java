package com.flyfox.component.hibernate.sql;

import java.util.List;

public class Page<T> implements java.io.Serializable {

	private static final long serialVersionUID = -3583679573095029583L;

	protected SQLType sqlType = SQLType.SQL;

	protected String sqlSelect;

	protected String sqlFrom;

	protected Paginator paginator;

	protected List<T> list;

	public Page() {
	}

	public Page(Paginator paginator) {
		this.paginator = paginator;
	}

	public int getPageNo() {
		return paginator.getPageNo();
	}

	public int getPageSize() {
		return paginator.getPageSize();
	}

	public int getTotalRecords() {
		return paginator.getTotalRecords();
	}

	public int getFirstResult() {
		return paginator.getFirstResult();
	}

	public int getMaxResults() {
		return paginator.getMaxResults();
	}

	public String getSqlSelect() {
		return sqlSelect;
	}

	public void setSqlSelect(String sqlSelect) {
		this.sqlSelect = sqlSelect;
	}

	public String getSqlFrom() {
		return sqlFrom;
	}

	public void setSqlFrom(String sqlFrom) {
		this.sqlFrom = sqlFrom;
	}

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public SQLType getSqlType() {
		return sqlType;
	}

	public void setSqlType(SQLType sqlType) {
		this.sqlType = sqlType;
	}

}
