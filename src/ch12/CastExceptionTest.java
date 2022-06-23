package ch12;

class Shape {}

class Rectangle extends Shape {}

class Circle extends Shape {}

public class CastExceptionTest {
    public static void main(String[] args) {
        Rectangle r = new Rectangle();
        try{
            casting(r);
        } catch (ClassCastException e){
            System.out.println("클래스 캐스팅 오류 발생 : "+e.getMessage());
        }
    }

    static void casting(Shape s) {
        Circle c = (Circle) s;
    }
}
