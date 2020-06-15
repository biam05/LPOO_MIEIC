public class TGV extends Comboio {
    public TGV(String nome) {
        super(nome);
        this.servicoABordo = new ServicoPrioritario();
    }

    @Override
    public String toString(){
        String c, l;
        if(getNumCarruagens()>1) c = " carruagens, ";
        else c = " carruagem, ";

        if(numLugares>1) l = " lugares, ";
        else l = " lugar, ";

        return "TGV " + nome + ", " + getNumCarruagens() + c + numLugares + l + numPassageiros + " passageiros";

    }
}
