package com.yuhi.mock.service;

import com.yuhi.mock.entity.MockMethod;
import com.yuhi.mock.exception.MockAccessException;

import java.util.Map;

/**
 * ClassName MockMethodApdate.
 * @coment: 模拟方法适配器
 * @user: 李森林
 * @category @author www.justintoForest@Gamil.com
 * @since: 2017-05-15 下午 05:18
 */
public interface MockMethodApdate {
    /**
     * @category 接口初始化.
     * @author www.justintoForest@Gamil.com
     * @since 2017-05-16 上午 09:58
     */
    void init(Map paramMap) throws MockAccessException;
    /**
     * @category 接口校验.
     * @author www.justintoForest@Gamil.com
     * @since 2017-05-15 下午 05:19
     */
    boolean validApi() throws MockAccessException;

    /**
     * @category 加载测试结果.
     * @author www.justintoForest@Gamil.com
     * @since 2017-05-15 下午 05:19
     */
    MockMethod loadMethod() throws MockAccessException;
}
