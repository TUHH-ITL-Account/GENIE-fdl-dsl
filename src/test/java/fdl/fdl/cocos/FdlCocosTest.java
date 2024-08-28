package fdl.fdl.cocos;

import de.se_rwth.commons.logging.Log;
import de.se_rwth.commons.logging.LogStub;
import fdl.fdl._ast.ASTFdlArtifact;
import fdl.fdl._cocos.FdlCoCoChecker;
import fdl.fdl._cocos.IdentifiersInWithPartExist;
import fdl.fdl._cocos.KnownCommands;
import fdl.fdl._cocos.KnownPhysicalQuantitiesInSpecification;
import fdl.fdl.lang.AbstractTest;
import java.io.IOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FdlCocosTest extends AbstractTest {

  @BeforeClass
  public static void init() {
    LogStub.init();
  }

  @After
  public void clearLog() {
    Log.clearFindings();
  }

  @Test
  public void testInvalidCommand() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/cocos/invalid/InvalidCommand.fdl");

    FdlCoCoChecker checker = new FdlCoCoChecker();
    checker.addCoCo(new KnownCommands());
    checker.checkAll(a);

    Assert.assertFalse(Log.getFindings().isEmpty());
  }

  @Test
  public void testInvalidAliasIdentifier() {
    ASTFdlArtifact a = parseModel(
        "src/test/resources/fdl/cocos/invalid/UnknownAliasIdentifier.fdl");

    FdlCoCoChecker checker = new FdlCoCoChecker();
    checker.addCoCo(new IdentifiersInWithPartExist());
    checker.checkAll(a);

    Assert.assertFalse(Log.getFindings().isEmpty());
  }

  @Test
  public void testInvalidSpecIdentifier() {
    ASTFdlArtifact a = parseModel(
        "src/test/resources/fdl/cocos/invalid/UnknownSpecIdentifier.fdl");

    FdlCoCoChecker checker = new FdlCoCoChecker();
    checker.addCoCo(new IdentifiersInWithPartExist());
    checker.checkAll(a);

    Assert.assertFalse(Log.getFindings().isEmpty());
  }


  public void testInvalidSpecQuantity() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/cocos/invalid/UnknownSpecQuantity.fdl");

    FdlCoCoChecker checker = new FdlCoCoChecker();
    checker.addCoCo(new KnownPhysicalQuantitiesInSpecification());
    checker.checkAll(a);

    Assert.assertFalse(Log.getFindings().isEmpty());
  }


  public void testValidSpecQuantity() {
    ASTFdlArtifact a = parseModel("src/test/resources/fdl/examples/Massestrom_Eq1.fdl");

    FdlCoCoChecker checker = new FdlCoCoChecker();
    checker.addCoCo(new KnownPhysicalQuantitiesInSpecification());
    checker.checkAll(a);

    Assert.assertFalse(Log.getFindings().isEmpty());
  }
}
