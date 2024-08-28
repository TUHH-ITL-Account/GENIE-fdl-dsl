package fdl.fdl._symboltable;

import fdl.fdl._ast.ASTExtendedName;
import fdl.mathexpressions._ast.ASTAssignmentExpression;
import fdl.mathexpressions._ast.ASTExpression;
import fdl.types.containers.Reference;
import java.util.List;
import java.util.Map;

public class EquationSymbol extends EquationSymbolTOP {

  private Map<String, String> aliasMap;
  private Map<String, ASTExpression> specificationMap;
  private Map<String, Reference> referenceMap;
  private Map<String, String> imageMap;
  private List<String> listIdentifiers;

  public EquationSymbol() {
    super("Equation");
  }

  public EquationSymbol(String n) {
    this();
  }

  @Override
  public String getName() {
    return "Equation";
  }

  public ASTExpression getRootExpression() {
    return this.getAstNode().getExpression();
  }

  public Map<String, String> getAliasMap() {
    return aliasMap;
  }

  public void setAliasMap(Map<String, String> aliasMap) {
    this.aliasMap = aliasMap;
  }

  public Map<String, ASTExpression> getSpecificationMap() {
    return specificationMap;
  }

  public void setSpecificationMap(Map<String, ASTExpression> specificationMap) {
    this.specificationMap = specificationMap;
  }

  public Map<String, Reference> getReferenceMap() {
    return referenceMap;
  }

  public void setReferenceMap(Map<String, Reference> referenceMap) {
    this.referenceMap = referenceMap;
  }

  public Map<String, String> getImageMap() {
    return imageMap;
  }

  public void setImageMap(Map<String, String> imageMap) {
    this.imageMap = imageMap;
  }

  public List<String> getListIdentifiers() {
    return listIdentifiers;
  }

  public void setListIdentifiers(List<String> listIdentifiers) {
    this.listIdentifiers = listIdentifiers;
  }

  public boolean hasSingleLeftSide() {
    if (!(getRootExpression() instanceof ASTAssignmentExpression)) {
      return false;
    }
    return ((ASTAssignmentExpression) getRootExpression()).getLeft() instanceof ASTExtendedName;
  }
}
