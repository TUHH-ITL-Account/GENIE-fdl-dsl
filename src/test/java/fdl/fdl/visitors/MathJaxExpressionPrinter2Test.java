/* (c) https://github.com/MontiCore/monticore */
package fdl.fdl.visitors;

import static org.junit.Assert.assertEquals;

import fdl.fdl._parser.FdlParser;
import fdl.fdl.util.ASTHelper;
import fdl.mathexpressions._ast.ASTExpression;
import java.io.IOException;
import java.util.Optional;
import org.junit.Test;

public class MathJaxExpressionPrinter2Test {

  @Test
  public void testMathJaxExpressionPrettyPrint() throws IOException {
    FdlParser parser = new FdlParser();

    Optional<ASTExpression> o = parser.parse_StringExpression(
        "y^^{.}_{\"1\"}+32=3.5*V^{2}_{\"asd\"}");
    String s = ASTHelper.expressionToEscapedMathJaxString(o.get());
    assertEquals("\\dot{\\mathrm{y}}_{1}+32=3.5 \\cdot \\mathrm{V}^{2}_{asd}", s);
  }

}
