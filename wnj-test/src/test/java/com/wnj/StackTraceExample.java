package com.wnj;

public class StackTraceExample {

    public static void main(String args[]) {
        //calling a method to print stack trace further down 
        first();
    }

    public static void first() {
        second();
    }

    private static void second() {
        third();
    }

    private static void third() {
        //If you want to print stack trace on console than use dumpStack() method 
//        System.out.println("Stack trace of current thread using dumpStack() method");
//        Thread.currentThread().dumpStack();
        //This is another way to print stack trace from current method System.err.println("Printing stack trace using printStackTrace() method of Throwable "); 
//        new Throwable().printStackTrace();
        //If you want stack trace as StackTraceElement in program itself than
        //use getStackTrace() method of Thread class 
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        //Once you get StackTraceElement you can also print it to console 
        System.out.println("displaying Stack trace from StackTraceElement in Java");
        for (StackTraceElement st : stackTrace) {
             System.out.println(st.toString());
        }
        System.out.println("============");
    }
}