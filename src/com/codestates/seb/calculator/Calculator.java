package com.codestates.seb.calculator;
import java.util.Scanner;

public class Calculator {
  public static void main(String[] args) {

    double num1;
    double num2;
    double result;
    String operator;

    System.out.println("===Java Calculator===");
    Scanner input = new Scanner(System.in);

    System.out.println("숫자를 입력하세요 >");
    num1 = input.nextDouble();
    System.out.println("연산자를 입력하세요 >");
    operator = input.nextLine();
    operator = input.nextLine();
    System.out.println("숫자를 입력하세요 >");
    num2 = input.nextDouble();

    if (operator.equals("+")) {
      result = num1 + num2;
      System.out.println("계산 결과 :");
      System.out.println(result);
    } else if (operator.equals("-")) {
      result = num1 - num2;
      System.out.println("계산 결과 :");
      System.out.println(result);
    } else if (operator.equals("*")) {
      result = num1 * num2;
      System.out.println("계산 결과 :");
      System.out.println(result);
    } else if (operator.equals("/")) {
      result = num1 / num2;
      System.out.println("계산 결과 :");
      System.out.println(result);
    } else if (operator.equals("%")) {
      result = num1 % num2;
      System.out.println("계산 결과 :");
      System.out.println(result);
    }
  }
}
