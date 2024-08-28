package fdl.fdl._symboltable;

import java.util.List;

public class SequenceSymbol extends SequenceSymbolTOP {

  private String term;
  private List<String> sequence;

  public SequenceSymbol() {
    super("Sequence");
  }

  public SequenceSymbol(String n) {
    this();
  }

  @Override
  public String getName() {
    return "Sequence";
  }

  public String getTerm() {
    return term;
  }

  public void setTerm(String term) {
    this.term = term;
  }

  public List<String> getSequence() {
    return sequence;
  }

  public void setSequence(List<String> sequence) {
    this.sequence = sequence;
  }
}
