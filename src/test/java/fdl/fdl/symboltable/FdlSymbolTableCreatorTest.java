/* (c) https://github.com/MontiCore/monticore */
package fdl.fdl.symboltable;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import de.monticore.io.paths.MCPath;
import de.se_rwth.commons.logging.Log;
import fdl.automata.util.DFAutomaton.Relevancy;
import fdl.fdl.FdlTool;
import fdl.fdl.FdlMill;
import fdl.fdl._ast.ASTFdlArtifact;
import fdl.fdl._ast.ASTSpaces;
import fdl.fdl._symboltable.CharacteristicsSymbol;
import fdl.fdl._symboltable.ConstantNumberSymbol;
import fdl.fdl._symboltable.DefinitionSymbol;
import fdl.fdl._symboltable.EquationSymbol;
import fdl.fdl._symboltable.ExamplesSymbol;
import fdl.fdl._symboltable.FdlScopesGenitor;
import fdl.fdl._symboltable.FigureSymbol;
import fdl.fdl._symboltable.HierarchySymbol;
import fdl.fdl._symboltable.IFdlArtifactScope;
import fdl.fdl._symboltable.IFdlGlobalScope;
import fdl.fdl._symboltable.IFdlScope;
import fdl.fdl._symboltable.PatternSymbol;
import fdl.fdl._symboltable.ProgressionSymbol;
import fdl.fdl._symboltable.SequenceSymbol;
import fdl.fdl._symboltable.SymbolTableCompleter;
import fdl.fdl._symboltable.TradeOffSymbol;
import fdl.fdl._symboltable.VariableGroupsSymbol;
import fdl.fdl._visitor.FdlTraverser;
import fdl.fdl.util.ASTHelper;
import java.nio.file.Paths;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

public class FdlSymbolTableCreatorTest {

  private IFdlGlobalScope globalScope;

  @Before
  public void setup() {
    final MCPath symbolPath =
        new MCPath(Paths.get("src/test/resources/automata/symboltable"));

    globalScope = FdlMill.globalScope();
    globalScope.clear();
    globalScope.setSymbolPath(symbolPath);

    Log.enableFailQuick(false);
  }

  @Test
  public void testDefinitionSymbol() {
    ASTFdlArtifact ast = new FdlTool()
        .parse("src/test/resources/fdl/examples/Ladeeinheit_Def.fdl");
    FdlScopesGenitor genitor = FdlMill.scopesGenitor();
    FdlTraverser traverser = FdlMill.traverser();
    traverser.setFdlHandler(genitor);
    traverser.add4Fdl(genitor);
    genitor.putOnStack(globalScope);
    IFdlArtifactScope artifact = genitor.createFromAST(ast);
    SymbolTableCompleter completer = new SymbolTableCompleter();
    ast.accept(completer);

    IFdlScope s = artifact.getSubScopes().stream().findAny().get();

    Optional<DefinitionSymbol> sym = s.resolveDefinition("Definition");
    assertTrue(sym.isPresent());
    assertEquals("Ladeeinheit", sym.get().getTerm());
    assertEquals("eine transportfähige Verpackung, die irgendein Gut zusammenhält",
        sym.get().getDefinition());
  }

  @Test
  public void testCharacteristicsSymbol() {
    ASTFdlArtifact ast = new FdlTool()
        .parse("src/test/resources/fdl/examples/Ladeeinheit_Char.fdl");
    FdlScopesGenitor genitor = FdlMill.scopesGenitor();
    FdlTraverser traverser = FdlMill.traverser();
    traverser.setFdlHandler(genitor);
    traverser.add4Fdl(genitor);
    genitor.putOnStack(globalScope);
    IFdlArtifactScope artifact = genitor.createFromAST(ast);
    SymbolTableCompleter completer = new SymbolTableCompleter();
    ast.accept(completer);

    IFdlScope s = artifact.getSubScopes().stream().findAny().get();

    Optional<CharacteristicsSymbol> sym = s.resolveCharacteristics("Characteristics");
    assertTrue(sym.isPresent());
    assertEquals("Ladeeinheit", sym.get().getCategories().get(0));
    assertEquals(
        "Erhöhung der Umschlagleistung auf Grund besserer Handhabung und schnellerer Umlade-und Umschlagvorgäng",
        sym.get().getCharacteristics().get(0));
    assertEquals("Schutz vor Umwelteinflüssen und Diebstahl",
        sym.get().getCharacteristics().get(1));
    assertEquals("Sicherstellung der Entlastung von körperlicher Arbeit",
        sym.get().getCharacteristics().get(2));
    assertEquals(
        "Bessere Auslastung von Lager- und Transportkapazitäten durch Bündelung von Packstücken zu Einheiten",
        sym.get().getCharacteristics().get(3));
    assertEquals(
        "Entstehung von zusätzlichen Kosten für Ladehilfsmittel, Sicherungsmittel, Verpackungsmaschinen etc.",
        sym.get().getCharacteristics().get(4));
    assertEquals("Grundbaustein der Ladungsbildung", sym.get().getCharacteristics().get(5));
  }

  @Test
  public void testTradeOffSymbol() {
    ASTFdlArtifact ast = new FdlTool()
        .parse("src/test/resources/fdl/examples/Ladeeinheit_TO.fdl");
    FdlScopesGenitor genitor = FdlMill.scopesGenitor();
    FdlTraverser traverser = FdlMill.traverser();
    traverser.setFdlHandler(genitor);
    traverser.add4Fdl(genitor);
    genitor.putOnStack(globalScope);
    IFdlArtifactScope artifact = genitor.createFromAST(ast);
    SymbolTableCompleter completer = new SymbolTableCompleter();
    ast.accept(completer);

    IFdlScope s = artifact.getSubScopes().stream().findAny().get();

    Optional<TradeOffSymbol> sym = s.resolveTradeOff("Trade-Off");
    assertTrue(sym.isPresent());
    assertEquals("macht Dinge transportierbar mit verschiedenen Fördermitteln",
        sym.get().getAdvantages().get(0));
    assertEquals("macht Dinge effizienter transportierbar", sym.get().getAdvantages().get(1));
    assertEquals("Kosten", sym.get().getDisadvantages().get(0));
    assertEquals("Abfall", sym.get().getDisadvantages().get(1));
  }

  @Test
  public void testExampleSymbol() {
    ASTFdlArtifact ast = new FdlTool()
        .parse("src/test/resources/fdl/examples/Stetigförderer_Ex_advanced.fdl");
    FdlScopesGenitor genitor = FdlMill.scopesGenitor();
    FdlTraverser traverser = FdlMill.traverser();
    traverser.setFdlHandler(genitor);
    traverser.add4Fdl(genitor);
    genitor.putOnStack(globalScope);
    IFdlArtifactScope artifact = genitor.createFromAST(ast);
    SymbolTableCompleter completer = new SymbolTableCompleter();
    ast.accept(completer);

    IFdlScope s = artifact.getSubScopes().stream().findAny().get();

    Optional<ExamplesSymbol> sym = s.resolveExamples("Examples");
    assertTrue(sym.isPresent());
    assertEquals("Fördermittel", sym.get().getCategories().get(0));
    assertEquals("Maschinerie", sym.get().getCategories().get(1));
    assertEquals("Rollenbahn", sym.get().getExamples().get(0).getName());
    assertEquals("aufgeständert", sym.get().getExamples().get(0).getProperties().get(0));
    assertEquals("ortsfest", sym.get().getExamples().get(0).getProperties().get(1));
    assertEquals("Abwälzung", sym.get().getExamples().get(0).getProperties().get(2));
    assertEquals("Bandförderer", sym.get().getExamples().get(1).getName());
    assertEquals("aufgeständert", sym.get().getExamples().get(1).getProperties().get(0));
    assertEquals("ortsfest", sym.get().getExamples().get(1).getProperties().get(1));
    assertEquals("Zugmittel", sym.get().getExamples().get(1).getProperties().get(2));
  }

  @Test
  public void testExampleAdditionalInfoSymbol() {
    ASTFdlArtifact ast = new FdlTool()
        .parse("src/test/resources/fdl/examples/Example_AdditionalInfo1.fdl");
    FdlScopesGenitor genitor = FdlMill.scopesGenitor();
    FdlTraverser traverser = FdlMill.traverser();
    traverser.setFdlHandler(genitor);
    traverser.add4Fdl(genitor);
    genitor.putOnStack(globalScope);
    IFdlArtifactScope artifact = genitor.createFromAST(ast);
    SymbolTableCompleter completer = new SymbolTableCompleter();
    ast.accept(completer);

    IFdlScope s = artifact.getSubScopes().stream().findAny().get();

    Optional<ExamplesSymbol> sym = s.resolveExamples("Examples");
    assertTrue(sym.isPresent());
    assertEquals("X", sym.get().getExplicitFalse().get(0));
    assertEquals("Y", sym.get().getExplicitFalse().get(1));
    assertEquals("Example_AdditionalInfo2", sym.get().getSuperFdls().get(0));
    assertEquals("Stetigförderer_Ex_advanced", sym.get().getSubFdls().get(0));
    assertTrue(sym.get().getOverlapFdls().isEmpty());
    assertEquals("Unstetigförderer_Ex_simple", sym.get().getOpposingFdls().get(0));
  }

  @Test
  public void testSequenceSymbol() {
    ASTFdlArtifact ast = new FdlTool()
        .parse("src/test/resources/fdl/examples/Example_Seq.fdl");
    FdlScopesGenitor genitor = FdlMill.scopesGenitor();
    FdlTraverser traverser = FdlMill.traverser();
    traverser.setFdlHandler(genitor);
    traverser.add4Fdl(genitor);
    genitor.putOnStack(globalScope);
    IFdlArtifactScope artifact = genitor.createFromAST(ast);
    SymbolTableCompleter completer = new SymbolTableCompleter();
    ast.accept(completer);

    IFdlScope s = artifact.getSubScopes().stream().findAny().get();

    Optional<SequenceSymbol> sym = s.resolveSequence("Sequence");
    assertTrue(sym.isPresent());
    assertEquals("Anziehien", sym.get().getTerm());
    assertEquals("Unterwäsche", sym.get().getSequence().get(0));
    assertEquals("Kleidung", sym.get().getSequence().get(1));
    assertEquals("Accesoires", sym.get().getSequence().get(2));
    assertEquals("Überbekleidung", sym.get().getSequence().get(3));
    assertEquals("Maske, weil Corona", sym.get().getSequence().get(4));
  }

  @Test
  public void testFigureSymbol() {
    ASTFdlArtifact ast = new FdlTool()
        .parse("src/test/resources/fdl/examples/Example_Fig.fdl");
    FdlScopesGenitor genitor = FdlMill.scopesGenitor();
    FdlTraverser traverser = FdlMill.traverser();
    traverser.setFdlHandler(genitor);
    traverser.add4Fdl(genitor);
    genitor.putOnStack(globalScope);
    IFdlArtifactScope artifact = genitor.createFromAST(ast);
    SymbolTableCompleter completer = new SymbolTableCompleter();
    ast.accept(completer);

    IFdlScope s = artifact.getSubScopes().stream().findAny().get();

    Optional<FigureSymbol> sym = s.resolveFigure("Figure");
    assertTrue(sym.isPresent());
    assertEquals("Systematik12.png", sym.get().getFilename());
    assertTrue(sym.get().getLabelMap().containsKey("Transportmittel"));
    assertArrayEquals(new Integer[]{12, 34}, sym.get().getLabelMap().get("Transportmittel"));
    assertTrue(sym.get().getLabelMap().containsKey("Transportroute"));
    assertArrayEquals(new Integer[]{54, 122}, sym.get().getLabelMap().get("Transportroute"));
    assertTrue(sym.get().getLabelMap().containsKey("Transportgegenstand"));
    assertArrayEquals(new Integer[]{0, 0}, sym.get().getLabelMap().get("Transportgegenstand"));
  }

  @Test
  public void testEquationSymbol() {
    ASTFdlArtifact ast = new FdlTool()
        .parse("src/test/resources/fdl/examples/Massestrom_Eq1.fdl");
    FdlScopesGenitor genitor = FdlMill.scopesGenitor();
    FdlTraverser traverser = FdlMill.traverser();
    traverser.setFdlHandler(genitor);
    traverser.add4Fdl(genitor);
    genitor.putOnStack(globalScope);
    IFdlArtifactScope artifact = genitor.createFromAST(ast);
    SymbolTableCompleter completer = new SymbolTableCompleter();
    ast.accept(completer);

    IFdlScope s = artifact.getSubScopes().stream().findAny().get();

    Optional<EquationSymbol> sym = s.resolveEquation("Equation");
    assertTrue(sym.isPresent());
    assertEquals("Massestrom", sym.get().getAliasMap().get("m^^{.}"));
    assertEquals("Gutabstand", sym.get().getAliasMap().get("l_{a}"));
    assertEquals("mass", ASTHelper.expressionToString(sym.get().getSpecificationMap().get("m")));
    assertEquals("length",
        ASTHelper.expressionToString(sym.get().getSpecificationMap().get("l_{a}")));
    assertEquals("length/time",
        ASTHelper.expressionToString(sym.get().getSpecificationMap().get("v")));
  }

  @Test
  public void testConstantSymbol() {
    ASTFdlArtifact ast = new FdlTool()
        .parse("src/test/resources/fdl/examples/Example_Const.fdl");
    FdlScopesGenitor genitor = FdlMill.scopesGenitor();
    FdlTraverser traverser = FdlMill.traverser();
    traverser.setFdlHandler(genitor);
    traverser.add4Fdl(genitor);
    genitor.putOnStack(globalScope);
    IFdlArtifactScope artifact = genitor.createFromAST(ast);
    SymbolTableCompleter completer = new SymbolTableCompleter();
    ast.accept(completer);

    IFdlScope s = artifact.getSubScopes().stream().findAny().get();

    Optional<ConstantNumberSymbol> sym = s.resolveConstantNumber("ConstantNumber");
    assertTrue(sym.isPresent());
    assertEquals("Erdbeschleunigung", sym.get().getTerm());
    assertEquals("g", sym.get().getIdentifier());
    assertEquals(9.81, sym.get().getValue(), 0.001);
    assertEquals("m/s^{2}", ASTHelper.expressionToString(sym.get().getUnit()));
  }

  @Test
  public void testEquationSymbolReferences() {
    ASTFdlArtifact ast = new FdlTool()
        .parse("src/test/resources/fdl/examples/Massestrom_Eq1.fdl");
    FdlScopesGenitor genitor = FdlMill.scopesGenitor();
    FdlTraverser traverser = FdlMill.traverser();
    traverser.setFdlHandler(genitor);
    traverser.add4Fdl(genitor);
    genitor.putOnStack(globalScope);
    IFdlArtifactScope artifact = genitor.createFromAST(ast);
    SymbolTableCompleter completer = new SymbolTableCompleter();
    ast.accept(completer);

    IFdlScope s = artifact.getSubScopes().stream().findAny().get();

    Optional<EquationSymbol> sym = s.resolveEquation("Equation");
    assertTrue(sym.isPresent());
    assertTrue(sym.get().getReferenceMap().get("m^^{.}").isVariable());
    assertEquals(20d, sym.get().getReferenceMap().get("m^^{.}").getMin(), 0.001);
    assertEquals(40d, sym.get().getReferenceMap().get("m^^{.}").getMax(), 0.001);
    assertTrue(sym.get().getReferenceMap().get("m^^{.}").hasUnit());
    assertEquals("kg/s", ASTHelper.expressionToString(sym.get().getReferenceMap().get("m^^{.}").getUnitExpression()));
    assertEquals(ASTSpaces.INT, sym.get().getReferenceMap().get("m^^{.}").getSpace());

    assertTrue(sym.get().getReferenceMap().get("m").isVariable());
    assertEquals(1d, sym.get().getReferenceMap().get("m").getMin(), 0.001);
    assertEquals(5d, sym.get().getReferenceMap().get("m").getMax(), 0.001);
    assertTrue(sym.get().getReferenceMap().get("m").hasUnit());
    assertEquals("kg", ASTHelper.expressionToString(sym.get().getReferenceMap().get("m").getUnitExpression()));
    assertEquals(ASTSpaces.FLOAT, sym.get().getReferenceMap().get("m").getSpace());
    assertEquals(2, sym.get().getReferenceMap().get("m").getDecimalSpaces());

    assertTrue(sym.get().getReferenceMap().get("l_{a}").isVariable());
    assertEquals(5d, sym.get().getReferenceMap().get("l_{a}").getMin(), 0.001);
    assertEquals(100d, sym.get().getReferenceMap().get("l_{a}").getMax(), 0.001);
    assertTrue(sym.get().getReferenceMap().get("l_{a}").hasUnit());
    assertEquals("m", ASTHelper.expressionToString(sym.get().getReferenceMap().get("l_{a}").getUnitExpression()));
    assertEquals(ASTSpaces.FLOAT, sym.get().getReferenceMap().get("l_{a}").getSpace());
    assertEquals(-1, sym.get().getReferenceMap().get("l_{a}").getDecimalSpaces());

    assertTrue(sym.get().getReferenceMap().get("v").isVariable());
    assertEquals(1d, sym.get().getReferenceMap().get("v").getMin(), 0.001);
    assertEquals(1d, sym.get().getReferenceMap().get("v").getMax(), 0.001);
    assertTrue(sym.get().getReferenceMap().get("v").hasUnit());
    assertEquals("km/h", ASTHelper.expressionToString(sym.get().getReferenceMap().get("v").getUnitExpression()));
    assertNull(sym.get().getReferenceMap().get("v").getSpace());

    assertTrue(sym.get().getReferenceMap().get("a").isParameter());
    assertEquals(ASTSpaces.INT, sym.get().getReferenceMap().get("a").getSpace());
  }

  @Test
  public void testPatternSymbol() {
    ASTFdlArtifact ast = new FdlTool()
        .parse("src/test/resources/fdl/examples/Example_Pattern.fdl");
    FdlScopesGenitor genitor = FdlMill.scopesGenitor();
    FdlTraverser traverser = FdlMill.traverser();
    traverser.setFdlHandler(genitor);
    traverser.add4Fdl(genitor);
    genitor.putOnStack(globalScope);
    IFdlArtifactScope artifact = genitor.createFromAST(ast);
    SymbolTableCompleter completer = new SymbolTableCompleter();
    ast.accept(completer);

    IFdlScope s = artifact.getSubScopes().stream().findAny().get();

    Optional<PatternSymbol> sym = s.resolvePattern("Pattern");
    assertTrue(sym.isPresent());
    assertFalse(sym.get().getAutomaton().getStates().isEmpty());
    assertFalse(sym.get().getAutomaton().getTransitions().isEmpty());
    assertEquals(sym.get().getAutomaton().getRelevancy(), Relevancy.ALL);
  }

  @Test
  public void testProgressionSymbol() {
    ASTFdlArtifact ast = new FdlTool()
        .parse("src/test/resources/fdl/examples/Example_Pro.fdl");
    FdlScopesGenitor genitor = FdlMill.scopesGenitor();
    FdlTraverser traverser = FdlMill.traverser();
    traverser.setFdlHandler(genitor);
    traverser.add4Fdl(genitor);
    genitor.putOnStack(globalScope);
    IFdlArtifactScope artifact = genitor.createFromAST(ast);
    SymbolTableCompleter completer = new SymbolTableCompleter();
    ast.accept(completer);

    IFdlScope s = artifact.getSubScopes().stream().findAny().get();

    Optional<ProgressionSymbol> sym = s.resolveProgression("Progression");
    assertTrue(sym.isPresent());
  }

  @Test
  public void testHierarchySymbol() {
    ASTFdlArtifact ast = new FdlTool()
        .parse("src/test/resources/fdl/examples/Automatische_Verladesysteme_Hie.fdl");
    FdlScopesGenitor genitor = FdlMill.scopesGenitor();
    FdlTraverser traverser = FdlMill.traverser();
    traverser.setFdlHandler(genitor);
    traverser.add4Fdl(genitor);
    genitor.putOnStack(globalScope);
    IFdlArtifactScope artifact = genitor.createFromAST(ast);
    SymbolTableCompleter completer = new SymbolTableCompleter();
    ast.accept(completer);

    IFdlScope s = artifact.getSubScopes().stream().findAny().get();

    Optional<HierarchySymbol> sym = s.resolveHierarchy("Hierarchy");
    assertTrue(sym.isPresent());
  }

  @Test
  public void testVariableGroupsSymbol() {
    ASTFdlArtifact ast = new FdlTool()
        .parse("src/test/resources/fdl/examples/Example_VariableGroups.fdl");
    FdlScopesGenitor genitor = FdlMill.scopesGenitor();
    FdlTraverser traverser = FdlMill.traverser();
    traverser.setFdlHandler(genitor);
    traverser.add4Fdl(genitor);
    genitor.putOnStack(globalScope);
    IFdlArtifactScope artifact = genitor.createFromAST(ast);
    SymbolTableCompleter completer = new SymbolTableCompleter();
    ast.accept(completer);

    IFdlScope s = artifact.getSubScopes().stream().findAny().get();

    Optional<VariableGroupsSymbol> sym = s.resolveVariableGroups("VariableGroups");
    assertTrue(sym.isPresent());
  }
}
