package fdl.fdl.visitors;

import de.monticore.literals.mccommonliterals._ast.ASTBasicDoubleLiteral;
import de.monticore.literals.mccommonliterals._ast.ASTBasicFloatLiteral;
import de.monticore.literals.mccommonliterals._ast.ASTBasicLongLiteral;
import de.monticore.literals.mccommonliterals._ast.ASTNatLiteral;
import de.monticore.literals.mccommonliterals._ast.ASTStringLiteral;
import fdl.fdl._ast.ASTExtendedName;
import fdl.fdl._ast.ASTFdlNumber;
import fdl.fdl._visitor.FdlTraverser;
import fdl.fdl._visitor.FdlTraverserImplementation;
import fdl.fdl.util.ASTHelper;
import fdl.fdl.util.EnumTranslator;
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
 * This traverser aims to create MathJax formatted Strings from Expression ASTs
 */
public class MathJaxExpressionPrinter extends FdlTraverserImplementation {

  private final StringBuilder sb;

  public MathJaxExpressionPrinter() {
    sb = new StringBuilder();
  }

  public String getExpressionString() {
    return sb.toString();
  }

  public StringBuilder getSb() {
    return sb;
  }

  @Override
  public void traverse(ASTExtendedName node) {
    if (node.isPresentTopscript()) {
      String top = EnumTranslator.topsymbolToString(node.getTopscript().getSymbol().getIntValue());
      if (top == null) {
        top = "^";
      }
      switch (top) {
        case "^" -> sb.append("\\hat{");
        case "*" -> sb.append("\\breve{");
        case "." -> sb.append("\\dot{");
        case "~" -> sb.append("\\tilde{");
        case "´" -> sb.append("\\acute{");
        case "`" -> sb.append("\\grave{");
        case "°" -> sb.append("\\mathring{");
        case "-" -> sb.append("\\bar{");
        default -> sb.append("?");
      }
    }

    if (node.getIdentifier().isPresentName()) {
      sb.append(node.getIdentifier().getName());
    } else {
      sb.append("\\");
      sb.append(node.getIdentifier().getSymbolCommand().getName());
    }

    if (node.isPresentTopscript()) {
      sb.append("}");
    }

    if (node.isPresentSuperscript()) {
      sb.append("^{");
      node.getSuperscript().getExpression().accept(this);
      sb.append("}");
    }

    if (node.isPresentSubscript()) {
      sb.append("_{");
      sb.append(node.getSubscript().getString());
      sb.append("}");
    }
  }

  @Override
  public void traverse(ASTFdlNumber node) {
    sb.append(ASTHelper.getFdlNumber(node));
  }

  @Override
  public void traverse(ASTNatLiteral node) {
    sb.append(node.getValue());
  }

  @Override
  public void traverse(ASTBasicFloatLiteral node) {
    sb.append(node.getValue());
  }

  @Override
  public void traverse(ASTBasicLongLiteral node) {
    sb.append(node.getValue());
  }

  @Override
  public void traverse(ASTBasicDoubleLiteral node) {
    sb.append(node.getValue());
  }

  @Override
  public void traverse(ASTStringLiteral node) {
    sb.append(node.getValue());
  }

  @Override
  public void traverse(ASTPlusPrefixExpression node) {
    sb.append("+");
    node.getExpression().accept(this);
  }

  @Override
  public void traverse(ASTMinusPrefixExpression node) {
    sb.append("-");
    node.getExpression().accept(this);
  }

  @Override
  public void traverse(ASTBracketExpression node) {
    sb.append("(");
    node.getExpression().accept(this);
    sb.append(")");
  }

  @Override
  public void traverse(ASTAssignmentExpression node) {
    if (null != node.getLeft()) {
      node.getLeft().accept(this);
    }
    sb.append("=");
    if (null != node.getRight()) {
      node.getRight().accept(this);
    }
  }

  @Override
  public void traverse(ASTPlusExpression node) {
    if (null != node.getLeft()) {
      node.getLeft().accept(this);
    }
    sb.append("+");
    if (null != node.getRight()) {
      node.getRight().accept(this);
    }
  }

  @Override
  public void traverse(ASTMinusExpression node) {
    if (null != node.getLeft()) {
      node.getLeft().accept(this);
    }
    sb.append("-");
    if (null != node.getRight()) {
      node.getRight().accept(this);
    }
  }

  @Override
  public void traverse(ASTMultExpression node) {
    if (null != node.getLeft()) {
      node.getLeft().accept(this);
    }
    sb.append(" \\cdot ");
    if (null != node.getRight()) {
      node.getRight().accept(this);
    }
  }

  @Override
  public void traverse(ASTDivideExpression node) {
    sb.append("\\frac{");
    if (null != node.getLeft()) {
      node.getLeft().accept(this);
    }
    sb.append("}{");
    if (null != node.getRight()) {
      node.getRight().accept(this);
    }
    sb.append("}");
  }

  @Override
  public void traverse(ASTExponentialExpression node) {
    if (null != node.getBase()) {
      node.getBase().accept(this);
    }
    sb.append("^{");
    if (null != node.getExponent()) {
      node.getExponent().accept(this);
    }
    sb.append("}");
  }

  @Override
  public void traverse(ASTSquareRootExpression node) {
    sb.append("\\sqrt{");
    if (null != node.getExpression()) {
      node.getExpression().accept(this);
    }
    sb.append("}");
  }

  @Override
  public void traverse(ASTRootExpression node) {
    sb.append("\\sqrt");
    if (null != node.getLiteral()) {
      sb.append("[");
      node.getLiteral().accept(this);
      sb.append("]");
    }
    sb.append("{");
    if (null != node.getExpression()) {
      node.getExpression().accept(this);
    }
    sb.append("}");
  }

  @Override
  public void traverse(ASTLogarithmExpression node) {
    sb.append("log(");
    if (null != node.getExpression()) {
      node.getExpression().accept(this);
    }
    sb.append(", ");
    if (null != node.getBase()) {
      node.getBase().accept(this);
    }
    sb.append(")");
  }

  @Override
  public void traverse(ASTMaxExpression node) {
    sb.append("max(");
    if (null != node.getLeft()) {
      node.getLeft().accept(this);
    }
    sb.append(", ");
    if (null != node.getRight()) {
      node.getRight().accept(this);
    }
    sb.append(")");
  }

  @Override
  public void traverse(ASTMinExpression node) {
    sb.append("min(");
    if (null != node.getLeft()) {
      node.getLeft().accept(this);
    }
    sb.append(", ");
    if (null != node.getRight()) {
      node.getRight().accept(this);
    }
    sb.append(")");
  }

  @Override
  public void traverse(ASTSumExpression node) {
    sb.append("\\sum");
    if (null != node.getExpression()) {
      sb.append("_{");
      node.getExpression().accept(this);
      sb.append("}");
    }
    sb.append("(");
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
