package org.zrx.springframework.samples.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * Function:    测试用例抽象类，加载spring 上下文配置 servlet-context.xml
 * Author:      zhangrixiong
 * DateTime:    2016/8/22 23:22
 *
 * 配置  spring 应用上下文
 */
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
public class AbstractContextControllerTests {

    @Autowired
    protected WebApplicationContext wac;

}
