package org.zrx.springframework.samples.mvc.mapping;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Function:    ClasslevelMappingController
 * Author:      zhangrixiong
 * DateTime:    2016/8/22 17:55
 */
@Controller
@RequestMapping("/class-mapping/*")
public class ClasslevelMappingController {

    @RequestMapping("/path")
    public @ResponseBody String byPath() {
        return "class-mapping by path!";
    }

    @RequestMapping(value="/path/*", method=RequestMethod.GET)
    public @ResponseBody String byPathPattern(HttpServletRequest request) {
        return "class-mapping by path pattern ('" + request.getRequestURI() + "')";
    }

    @RequestMapping(value="/method", method=RequestMethod.GET)
    public @ResponseBody String byMethod() {
        return "class-mapping by path + method";
    }

    @RequestMapping(value="/parameter", method=RequestMethod.GET, params="foo")
    public @ResponseBody String byParameter() {
        return "class-mapping by path + method + presence of query parameter!";
    }

    @RequestMapping(value="/parameter", method=RequestMethod.GET, params="!foo")
    public @ResponseBody String byParameterNegation() {
        return "class-mapping by path + method + not presence of query parameter!";
    }

    @RequestMapping(value="/header", method=RequestMethod.GET, headers="FooHeader=foo")
    public @ResponseBody String byHeader() {
        return "class-mapping by path + method + presence of header!";
    }

    @RequestMapping(value="/notheader", method=RequestMethod.GET, headers="!FooHeader")
    public @ResponseBody String byHeaderNegation() {
        return "class-mapping by path + method + absence of header!";
    }


    @RequestMapping(value="/consumes", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody String byConsumes(@RequestBody JavaBean javaBean) {
        return "class-mapping by path + method + consumable media type (javaBean '" + javaBean + "')";
    }

    @RequestMapping(value="/produces", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody JavaBean byProduces() {
        return new JavaBean();
    }

    @RequestMapping(value="/produces", method=RequestMethod.GET,produces = "application/xml")
    public @ResponseBody JavaBean byProducesXML() {
        return new JavaBean();
    }

}
