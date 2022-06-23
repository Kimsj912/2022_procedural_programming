package ch04;

import java.util.Scanner;

public class assign4 {
    public static void main(String[] args){
        // 3번
        /**
         * 명지대학교를 졸업하기 위해 최소 140학점을 이수해야 한다고 하자.
         * 이수한 학점 중 전공은 70학점 이상이어야 하며,
         * 교양과 일반은 각각 30학점 이상이거나 두 영역의 합이 80학점 이상이어야 한다.
         * 이수한 세 개의 학점을 각각 키보드로 입력 받아 졸업 여부를 출력하는 프로그램을 작성하시오.
         * (위의 나열된 모든 조건 고려, 코드와 결과를 캡쳐하여 첨부하시오.)
         * **/

        Scanner sc= new Scanner(System.in);
        System.out.print("전공 이수 학점: ");
        int major = sc.nextInt();
        System.out.print("교양 이수 학점: ");
        int ge = sc.nextInt();
        System.out.print("일반 이수 학점: ");
        int general = sc.nextInt();

        boolean result = false;
        if(major>=75 && ((ge>=30 && general>=30)||ge+general>=80)) result=true;
        System.out.println("졸업 가능 여부: "+(result?"졸업 가능":"졸업 불가능"));






        // 2번
//        System.out.print("0~999 사이의 숫자를 입력하시오: ");
//        Scanner sc = new Scanner(System.in);
//        String n=sc.nextLine();
//
//        int sum = 0;
//        for(int i=0;i<n.length();i++){
//            sum +=Integer.parseInt(String.valueOf(n.charAt(i)));
//        }
//        System.out.printf("각 자릿수의 합: %d",sum);

    }
}
