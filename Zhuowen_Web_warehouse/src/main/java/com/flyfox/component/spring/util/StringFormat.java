package com.flyfox.component.spring.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串格式化工具类. StringFormat
 * 
 * @author Jerry Sun
 * @date 2011-2-28
 */
public class StringFormat {
	
	static final Logger logger = LoggerFactory.getLogger(StringFormat.class);

	/**
	 * String format 根据后面传的参数格式化第一个参数的字符串.
	 * 
	 * @param str
	 * @param args
	 * @return 格式化后的字符串.
	 */
	public static String format(String str, Object... args) {
		if (str == null || "".equals(str))
			return "";
		if (args.length == 0) {
			return str;
		}
		String result = str;
		java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\{(\\d+:{0,1}@{0,1}\\w*)\\}");
		java.util.regex.Matcher m = p.matcher(str);

		while (m.find()) {
			int index = Integer.parseInt(m.group(1));
			if (index < args.length) {
				result = result.replace(m.group(), args[index].toString());
			}
		}
		return result;
	}

	/**
	 * 格式化危险字符
	 * 
	 * @param str
	 * @param args
	 * @return 格式化后的字符串.
	 */
	public static String replaceFilter(String str, Object... args) {
		if (str == null || "".equals(str))
			return "";
		if (args.length == 0) {
			return str;
		}
		try {
			if (args.length % 2 == 0) {
				for (int i = 0; i < args.length; i += 2) {
					str = str.replace(args[i].toString(), args[i + 1].toString());
				}
			}
		} catch (Exception ex) {
			// System.out.println(ex.getMessage());
			logger.error(ex.toString(), ex);

		}
		return str;
	}

	/**
	 * 去除order by
	 * 
	 * 2015年4月2日 下午8:54:37
	 * flyfox 330627517@qq.com
	 * @param sql
	 * @return
	 */
	public static String replaceFormatSqlOrderBy(String sql) {
		sql = sql.replaceAll("(\\s)+", " ");
		int index = sql.toLowerCase().lastIndexOf("order by");
		if (index > sql.toLowerCase().lastIndexOf(")")) {
			String sql1 = sql.substring(0, index);
			String sql2 = sql.substring(index);
			sql2 = sql2
					.replaceAll(
							"[oO][rR][dD][eE][rR] [bB][yY] [\u4e00-\u9fa5a-zA-Z0-9_.]+((\\s)+(([dD][eE][sS][cC])|([aA][sS][cC])))?(( )*,( )*[\u4e00-\u9fa5a-zA-Z0-9_.]+(( )+(([dD][eE][sS][cC])|([aA][sS][cC])))?)*",
							"");
			return sql1 + sql2;
		}
		return sql;
	}

}
