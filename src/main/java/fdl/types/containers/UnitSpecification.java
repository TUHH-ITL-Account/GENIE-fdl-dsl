package fdl.types.containers;

import fdl.mathexpressions._ast.ASTExpression;

public class UnitSpecification {

  private ASTExpression expression;
  private String stringRepresentation;

  public UnitSpecification(ASTExpression node) {
    this.expression = node.deepClone();
    this.stringRepresentation = null;
  }


}
