package fdl.fdl._symboltable;

import fdl.automata.util.DFAutomaton;
import fdl.fdl._ast.ASTAdditionalInfo;
import fdl.fdl._ast.ASTAlias;
import fdl.fdl._ast.ASTAliasBlock;
import fdl.fdl._ast.ASTCharacteristics;
import fdl.fdl._ast.ASTConstantNumber;
import fdl.fdl._ast.ASTDefinition;
import fdl.fdl._ast.ASTEquation;
import fdl.fdl._ast.ASTExample;
import fdl.fdl._ast.ASTExamples;
import fdl.fdl._ast.ASTFigure;
import fdl.fdl._ast.ASTHierarchy;
import fdl.fdl._ast.ASTHierarchyLevel;
import fdl.fdl._ast.ASTImageBlock;
import fdl.fdl._ast.ASTImageReference;
import fdl.fdl._ast.ASTLabel;
import fdl.fdl._ast.ASTOppose;
import fdl.fdl._ast.ASTOverlap;
import fdl.fdl._ast.ASTPattern;
import fdl.fdl._ast.ASTPosition;
import fdl.fdl._ast.ASTProgression;
import fdl.fdl._ast.ASTProgressionContent;
import fdl.fdl._ast.ASTReference;
import fdl.fdl._ast.ASTReferenceBlock;
import fdl.fdl._ast.ASTSequence;
import fdl.fdl._ast.ASTSeries;
import fdl.fdl._ast.ASTSpecification;
import fdl.fdl._ast.ASTSpecificationBlock;
import fdl.fdl._ast.ASTSubset;
import fdl.fdl._ast.ASTSuperset;
import fdl.fdl._ast.ASTTradeOff;
import fdl.fdl._ast.ASTVariableGroup;
import fdl.fdl._ast.ASTVariableGroups;
import fdl.fdl._ast.ASTVariablePair;
import fdl.fdl._ast.ASTXAxis;
import fdl.fdl._ast.ASTYAxis;
import fdl.fdl._symboltable.HierarchySymbol.HierarchyNode;
import fdl.fdl._visitor.FdlTraverserImplementation;
import fdl.fdl.util.ASTHelper;
import fdl.mathexpressions._ast.ASTExpression;
import fdl.types.containers.Example;
import fdl.types.containers.Reference;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolTableCompleter extends FdlTraverserImplementation {

  public void visit(ConstantNumberSymbol symbol) {
    ASTConstantNumber ast = symbol.getAstNode();
    symbol.setTerm(ast.getTerm());
    symbol.setIdentifier(ASTHelper.extendedNameToString(ast.getIdentifier()));
    String val = ASTHelper.getFdlNumber(ast.getValue());
    symbol.setValue(Double.parseDouble(val));
    symbol.setUnit(ast.isPresentUnit() ? ast.getUnit() : null);
  }

  public void visit(DefinitionSymbol symbol) {
    ASTDefinition ast = symbol.getAstNode();
    symbol.setTerm(ast.getTerm());
    symbol.setDefinition(ast.getDefinition());
  }

  public void visit(CharacteristicsSymbol symbol) {
    ASTCharacteristics ast = symbol.getAstNode();
    symbol.setObject(ast.getObject());
    symbol.setCharacteristics(ast.getCharacteristics().getStringList());
    symbol.setCategories(
        ast.isPresentCategories() ? ast.getCategories().getStringList() : new ArrayList<>());
    for (ASTAdditionalInfo addInfo : ast.getAdditionalInfoList()) {
      if (addInfo.isPresentSetLogic()) {
        if (addInfo.getSetLogic() instanceof ASTSuperset) {
          symbol.getSuperFdls()
              .addAll(((ASTSuperset) addInfo.getSetLogic()).getSuperFdls().getStringList());
        } else if (addInfo.getSetLogic() instanceof ASTSubset) {
          symbol.getSubFdls()
              .addAll(((ASTSubset) addInfo.getSetLogic()).getSubFdls().getStringList());
        } else if (addInfo.getSetLogic() instanceof ASTOverlap) {
          symbol.getOverlapFdls()
              .addAll(((ASTOverlap) addInfo.getSetLogic()).getOverlapFdls().getStringList());
        } else if (addInfo.getSetLogic() instanceof ASTOppose) {
          symbol.getOpposingFdls()
              .addAll(((ASTOppose) addInfo.getSetLogic()).getOpposingFdls().getStringList());
        }
      } else if (addInfo.isPresentExplicitFalse()) {
        symbol.getExplicitFalse()
            .addAll(addInfo.getExplicitFalse().getExplicitFalse().getStringList());
      }
    }
  }

  public void visit(ExamplesSymbol symbol) {
    ASTExamples ast = symbol.getAstNode();
    symbol.setObject(ast.getObject());
    List<Example> toSave = new ArrayList<>();
    for (ASTExample ex : ast.getExampleList()) {
      toSave.add(new Example(ex));
    }
    symbol.setExamples(toSave);
    symbol.setCategories(
        ast.isPresentCategories() ? ast.getCategories().getStringList() : new ArrayList<>());
    for (ASTAdditionalInfo addInfo : ast.getAdditionalInfoList()) {
      if (addInfo.isPresentSetLogic()) {
        if (addInfo.getSetLogic() instanceof ASTSuperset) {
          symbol.getSuperFdls()
              .addAll(((ASTSuperset) addInfo.getSetLogic()).getSuperFdls().getStringList());
        } else if (addInfo.getSetLogic() instanceof ASTSubset) {
          symbol.getSubFdls()
              .addAll(((ASTSubset) addInfo.getSetLogic()).getSubFdls().getStringList());
        } else if (addInfo.getSetLogic() instanceof ASTOverlap) {
          symbol.getOverlapFdls()
              .addAll(((ASTOverlap) addInfo.getSetLogic()).getOverlapFdls().getStringList());
        } else if (addInfo.getSetLogic() instanceof ASTOppose) {
          symbol.getOpposingFdls()
              .addAll(((ASTOppose) addInfo.getSetLogic()).getOpposingFdls().getStringList());
        }
      } else if (addInfo.isPresentExplicitFalse()) {
        symbol.getExplicitFalse()
            .addAll(addInfo.getExplicitFalse().getExplicitFalse().getStringList());
      }
    }
  }

  public void visit(TradeOffSymbol symbol) {
    ASTTradeOff ast = symbol.getAstNode();
    symbol.setObject(ast.getObject());
    symbol.setAdvantages(ast.getAdvantages().getStringList());
    symbol.setDisadvantages(ast.getDisadvantages().getStringList());
  }

  public void visit(EquationSymbol symbol) {
    ASTEquation ast = symbol.getAstNode();

    if (ast.isPresentWithPart()) {
      Map<String, String> aliases = new HashMap<>();
      for (ASTAliasBlock block : ast.getWithPart().getAliasBlockList()) {
        for (ASTAlias alias : block.getAliasList()) {
          aliases.put(ASTHelper.extendedNameToString(alias.getExtendedName()),
              alias.getTerm());
        }
      }
      symbol.setAliasMap(aliases);

      Map<String, ASTExpression> specs = new HashMap<>();
      List<String> listIds = new ArrayList<>();
      for (ASTSpecificationBlock block : ast.getWithPart().getSpecificationBlockList()) {
        for (ASTSpecification spec : block.getSpecificationList()) {
          String name = ASTHelper.extendedNameToString(spec.getExtendedName());
          specs.put(name, spec.getExpression());
          if (spec.isIsList()) {
            listIds.add(name);
          }
        }
      }
      symbol.setSpecificationMap(specs);
      symbol.setListIdentifiers(listIds);

      Map<String, Reference> refs = new HashMap<>();
      for (ASTReferenceBlock block : ast.getWithPart().getReferenceBlockList()) {
        for (ASTReference ref : block.getReferenceList()) {
          refs.put(ASTHelper.extendedNameToString(ref.getExtendedName()),
              new Reference(ref));
        }
      }
      symbol.setReferenceMap(refs);

      Map<String, String> imageMap = new HashMap<>();
      for (ASTImageBlock block : ast.getWithPart().getImageBlockList()) {
        for (ASTImageReference ref : block.getImageReferenceList()) {
          imageMap.put(ref.getCaption(), ref.getFilename());
        }
      }
      symbol.setImageMap(imageMap);
    }

  }

  public void visit(SequenceSymbol symbol) {
    ASTSequence ast = symbol.getAstNode();

    symbol.setTerm(ast.getObject());
    symbol.setSequence(new ArrayList<>(ast.getStringList()));
  }

  public void visit(FigureSymbol symbol) {
    ASTFigure ast = symbol.getAstNode();

    symbol.setFilename(ast.getFilename());

    Map<String, Integer[]> labelMap = new HashMap<>();
    for (ASTLabel label : ast.getLabelList()) {
      labelMap.put(label.getTerm(), new Integer[]{Integer.parseInt(label.getPosition().getX()),
          Integer.parseInt(label.getPosition().getY())});
    }
    symbol.setLabelMap(labelMap);
  }

  public void visit(PatternSymbol symbol) {
    ASTPattern ast = symbol.getAstNode();
    //adjust later if NDA
    symbol.setObject(ast.getObject());
    symbol.setAutomaton(new DFAutomaton(ast.getAutomaton()));
  }

  public void visit(HierarchySymbol symbol) {
    ASTHierarchy ast = symbol.getAstNode();

    symbol.setObject(ast.getObject());
    int lastDepth = 0;
    HierarchyNode lastNode = new HierarchyNode("");
    int nodeIdCounter = 0;
    if (!ast.getHierarchyLevelList().isEmpty()) {
      ASTHierarchyLevel init = ast.getHierarchyLevelList().get(0);
      lastDepth = init.getDepthList().size();
      lastNode = new HierarchyNode(nodeIdCounter, init.getLevelName());
      nodeIdCounter++;
    }
    symbol.setHierarchyRoot(lastNode);
    for (int i = 1; i < ast.getHierarchyLevelList().size(); i++) {
      ASTHierarchyLevel level = ast.getHierarchyLevelList().get(i);
      HierarchyNode nextNode = new HierarchyNode(nodeIdCounter, level.getLevelName());
      nodeIdCounter++;
      int nextDepth = level.getDepthList().size();
      if (nextDepth == lastDepth) {
        nextNode.setParent(lastNode.getParent());
        lastNode.getParent().getChildren().add(nextNode);
      } else if (nextDepth > lastDepth) {
        nextNode.setParent(lastNode);
        lastNode.getChildren().add(nextNode);
      } else { //nextDepth < lastDepth
        while (lastDepth + 1 > nextDepth) {
          lastNode = lastNode.getParent();
          lastDepth--;
        }
        nextNode.setParent(lastNode);
        lastNode.getChildren().add(nextNode);
      }
      lastNode = nextNode;
      lastDepth = nextDepth;
    }
  }

  public void visit(ProgressionSymbol symbol) {
    ASTProgression ast = symbol.getAstNode();

    symbol.setDescription(ast.getObject());
    int seriesIndex = 1;
    Map<String, List<Integer[]>> map = new HashMap<>();
    for (ASTProgressionContent cont : ast.getProgressionContentList()) {
      if (cont instanceof ASTXAxis) {
        symbol.setxAxis(((ASTXAxis) cont).getXLabel());
      } else if (cont instanceof ASTYAxis) {
        symbol.setyAxis(((ASTYAxis) cont).getYLabel());
      } else { // cont == Series
        String lineTitle =
            ((ASTSeries) cont).isPresentLineTitle() ? ((ASTSeries) cont).getLineTitle()
                : String.valueOf(seriesIndex);
        seriesIndex++;
        List<Integer[]> series = new ArrayList<>();
        for (ASTPosition pos : ((ASTSeries) cont).getPositionList()) {
          series.add(new Integer[]{Integer.parseInt(pos.getX()), Integer.parseInt(pos.getY())});
        }
        map.put(lineTitle, series);
      }
    }
    symbol.setSeries(map);
  }

  public void visit(VariableGroupsSymbol symbol) {
    ASTVariableGroups ast = symbol.getAstNode();

    List<List<SimpleEntry<String,String>>> variableGroups = new ArrayList<>();
    Map<SimpleEntry<String,String>, List<List<SimpleEntry<String,String>>>> variableToGroupsMap = new HashMap<>();
    for(ASTVariableGroup fdlGroup : ast.getVariableGroupList()) {
      List<SimpleEntry<String,String>> group = new ArrayList<>();
      for(ASTVariablePair pair : fdlGroup.getVariablePairList()) {
        String identifier = ASTHelper.extendedNameToString(pair.getExtendedName());
        SimpleEntry<String,String> arrayPair = new SimpleEntry<>(identifier, pair.getSourceFdl());
        group.add(arrayPair);
        List<List<SimpleEntry<String,String>>> grps = variableToGroupsMap.computeIfAbsent(arrayPair,
            k -> new ArrayList<>());
        grps.add(group);
      }
      variableGroups.add(group);
    }
    symbol.setVariableGroups(variableGroups);
    symbol.setVariableToGroupsMap(variableToGroupsMap);
  }
}
