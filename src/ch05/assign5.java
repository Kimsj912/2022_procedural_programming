package ch05;

import java.util.Scanner;

public class assign5 {
    public static void main(String[] args){
        light("RED");
        light("YELLOW");
        light("GREEN");
        light("BLUE");
    }

    // ���� �Է¹޾�, ��ȣ�� �Һ��� ����ϴ� �޼ҵ�
    public static void light(String color){
        String result = switch (color) {
            case "RED" -> "�������� �����ϴ�.";
            case "YELLOW" -> "������� �����ϴ�.";
            case "GREEN" -> "�ʷϺ��� �����ϴ�.";
            default -> "����, �߸��� �� �Է�: " + color;
        };
        System.out.println(result);
    }

//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("������ �Է��Ͻÿ�: ");
//        double T = sc.nextDouble();
//
//        System.out.print("ü���� �Է��Ͻÿ�: ");
//        double W = sc.nextDouble();
//
//        double BMI = W/Math.pow(T,2);
//        System.out.printf("BMI : %.2f%n",BMI);
//
//        String obesity;
//        if(BMI>=30) obesity="��";
//        else if(BMI>=25) obesity="��ü��";
//        else if(BMI>=18.5) obesity="����";
//        else obesity="��ü��";
//        System.out.printf("�񸸵�: %s",obesity);
//
//    }
}
