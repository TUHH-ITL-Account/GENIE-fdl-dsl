/* (c) https://github.com/MontiCore/monticore */
package fdl.mathexpressions.parser;

import static org.junit.Assert.assertEquals;

import fdl.fdl._parser.FdlParser;
import fdl.fdl.util.ASTHelper;
import fdl.mathexpressions._ast.ASTExpression;
import java.io.IOException;
import java.util.Optional;
import org.junit.Test;

public class MathExpressionsParserTest {

  @Test
  public void testSquareRootExpression() throws IOException {
    FdlParser parser = new FdlParser();
    parser.parse_StringExpression("SQRT(42)");
  }

}
