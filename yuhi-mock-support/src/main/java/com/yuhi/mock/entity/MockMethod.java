package com.yuhi.mock.entity;

/**
 * ClassName MockMethod.
 * @coment:  模拟方法
 * @user: 李森林
 * @category @author www.justintoForest@Gamil.com
 * @since: 2017-05-15 上午 11:26
 */
public class MockMethod {
    //接口名（）
    private String apiname;
    //接口方法名
    private String methodname;
//   接口参数类型
    private String paramTypes;
//    接口参数值（json）
    private String paramResult;
//    测试结果
    private String result;
//    测试结果类型
    private String resultType;

    public String getApiname() {
        return apiname;
    }

    public void setApiname(String apiname) {
        this.apiname = apiname;
    }

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    public String getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(String paramTypes) {
        this.paramTypes = paramTypes;
    }

    public String getParamResult() {
        return paramResult;
    }

    public void setParamResult(String paramResult) {
        this.paramResult = paramResult;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
