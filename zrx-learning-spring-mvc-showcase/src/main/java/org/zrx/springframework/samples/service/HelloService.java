package org.zrx.springframework.samples.service;

import org.springframework.stereotype.Service;

/**
 * Function:    HelloService
 * Author:      zhangrixiong
 * DateTime:    2016/9/26 12:41
 */
@Service
public class HelloService {

    public Object hello() {
        return "Greate Aspectj !";
    }
}
