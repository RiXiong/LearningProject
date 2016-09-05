package org.zrx.springframework.samples.mvc.mapping;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.zrx.springframework.samples.mvc.AbstractContextControllerTests;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Fynction:    R on 2016/9/5.
 * Author:      zhangrixiong
 * DateTime:    2016/9/5 0:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MappingControllerTest extends AbstractContextControllerTests {

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
    }

    @Test
    public void byPath() throws Exception {

    }

    @Test
    public void byPathPattern() throws Exception {

    }

    @Test
    public void byMethod() throws Exception {

    }

    @Test
    public void byParameter() throws Exception {

    }

    @Test
    public void byParameterNegation() throws Exception {

    }

    @Test
    public void byHeader() throws Exception {

    }

    @Test
    public void byHeaderNegation() throws Exception {

    }

    @Test
    public void byConsumes() throws Exception {

    }

    @Test
    public void byProducesJson() throws Exception {

    }

    @Test
    public void byProducesXml() throws Exception {

    }
}