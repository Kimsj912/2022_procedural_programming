package ch05;

import java.util.Scanner;

public class assign5 {
    public static void main(String[] args){
        light("RED");
        light("YELLOW");
        light("GREEN");
        light("BLUE");
    }

    // 색을 입력받아, 신호등 불빛을 출력하는 메소드
    public static void light(String color){
        String result = switch (color) {
            case "RED" -> "빨간불이 켜집니다.";
            case "YELLOW" -> "노란불이 켜집니다.";
            case "GREEN" -> "초록불이 켜집니다.";
            default -> "에러, 잘못된 색 입력: " + color;
        };
        System.out.println(result);
    }

//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("신장을 입력하시오: ");
//        double T = sc.nextDouble();
//
//        System.out.print("체중을 입력하시오: ");
//        double W = sc.nextDouble();
//
//        double BMI = W/Math.pow(T,2);
//        System.out.printf("BMI : %.2f%n",BMI);
//
//        String obesity;
//        if(BMI>=30) obesity="비만";
//        else if(BMI>=25) obesity="과체중";
//        else if(BMI>=18.5) obesity="정상";
//        else obesity="저체중";
//        System.out.printf("비만도: %s",obesity);
//
//    }
}
