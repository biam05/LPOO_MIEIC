package geometria;

public class FiguraComposta implements Figura, Countable{
    protected Figura[] figuras;

    public FiguraComposta(Figura[] figuras) {
        this.figuras = figuras;
    }

    @Override
    public double getArea() {
        double total = 0;
        for(Figura figura : figuras)
            total += figura.getArea();
        return total;
    }

    @Override
    public double getPerimetro() {
        double total = 0;
        for(Figura figura : figuras)
            total += figura.getPerimetro();
        return total;
    }

    @Override
    public int count() {
        return figuras.length;
    }
}
