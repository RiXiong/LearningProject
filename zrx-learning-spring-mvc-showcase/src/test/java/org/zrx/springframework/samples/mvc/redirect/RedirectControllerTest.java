package org.zrx.springframework.samples.mvc.redirect;

import org.junit.Before;
import org.junit.Test;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Fynction:    R on 2016/9/5.
 * Author:      zhangrixiong
 * DateTime:    2016/9/5 21:51
 */
public class RedirectControllerTest {

    private MockMvc mockMvc;

    /**
     * 在HTTP/1.0 302 状态为 Moved Temporarily
     * 在HTTP/1.1 302 状态为 Found
     * https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        // isFound 标识302 原来标识302的 isMovedTemporarily 废弃
        this.mockMvc = standaloneSetup(new RedirectController(new DefaultFormattingConversionService()))
                .alwaysExpect(status().isFound()).build();
    }

    @Test
    public void uriTemplate() throws Exception {
//        this.mockMvc.perform(get("/redirect/uriTemplate"))
//                .andExpect(redirectedUrl("/redirect/a123?date=12%2F12%2F11"));
    }

    @Test
    public void uriComponentsBuilder() throws Exception {
        this.mockMvc.perform(get("/redirect/uriComponentsBuilder"))
                .andExpect(redirectedUrl("/redirect/a123?date=12/12/11"));
    }

    @Test
    public void show() throws Exception {

    }

}