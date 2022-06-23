package ch09;

public class TriangleTest {
    private static class Triangle {
        // Variable
        private double base, height;
        // Getter & Setter
        public double getBase(){return base;}
        public void setBase(double base){this.base = base;}
        public double getHeight(){return height;}
        public void setHeight(double height){this.height = height;}

        // Constructor
        public Triangle(double base, double height){
            this.base = base;
            this.height = height;
        }

        // Method
        public double findArea(){
            return this.base * this.height /2;
        }
        public boolean isSameArea(Triangle other){
            return this.findArea() == other.findArea();
        }

    }


    public static void main(String[] args) {
        Triangle t1 = new Triangle(10.0, 5.0);
        Triangle t2 = new Triangle(5.0, 10.0);
        Triangle t3 = new Triangle(6.0, 8.0);

        System.out.println(t1.findArea());
        System.out.println(t1.isSameArea(t2));
        System.out.println(t1.isSameArea(t3));
    }
}
