package fdl.fdl._cocos;

import de.se_rwth.commons.logging.Log;
import fdl.fdl._ast.ASTAlias;
import fdl.fdl._ast.ASTAliasBlock;
import fdl.fdl._ast.ASTEquation;
import fdl.fdl._ast.ASTExtendedName;
import fdl.fdl._ast.ASTReference;
import fdl.fdl._ast.ASTReferenceBlock;
import fdl.fdl._ast.ASTSpecification;
import fdl.fdl._ast.ASTSpecificationBlock;
import fdl.fdl._visitor.FdlTraverser;
import fdl.fdl._visitor.FdlTraverserImplementation;
import fdl.fdl.util.ASTHelper;
import java.util.HashSet;
import java.util.Set;

public class IdentifiersInWithPartExist implements FdlASTEquationCoCo {

  @Override
  public void check(ASTEquation node) {

    if (node.isPresentWithPart()) {
      Set<String> foundIdentifiers = new HashSet<>();
      for (ASTAliasBlock block : node.getWithPart().getAliasBlockList()) {
        for (ASTAlias alias : block.getAliasList()) {
          foundIdentifiers.add(ASTHelper.extendedNameToString(alias.getExtendedName()));
        }
      }
      for (ASTSpecificationBlock block : node.getWithPart().getSpecificationBlockList()) {
        for (ASTSpecification spec : block.getSpecificationList()) {
          foundIdentifiers.add(ASTHelper.extendedNameToString(spec.getExtendedName()));
        }
      }
      for (ASTReferenceBlock block : node.getWithPart().getReferenceBlockList()) {
        for (ASTReference ref : block.getReferenceList()) {
          foundIdentifiers.add(ASTHelper.extendedNameToString(ref.getExtendedName()));
        }
      }
      if (!foundIdentifiers.isEmpty()) {
        ExtendedNameGrabber visitor = new ExtendedNameGrabber();
        node.getExpression().accept(visitor);
        Set<String> identifiersInEquation = visitor.getFoundIdentifiers();

        for (String id : foundIdentifiers) {
          if (identifiersInEquation.stream().noneMatch(id::equals)) {
            Log.error("0xC003: Identifier '" + id + "' in WITH-block does not appear in equation.");
          }
        }
      }
    }
  }

  private static class ExtendedNameGrabber extends FdlTraverserImplementation {

    private final Set<String> foundIdentifiers;

    public ExtendedNameGrabber() {
      foundIdentifiers = new HashSet<>();
    }

    public Set<String> getFoundIdentifiers() {
      return foundIdentifiers;
    }

    @Override
    public void visit(ASTExtendedName node) {
      foundIdentifiers.add(ASTHelper.extendedNameToString(node));
    }
  }
}
