package com.wnj.util;

/**
 * <pre>
 *     1, Lambda表达式，也可称为闭包。是对《接口匿名实现类》的一种简化
 *     2, Lambda 允许把函数作为一个方法的参数,《接口的匿名实现类》作为引用传递
 *     3, Lambda 操作符"->", 左侧师入参，右侧是表达式功能
 * </pre>
 */
public class LambdaTester {
   public static void main(String args[]){
      LambdaTester tester = new LambdaTester();

      // 类型声明
      MathOperation addition = (int a, int b) -> a + b;

      // 不用类型声明
      MathOperation subtraction = (a, b) -> a - b;

      // 大括号中的返回语句
      MathOperation multiplication = (int a, int b) -> { int c = b + 1; return a * c; };


      System.out.println("============《接口的匿名实现类》直接调用==========");
      System.out.println("10 + 5 = " + addition.operation(10, 5));
      System.out.println("============《接口的匿名实现类》作为引用传递==========");
      System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
      System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
      System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));

      // 其他方法
      HelloService helloService = name -> System.out.println("Hello " + name);
      System.out.println("============《接口的匿名实现类》直接调用==========");
      helloService.hello("tomcat");
   }
    
   interface MathOperation {
      int operation(int a, int b);
   }
    
   interface HelloService {
      void hello(String name);
   }
    
   private int operate(int a, int b, MathOperation mathOperation){
      return mathOperation.operation(a, b);
   }
}