public class Triangle implements Shape{

    private float base_size;
    private float height;

    public Triangle(float base_size, float height) {
        this.base_size = base_size;
        this.height = height;
    }

    public float getBase_size() {
        return base_size;
    }

    public void setBase_size(float base_size) {
        this.base_size = base_size;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public double getArea() {
        return (base_size * height)/2;
    }

    @Override
    public void draw() {
        System.out.println("Triangle");
    }
}
