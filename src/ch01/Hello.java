package ch01;

import java.util.Scanner;

public class Hello {
    public static void main(String[] args){
        // 3주차 과제
        Scanner sc = new Scanner(System.in);
        double celsius = sc.nextDouble();
        // 섭씨 온도 = (화씨온도 - 32.0)/1.8 이므로 화씨온드 화씨온도 = (섭씨온도 *1.8) + 32.0이다.
        double fahrenheit = (celsius*1.8) + 32.0;
        System.out.printf("화씨 %.1f도는 섭씨로 %.1f도 입니다.",fahrenheit,celsius);

    }
}
