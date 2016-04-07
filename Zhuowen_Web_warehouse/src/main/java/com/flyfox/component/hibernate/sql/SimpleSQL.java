package com.flyfox.component.hibernate.sql;


/**
 * 简单sql
 * 
 * 2015年4月2日 下午8:45:39 flyfox 330627517@qq.com
 */
public class SimpleSQL {

	protected String sql = "";

	protected SQLType sqlType = SQLType.SQL;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public SQLType getSqlType() {
		return sqlType;
	}

	public void setSqlType(SQLType sqlType) {
		this.sqlType = sqlType;
	}
}
