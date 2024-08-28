/* (c) https://github.com/MontiCore/monticore */
package fdl.fdl.visitors;

import static org.junit.Assert.assertEquals;

import fdl.fdl._parser.FdlParser;
import fdl.fdl.util.ASTHelper;
import fdl.mathexpressions._ast.ASTExpression;
import java.io.IOException;
import java.util.Optional;
import org.junit.Test;

public class ExpressionPrettyPrintTest {

  @Test
  public void testExpressionPrettyPrint() throws IOException {
    FdlParser parser = new FdlParser();

    Optional<ASTExpression> o = parser.parse_StringExpression("y+32=3.5*V");
    String s = ASTHelper.expressionToString(o.get());
    assertEquals("y+32==3.5*V", s);
  }

  //todo: cases
  @Test
  public void testSquareRootExpression() throws IOException {
    FdlParser parser = new FdlParser();

    Optional<ASTExpression> o = parser.parse_StringExpression("SQRT(42)");
    String s = ASTHelper.expressionToString(o.get());
    assertEquals("Sqrt(42)", s);
  }

}
