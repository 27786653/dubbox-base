package com.yuhi.mock.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName MockAccessException.
 * @coment: 模拟异常处理类
 * @user: 李森林
 * @category @author www.justintoForest@Gamil.com 
 * @since: 2017-05-15 上午 11:38
 */
public class MockAccessException extends IllegalAccessException {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    public MockAccessException(String s) {
        super(s);
    }
}
