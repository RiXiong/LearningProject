package org.zrx.springframework.samples.mvc.views;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.zrx.springframework.samples.mvc.AbstractContextControllerTests;

/**
 * Fynction:    R on 2016/9/5.
 * Author:      zhangrixiong
 * DateTime:    2016/9/5 21:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ViewsControllerTest extends AbstractContextControllerTests {

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
    }

    @Test
    public void prepare() throws Exception {
        this.mockMvc.perform(get("/views/html"))
                .andExpect(view().name(containsString("views/html")))
                .andExpect(model().attribute("foo", "bar"))
                .andExpect(model().attribute("fruit", "apple"))
                .andExpect(model().size(2));
    }

    @Test
    public void usingRequestToViewnameTranslator() throws Exception {
        this.mockMvc.perform(get("/views/viewName"))
                .andExpect(view().name(containsString("views/viewName")))
                .andExpect(model().attribute("foo", "bar"))
                .andExpect(model().attribute("fruit", "apple"))
                .andExpect(model().size(2));
    }

    @Test
    public void pathVars() throws Exception {
        this.mockMvc.perform(get("/views/pathVariables/bar/apple"))
                .andExpect(view().name(containsString("views/html")));
    }

    @Test
    public void dataBinding() throws Exception {
        this.mockMvc.perform(get("/views/dataBinding/bar/apple"))
                .andExpect(view().name(containsString("views/dataBinding")));
    }

}