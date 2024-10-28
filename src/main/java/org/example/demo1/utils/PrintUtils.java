package org.example.demo1.utils;

public final class PrintUtils {
    private PrintUtils(){}

    public static void print(String msg){
        System.out.println(msg);
    }
    public static void print(String msg, Object... args){
        System.out.printf(msg, args);
    }
    public static void println(String msg){
        System.out.println(msg);
    }
    public static void println(String msg, Object... args){
        System.out.printf(msg, args);
    }
    public static void printf(String format, Object... args){
        System.out.printf(format, args);
    }

}