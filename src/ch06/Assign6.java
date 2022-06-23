package ch06;

public class Assign6 {
    // #1
//    public static void main(String[] args){
//        for(int i=1; i<=5;i++){
//            for(int j=1;j<=i;j++){
//                System.out.print("*");
//            }
//            System.out.println();
//        }
//
//    }

    // #2
    public static void main(String[] args){
        System.out.println("factorial(10) : "+factorial(10));
        System.out.println("factorial(4,10) : "+factorial(4,10));
    }

    public static int factorial(int end){
        int start = 1;
        return factorial(start, end);
    }

    public static int factorial(int start, int end){
        int retVal=1;
        while(start <= end){
            retVal*=start++;
        }
        return retVal;
    }
}
