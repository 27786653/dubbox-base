package Digester;
import com.yuhi.mock.entity.MockMethod;
import com.yuhi.mock.entity.MockTestConfig;
import com.yuhi.mock.service.MockMethodApdate;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.ExtendedBaseRules;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import java.io.InputStream;

/**
 * Created by 李森林 on 2017-05-16.
 */
public class TestMockDigester {

    private static final Logger logger = LoggerFactory.getLogger(TestMockDigester.class);
    @Test
    public  void  initConfigute() throws SAXNotRecognizedException, SAXNotSupportedException {
        InputStream config = TestMockDigester.class.getResourceAsStream("/mock-test.xml");
        Digester dig = new Digester();
        //指定它不要用DTD验证XML文档的合法性——这是因为我们没有为XML文档定义DTD
        dig.setValidating(false);
//        dig.setRules(new ExtendedBaseRules());
//        dig.push(cache);

//        // 从library标签开始解析,并新建一个Library对象做为根对象
//        dig.addObjectCreate("mocktest/systemenv",MockTestConfig.class);
//        // 根据library标签属性值设置对象的属性,一次可以设置多个属性
//        dig.addSetProperty("mocktest", "systemenv","test");
//        dig.addSetNext("mocktest", "setSystemenv");
        dig.addObjectCreate("mocktest",  MockTestConfig.class);
        dig.addSetProperties("mocktest");
//        dig.addBeanPropertySetter("mocktest/?");

        String key = "mocktest/holder";
        dig.addObjectCreate(key, "class", MockMethodApdate.class);
        dig.addSetProperties(key);
        dig.addBeanPropertySetter(key + "/?");
        dig.addSetNext(key, "setHolder");

        try{
            MockTestConfig cache2=(MockTestConfig)dig.parse(config);
            System.out.print("");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            IOUtils.closeQuietly(config);
        }
    }

    @Test
    public void testeumn(){
        System.out.print(MockMethod.Type.DEV.getValue());
    }
}
