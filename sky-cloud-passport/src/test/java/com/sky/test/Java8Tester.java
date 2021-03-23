package com.sky.test;

import java.util.*;

/**
 * @name: com.sky.test.Java8Tester <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/3/23 9:30 <tb>
 */

public class Java8Tester {
    public static void main(String args[]){

        Java8Tester java8Tester = new Java8Tester();
        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);

        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> b = Optional.of(value2);
        System.out.println(java8Tester.sum(a,b));
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b){

        // Optional.isPresent - 判断值是否存在

        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }

    public Object getFromMap(String key){

        Map<String,Object> map = new HashMap<>(4);
        map.put("a", "aaa");
        map.put("b", "bbb");
        map.put("c", "ccc");

        Object value = map.get(key);
        if (Objects.isNull(value)){
            throw new NoSuchElementException("不存在key");
        }
        return value;

    }


    public Object getFromMapOp(String key){

        Map<String,Object> map = new HashMap<>(4);
        map.put("a", "aaa");
        map.put("b", "bbb");
        map.put("c", "ccc");

        Object value = map.get(key);

        return Optional.ofNullable(value).orElseThrow(()->new NoSuchElementException("不存在key"));
    }

    /**
     * 如果字符串不是数字，会抛出异常
     * @param a 字符串
     * @return 数字
     */
    public Integer string2Int(String a){
        return Integer.parseInt(a);
    }

    /**
     * Optional.empty对应异常的情况，后续比较好处理；
     * @param a 字符串
     * @return 可能转换失败的整数，延迟到使用方去处理
     */
    public Optional<Integer> string2Int_op(String a){
        try{
            return Optional.of(Integer.parseInt(a));
        }catch (Exception ex){
            return Optional.empty();
        }
    }

    public Integer getFromProperties(Properties properties, String key) {
        String value = properties.getProperty(key);
        if (Objects.nonNull(value)) {
            try {
                Integer integer = Integer.parseInt(value);
                if (integer > 0) {
                    return integer;
                }
            } catch (Exception ex) {
                //无需处理异常
                return 0;
            }
        }
        return 0;
    }


    public Integer getFromProperties_op(Properties properties, String key) {
        return Optional.ofNullable(properties.getProperty(key))
                .map(item -> {
                    try {
                        return Integer.parseInt(item);
                    } catch (Exception ex) {
                        return 0;
                    }
                })
                .orElse(0);
    }

}