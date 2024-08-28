package fdl.fdl.util;

import de.monticore.ast.ASTNode;
import de.monticore.literals.prettyprint.MCCommonLiteralsPrettyPrinter;
import de.monticore.prettyprint.IndentPrinter;
import fdl.fdl.FdlMill;
import fdl.fdl._ast.ASTExtendedName;
import fdl.fdl._ast.ASTFdlNumber;
import fdl.fdl._visitor.FdlTraverser;
import fdl.fdl.visitors.ExpressionPrinter;
import fdl.fdl.visitors.ExtendedNameCollector;
import fdl.fdl.visitors.ExtendedNameCollector2;
import fdl.fdl.visitors.MathJaxExpressionPrinter;
import fdl.fdl.visitors.MathJaxExpressionPrinter2;
import fdl.mathexpressions._ast.ASTExpression;
import java.util.Map;
import java.util.Set;

public class ASTHelper {

  public static String extendedNameToString(ASTExtendedName node) {
    if(node == null) {
      return null;
    }
    StringBuilder name = new StringBuilder();

    if (node.getIdentifier().isPresentName()) {
      name.append(node.getIdentifier().getName());
    } else {
      name.append("@")
          .append(node.getIdentifier().getSymbolCommand().getName());
    }

    if (node.isPresentTopscript()) {
      name.append("^^{")
          .append(EnumTranslator.topsymbolToString(node.getTopscript().getSymbol().getIntValue()))
          .append("}");
    }

    if (node.isPresentSuperscript()) {
      name.append("^{")
          .append(expressionToString(node.getSuperscript().getExpression()))
          .append("}");
    }

    if (node.isPresentSubscript()) {
      name.append("_{")
          .append(node.getSubscript().getString())
          .append("}");
    }

    return name.toString();
  }

  /**
   * Builds a String representing the given Expression node using a traverser. May cause errors when
   * traversing unhandled/not yet implemented expressions.
   *
   * @param expr ASTExpression node
   * @return a String
   */
  public static String expressionToString(ASTExpression expr) {
    if(expr == null) {
      return null;
    }
    ExpressionPrinter vis = new ExpressionPrinter();
    expr.accept(vis);
    return vis.getExpressionString();
  }

  public static String expressionToMathJaxString(ASTExpression expr) {
    if(expr == null) {
      return null;
    }
    MathJaxExpressionPrinter vis = new MathJaxExpressionPrinter();
    expr.accept(vis);
    return vis.getExpressionString();
  }

  public static String expressionToEscapedMathJaxString(ASTExpression expr) {
    if(expr == null) {
      return null;
    }
    MathJaxExpressionPrinter2 vis = new MathJaxExpressionPrinter2();
    expr.accept(vis);
    return vis.getExpressionString();
  }

  public static Set<String> collectExtendedNames(ASTNode node) {
    if(node == null) {
      return null;
    }
    ExtendedNameCollector vis = new ExtendedNameCollector();
    FdlTraverser traverser = FdlMill.traverser();
    traverser.add4Fdl(vis);
    node.accept(traverser);

    return vis.getNames();
  }

  /**
   * Gathers all extended names with their mathjax representation as a map
   *
   * @param node Starting node
   * @return a map with entries original extended name -> mathjax extended name
   */
  public static Map<String, String> collectExtendedNamesMap(ASTNode node) {
    if(node == null) {
      return null;
    }
    ExtendedNameCollector2 vis = new ExtendedNameCollector2();
    FdlTraverser traverser = FdlMill.traverser();
    traverser.add4Fdl(vis);
    node.accept(traverser);

    return vis.getNames();
  }

  /**
   * Converts a ASTFdlNumber into its String representation. Such numbers may be of types Integer,
   * Long, Float and Double, possibly negative.
   *
   * @param node the FdlNumber node
   * @return a String
   */
  public static String getFdlNumber(ASTFdlNumber node) {
    if(node == null) {
      return null;
    }
    IndentPrinter printer = new IndentPrinter();
    MCCommonLiteralsPrettyPrinter pp = new MCCommonLiteralsPrettyPrinter(printer);
    FdlTraverser trav = FdlMill.traverser();
    trav.add4MCCommonLiterals(pp);
    node.accept(trav);
    return printer.getContent();
  }

}
