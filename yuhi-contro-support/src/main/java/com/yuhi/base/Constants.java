package com.yuhi.base;

/**
 * 常量表
 * 
 */
public interface Constants {
	
	public static final String Exception_Head = "很抱歉报错了 :";
	/** 客户端语言 */
	public static final String USERLANGUAGE = "userLanguage";
	/** 当前用户 */
	public static final String CURRENT_USER = "ACTIVEUSER";
	/** 在线用户数量 */
	public static final String ALLUSER_NUMBER = "ALLUSER_NUMBER";
	/** 登录用户数量 */
	public static final String USER_NUMBER = "USER_NUMBER";
	/** 上次请求地址 */
	public static final String PREREQUEST = "PREREQUEST";
	/** 上次请求时间 */
	public static final String PREREQUEST_TIME = "PREREQUEST_TIME";
	/** 非法请求次数 */
	public static final String MALICIOUS_REQUEST_TIMES = "MALICIOUS_REQUEST_TIMES";
	/** 缓存命名空间 */
	public static final String CACHE_NAMESPACE = "system:";
	/** 失效用户标记*/
	public static final String SESSION_FORCE_LOGOUT_KEY = "SESSION_FAILED";
	public static final String SESSION_FORCE_LOGOUT_PARAM = "forceLogout";
	/** 验证码标记*/
	public static final String SESSION_VALIDATE_CODE = "validateCode";
	/** 登录异常信息*/
	public static final String LOGIN_ERROR_MSG = "error";
	/** 授权中心key列表*/
	public static final String AUTHENTICATION_USER_ID = "userid";
	public static final String AUTHENTICATION_USER_NAME = "username";
	public static final String AUTHENTICATION_USER_SYSTEMCODE = "systemcode";
	public static final String AUTHENTICATION_USER_SALT = "salt";
	public static final String AUTHENTICATION_USER_POSTID = "postid";
}
