package com.yuhi.mock.support.dubbo.support.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.alibaba.dubbo.container.spring.SpringContainer;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yuhi.mock.Constants;
import com.yuhi.mock.service.MockClient;
import com.yuhi.mock.service.MockMethodApdate;
import com.yuhi.mock.service.impl.LocalMockMethodApdateImpl;
import com.yuhi.util.ReflectUtils;
import com.yuhi.util.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName ServiceMockFilter.
 * @coment: 模拟提供服务过滤器
 * @user: 李森林
 * @category @author www.justintoForest@Gamil.com
 * @since: 2017-05-12 上午 11:45
 */
@Activate("ServiceMockFilter")
public class ServiceMockFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = null; //首先定义一个返回对象;
        String interfaceName = invoker.getUrl().getPath();     //获取接口名称
        String methodName = invocation.getMethodName(); //获取方法名称
        Object[] arguments =invocation.getArguments();        //获取参数，我举的例子是一个参数，实际可能有多个参数，多种类型,
        String paramJson = JSON.toJSONString(arguments, SerializerFeature.WriteClassName);//这里面可能有泛型,所以入参在序列化时要上json
        List<String> paramTypes = ReflectUtils.getMethodParameterType(interfaceName, methodName);     //获取所有的入参类型,这块要自己写反射处理
        Map paramMap = new HashMap();
        paramMap.put(Constants.API.API_NAME, interfaceName);
        paramMap.put(Constants.API.METHOD_NAME, methodName);
        paramMap.put(Constants.API.METHOD_PARAMS, paramTypes);
        paramMap.put(Constants.API.CALL_PARAMS_JSON, paramJson);
        Object mockData= MockClient.send(paramMap, "getInterfaceInfo");
        if(null!=mockData){
            result=new RpcResult(mockData);
        }else{
            //如果不存在，调用真实的接口获取返回值，
            result = invoker.invoke(invocation);   //调用真实接口
            //然后把接口信息存在我们的测试平台
        }
        return result;
    }
}
