package org.zrx.springframework.samples.mvc.redirect;

import org.joda.time.LocalDate;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;

/**
 * Function:    RedirectController
 * Author:      zhangrixiong
 * DateTime:    2016/8/22 18:03
 */
@Controller
@RequestMapping("/redirect")
public class RedirectController {

    private final ConversionService conversionService;


    // @Inject 根据类型自动装配 构造函数注解
    @Inject
    public RedirectController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @RequestMapping(value = "/urilTemplate", method = RequestMethod.GET)
    public String uriTemplate(RedirectAttributes redirectAttrs) {
        redirectAttrs.addAttribute("account", "a123");  // Used as URI template variable
        redirectAttrs.addAttribute("date", new LocalDate(2011, 12, 31));  // Appended as a query parameter
        return "redirect:/redirect/{account}";
    }

    @RequestMapping(value="/uriComponentsBuilder", method=RequestMethod.GET)
    public String uriComponentsBuilder() {
        String date = this.conversionService.convert(new LocalDate(2011, 12, 12), String.class);
        UriComponents redirectUri = UriComponentsBuilder.fromPath("/redirect/{account}").queryParam("date", date)
                .build().expand("a123").encode();
        return "redirect:" + redirectUri.toUriString();
    }

    @RequestMapping(value = "/{account}", method = RequestMethod.GET)
    public String show(@PathVariable String account, @RequestParam(required=false) LocalDate date) {
        return "redirect/redirectResults";
    }
}
