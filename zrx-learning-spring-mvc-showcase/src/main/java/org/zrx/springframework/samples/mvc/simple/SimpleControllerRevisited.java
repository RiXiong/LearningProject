package org.zrx.springframework.samples.mvc.simple;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by R on 2016/4/23.
 */
@Controller
public class SimpleControllerRevisited {

    @RequestMapping(value="/simple/revisited", method= RequestMethod.GET, headers="Accept=text/plain")
    public @ResponseBody String simple() {
        return "Hello world revisited!";
    }
}
