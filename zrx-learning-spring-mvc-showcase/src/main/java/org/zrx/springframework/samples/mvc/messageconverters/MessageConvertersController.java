package org.zrx.springframework.samples.mvc.messageconverters;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Function:    MessageConvertersController —— 消息转换
 * Author:      zhangrixiong
 * DateTime:    2016/8/22 17:57
 */
@Controller
@RequestMapping("/messageconverters")
public class MessageConvertersController {

    // StringHttpMessageConverter

    public String readString(@RequestBody String string) {
        return "读一个String'" + string + "'";
    }

    @RequestMapping(value = "/string", method = RequestMethod.GET)
    public @ResponseBody String writeString() {
        return "写一个String";
    }

    // Form encoded data (application/x-www-form-urlencoded)

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String readForm(@ModelAttribute JavaBean bean) {
        return "Read x-www-form-urlencoded: " + bean;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public @ResponseBody  MultiValueMap<String, String> writeForm() {
        MultiValueMap<String,String> map = new LinkedMultiValueMap<String, String>();
        map.add("foo", "bar");
        map.add("fruit", "apple");
        return map;
    }

    // Jaxb2RootElementHttpMessageConverter (requires JAXB2 on the classpath - useful for serving clients that expect to work with XML)

    @RequestMapping(value = "/xml", method = RequestMethod.POST)
    public @ResponseBody String readXml(@RequestBody JavaBean bean) {
        return "Read from XML: " + bean;
    }

    @RequestMapping(value="/xml", method=RequestMethod.GET)
    public @ResponseBody JavaBean writeXml() {
        return new JavaBean("bar", "apple");
    }

    // MappingJacksonHttpMessageConverter (requires Jackson on the classpath - particularly useful for serving JavaScript clients that expect to work with JSON)

    @RequestMapping(value="/json", method=RequestMethod.POST)
    public @ResponseBody String readJson(@Valid @RequestBody JavaBean bean) {
        return "Read from JSON: " + bean;
    }

    @RequestMapping(value="/json", method=RequestMethod.GET)
    public @ResponseBody JavaBean writeJson() {
        return new JavaBean("bar", "apple");
    }

    // AtomFeedHttpMessageConverter (requires Rome on the classpath - useful for serving Atom feeds)


}
