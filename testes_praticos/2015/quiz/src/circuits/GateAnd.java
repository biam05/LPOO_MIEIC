package circuits;

public class GateAnd extends LogicGate {

    public GateAnd(LogicVariable output, LogicVariable input1, LogicVariable input2) throws ColisionException, CycleException{
        if(output.getCalculatedBy() != null) throw new ColisionException();
        if(input1.getCalculatedBy() != null){
            if(input1.getCalculatedBy().getInputs()[0] == output) throw new CycleException();
            if(input1.getCalculatedBy().getInputs()[1] == output) throw new CycleException();
        }
        if(input2.getCalculatedBy() != null){
            if(input2.getCalculatedBy().getInputs()[0] == output) throw new CycleException();
            if(input2.getCalculatedBy().getInputs()[1] == output) throw new CycleException();
        }
        this.output = output;
        this.inputs = new LogicVariable[]{input1, input2};
        this.symbol = "AND";
        setFormula();
        setValue();
        this.output.setGate(this);
    }

    public void setFormula(){
        this.formula = this.symbol + "(" + this.inputs[0].getFormula() + "," + this.inputs[1].getFormula() + ")";
    }

    public void setValue(){
        this.value = this.inputs[0].getValue() && this.inputs[1].getValue();
        this.output.setValue(this.value);
    }
}
