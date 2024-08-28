package fdl.automata.util;

import fdl.automata._ast.ASTState;

public class State {

  private final String name;
  private final String label;
  private final boolean isInitial;
  private final boolean isFinal;

  public State(ASTState node) {
    this.name = node.getName();
    this.label = node.getLabel();
    this.isInitial = node.isINIT();
    this.isFinal = node.isFIN();
  }

  public String getName() {
    return name;
  }

  public String getLabel() {
    return label;
  }

  public boolean isInitial() {
    return isInitial;
  }

  public boolean isFinal() {
    return isFinal;
  }
}
