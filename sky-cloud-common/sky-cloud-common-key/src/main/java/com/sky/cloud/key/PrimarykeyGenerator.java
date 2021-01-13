package com.sky.cloud.key;


public interface PrimarykeyGenerator {
    Long generateId(String var1, String var2);

    String formKey(String var1, String var2);
}

