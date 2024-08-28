package fdl.fdl._symboltable;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;

public class VariableGroupsSymbol extends VariableGroupsSymbolTOP {

  /**
   * List of pairs [identifier:fdl] grouped together with equivalent pairs.
   */
  List<List<SimpleEntry<String,String>>> variableGroups;

  /**
   * Pairs [identifier:fdl] pointing at a list of groups they are part of.
   */
  Map<SimpleEntry<String,String>, List<List<SimpleEntry<String,String>>>> variableToGroupsMap;

  public VariableGroupsSymbol() {
    super("VariableGroups");
  }

  public VariableGroupsSymbol(String n) {
    this();
  }

  @Override
  public String getName() {
    return "VariableGroups";
  }

  public List<List<SimpleEntry<String,String>>> getVariableGroups() {
    return variableGroups;
  }

  public void setVariableGroups(List<List<SimpleEntry<String,String>>> variableGroups) {
    this.variableGroups = variableGroups;
  }

  public Map<SimpleEntry<String,String>, List<List<SimpleEntry<String,String>>>> getVariableToGroupsMap() {
    return variableToGroupsMap;
  }

  public void setVariableToGroupsMap(
      Map<SimpleEntry<String,String>, List<List<SimpleEntry<String,String>>>> variableToGroupsMap) {
    this.variableToGroupsMap = variableToGroupsMap;
  }
}
