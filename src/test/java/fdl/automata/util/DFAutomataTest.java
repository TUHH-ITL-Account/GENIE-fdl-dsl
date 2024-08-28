package fdl.automata.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import fdl.exceptions.DslException;
import fdl.fdl.FdlTool;
import fdl.fdl._symboltable.PatternSymbol;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class DFAutomataTest {


  @Test
  public void testPatternSymbol() throws DslException {
    FdlTool cli = new FdlTool();
    PatternSymbol sym = (PatternSymbol) cli.fullParse("src/test/resources/fdl/examples/Example_Pattern.fdl").getLocalSymbol();
    DFAutomaton aut = sym.getAutomaton();

    Set<List<String>> runs1 = aut.getValidRunsUpTo(1, 1);
    assertThat("Expected number of valid runs", runs1.size(), is(1));

    Set<List<String>> runs5 = aut.getValidRunsUpTo(5, 1);
    assertThat("Expected number of valid runs", runs5.size(), is(8));
  }

}
