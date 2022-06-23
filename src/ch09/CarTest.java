package ch09;

import java.util.Objects;

public class CarTest {
    private static class Car {
        // Static Variable
        static int numberOfCar = 0;
        static int numberOfRedCar = 0;

        // Static Variable Getter
        static int getNumberOfCar(){return numberOfCar;}
        static int getNumberOfRedCar(){return numberOfRedCar;}

        // Constructor
        public Car (String color){
            numberOfCar++;
            if(Objects.equals(color, "red")) numberOfRedCar++;
        }
    }

    public static void main(String[] args) {
        Car c1 = new Car("red");
        Car c2 = new Car("blue");
        Car c3 = new Car("red");

        System.out.printf("자동차 수: %d, 빨간색 자동차 수: %d",
                Car.getNumberOfCar(), Car.getNumberOfRedCar());
    }
}
