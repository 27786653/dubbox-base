package com.yuhi.mock.service.impl;

import com.yuhi.mock.Constants;
import com.yuhi.mock.entity.MockMethod;
import com.yuhi.mock.exception.MockAccessException;
import com.yuhi.mock.service.MockMethodApdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * ClassName AbstractMockApdate.
 * @coment: 默认实现（加载基础配置）
 * @user: 李森林
 * @category @author www.justintoForest@Gamil.com
 * @since: 2017-05-16 上午 10:07
 */
public abstract class AbstractMockApdate  implements MockMethodApdate {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected MockMethod method;

    public AbstractMockApdate(){
        method=new MockMethod();
    }

    @Override
    public void init(Map paramMap) throws MockAccessException {
        try{
            method.setApiname(paramMap.get(Constants.API.API_NAME).toString());
            method.setMethodname(paramMap.get(Constants.API.METHOD_NAME).toString());
            method.setParamTypes(paramMap.get(Constants.API.METHOD_PARAMS).toString());
            method.setParamResult(paramMap.get(Constants.API.CALL_PARAMS_JSON).toString());
        }catch (Exception e){
            throw new MockAccessException("参数设置失败");
        }
    }

    @Override
    public abstract boolean validApi() throws MockAccessException;

    @Override
    public abstract MockMethod loadMethod() throws MockAccessException;
}
