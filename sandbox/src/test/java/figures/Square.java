package figures;

public class Square {
    public static void printSquareArea(double a) {
        String text = String.format("Площадь прямоугольника со стороной %f = %f", a, squareArea(a));
        System.out.println(text);
    }

    private static double squareArea(double a) {
        return a * a;
    }
}
