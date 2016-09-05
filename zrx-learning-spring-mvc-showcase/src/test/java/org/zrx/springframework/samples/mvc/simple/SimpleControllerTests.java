package org.zrx.springframework.samples.mvc.simple;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Function:    SimpleControllerTests
 * Author:      Apple
 * DateTime:    2016/8/22 16:52
 * 测试用例尽可能的覆盖全面，避免出现垃圾代码
 */
public class SimpleControllerTests {

    /**
     * 测试 SimpleController() 类
     * @throws Exception
     */
    @Test
    public void simple() throws Exception {
        standaloneSetup(new SimpleController()).build()
                .perform(get("/simple"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(content().string("Hello world!"));
    }

}
