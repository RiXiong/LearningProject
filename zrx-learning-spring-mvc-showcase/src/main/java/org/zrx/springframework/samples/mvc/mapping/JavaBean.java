package org.zrx.springframework.samples.mvc.mapping;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Function:    JavaBean
 * Author:      zhangrixiong
 * DateTime:    2016/8/22 17:55
 */
@XmlRootElement
public class JavaBean {

    private String  foo = "bar";

    private String fruit = "apple";

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}