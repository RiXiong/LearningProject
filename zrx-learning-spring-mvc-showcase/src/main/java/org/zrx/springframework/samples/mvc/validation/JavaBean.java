package org.zrx.springframework.samples.mvc.validation;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Function:    JavaBean
 * Author:      zhangrixiong
 * DateTime:    2016/8/22 18:04
 *
 * 知识普及：
 *      javax.validation.constraints 是一个java检验JavaBean 的工具类，善于检验注解
 *
 */
public class JavaBean {

    @NotNull
    @Max(5)
    private Integer number;

    @NotNull
    @Future
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date date;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}