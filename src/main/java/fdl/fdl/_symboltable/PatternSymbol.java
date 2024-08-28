package fdl.fdl._symboltable;

import fdl.automata.util.DFAutomaton;

public class PatternSymbol extends PatternSymbolTOP {

  private DFAutomaton automaton;
  private String object;

  public PatternSymbol() {
    super("Pattern");
  }

  public PatternSymbol(String n) {
    this();
  }

  @Override
  public String getName() {
    return "Pattern";
  }

  public DFAutomaton getAutomaton() {
    return automaton;
  }

  public void setAutomaton(DFAutomaton automaton) {
    this.automaton = automaton;
  }

  public String getObject() {
    return object;
  }

  public void setObject(String object) {
    this.object = object;
  }
}