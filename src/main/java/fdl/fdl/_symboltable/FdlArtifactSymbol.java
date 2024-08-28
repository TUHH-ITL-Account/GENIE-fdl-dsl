package fdl.fdl._symboltable;

import de.se_rwth.commons.logging.Log;

public class FdlArtifactSymbol extends FdlArtifactSymbolTOP {

  public FdlArtifactSymbol(String name) {
    super(name);
  }

  // todo: test
  public ICommonFdlSymbol getLocalSymbol() {
    if (!getSpannedScope().getLocalDefinitionSymbols().isEmpty()) {
      return getSpannedScope().getLocalDefinitionSymbols().get(0);
    } else if (!getSpannedScope().getLocalExamplesSymbols().isEmpty()) {
      return getSpannedScope().getLocalExamplesSymbols().get(0);
    } else if (!getSpannedScope().getLocalCharacteristicsSymbols().isEmpty()) {
      return getSpannedScope().getLocalCharacteristicsSymbols().get(0);
    } else if (!getSpannedScope().getLocalTradeOffSymbols().isEmpty()) {
      return getSpannedScope().getLocalTradeOffSymbols().get(0);
    } else if (!getSpannedScope().getLocalSequenceSymbols().isEmpty()) {
      return getSpannedScope().getLocalSequenceSymbols().get(0);
    } else if (!getSpannedScope().getLocalEquationSymbols().isEmpty()) {
      return getSpannedScope().getLocalEquationSymbols().get(0);
    } else if (!getSpannedScope().getLocalFigureSymbols().isEmpty()) {
      return getSpannedScope().getLocalFigureSymbols().get(0);
    } else if (!getSpannedScope().getLocalConstantNumberSymbols().isEmpty()) {
      return getSpannedScope().getLocalConstantNumberSymbols().get(0);
    } else if (!getSpannedScope().getLocalPatternSymbols().isEmpty()) {
      return getSpannedScope().getLocalPatternSymbols().get(0);
    } else if (!getSpannedScope().getLocalProgressionSymbols().isEmpty()) {
      return getSpannedScope().getLocalProgressionSymbols().get(0);
    } else if (!getSpannedScope().getLocalHierarchySymbols().isEmpty()) {
      return getSpannedScope().getLocalHierarchySymbols().get(0);
    } else if (!getSpannedScope().getLocalVariableGroupsSymbols().isEmpty()) {
      return getSpannedScope().getLocalVariableGroupsSymbols().get(0);
    } else {
      Log.error("Unhandled case in getting local symbol from artifact symbol.");
    }
    return null;
  }

}
