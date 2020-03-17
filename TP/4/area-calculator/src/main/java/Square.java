public class Square implements Shape {

    private float side;

    public Square(float side){
        this.side = side;
    }

    public float getSide(){
        return side;
    }

    public void setSide(float s){
        side = s;
    }

    @Override
    public double getArea() {
        return Math.pow(side, 2);
    }

    @Override
    public void draw() {
        System.out.println("Square");
    }
}
