public class Coordinates {
    private double x; //Значение поля должно быть больше -775
    private Long y; //Поле не может быть null

    public double getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(Long y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
