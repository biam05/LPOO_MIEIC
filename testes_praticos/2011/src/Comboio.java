import java.util.ArrayList;

public class Comboio {
    protected String nome;
    protected ArrayList<Carruagem> carruagens;
    protected int numLugares;
    protected int numPassageiros;
    protected int lugaresLivres;
    protected ServicoABordo servicoABordo;

    public Comboio(String nome) {
        this.nome = nome;
        this.carruagens = new ArrayList<>();
        this.numLugares = 0;
        getNumLugares();
        this.numPassageiros = 0;
        this.lugaresLivres = numLugares;
        this.servicoABordo = new ServicoRegular();

    }

    public String getNome() {
        return nome;
    }

    public void addCarruagem(Carruagem c){
        carruagens.add(c);
        getNumLugares();
    }

    public int getNumLugares() {
        int total = 0;
        for(Carruagem carruagem : carruagens)
            total += carruagem.getNumLugares();
        numLugares = total;
        lugaresLivres = numLugares - numPassageiros;
        return numLugares;
    }

    public int getLugaresLivres() {
        return lugaresLivres;
    }

    public int getNumCarruagens() {
        return carruagens.size();
    }

    public Carruagem getCarruagemByOrder(int i) {
        return carruagens.get(i);
    }

    public void removeAllCarruagens(int i) {
        ArrayList<Carruagem> temp = new ArrayList<>();
        for(Carruagem carruagem : carruagens)
            if(carruagem.getNumLugares() != 10)
                temp.add(carruagem);

        carruagens = temp;
    }

    public int getNumPassageiros() {
        return numLugares - lugaresLivres;
    }

    public void addPassageiros(int i) throws PassengerCapacityExceeded{
        if (i > this.lugaresLivres)
            throw new PassengerCapacityExceeded();
        else{
            this.numPassageiros += i;
            this.lugaresLivres -= i;
        }

    }

    public void removePassageiros(int i) {
        numPassageiros--;
        lugaresLivres++;
    }

    public void removePassageiros(){
        numPassageiros = 0;
        lugaresLivres = numLugares;
    }

    public ArrayList<Carruagem> getCarruagens() {
        return carruagens;
    }

    @Override
    public String toString(){
        String c, l;
        if(getNumCarruagens()>1) c = " carruagens, ";
        else c = " carruagem, ";

        if(numLugares>1) l = " lugares, ";
        else l = " lugar, ";

        return "Comboio " + nome + ", " + getNumCarruagens() + c + numLugares + l + numPassageiros + " passageiros";

    }

    @Override
    public boolean equals(Object obj) {
        Comboio train = (Comboio) obj;

        if (this.getNumCarruagens() == train.getNumCarruagens()) {
            for (int i = 0; i < this.getNumCarruagens(); i++) {
                if (this.getCarruagemByOrder(i).getNumLugares() != train.getCarruagemByOrder(i).getNumLugares())
                    return false;
            }
            return true;
        } else
            return false;
    }

    public ServicoABordo getServicoABordo() {
        return servicoABordo;
    }

    public String getDescricaoServico() {
        return servicoABordo.getDescricao();
    }

    public void setServicoABordo(ServicoABordo s) {
        this.servicoABordo = s;
    }
}
