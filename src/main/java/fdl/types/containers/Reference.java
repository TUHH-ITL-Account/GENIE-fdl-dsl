package fdl.types.containers;

import static fdl.fdl.util.ASTHelper.getFdlNumber;

import fdl.fdl._ast.ASTConstantsFdl;
import fdl.fdl._ast.ASTRangeSpecification;
import fdl.fdl._ast.ASTReference;
import fdl.fdl._ast.ASTSpaceSpecification;
import fdl.fdl._ast.ASTSpaces;
import fdl.fdl._symboltable.ConstantNumberSymbol;
import fdl.mathexpressions._ast.ASTExpression;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Reference {

  private boolean isVariable;
  private boolean isParameter;
  private ConstantNumberSymbol constant;
  private String constantName;

  private double min;
  private double max;
  private ASTExpression unitExpression;
  private ASTSpaces space;
  private int decimalSpaces;
  private boolean isForced;
  private List<Double> setSpace;
  private RoundingModes roundingMode;

  public Reference(ASTReference node) {
    if (node.isPresentRangeSpecification()) {
      isVariable = true;
      isParameter = false;
      constant = null;
      ASTRangeSpecification rs = node.getRangeSpecification();
      min = Double.parseDouble(getFdlNumber(rs.getStart()));
      max =
          rs.isPresentEnd() ? Double.parseDouble(getFdlNumber(rs.getEnd())) : min;
      unitExpression = rs.isPresentUnit() ? rs.getUnit() : null;
      if (rs.isPresentSpaceSpecification()) {
        ASTSpaceSpecification ss = rs.getSpaceSpecification();
        if(ss.isPresentSpaces()) {
          space = ss.getSpaces();
        } else {
          setSpace = ss.getSetRef().getFdlNumberList().stream()
              .map(x -> Double.valueOf(getFdlNumber(x))).distinct().collect(Collectors.toList());
          Collections.sort(setSpace);
          min = setSpace.isEmpty() ? 1 : setSpace.get(0);
          max = setSpace.isEmpty() ? 1 : setSpace.get(setSpace.size()-1);
        }
        isForced = ss.isForce();
        decimalSpaces =
            ss.isPresentDecimalplace() ? Integer.parseInt(ss.getDecimalplace()) : -1;
        if(ss.isPresentRoundingModes()) {
          roundingMode = numToRoundingMode(ss.getRoundingModes().getIntValue());
        }
      }
    } else if (node.isPresentParameter()) {
      isVariable = false;
      isParameter = true;
      constant = null;
      ASTSpaceSpecification ss = node.getParameter().getSpaceSpecification();
      if(ss.isPresentSpaces()) {
        space = ss.getSpaces();
      } else {
        setSpace = ss.getSetRef().getFdlNumberList().stream()
            .map(x -> Double.valueOf(getFdlNumber(x))).distinct().collect(Collectors.toList());
        Collections.sort(setSpace);
        min = setSpace.isEmpty() ? 1 : setSpace.get(0);
        max = setSpace.isEmpty() ? 1 : setSpace.get(setSpace.size()-1);
      }
      isForced = ss.isForce();
      decimalSpaces = ss.isPresentDecimalplace() ? Integer.parseInt(ss.getDecimalplace()) : -1;
      if(ss.isPresentRoundingModes()) {
        roundingMode = numToRoundingMode(ss.getRoundingModes().getIntValue());
      }
    } else if (node.isPresentConstantRef()) {
      isVariable = false;
      isParameter = false;
      constant = null;
      constantName = node.getConstantRef().getRef();
    }
  }

  public boolean isVariable() {
    return isVariable;
  }

  public boolean isParameter() {
    return isParameter;
  }

  public boolean isConstant() {
    return constantName != null;
  }

  public ConstantNumberSymbol getConstant() {
    return constant;
  }

  public double getMin() {
    return min;
  }

  public double getMax() {
    return max;
  }

  public boolean hasSpace() {
    return space != null;
  }

  public ASTSpaces getSpace() {
    return space;
  }

  public boolean isSetSpace() {
    return setSpace != null;
  }

  public List<Double> getSetSpace() {
    return setSpace;
  }

  public boolean hasRoundingMode() {
    return roundingMode != null;
  }

  public RoundingModes getRoundingMode() {
    return roundingMode;
  }

  public int getDecimalSpaces() {
    return decimalSpaces;
  }

  public boolean isForced() {
    return isForced;
  }

  public boolean hasUnit() {
    return unitExpression != null;
  }

  public ASTExpression getUnitExpression() {
    return unitExpression;
  }

  public void setConstant(ConstantNumberSymbol constant) {
    this.constant = constant;
  }

  public String getConstantName() {
    return constantName;
  }

  public enum RoundingModes {
    ROUND_UP, ROUND_DOWN, ROUND_NEAREST
  }

  private RoundingModes numToRoundingMode(int num) {
    switch(num) {
      case ASTConstantsFdl.ROUND_UP -> {return RoundingModes.ROUND_UP;}
      case ASTConstantsFdl.ROUND_DOWN -> {return RoundingModes.ROUND_DOWN;}
      case ASTConstantsFdl.ROUND_NEAREST -> {return RoundingModes.ROUND_NEAREST;}
    }
    return null;
  }
}
