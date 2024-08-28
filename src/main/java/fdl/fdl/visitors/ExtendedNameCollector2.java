package fdl.fdl.visitors;

import fdl.fdl._ast.ASTExtendedName;
import fdl.fdl._visitor.FdlVisitor2;
import fdl.fdl.util.ASTHelper;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Collects a set of all ExtendedNames appearing in the visited AST. Thought to be used on
 * Expressions, but not limited to them.
 */
public class ExtendedNameCollector2 implements FdlVisitor2 {

  Map<String, String> names;

  public ExtendedNameCollector2() {
    names = new LinkedHashMap<>();
  }

  public Map<String, String> getNames() {
    return names;
  }

  @Override
  public void visit(ASTExtendedName node) {
    names.put(ASTHelper.extendedNameToString(node), ASTHelper.expressionToMathJaxString(node));
  }
}
