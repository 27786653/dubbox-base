package com.yuhi.base;

/**
 * ClassName Constants.
 * @coment: 常量
 * @user: 李森林
 * @category @author justintoForest@Gamil.com
 * @since: 2017-05-05 下午 04:53
 */
public class Constants {

	public interface System{
		String Exception_Head = "很抱歉报错了 :";
		/** 客户端语言 */
		String USERLANGUAGE = "userLanguage";
		/** 当前用户 */
		String CURRENT_USER = "ACTIVEUSER";
		/** 在线用户数量 */
		String ALLUSER_NUMBER = "ALLUSER_NUMBER";
		/** 登录用户数量 */
		String USER_NUMBER = "USER_NUMBER";
		/** 上次请求地址 */
		String PREREQUEST = "PREREQUEST";
		/** 上次请求时间 */
		String PREREQUEST_TIME = "PREREQUEST_TIME";
		/** 非法请求次数 */
		String MALICIOUS_REQUEST_TIMES = "MALICIOUS_REQUEST_TIMES";
		/** 缓存命名空间 */
		String CACHE_NAMESPACE = "system:";
		/** 失效用户标记*/
		String SESSION_FORCE_LOGOUT_KEY = "SESSION_FAILED";
		String SESSION_FORCE_LOGOUT_PARAM = "forceLogout";
		/** 验证码标记*/
		String SESSION_VALIDATE_CODE = "validateCode";
		/** 登录异常信息*/
		String LOGIN_ERROR_MSG = "error";

		String ERROR_CODE_PREFIX = "error_code_";   // 属性文件错误码前缀
		String REQUEST_PARAM_NAME = "params";       // 鉴权接口的请求参数名
	}
	/**  授权 */
	public interface authentication{
		/** 授权中心key列表*/
		String AUTHENTICATION_USER_ID = "userid";
		String AUTHENTICATION_USER_NAME = "username";
		String AUTHENTICATION_USER_SYSTEMCODE = "systemcode";
		String AUTHENTICATION_USER_SALT = "salt";
		String AUTHENTICATION_USER_POSTID = "postid";
	}
	/**  上传 */
	public interface upload{
		/**  上传文件*/
		String UPFILE_KEY = "upfile";
		String UPLOAD_IMAGE = "upload/image";
		String JSUCCESS = "{\"success\":true}";
		String JERROR = "{\"success\":flase}";
		String SUCCESS = "success";
		String ERROR = "error";
		String STATE = "state";
		String URL = "url";
		String NAME = "name";
		String ORIGINAL = "original";
		String PIC = "pic";
		String FILE = "file";
		String UPLOAD_FILE = "upload/file";
	}

	/**  代码生成*/
	public interface GenCode{
		String ENTITY_FILE_FTL = "entity.ftl";
		String DAO_FILE_FTL = "dao.ftl";
		String DAOIMPL_FILE_FTL = "daoimpl.ftl";
		String SERVICE_FILE_FTL = "service.ftl";
		String SERVICEIMPL_FILE_FTL = "serviceimpl.ftl";
		String CONTROLLER_FILE_FTL = "controller.ftl";
		String COMMONJS_FILE_FTL = "commonjs.ftl";
		String HTML_FILE_FTL = "listhtml.ftl";
		String HTMLADD_FILE_FTL = "listadd.ftl";
	}


	/**  代码生成*/
	public interface Wechar{
		/**微信二维码路径*/
		String WECHARURL = "/wc/history/";
	}


}
