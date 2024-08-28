package fdl.fdl.visitors;

import fdl.fdl._ast.ASTExtendedName;
import fdl.fdl._visitor.FdlVisitor2;
import fdl.fdl.util.ASTHelper;
import java.util.HashSet;
import java.util.Set;

/**
 * Collects a set of all ExtendedNames appearing in the visited AST. Thought to be used on
 * Expressions, but not limited to them.
 */
public class ExtendedNameCollector implements FdlVisitor2 {

  Set<String> names;

  public ExtendedNameCollector() {
    this.names = new HashSet<>();
  }

  public Set<String> getNames() {
    return this.names;
  }

  @Override
  public void visit(ASTExtendedName node) {
    this.names.add(ASTHelper.extendedNameToString(node));
  }
}
