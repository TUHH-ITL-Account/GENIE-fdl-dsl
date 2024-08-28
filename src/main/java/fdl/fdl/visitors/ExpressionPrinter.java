package fdl.fdl.visitors;

import de.monticore.literals.mccommonliterals._ast.ASTBasicDoubleLiteral;
import de.monticore.literals.mccommonliterals._ast.ASTBasicFloatLiteral;
import de.monticore.literals.mccommonliterals._ast.ASTBasicLongLiteral;
import de.monticore.literals.mccommonliterals._ast.ASTNatLiteral;
import de.monticore.literals.mccommonliterals._ast.ASTStringLiteral;
import fdl.fdl._ast.ASTExtendedName;
import fdl.fdl._visitor.FdlTraverserImplementation;
import fdl.fdl.util.ASTHelper;
import fdl.mathexpressions._ast.ASTArcuscosinusExpression;
import fdl.mathexpressions._ast.ASTArcussinusExpression;
import fdl.mathexpressions._ast.ASTArcustangensExpression;
import fdl.mathexpressions._ast.ASTAssignmentExpression;
import fdl.mathexpressions._ast.ASTBracketExpression;
import fdl.mathexpressions._ast.ASTCosinusExpression;
import fdl.mathexpressions._ast.ASTDivideExpression;
import fdl.mathexpressions._ast.ASTExponentialExpression;
import fdl.mathexpressions._ast.ASTLogarithmExpression;
import fdl.mathexpressions._ast.ASTMaxExpression;
import fdl.mathexpressions._ast.ASTMinExpression;
import fdl.mathexpressions._ast.ASTMinusExpression;
import fdl.mathexpressions._ast.ASTMinusPrefixExpression;
import fdl.mathexpressions._ast.ASTMultExpression;
import fdl.mathexpressions._ast.ASTPlusExpression;
import fdl.mathexpressions._ast.ASTPlusPrefixExpression;
import fdl.mathexpressions._ast.ASTRootExpression;
import fdl.mathexpressions._ast.ASTSinusExpression;
import fdl.mathexpressions._ast.ASTSquareRootExpression;
import fdl.mathexpressions._ast.ASTSumExpression;
import fdl.mathexpressions._ast.ASTTangensExpression;

/**
 * This traverser aims to create Strings from Expression ASTs, aimed to fit the Symja library
 */
public class ExpressionPrinter extends FdlTraverserImplementation {

  private final StringBuilder sb;

  public ExpressionPrinter() {
    this.sb = new StringBuilder();
  }

  public String getExpressionString() {
    return this.sb.toString();
  }

  public StringBuilder getSb() {
    return this.sb;
  }

  @Override
  public void traverse(ASTExtendedName node) {
    this.sb.append(ASTHelper.extendedNameToString(node));
  }

  @Override
  public void traverse(ASTNatLiteral node) {
    this.sb.append(node.getValue());
  }

  @Override
  public void traverse(ASTBasicFloatLiteral node) {
    this.sb.append(node.getValue());
  }

  @Override
  public void traverse(ASTBasicLongLiteral node) {
    this.sb.append(node.getValue());
  }

  @Override
  public void traverse(ASTBasicDoubleLiteral node) {
    this.sb.append(node.getValue());
  }

  @Override
  public void traverse(ASTStringLiteral node) {
    this.sb.append(node.getValue());
  }

  @Override
  public void traverse(ASTPlusPrefixExpression node) {
    this.sb.append("+");
    node.getExpression().accept(this);
  }

  @Override
  public void traverse(ASTMinusPrefixExpression node) {
    this.sb.append("-");
    node.getExpression().accept(this);
  }

  @Override
  public void traverse(ASTBracketExpression node) {
    this.sb.append("(");
    node.getExpression().accept(this);
    this.sb.append(")");
  }

  @Override
  public void traverse(ASTAssignmentExpression node) {
    if (null != node.getLeft()) {
      node.getLeft().accept(this);
    }
    this.sb.append("==");
    if (null != node.getRight()) {
      node.getRight().accept(this);
    }
  }

  @Override
  public void traverse(ASTPlusExpression node) {
    if (null != node.getLeft()) {
      node.getLeft().accept(this);
    }
    this.sb.append("+");
    if (null != node.getRight()) {
      node.getRight().accept(this);
    }
  }

  @Override
  public void traverse(ASTMinusExpression node) {
    if (null != node.getLeft()) {
      node.getLeft().accept(this);
    }
    this.sb.append("-");
    if (null != node.getRight()) {
      node.getRight().accept(this);
    }
  }

  @Override
  public void traverse(ASTMultExpression node) {
    if (null != node.getLeft()) {
      node.getLeft().accept(this);
    }
    this.sb.append("*");
    if (null != node.getRight()) {
      node.getRight().accept(this);
    }
  }

  @Override
  public void traverse(ASTDivideExpression node) {
    if (null != node.getLeft()) {
      node.getLeft().accept(this);
    }
    this.sb.append("/");
    if (null != node.getRight()) {
      node.getRight().accept(this);
    }
  }

  @Override
  public void traverse(ASTExponentialExpression node) {
    if (null != node.getBase()) {
      node.getBase().accept(this);
    }
    sb.append("^(");
    if (null != node.getExponent()) {
      node.getExponent().accept(this);
    }
    sb.append(")");
  }

  @Override
  public void traverse(ASTSquareRootExpression node) {
    sb.append("Sqrt(");
    if (null != node.getExpression()) {
      node.getExpression().accept(this);
    }
    sb.append(")");
  }

  @Override
  public void traverse(ASTRootExpression node) {
    this.sb.append("rt(");
    if (null != node.getExpression()) {
      node.getExpression().accept(this);
    }
    this.sb.append(", ");
    if (null != node.getLiteral()) {
      node.getLiteral().accept(this);
    }
    this.sb.append(")");
  }

  @Override
  public void traverse(ASTLogarithmExpression node) {
    this.sb.append("log(");
    if (null != node.getExpression()) {
      node.getExpression().accept(this);
    }
    this.sb.append(", ");
    if (null != node.getBase()) {
      node.getBase().accept(this);
    }
    this.sb.append(")");
  }

  @Override
  public void traverse(ASTMaxExpression node) {
    this.sb.append("Max(");
    if (null != node.getLeft()) {
      node.getLeft().accept(this);
    }
    this.sb.append(", ");
    if (null != node.getRight()) {
      node.getRight().accept(this);
    }
    this.sb.append(")");
  }

  @Override
  public void traverse(ASTMinExpression node) {
    this.sb.append("Min(");
    if (null != node.getLeft()) {
      node.getLeft().accept(this);
    }
    this.sb.append(", ");
    if (null != node.getRight()) {
      node.getRight().accept(this);
    }
    this.sb.append(")");
  }

  //todo: fix mathematical sum
  @Override
  public void traverse(ASTSumExpression node) {
    sb.append("Sum(");
    if (null != node.getExpression()) {
      node.getExpression().accept(this);
    }
    this.sb.append(", ");
    if (null != node.getVar()) {
      node.getVar().accept(this);
    }
    sb.append(")");
  }

  @Override
  public void traverse(ASTSinusExpression node) {
    sb.append("sin(");
    if (null != node.getExpression()) {
      node.getExpression().accept(this);
    }
    sb.append(")");
  }

  @Override
  public void traverse(ASTCosinusExpression node) {
    sb.append("cos(");
    if (null != node.getExpression()) {
      node.getExpression().accept(this);
    }
    sb.append(")");
  }

  @Override
  public void traverse(ASTTangensExpression node) {
    sb.append("tan(");
    if (null != node.getExpression()) {
      node.getExpression().accept(this);
    }
    sb.append(")");
  }

  @Override
  public void traverse(ASTArcussinusExpression node) {
    sb.append("arcsin(");
    if (null != node.getExpression()) {
      node.getExpression().accept(this);
    }
    sb.append(")");
  }

  @Override
  public void traverse(ASTArcuscosinusExpression node) {
    sb.append("arccos(");
    if (null != node.getExpression()) {
      node.getExpression().accept(this);
    }
    sb.append(")");
  }

  @Override
  public void traverse(ASTArcustangensExpression node) {
    sb.append("arctan(");
    if (null != node.getExpression()) {
      node.getExpression().accept(this);
    }
    sb.append(")");
  }
}
