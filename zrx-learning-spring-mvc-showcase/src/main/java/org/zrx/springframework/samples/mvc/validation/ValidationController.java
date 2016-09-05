package org.zrx.springframework.samples.mvc.validation;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Function:    ValidationController
 * Author:      zhangrixiong
 * DateTime:    2016/8/22 18:04
 */
@Controller
public class ValidationController {

    // 执行限制 JavaBean 参数 ，在类苦衷提供jsr-330
    @RequestMapping("/validate")
    public @ResponseBody String validate(@Valid JavaBean bean, BindingResult result) {
        if (result.hasErrors()) {
            return "Object has validation errors ";
        } else {
            return "No error";
        }
    }

}
