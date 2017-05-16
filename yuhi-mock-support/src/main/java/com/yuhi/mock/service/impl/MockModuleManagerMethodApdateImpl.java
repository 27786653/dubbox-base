package com.yuhi.mock.service.impl;

import com.alibaba.fastjson.JSON;
import com.yuhi.mock.Constants;
import com.yuhi.mock.entity.MockMethod;
import com.yuhi.mock.exception.MockAccessException;
import com.yuhi.mock.service.MockMethodApdate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * ClassName MockModuleManagerMethodApdateImpl.
 * @coment: 模拟测试平台接入方法测试
 * @user: 李森林
 * @category @author www.justintoForest@Gamil.com
 * @since: 2017-05-15 下午 04:48
 */
public class MockModuleManagerMethodApdateImpl extends AbstractMockApdate {


    private String MockModuleManagerUrl="";


    public MockModuleManagerMethodApdateImpl(String mockModuleManagerUrl) {
        super();
        MockModuleManagerUrl = mockModuleManagerUrl;
        Assert.hasText(MockModuleManagerUrl);
    }

    // 首先我们发送一个请求，确认这个接口的这个方法是否在我们测试平台存在了
    @Override
    public boolean validApi() throws MockAccessException {
        String MockModuleJsonStr=loadJson(MockModuleManagerUrl);
        if(StringUtils.isEmpty(MockModuleJsonStr)||MockModuleJsonStr.length()==0)
        {
            logger.error("测试系统未注册该数据");
            return false;
        }
        return true;
    }

    //下面定义一个httpclient，用于发送Http请求，
    @Override
    public MockMethod loadMethod() throws MockAccessException {
        String MockModuleJsonStr=loadJson(MockModuleManagerUrl);
        MockModuleJson json = JSON.parseObject(MockModuleJsonStr,MockModuleJson.class);
        method.setResultType(json.getClasstype());
        method.setResult(json.getResultjson());
//     调用记录，参数识别，版本控制，
        return method;
    }

    /**
     * @category 加载json数据.
     * @author www.justintoForest@Gamil.com
     * @since 2017-05-16 上午 10:06
     */
    private static String loadJson (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            HttpURLConnection uc = (HttpURLConnection) urlObject.openConnection();
            uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            uc.setRequestProperty("Charset", "utf-8");
            uc.setRequestMethod("GET");
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setUseCaches(false);
            uc.connect();
            //读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    uc.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                json.append(line);
            }
            in.close();
            in = null;
            if (uc != null) {
                // 关闭连接
                uc.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    private class MockModuleJson{
        private String classtype;
        private String resultjson;

        public String getResultjson() {
            return resultjson;
        }

        public void setResultjson(String resultjson) {
            this.resultjson = resultjson;
        }

        public String getClasstype() {
            return classtype;
        }

        public void setClasstype(String classtype) {
            this.classtype = classtype;
        }
    }
}
