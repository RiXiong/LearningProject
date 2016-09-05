package org.zrx.springframework.samples.mvc.messageconverters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.zrx.springframework.samples.mvc.AbstractContextControllerTests;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Fynction:    R on 2016/9/4.
 * Author:      zhangrixiong
 * DateTime:    2016/9/4 22:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageConvertersControllerTest  extends AbstractContextControllerTests {

    private static String URI = "/messageconverters/{action}";
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                      .alwaysExpect(status().isOk()).build();
    }

    @Test
    public void readString() throws Exception {
        this.mockMvc.perform(post(URI,"string").content("foo".getBytes()))
                .andExpect(content().string("Read string 'foo'"));
    }

    @Test
    public void writeString() throws Exception {
        this.mockMvc.perform(get(URI, "string"))
                .andExpect(content().string("Wrote a string"));
    }

    @Test
    public void readForm() throws Exception {
        this.mockMvc.perform(
                post(URI, "form")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("foo", "bar")
                        .param("fruit", "apple"))
                        .andExpect(content().string("Read x-www-form-urlencoded: JavaBean {foo=[bar], fruit=[apple]}"));
    }

    @Test
    public void writeForm() throws Exception {
        this.mockMvc.perform(get(URI, "form"))
                .andExpect(content().contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(content().string("foo=bar&fruit=apple"));
    }

    private static String XML =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                    "<javaBean><foo>bar</foo><fruit>apple</fruit></javaBean>";

    @Test
    public void readXml() throws Exception {
        this.mockMvc.perform(
                post(URI, "xml")
                        .contentType(MediaType.APPLICATION_XML)
                        .content(XML.getBytes()))
                .andExpect(content().string("Read from XML: JavaBean {foo=[bar], fruit=[apple]}"));
    }

    @Test
    public void writeXml() throws Exception {
        this.mockMvc.perform(get(URI, "xml").accept(MediaType.APPLICATION_XML))
                .andExpect(content().xml(XML));
    }

    @Test
    public void readJson() throws Exception {
        this.mockMvc.perform(
                post(URI, "json")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"foo\": \"bar\", \"fruit\": \"apple\" }".getBytes()))
                .andExpect(content().string("Read from JSON: JavaBean {foo=[bar], fruit=[apple]}"));
    }

    @Test
    public void writeJson() throws Exception {
        this.mockMvc.perform(get(URI, "json").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.foo").value("bar"))
                .andExpect(jsonPath("$.fruit").value("apple"));
    }

    @Test
    public void writeJson2() throws Exception {
        this.mockMvc.perform(get(URI, "json").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.foo").value("bar"))
                .andExpect(jsonPath("$.fruit").value("apple"));
    }
}