package com.yuhi.mock.entity;

import com.yuhi.mock.exception.MockAccessException;
import com.yuhi.mock.service.MockMethodApdate;
import org.apache.commons.digester.Digester;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * Created by 李森林 on 2017-05-16.
 */
public class MockTestConfig {
    private static final Logger logger = LoggerFactory.getLogger(MockTestConfig.class);
    private String systemenv;

    private MockMethodApdate holder;

    public  MockTestConfig(){}

    public MockMethodApdate getHolder() {
        return holder;
    }

    public void setHolder(MockMethodApdate holder) {
        this.holder = holder;
    }

    public String getSystemenv() {
        return systemenv;
    }

    public void setSystemenv(String systemenv) {
        this.systemenv = systemenv;
    }


    public static MockTestConfig getinstance(){
        MockTestConfig cache= initConfigute();
        if(cache==null){
            logger.error("Initialization configuration failed!!!. Please configure the file [mock-test.xml] . No MOCK tests need to be done. Please remove!!");
            return null;
        }
        if(StringUtils.isEmpty(cache.getSystemenv())){
            logger.debug("No simulated environment variables are not configured，default environment：[DEV]");
            cache.setSystemenv(MockMethod.Type.DEV.getValue());
        }
        if(cache.getSystemenv().equals(MockMethod.Type.PRO.getValue())){
            logger.debug("Initialization configuration creation failed. Running environment: [PRO]");
            return null;
        }
        if(cache.getHolder()==null){
            logger.debug("Initialization configuration creation failed. Please configure [mock-test.xml]/[holder] node");
            return null;
        }
        return cache;
    }

    /**
     * @category 根据配置实例化配置对象.
     * @author www.justintoForest@Gamil.com
     * @since 2017-05-17 上午 09:39
     */
    private static MockTestConfig initConfigute(){
        InputStream config = MockTestConfig.class.getResourceAsStream("/mock-test.xml");
        Digester dig = new Digester();
        dig.setValidating(false);
        dig.addObjectCreate("mocktest",  MockTestConfig.class);
        dig.addSetProperties("mocktest");
        String key = "mocktest/holder";
        dig.addObjectCreate(key, "class", MockMethodApdate.class);
        dig.addSetProperties(key);
        dig.addBeanPropertySetter(key + "/?");
        dig.addSetNext(key, "setHolder");
        try{
            return (MockTestConfig)dig.parse(config);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(config);
        }
        return null;
    }

}
