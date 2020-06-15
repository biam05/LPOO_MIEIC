package circuits;

public class LogicVariable {
    protected String name;
    protected boolean value;
    protected LogicGate gate;
    protected String formula;

    public LogicVariable(String name, boolean value) {
        this.name = name;
        this.value = value;
        this.formula = name;
    }

    public LogicVariable(String name) {

        this.name = name;
        this.formula = name;
    }

    public String getName() {
        return name;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public LogicGate getCalculatedBy() {
        return gate;
    }

    public void setGate(LogicGate gate) {
        this.gate = gate;
        this.formula = gate.getFormula();
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                   // are the references equal
        if (o == null) return false;                  // is the other object null
        if (getClass() != o.getClass()) return false; // both objects the same class
        LogicVariable p = (LogicVariable) o;                          // cast the other object
        return this.name == p.getName();        // actual comparison
    }


}
