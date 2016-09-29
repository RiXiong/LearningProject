package com.zrx.guava;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Function:    Guava
 * Author:      zhangrixiong
 * DateTime:    2016/9/29 15:30
 *
 * <p> 使用 google 的 guava 取代 apache 的 Apache Common jar 工具包
 *
 *    1） Guava Collection API  集合API
 *    2） Guava Basic Utilities 工具类API
 *    3） IO API  IO 类API
 *    4） Cache API 缓存类API
 * </p>
 */
public class Guava {
    public static  void main(String[] args) {
        Map<String, String> map = Maps.newHashMap();
        map.put("1", "sa");
        map.put("2", "abc");
        for(Map.Entry<String, String> m: map.entrySet()) {
            System.out.println(m.getValue());
        }
        Guava guava = new Guava();
        guava.doSomething(null);
    }

    public void doSomething( List<Object> list ) {
        checkArgument( list != null, "List must not be null" );
        checkArgument( !list.isEmpty(), "List must not be empty" );
        doSomethingMore( list );
    }

    public void doSomethingMore( List<Object> list ){
        System.out.println("Hello World!");
    }

}
