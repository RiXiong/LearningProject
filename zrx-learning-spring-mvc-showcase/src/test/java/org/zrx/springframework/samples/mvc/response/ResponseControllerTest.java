package org.zrx.springframework.samples.mvc.response;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


/**
 * Fynction:    R on 2016/9/5.
 * Author:      zhangrixiong
 * DateTime:    2016/9/5 21:52
 */
public class ResponseControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = standaloneSetup(new ResponseController()).build();
    }

    @Test
    public void responseBody() throws Exception {
        this.mockMvc.perform(get("/response/annotation"))
                .andExpect(status().isOk())
                .andExpect(content().string("The String ResponseBody"));
    }

    @Test
    public void responseAcceptHeaderCharset() throws Exception {
        this.mockMvc.perform(
                get("/response/charset/accept")
                        .accept(new MediaType("text", "plain", Charset.forName("UTF-8"))))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "\u3053\u3093\u306b\u3061\u306f\u4e16\u754c\uff01 (\"Hello world!\" in Japanese)"));
    }

    @Test
    public void responseProducesConditionCharset() throws Exception {
        this.mockMvc.perform(get("/response/charset/produce"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "\u3053\u3093\u306b\u3061\u306f\u4e16\u754c\uff01 (\"Hello world!\" in Japanese)"));
    }

    @Test
    public void responseEntityStatusCode() throws Exception {
        this.mockMvc.perform(get("/response/entity/status"))
                .andExpect(status().isForbidden())
                .andExpect(content().string(
                        "The String ResponseBody with custom status code(403 Forbidden)"));
    }

    @Test
    public void responseEntityCustomHeaders() throws Exception {
        this.mockMvc.perform(get("/response/entity/headers"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "The String ResponseBody with custom header Content-Type=text/plain"));
    }

}