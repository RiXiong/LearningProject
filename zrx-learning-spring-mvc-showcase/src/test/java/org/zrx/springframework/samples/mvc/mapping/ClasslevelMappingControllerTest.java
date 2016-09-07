package org.zrx.springframework.samples.mvc.mapping;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.zrx.springframework.samples.mvc.AbstractContextControllerTests;


import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Fynction:    R on 2016/9/5.
 * Author:      zhangrixiong
 * DateTime:    2016/9/5 21:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ClasslevelMappingControllerTest extends AbstractContextControllerTests {

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
    }

    @Test
    public void byPath() throws Exception {
        this.mockMvc.perform(get("/class-mapping/path"))
                .andExpect(content().string("class-mapping by path!"));
    }

    @Test
    public void byPathPattern() throws Exception {
        this.mockMvc.perform(get("/class-mapping/path/wildcard"))
                .andExpect(content().string("class-mapping by path pattern ('/class-mapping/path/wildcard')"));
    }

    @Test
    public void byMethod() throws Exception {
        this.mockMvc.perform(get("/class-mapping/method"))
                .andExpect(content().string("class-mapping by path + method"));
    }

    @Test
    public void byParameter() throws Exception {
        this.mockMvc.perform(get("/class-mapping/parameter?foo=bar"))
                .andExpect(content().string("class-mapping by path + method + presence of query parameter!"));
    }

    @Test
    public void byNotParameter() throws Exception {
        this.mockMvc.perform(get("/class-mapping/parameter"))
                .andExpect(content().string("class-mapping by path + method + not presence of query parameter!"));
    }

    @Test
    public void byHeader() throws Exception {
        this.mockMvc.perform(get("/class-mapping/header").header("FooHeader", "foo"))
                .andExpect(content().string("class-mapping by path + method + presence of header!"));
    }

    @Test
    public void byHeaderNegation() throws Exception {
        this.mockMvc.perform(get("/class-mapping/header"))
                .andExpect(content().string("class-mapping by path + method + absence of header!"));
    }

    @Test
    public void byConsumes() throws Exception {
        this.mockMvc.perform(
                post("/class-mapping/consumes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"foo\": \"bar\", \"fruit\": \"apple\" }".getBytes()))
                .andExpect(content().string(startsWith("class-mapping by path + method + consumable media type (javaBean")));
    }

    @Test
    public void byProducesAcceptJson() throws Exception {
        this.mockMvc.perform(get("/class-mapping/produces").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.foo").value("bar"))
                .andExpect(jsonPath("$.fruit").value("apple"));
    }

    @Test
    public void byProducesAcceptXml() throws Exception {
        this.mockMvc.perform(get("/class-mapping/produces").accept(MediaType.APPLICATION_XML))
                .andExpect(xpath("/javaBean/foo").string("bar"))
                .andExpect(xpath("/javaBean/fruit").string("apple"));
    }

    /**
     *
     * @throws Exception
     * @see  MappingController#byProducesJson
     */
    @Test
    public void byProducesJsonExtension() throws Exception {
        this.mockMvc.perform(get("/class-mapping/produces.json"))
                .andExpect(jsonPath("$.foo").value("bar"))
                .andExpect(jsonPath("$.fruit").value("apple"));
    }

    @Test
    public void byProducesXmlExtension() throws Exception {
        this.mockMvc.perform(get("/class-mapping/produces.xml"))
                .andExpect(xpath("/javaBean/foo").string("bar"))
                .andExpect(xpath("/javaBean/fruit").string("apple"));
    }

}