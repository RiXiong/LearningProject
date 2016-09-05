package org.zrx.springframework.samples.mvc.validation;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Fynction:    R on 2016/9/5.
 * Author:      zhangrixiong
 * DateTime:    2016/9/5 21:52
 */
public class ValidationControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = standaloneSetup(new ValidationController()).alwaysExpect(status().isOk()).build();
    }

    /**
     * test<MethodUnderTest>_<state>
     *
     * @throws Exception
     */
    @Test
    public void validate_Success() throws Exception {
        this.mockMvc.perform(get("/validate?number=3&date=2029-07-04"))
                .andExpect(content().string("No errors"));
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void validate_Errors() throws Exception {
        this.mockMvc.perform(get("/validate?number=3&date=2010-07-01"))
                .andExpect(content().string("Object has validation errors"));
    }
}