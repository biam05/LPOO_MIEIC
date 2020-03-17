public class Elipse implements Shape {

    private float x_radius;
    private float y_radius;

    public Elipse(float x_radius, float y_radius) {
        this.x_radius = x_radius;
        this.y_radius = y_radius;
    }

    public float getX_radius() {
        return x_radius;
    }

    public float getY_radius() {
        return y_radius;
    }

    public void setX_radius(float x_radius) {
        this.x_radius = x_radius;
    }

    public void setY_radius(float y_radius) {
        this.y_radius = y_radius;
    }

    @Override
    public double getArea() {
        return Math.PI * x_radius * y_radius;
    }

    @Override
    public void draw() {
        System.out.println("Elipse");
    }
}
