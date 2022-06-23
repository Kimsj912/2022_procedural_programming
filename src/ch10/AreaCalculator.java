package ch10;

public class AreaCalculator {
    public static void main(String[] args){
        Square s = new Square();
        s.name = "���簢��";
        s.length = 3;

        Triangle t = new Triangle();
        t.name = "�ﰢ��";
        t.base = 4;
        t.height = 3;

        Cricle c = new Cricle();
        c.name = "��";
        c.radius = 4;

        Shape[] shapes = {s,t,c};
        for (int i = 0; i < shapes.length ; i++) {
            System.out.printf("%s�� ����: %.2f\n", shapes[i].name, shapes[i].area());
        }
    }
};

class Shape {
    String name;
    double area() { return 0; }
}

class Square extends Shape{
    int length; // ���簢���� �� ���� ����
    double area() {return length*length;}
}
class Triangle extends Shape{
    int base; // �ﰢ�� �غ��� ����
    int height; // �ﰢ���� ����
    double area() {return (base*height)/2;} // ���ﰢ���� ����
}
class Cricle extends Shape{
    int radius; // ���ﰢ���� �� ���� ����
    double area() {return radius*radius*3.141592; } // ���ﰢ���� ����
}
