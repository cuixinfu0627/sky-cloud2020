package com.sky.cloud.utils;


import com.sky.cloud.utils.RequestHeaderUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @name: AppContextUtil <tb>
 * @title: ThreadLocal是线程内的全局上下文。就是在单个线程中，方法之间共享的内存，每个方法都可以从该上下文中获取值和修改值。<tb>
 * 在调用api时都会传一个token参数，通常会写一个拦截器来校验token是否合法，我们可以通过token找到对应的用户信息(User)，
 * 如果token合法，然后将用户信息存储到ThreadLocal中，这样无论是在controller、service、dao的哪一层都能访问到该用户的信息。作用类似于Web中的request作用域。
 * <p>
 * 传统方式我们要在方法中访问某个变量，可以通过传参的形式往方法中传参，如果多个方法都要使用那么每个方法都要传参；
 * 如果使用ThreadLocal所有方法就不需要传该参数了，每个方法都可以通过ThreadLocal来访问该值。
 * ThreadLocalUtil.set("key", value); 保存值
 * T value = ThreadLocalUtil.get("key"); 获取值
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/21 10:52 <tb>
 */
public class AppContextUtil {

    /**
     * 通用
     **/
    private static final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>(4);
        }
    };

    /**
     * 当前用户
     **/
    private static final ThreadLocal requestThreadUser = new ThreadLocal<>();

    /**
     * 用户SessionId
     **/
    private static final Map<Long, Map<Boolean, String>> userIdSessionMapping = new ConcurrentHashMap<>();

    /**
     * ==========================当前线程User==========================
     **/
    public static <T> void setCurrentUser(T user) {
        if (user == null) {
            return;
        }
        requestThreadUser.set(user);
    }

    public static <T> T getCurrentUser() {
        return (T) requestThreadUser.get();
    }

    public static void removeCurrentUser() {
        requestThreadUser.remove();
    }


    /**
     * ==========================获取当前线程sserSessionId==========================
     **/
    public static void addUserSessionId(long userId, String sessionId, String userAgent) {
        Map<Boolean, String> map = userIdSessionMapping.computeIfAbsent(userId, (Long key) -> new ConcurrentHashMap<Boolean, String>());
        map.put(RequestHeaderUtil.isMobileDevice(userAgent), sessionId);
    }

    public static void removeUserSession(long userId, String sessionId) {
        Map<Boolean, String> userSessions = userIdSessionMapping.get(userId);
        if (userSessions != null && !userSessions.isEmpty()) {
            for (Map.Entry<Boolean, String> entry : userSessions.entrySet()) {
                Boolean key = entry.getKey();
                if (entry.getValue().equalsIgnoreCase(sessionId)) {
                    userSessions.remove(key);
                }
            }
        }
    }

    public static Map<Boolean, String> getUserSessions(long userId) {
        return userIdSessionMapping.getOrDefault(userId, Collections.emptyMap());
    }

    /**
     * ==========================通用的存储当前线程==========================
     **/
    public static Map<String, Object> getThreadLocal() {
        return threadLocal.get();
    }

    public static <T> T get(String key) {
        Map map = (Map) threadLocal.get();
        return (T) map.get(key);
    }

    public static <T> T get(String key, T defaultValue) {
        Map map = (Map) threadLocal.get();
        return (T) map.get(key) == null ? defaultValue : (T) map.get(key);
    }

    public static void set(String key, Object value) {
        Map map = (Map) threadLocal.get();
        map.put(key, value);
    }

    public static void set(Map<String, Object> keyValueMap) {
        Map map = (Map) threadLocal.get();
        map.putAll(keyValueMap);
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static <T> Map<String, T> fetchVarsByPrefix(String prefix) {
        Map<String, T> vars = new HashMap<>();
        if (prefix == null) {
            return vars;
        }
        Map map = (Map) threadLocal.get();
        Set<Map.Entry> set = map.entrySet();

        for (Map.Entry entry : set) {
            Object key = entry.getKey();
            if (key instanceof String) {
                if (((String) key).startsWith(prefix)) {
                    vars.put((String) key, (T) entry.getValue());
                }
            }
        }
        return vars;
    }

    public static <T> T remove(String key) {
        Map map = (Map) threadLocal.get();
        return (T) map.remove(key);
    }

    public static void clear(String prefix) {
        if (prefix == null) {
            return;
        }
        Map map = (Map) threadLocal.get();
        Set<Map.Entry> set = map.entrySet();
        List<String> removeKeys = new ArrayList<>();

        for (Map.Entry entry : set) {
            Object key = entry.getKey();
            if (key instanceof String) {
                if (((String) key).startsWith(prefix)) {
                    removeKeys.add((String) key);
                }
            }
        }
        for (String key : removeKeys) {
            map.remove(key);
        }
    }
}
