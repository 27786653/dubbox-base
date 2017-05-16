package com.yuhi.test;

import com.yuhi.util.PropertiesUtil;
import com.yuhi.util.ReflectUtils;

/**
 * Created by 李森林 on 2017-05-12.
 */
public class TestReflectUtils {

    public static void main(String[] args) {
        System.out.print(ReflectUtils.getMethodParameterType("com.yuhi.test.aaa","serialize"));
        System.out.print(ReflectUtils.getMethodTypeReturnString("com.yuhi.test.aaa","serialize"));
        String result = new PropertiesUtil("E://application.properties").readLocalProperty("result");
        System.out.print(result);
    }
}
