/* (c) https://github.com/MontiCore/monticore */
package fdl.fdl.lang;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import fdl.fdl._ast.ASTFdlArtifact;
import fdl.fdl._parser.FdlParser;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.antlr.v4.runtime.RecognitionException;

/**
 * Provides some helpers for tests.
 */
abstract public class AbstractTest {

  /**
   * Parses a model and ensures that the root node is present.
   *
   * @param modelFile the full file name of the model.
   * @return the root of the parsed model.
   */
  protected ASTFdlArtifact parseModel(String modelFile) {
    Path model = Paths.get(modelFile);
    FdlParser parser = new FdlParser();
    Optional<ASTFdlArtifact> optFdl;
    try {
      optFdl = parser.parse(model.toString());
      assertFalse(parser.hasErrors());
      assertTrue(optFdl.isPresent());
      return optFdl.get();
    } catch (RecognitionException | IOException e) {
      e.printStackTrace();
      fail("There was an exception when parsing the model " + modelFile + ": "
          + e.getMessage());
    }
    return null;
  }
}
