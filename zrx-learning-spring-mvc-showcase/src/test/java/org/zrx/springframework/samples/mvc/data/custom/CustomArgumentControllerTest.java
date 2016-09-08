package org.zrx.springframework.samples.mvc.data.custom;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Fynction:    R on 2016/9/8.
 * Author:      zhangrixiong
 * DateTime:    2016/9/8 0:48
 */
public class CustomArgumentControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(new CustomArgumentController())
                .setCustomArgumentResolvers(new CustomArgumentResolver()).build();
    }

    @Test
    public void param() throws Exception {
        this.mockMvc.perform(get("/data/custom"))
                .andExpect(content().string("Got 'foo' request attribute value 'bar'"));
    }
}