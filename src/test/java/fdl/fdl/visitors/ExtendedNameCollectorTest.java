/* (c) https://github.com/MontiCore/monticore */
package fdl.fdl.visitors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import fdl.fdl._parser.FdlParser;
import fdl.fdl.util.ASTHelper;
import fdl.mathexpressions._ast.ASTExpression;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import org.junit.Test;

public class ExtendedNameCollectorTest {

  @Test
  public void testCollectExtendedNames() throws IOException {
    FdlParser parser = new FdlParser();
    Optional<ASTExpression> o = parser.parse_StringExpression("y+32=3.5*V");
    Set<String> s = ASTHelper.collectExtendedNames(o.get());
    assertEquals(2, s.size());
    assertTrue(s.contains("y"));
    assertTrue(s.contains("V"));
  }

}
