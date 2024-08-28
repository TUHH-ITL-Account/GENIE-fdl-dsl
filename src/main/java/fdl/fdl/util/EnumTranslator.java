package fdl.fdl.util;

import fdl.fdl._ast.ASTTopsymbol;

public class EnumTranslator {

  public static String topsymbolToString(int num) {
    if (num == ASTTopsymbol.ROOF.getIntValue()) {
      return "^";
    } else if (num == ASTTopsymbol.STAR.getIntValue()) {
      return "*";
    } else if (num == ASTTopsymbol.POINT.getIntValue()) {
      return ".";
    } else if (num == ASTTopsymbol.TILDE.getIntValue()) {
      return "~";
    } else if (num == ASTTopsymbol.MINUS.getIntValue()) {
      return "-";
    }
    return null;
  }

}
