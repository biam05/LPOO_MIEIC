package circuits;

public abstract class LogicGate {
    protected LogicVariable output;
    protected LogicVariable[] inputs;
    protected String symbol;
    protected String formula;
    protected boolean value;

    public LogicVariable getOutput() {
        return output;
    }

    public LogicVariable[] getInputs() {
        return inputs;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getFormula() {
        return formula;
    }

    public boolean getValue() {
        return value;
    }
}
