package geometria;

public class Ponto implements Comparable<Ponto>{
    protected int x;
    protected int y;

    public Ponto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                   // are the references equal
        if (o == null) return false;                  // is the other object null
        if (getClass() != o.getClass()) return false; // both objects the same class
        Ponto p = (Ponto) o;                          // cast the other object
        return x == p.getX() && y == p.getY();        // actual comparison
    }

    @Override
    public int compareTo(Ponto ponto) {
        if (this.x == ponto.x) {
            if (this.y > ponto.y) return 1;
            else return 0;
        }
        else if (this.x > ponto.x) return 1;
        else if (this.x < ponto.x) return 0;

        return -1;
    }
}
