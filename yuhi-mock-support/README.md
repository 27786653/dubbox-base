根据博文https://testerhome.com/topics/7908
http://www.jianshu.com/p/c128ed5c394e?utm_campaign=hugo&utm_medium=reader_share&utm_content=note&utm_source=weixin-friends


1.DUbboFilter拦截请求，并且提取APi的相关信息
2.根据配置文件mock-test.xml初始化MockTestConfig模拟测试配置对象
包含：运行环境变量和MOCk测试实现
3.判断API配置项是否配置测试，如果存在即可调用MOCK进行测试，返回mock结果