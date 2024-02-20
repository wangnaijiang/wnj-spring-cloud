package com.wnj.javabase;

/**
 * 常量池(constant pool)指的是在编译期被确定，并被保存在已编译的.class文件中的一些数据。
 * 它包括了关于类、方法、接口等中的常量，也包括字符串常量。
 * @author WangNaiJiang
 * @since 2024-01-17 09:14
 */
public class ConstantPoolTest {
    public static void main(String[] args) {
        //s1,s2,s3 编译期确定
        String s1 = "123";
        String s2 = "123";
        String s3 = "12" + "3";
        //s4,s5 运行期确定
        String s4= new String("123");
        String s5 = new String("123");
        //true
        System.out.println(s1 == s2);
        //true
        System.out.println(s1 == s3);
        //false
        System.out.println(s1 == s4);
        //false
        System.out.println(s4 == s5);
    }
}
