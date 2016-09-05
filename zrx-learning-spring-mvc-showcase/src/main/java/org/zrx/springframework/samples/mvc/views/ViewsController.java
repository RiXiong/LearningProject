package org.zrx.springframework.samples.mvc.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Function:    ViewsController
 * Author:      zhangrixiong
 * DateTime:    2016/8/22 18:05
 */
@Controller
@RequestMapping("/views/*")
public class ViewsController {

    @RequestMapping(value = "html", method= RequestMethod.GET)
    public String prepare(Model model){
        model.addAttribute("foo", "bar");
        model.addAttribute("fruit", "apple");
        return "view/html";
    }

    @RequestMapping(value = "/viewName", method=RequestMethod.GET)
    public void usingRequestToViewnameTranslator(Model model) {
        model.addAttribute("foo", "bar");
        model.addAttribute("fruit", "apple");
    }

    @RequestMapping(value = "pathVariables/{foo}/{fruit}", method = RequestMethod.GET)
    public String pathVars(@PathVariable String foo, @PathVariable String fruit) {
       // 并不需要 把 @PathVariable String foo 和 fruit 添加到 model 中
       // 他们会在 退送到前端时，会自动合并到 model
       return "views/html";
    }

    @RequestMapping(value="dataBinding/{foo}/{fruit}", method = RequestMethod.GET)
    public String dataBinding(@Valid JavaBean javaBean, Model model) {
        // JavaBean "foo" and "fruit" properties populated from URI variables
        return "views/dataBinding";
    }
}
