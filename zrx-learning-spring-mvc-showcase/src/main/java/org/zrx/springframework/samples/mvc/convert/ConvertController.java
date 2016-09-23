package org.zrx.springframework.samples.mvc.convert;

import java.util.Collection;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Function:    ConvertController,实现SpringMVC 参数的格式转换化
 * Author:      zhangrixiong
 * DateTime:    2016/8/22 17:42
 */

@Controller
@RequestMapping("/convert")
public class ConvertController {

    /**
     * Api: {url}/convert/primitive?value=3
     *
     * @param value
     * @return
     */
    @RequestMapping("/primitive")
    public @ResponseBody String primitive(@RequestParam Integer value) {
        return "Converted primitive " + value;
    }

    /**
     *
     * Api: {url}/convert/date/2010-07-04
     *
     * <p>
     *    requires Joda-Time on the classpath
     *
     * @param value
     * @return
     */
    @RequestMapping("/date/{value}")
    public @ResponseBody String date(@PathVariable @DateTimeFormat(iso=ISO.DATE) Date value) {
        return "Converted date " + value;
    }

    /**
     * Api: {url}/convert/collection?values=1&values=2&values=3&values=4&values=5
     *   or
     * Api: {url}/convert/collection?values=1,2,3,4,5
     *
     * <p>
     *     SpringMVC实现 参数为 整形数组
     *
     * @param values
     * @return
     */
    @RequestMapping("/collection")
    public @ResponseBody String collection(@RequestParam Collection<Integer> values) {
        return "Converted collection " + values;
    }

    /**
     * Api: {url}/convert/formattedCollection?values=2016-08-01&values=2016-08-01&values=2016-08-01&values=4&values=2016-08-01
     *   or
     * Api: {url}/convert/formattedCollection=values=2016-08-01,2016-08-01,2016-08-01,2016-08-01,2016-08-01
     *
     * <p>
     *     SpringMVC实现 参数为 Date 数组
     *
     * @param values
     * @return
     */
    @RequestMapping("/formattedCollection")
    public @ResponseBody String formattedCollection(@RequestParam @DateTimeFormat(iso=ISO.DATE) Collection<Date> values) {
        return "Converted formatted collection " + values;
    }

    /**
     * Api: {url}/convert/bean?primitive=3  // JavaBean 对象实例中的 的 整形常量
     *     or
     * Api: {url}/convert/bean?date=2010-07-01  // JavaBean 对象实例中的 时间 对象常量
     *    or
     * Api: {url}/convert/bean?masked=(205) 333-3333  // JavaBean 对象中实例中的 String 类型 并且通过注解 限制 String 格式
     *    or
     * Api: {url}/convert/bean?list[0]=1&list[1]=2&list[2]=3 // JavaBean 对象实例中， list 类型 参数
     *   or
     * Api: {url}/convert/bean?formattedList[0]=2010-07-04&formattedList[1]=2011-07-04  // 通过注解反转 JavaBean 中的对象 formattedList，限定时间格式
     *   or
     * Api: {url}/convert/bean?map[0]=apple&map[1]=pear  // 对JavaBean 对象实例中的， map 对象装配实例
     *    or
     * Api: {url}/convert/bean?nested.foo=bar&nested.list[0].foo=baz&nested.map[key].list[0].foo=bip // 对JavaBean 组合对象装配实例参数
     *
     * @param bean
     * @return
     */
    @RequestMapping("/bean")
    public @ResponseBody String bean(JavaBean bean) {
        return "Converted " + bean;
    }

    /**
     * Api: {url}/convert/value?value=123456789
     *
     *
     * @param value
     * @return
     */
    @RequestMapping("/value")
    public @ResponseBody String valueObject(@RequestParam SocialSecurityNumber value) {
        System.out.println("SocialSecurityNumber 对象输出：" + value);
        return "Converted value object = " + value;
    }

    /**
     * Api: {url}/convert/custom?value=123-45-6789
     *
     * @param value
     * @return
     */
    @RequestMapping("/custom")
    public @ResponseBody String customConverter(@RequestParam @MaskFormat("###-##-####") String value) {
        return "Converted '" + value + "' with a custom converter";
    }
}
