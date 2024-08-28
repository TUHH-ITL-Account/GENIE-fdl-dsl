/* (c) https://github.com/MontiCore/monticore */
package fdl.fdl.util;

import static org.junit.Assert.assertEquals;

import fdl.fdl._ast.ASTFdlNumber;
import fdl.fdl._parser.FdlParser;
import fdl.fdl.lang.AbstractTest;
import java.io.IOException;
import org.junit.Test;

public class UtilTest extends AbstractTest {

  // todo: more cases
  @Test
  public void testGettingFdlNumbers() {
    FdlParser parser = new FdlParser();
    try {
      ASTFdlNumber num1 = parser.parse_StringFdlNumber("42").get();
      ASTFdlNumber num2 = parser.parse_StringFdlNumber("42.5").get();
      ASTFdlNumber num3 = parser.parse_StringFdlNumber("-42.5").get();
      ASTFdlNumber num4 = parser.parse_StringFdlNumber("-42").get();
      assertEquals("42", ASTHelper.getFdlNumber(num1));
      assertEquals("42.5", ASTHelper.getFdlNumber(num2));
      assertEquals("-42.5", ASTHelper.getFdlNumber(num3));
      assertEquals("-42", ASTHelper.getFdlNumber(num4));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
