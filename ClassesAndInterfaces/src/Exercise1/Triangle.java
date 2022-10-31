package Exercise1;

public class Triangle implements Figure {
    private double base;
    private double height;

    Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public double area() {
        return (base * height) / 2;
    }
}