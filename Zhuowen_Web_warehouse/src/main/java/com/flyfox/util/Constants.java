package com.flyfox.util;


/**
 * 常量类
 * 
 * @author flyfox 2012.08.08
 * @email 330627517@qq.com
 */
public class Constants {

	/**
	 * 底层调试标示
	 */
	public static final boolean DEBUG = Config.getToBoolean("CONSTANTS.DEBUG");
	/**
	 * 测试标示
	 */
	public static final boolean TEST = Config.getToBoolean("CONSTANTS.TEST");
	/**
	 * 默认分页
	 */
	public static final int DEFAULT_PAGE_SIZE = Config.getToInt("CONSTANTS.DEFAULT_PAGE_SIZE");
	
	/**
	 * session user
	 */
	public static final String SESSION_NAME = Config.getStr("ATTR.SESSION_NAME");

	/**
	 * 是否是移动设备
	 */
	public static final String SESSION_IS_MOILE = Config.getStr("ATTR.SESSION_IS_MOILE");

	/**
	 * model前缀
	 */
	public static final String PAGE_MODEL_NAME = Config.getStr("ATTR.MODEL_NAME");
	
	/**
	 * attr前缀
	 */
	public static final String PAGE_ATTR_NAME = Config.getStr("ATTR.ATTR_NAME");
}