package fdl.fdl._symboltable;

import fdl.mathexpressions._ast.ASTExpression;

public class ConstantNumberSymbol extends ConstantNumberSymbolTOP {

  private String term;
  private String identifier;
  private double value;
  private ASTExpression unit;

  public ConstantNumberSymbol() {
    super("ConstantNumber");
  }

  public ConstantNumberSymbol(String n) {
    this();
  }

  @Override
  public String getName() {
    return "ConstantNumber";
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public boolean hasUnit() {
    return unit != null;
  }

  public ASTExpression getUnit() {
    return unit;
  }

  public void setUnit(ASTExpression expression) {
    unit = expression;
  }

  public String getTerm() {
    return term;
  }

  public void setTerm(String term) {
    this.term = term;
  }
}
