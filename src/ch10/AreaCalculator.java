package ch10;

public class AreaCalculator {
    public static void main(String[] args){
        Square s = new Square();
        s.name = "정사각형";
        s.length = 3;

        Triangle t = new Triangle();
        t.name = "삼각형";
        t.base = 4;
        t.height = 3;

        Cricle c = new Cricle();
        c.name = "원";
        c.radius = 4;

        Shape[] shapes = {s,t,c};
        for (int i = 0; i < shapes.length ; i++) {
            System.out.printf("%s의 넓이: %.2f\n", shapes[i].name, shapes[i].area());
        }
    }
};

class Shape {
    String name;
    double area() { return 0; }
}

class Square extends Shape{
    int length; // 정사각형의 한 변의 길이
    double area() {return length*length;}
}
class Triangle extends Shape{
    int base; // 삼각형 밑변의 길이
    int height; // 삼각형의 높이
    double area() {return (base*height)/2;} // 정삼각형의 넓이
}
class Cricle extends Shape{
    int radius; // 정삼각형의 한 변의 길이
    double area() {return radius*radius*3.141592; } // 정삼각형의 넓이
}
