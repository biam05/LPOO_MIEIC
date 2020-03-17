public class Circle implements Shape {

    private float radius;

    public Circle(float radius){
        this.radius = radius;
    }

    public float getRadius(){
        return radius;
    }
    public void setRadius(float r){
        radius = r;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public void draw() {
        System.out.println("Circle");
    }
}
