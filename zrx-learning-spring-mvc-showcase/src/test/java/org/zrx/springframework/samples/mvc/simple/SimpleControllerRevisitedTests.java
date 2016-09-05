package org.zrx.springframework.samples.mvc.simple;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * Function:    SimpleControllerRevisitedTests
 * Author:      Apple
 * DateTime:    2016/8/22 16:52
 */
public class SimpleControllerRevisitedTests {

    /**
     * 通过mock 框架对springMVC 进行测试
     * @throws Exception
     */
    @Test
    public void simple() throws Exception {
        standaloneSetup(new SimpleControllerRevisited()).build()
                .perform(get("/simple/revisited").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain"))
                .andExpect(content().string("Hello world revisited!"));
    }
}
