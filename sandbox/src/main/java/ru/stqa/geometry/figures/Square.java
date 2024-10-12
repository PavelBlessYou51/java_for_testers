package ru.stqa.geometry.figures;

public class Square {
    public static void printArea(double a) {
        String text = String.format("Площадь прямоугольника со стороной %f = %f", a, area(a));
        System.out.println(text);
    }

    public static double area(double a) {
        return a * a;
    }

    public static double perimetr(double side) {
        return side * 4;
    }
}
