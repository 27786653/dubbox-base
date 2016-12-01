package com.yuhi.exception;
/**
 * 数据操作层异常
 * @author 李森林
 *
 */
public class jdbcException extends BaseException {

	public jdbcException(String defaultMessage) {
		super(defaultMessage);
	}
	public jdbcException(String module, String code, Object[] args) {
		super(module, code, args, null);
    }

    public jdbcException(String module, String defaultMessage) {
    	super(module, null, null, defaultMessage);
    }

    public jdbcException(String code, Object[] args) {
    	super(null, code, args, null);
    }

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
