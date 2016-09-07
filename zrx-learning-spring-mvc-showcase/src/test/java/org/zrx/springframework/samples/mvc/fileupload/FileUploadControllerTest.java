package org.zrx.springframework.samples.mvc.fileupload;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zrx.springframework.samples.mvc.AbstractContextControllerTests;


/**
 * Fynction:    R on 2016/9/5.
 * Author:      zhangrixiong
 * DateTime:    2016/9/5 21:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FileUploadControllerTest extends AbstractContextControllerTests {

    @Test
    public void readString() throws Exception {

        MockMultipartFile file = new MockMultipartFile("file", "orig", null, "bar".getBytes());

        webAppContextSetup(this.wac).build()
                .perform(fileUpload("/fileupload").file(file))
                .andExpect(model().attribute("message", "File 'orig' uploaded successfully"));
    }

}