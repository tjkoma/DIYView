package com.example.tjjunlintx.diyview;

/**
 * Created by tjjunlintx on 2017/6/23.
 */

public class JniTest {
    static{
        System.loadLibrary("jni-test");
    }
    public static void main(String [] args){
        JniTest jniTest = new JniTest();
        System.out.println(jniTest.get());
        jniTest.set("hello world");
    }
    public native String get();
    public native void set(String str);
}
