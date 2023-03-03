package com.example;


import org.apache.commons.lang3.StringUtils;

public class MyApplication {


    public static void main(String[] args) {
        // can use this library because of api("") dependency scope in build.gradle.kts (:business-logic)
        System.out.println(StringUtils.capitalize("capitalized string"));

        new PrintService().print(new MessageModel("Hi me!"));
    }
}