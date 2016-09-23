package org.zrx.springframework.samples.mvc.data.custom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by R on 2016/4/24.
 */
@Controller
public class CustomArgumentController {

    @ModelAttribute
    void beforeInvokingHandlerMethod(HttpServletRequest request) {
        request.setAttribute("foo", "bar");
    }

    @RequestMapping(value="/data/custom", method= RequestMethod.GET)
    public @ResponseBody String custom(@RequestAttribute("foo") String foo) {
        return "Got 'foo' request attribute value '" + foo + "'";
    }

}
