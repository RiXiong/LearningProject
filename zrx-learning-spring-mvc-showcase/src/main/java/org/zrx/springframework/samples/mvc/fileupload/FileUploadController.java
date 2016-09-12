package org.zrx.springframework.samples.mvc.fileupload;


import java.io.IOException;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.zrx.springframework.mvc.extensions.ajax.AjaxUtils;

/**
 * Function:    FileUploadController
 * Author:      zhangrixiong
 * DateTime:    2016/8/22 17:53
 */
@Controller
@RequestMapping("/fileupload")
public class FileUploadController {

    // ajax 提交表单
    @ModelAttribute
    public void ajaxAttribute(WebRequest request, Model model) {
        model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
    }

    // 表单提交
    @RequestMapping(method=RequestMethod.GET)
    public void fileUploadForm() {
        System.out.println("Hello World ..... ");
    }

    @RequestMapping(method=RequestMethod.POST)
    public void processUpload(@RequestParam MultipartFile file, Model model) throws IOException {
        model.addAttribute("message", "File '" + file.getOriginalFilename() + "' uploaded successfully");
    }
    
}
