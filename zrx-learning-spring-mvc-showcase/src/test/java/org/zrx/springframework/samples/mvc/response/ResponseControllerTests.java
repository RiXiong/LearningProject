package org.zrx.springframework.samples.mvc.response;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Function:    ResponseControllerTests
 * Author:      zhangrixiong
 * DateTime:    2016/8/23 15:38
 */
public class ResponseControllerTests {

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(new ResponseController()).build();
    }

    @Test
    public void responseBody() throws Exception {
        this.mockMvc.perform(get("/response/annotation"))
                .andExpect(status().isOk())
                .andExpect(content().string("The String ResponseBody"));
    }


    @Test
    public void responseCharsetAccept() throws Exception {
        this.mockMvc.perform(
                get("/response/charset/accept")
                        .accept(new MediaType("text", "plain", Charset.forName("UTF-8"))))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "\u3053\u3093\u306b\u3061\u306f\u4e16\u754c\uff01 (\"Hello world!\" in Japanese)"));
    }

    @Test
    public void responseCharsetProduce() throws Exception {
        this.mockMvc.perform(get("/response/charset/produce"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void responseEntityStatus() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/response/entity/status"))
                .andExpect(status().isForbidden())
                .andExpect(content().string(
                    "The String ResponseBody with custom header Content-Type=text/plain"));
    }

    @Test
    public void responseEntityHeader() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/response/entity/headers"))
                .andExpect(status().isOk()).andExpect(content().string(
                    "The String ResponseBody with custom header Content-Type=text/plain"));
    }
}
