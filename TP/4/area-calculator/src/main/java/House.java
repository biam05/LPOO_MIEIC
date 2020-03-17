public class House implements HasArea {
    private float area;

    public House(float area) {
        this.area = area;
    }

    @Override
    public double getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }
}
