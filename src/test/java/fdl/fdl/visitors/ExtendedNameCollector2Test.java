/* (c) https://github.com/MontiCore/monticore */
package fdl.fdl.visitors;

import static org.junit.Assert.assertEquals;

import fdl.fdl._parser.FdlParser;
import fdl.fdl.util.ASTHelper;
import fdl.mathexpressions._ast.ASTExpression;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import org.junit.Test;

public class ExtendedNameCollector2Test {

  @Test
  public void testCollectExtendedNamesMap() throws IOException {
    FdlParser parser = new FdlParser();
    Optional<ASTExpression> o = parser.parse_StringExpression("y+32=3.5*V^^{.}-x_{\"asd\"}");
    Map<String, String> s = ASTHelper.collectExtendedNamesMap(o.get());
    assertEquals(3, s.size());
  }

}
