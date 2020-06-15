package circuits;

import java.util.ArrayList;

public class CombinatorialCircuit {
    private ArrayList<LogicVariable> variables;

    public CombinatorialCircuit() {
        this.variables = new ArrayList<LogicVariable>();
    }

    public boolean addVariable(LogicVariable a) {
        for(LogicVariable var : variables){
            if(var.getName() == a.getName())
                return false;
        }
        this.variables.add(a);
        return true;
    }

    public LogicVariable getVariableByName(String name) {
        for(LogicVariable var : variables){
            if(var.getName() == name)
                return var;
        }
        return null;
    }
}
