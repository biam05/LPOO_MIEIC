package circuits;

public class GateNot extends LogicGate {

    public GateNot(LogicVariable output, LogicVariable input1) throws ColisionException, CycleException {
        if(output.getCalculatedBy() != null) throw new ColisionException();
        if(input1.getCalculatedBy() != null){
            if(input1.getCalculatedBy().getInputs()[0] == output) throw new CycleException();
            if(input1.getCalculatedBy().getInputs()[1] == output) throw new CycleException();
        }

        this.output = output;
        this.inputs = new LogicVariable[]{input1};
        this.symbol = "NOT";
        setFormula();
        setValue();

        this.output.setGate(this);
    }

    public void setFormula(){
        this.formula = this.symbol + "(" + this.inputs[0].getFormula() + ")";
    }

    public void setValue(){
        this.value = !this.inputs[0].getValue();
        this.output.setValue(this.value);
    }
}
