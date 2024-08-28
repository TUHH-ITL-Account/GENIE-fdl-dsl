package fdl.fdl.parser;

import static org.junit.Assert.assertNotNull;

import fdl.fdl._ast.ASTEquation;
import fdl.fdl._ast.ASTExtendedName;
import fdl.fdl._ast.ASTFdlArtifact;
import fdl.fdl._parser.FdlParser;
import fdl.fdl.lang.AbstractTest;
import fdl.mathexpressions._ast.ASTAssignmentExpression;
import fdl.mathexpressions._ast.ASTMultExpression;
import java.io.IOException;
import org.junit.Test;

public class FdlParserTest extends AbstractTest {

  @Test
  public void testDefinition() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Ladeeinheit_Def.fdl");
    assertNotNull(a);
  }

  @Test
  public void testCharacteristics() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Ladeeinheit_Char.fdl");
    assertNotNull(a);
  }

  @Test
  public void testSimpleExamples() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Unstetigförderer_Ex_simple.fdl");
    assertNotNull(a);
  }

  @Test
  public void testAdvancedExamples() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Stetigförderer_Ex_advanced.fdl");
    assertNotNull(a);
  }

  @Test
  public void testExamplesWithAdditionalInfo() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Example_AdditionalInfo1.fdl");
    assertNotNull(a);

    a = parseModel("src/test/resources/fdl/examples/Example_AdditionalInfo2.fdl");
    assertNotNull(a);
  }

  @Test
  public void testTradeOff() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Ladeeinheit_TO.fdl");
    assertNotNull(a);
  }

  @Test
  public void testSequence() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Example_Seq.fdl");
    assertNotNull(a);
  }

  @Test
  public void testFigure() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Example_Fig.fdl");
    assertNotNull(a);
  }

  @Test
  public void testEquation1() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Massestrom_Eq1.fdl");
    assertNotNull(a);
  }

  @Test
  public void testEquation2WithSymbolCommand() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Massestrom_Eq2.fdl");
    String t = ((ASTExtendedName) ((ASTMultExpression) ((ASTAssignmentExpression)
        ((ASTEquation) a.getFdlType()).getExpression()).getRight()).getLeft()).getTopscript()
        .toString();
    assertNotNull(a);
  }

  @Test
  public void testUmlaut() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/UmlautTäst.fdl");
    assertNotNull(a);
  }

  @Test
  public void testConstant() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Example_Const.fdl");
    assertNotNull(a);
  }

  @Test
  public void testSumAndIndexes() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Fachlast_Eq.fdl");
    assertNotNull(a);
  }

  @Test
  public void testFunctionExpression() throws IOException {
    FdlParser parser = new FdlParser();
    parser.parse_StringFunctionExpression("v(t)");
    parser.parse_StringExpression("v(t) = t + 2");
  }

  @Test
  public void testSZ() throws IOException {
    FdlParser parser = new FdlParser();
    parser.parse_StringStringLiteral("\"ß\"");
    parser.parse_StringExpression("namße_{\"sub\"} = t + 2");
  }

  @Test
  public void testSpaceSpec() throws IOException {
    FdlParser parser = new FdlParser();
    parser.parse_StringSpaceSpecification("IN {1, 1.3, 2} ROUND_UP");
    parser.parse_StringSpaceSpecification("IN {1, 1.3, 2} ! ROUND_UP");
    parser.parse_StringSpaceSpecification("IN INT ROUND_DOWN");
    parser.parse_StringSpaceSpecification("IN FLOAT [2] !");
    parser.parse_StringSpaceSpecification("IN FLOAT [2] ! ROUND_UP");
  }

  @Test
  public void testPatternAutomaton() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Example_Pattern.fdl");
    assertNotNull(a);
  }

  @Test
  public void testProgression() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Example_Pro.fdl");
    assertNotNull(a);
  }

  @Test
  public void testHierarchy() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Automatische_Verladesysteme_Hie.fdl");
    assertNotNull(a);
  }

  @Test
  public void testVariableGroups() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Example_VariableGroups.fdl");
    assertNotNull(a);
  }
}
