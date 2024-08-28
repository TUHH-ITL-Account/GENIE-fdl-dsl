package fdl.fdl._cocos;

import de.se_rwth.commons.logging.Log;
import fdl.fdl._ast.ASTAlias;
import fdl.fdl._ast.ASTAliasBlock;
import fdl.fdl._ast.ASTEquation;
import fdl.fdl._ast.ASTReference;
import fdl.fdl._ast.ASTReferenceBlock;
import fdl.fdl._ast.ASTSpecification;
import fdl.fdl._ast.ASTSpecificationBlock;
import fdl.fdl.util.ASTHelper;
import java.util.ArrayList;
import java.util.List;

public class IdentifiersInWithPartExistOnce implements FdlASTEquationCoCo {

  @Override
  public void check(ASTEquation node) {

    if (node.isPresentWithPart()) {
      List<String> aliasIdentifiers = new ArrayList<>();
      for (ASTAliasBlock block : node.getWithPart().getAliasBlockList()) {
        for (ASTAlias alias : block.getAliasList()) {
          String name = ASTHelper.extendedNameToString(alias.getExtendedName());
          if (aliasIdentifiers.stream().anyMatch(name::equals)) {
            Log.error("0xC003a: Alias '" + name + "' defined one more than once.");
          }
          aliasIdentifiers.add(name);
        }
      }
      List<String> specIdentifiers = new ArrayList<>();
      for (ASTSpecificationBlock block : node.getWithPart().getSpecificationBlockList()) {
        for (ASTSpecification spec : block.getSpecificationList()) {
          String name = ASTHelper.extendedNameToString(spec.getExtendedName());
          if (specIdentifiers.stream().anyMatch(name::equals)) {
            Log.error("0xC003b: Specification for '" + name + "' defined one more than once.");
          }
          specIdentifiers.add(name);
        }
      }
      List<String> refIdentifiers = new ArrayList<>();
      for (ASTReferenceBlock block : node.getWithPart().getReferenceBlockList()) {
        for (ASTReference spec : block.getReferenceList()) {
          String name = ASTHelper.extendedNameToString(spec.getExtendedName());
          if (refIdentifiers.stream().anyMatch(name::equals)) {
            Log.error("0xC003c: Reference for '" + name + "' defined one more than once.");
          }
          refIdentifiers.add(name);
        }
      }
    }
  }
}
