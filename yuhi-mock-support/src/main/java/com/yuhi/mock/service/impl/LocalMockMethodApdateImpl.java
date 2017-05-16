package com.yuhi.mock.service.impl;

import com.yuhi.mock.Constants;
import com.yuhi.mock.entity.MockMethod;
import com.yuhi.mock.exception.MockAccessException;
import com.yuhi.mock.service.MockMethodApdate;
import com.yuhi.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * ClassName LocalMockMethodApdateImpl.
 * @coment: 基于properties实现方法测试
 * @user: 李森林
 * @category @author www.justintoForest@Gamil.com
 * @since: 2017-05-15 上午 11:31
 */
public class LocalMockMethodApdateImpl  extends AbstractMockApdate{

    private String localpropertiesPath="E://application.properties";
    private String ResultJsonKey="result";
    private String ResultTypeKey="resultType";

    private PropertiesUtil pu;

    public LocalMockMethodApdateImpl() {
        super();
        logger.debug("使用默认配置路径！！");
        pu = new PropertiesUtil(localpropertiesPath);
    }

    /**
     * @category 本地磁盘路径加载配置.
     * @author www.justintoForest@Gamil.com
     * @since 2017-05-15 上午 11:41
     */
    public LocalMockMethodApdateImpl(String localpropertiesPath) throws MockAccessException {
        super();
        this.localpropertiesPath = localpropertiesPath;
        if(StringUtils.isEmpty(localpropertiesPath))throw new MockAccessException("测试配置文件路径异常！！");
        pu = new PropertiesUtil(localpropertiesPath);
    }

    @Override
    public boolean validApi() throws MockAccessException {
        return true;
    }


    @Override
    public MockMethod loadMethod()  throws MockAccessException{
        String resultjson=pu.readLocalProperty(ResultJsonKey);
        String resulttype=pu.readLocalProperty(ResultTypeKey);
        method.setResultType(resulttype);
        method.setResult(resultjson);
        return method;
    }

    public String getResultJsonKey() {
        return ResultJsonKey;
    }

    public void setResultJsonKey(String resultJsonKey) {
        ResultJsonKey = resultJsonKey;
    }

    public String getResultTypeKey() {
        return ResultTypeKey;
    }

    public void setResultTypeKey(String resultTypeKey) {
        ResultTypeKey = resultTypeKey;
    }
}
