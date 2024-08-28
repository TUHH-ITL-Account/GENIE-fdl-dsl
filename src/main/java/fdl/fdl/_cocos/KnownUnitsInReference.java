package fdl.fdl._cocos;

import de.se_rwth.commons.logging.Log;
import fdl.fdl._ast.ASTExtendedName;
import fdl.fdl._ast.ASTReference;
import fdl.fdl._visitor.FdlTraverser;
import fdl.fdl._visitor.FdlTraverserImplementation;
import fdl.fdl.util.ASTHelper;
import fdl.types.units.UnitHelper;
import java.util.HashSet;
import java.util.Set;

public class KnownUnitsInReference implements FdlASTReferenceCoCo {

  // Specifications are e.g. [m^^{.} = "mass"/"time"]
  @Override
  public void check(ASTReference node) {
    UnitExpressionTraverser traverser = new UnitExpressionTraverser();
    if (node.isPresentRangeSpecification()) {
      node.getRangeSpecification().accept(traverser);
    } else if (node.isPresentParameter()) {
      node.getParameter().accept(traverser);
    } else if (node.isPresentConstantRef()) {

    }

    Set<String> foundNames = traverser.getNames();
//todo fix
    for (String name : foundNames) {
      if (!UnitHelper.unitObjectsMap.containsKey(name)) {
        Log.warn("0xC002: Unknown unit '" + name + "'.");
      }
    }
  }

  private static class UnitExpressionTraverser extends FdlTraverserImplementation {

    Set<String> names;

    public UnitExpressionTraverser() {
      this.names = new HashSet<>();
    }

    public Set<String> getNames() {
      return this.names;
    }

    public void visit(ASTExtendedName node) {
      names.add(ASTHelper.extendedNameToString(node));
    }
  }
}
