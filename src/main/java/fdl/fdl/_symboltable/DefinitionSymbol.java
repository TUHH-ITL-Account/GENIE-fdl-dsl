package fdl.fdl._symboltable;

public class DefinitionSymbol extends DefinitionSymbolTOP {

  private String term;
  private String definition;

  public DefinitionSymbol() {
    super("Definition");
  }

  public DefinitionSymbol(String n) {
    this();
  }

  @Override
  public String getName() {
    return "Definition";
  }

  public String getTerm() {
    return term;
  }

  public void setTerm(String term) {
    this.term = term;
  }

  public String getDefinition() {
    return definition;
  }

  public void setDefinition(String definition) {
    this.definition = definition;
  }
}
