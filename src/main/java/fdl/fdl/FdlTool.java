package fdl.fdl;

import de.se_rwth.commons.logging.Finding;
import de.se_rwth.commons.logging.Log;
import fdl.exceptions.DslException;
import fdl.fdl._ast.ASTFdlArtifact;
import fdl.fdl._cocos.FdlCoCoChecker;
import fdl.fdl._cocos.StandardFdlCocoChecker;
import fdl.fdl._symboltable.FdlArtifactSymbol;
import fdl.fdl._symboltable.SymbolTableCompleter;
import fdl.fdl._visitor.FdlTraverser;
import java.io.File;

public class FdlTool extends FdlToolTOP {

  @Override
  public void completeSymbolTable(ASTFdlArtifact node) {
    FdlTraverser completer = new SymbolTableCompleter();
    node.accept(completer);
  }

  @Override
  public void runDefaultCoCos(ASTFdlArtifact ast) {
    FdlCoCoChecker checker = StandardFdlCocoChecker.createStandardChecker();
    checker.checkAll(ast);
  }

  /**
   * Given a model it parses the model, creates + completes the symbol table, runs the default cocos
   * and returns an ASTFdlArtifact symbol. Calls fullParse(model, 2)
   *
   * @param model The model/modelpath
   * @return A FdlArtifactSymbol
   */
  public FdlArtifactSymbol fullParse(String model) throws DslException {
    return fullParse(model, 2);
  }

  /**
   * Given a model it parses the model, creates + completes the symbol table, runs the default
   * cocos
   *
   * @param model  The model/modelpath
   * @param failOn 0: Never throw exceptions, 1: Throw exception on error, 2: Throw exception on
   *               warnings and errors
   * @return A FdlArtifactSymbol
   * @throws DslException
   */
  public FdlArtifactSymbol fullParse(String model, int failOn) throws DslException {
    File modelFile = new File(model);
    if (!modelFile.exists()) {
      throw new DslException("Model-file for '" + model + "' not found.");
    }
    if (!modelFile.isFile()) {
      throw new DslException("Model-file '" + model + "' is not a file.");
    }
    if (!modelFile.canRead()) {
      throw new DslException("No read permissions for model-file '" + model + "'.");
    }
    Log.enableFailQuick(false);
    ASTFdlArtifact ast = null;
    try {
      ast = parse(model);
      createSymbolTable(ast);
      completeSymbolTable(ast);
      runDefaultCoCos(ast);
    } catch (Exception ignored) {
    }

    if (failOn > 0 && Log.getFindings().size() > 0) {
      StringBuilder msg = new StringBuilder();
      msg.append("\\n");
      if (failOn == 2) {
        for (Finding f : Log.getFindings()) {
          msg.append(f.toString());
          msg.append("\n");
        }
      } else if (failOn == 1) {
        for (Finding f : Log.getFindings()) {
          if (f.isError()) {
            msg.append(f.toString());
          }
        }
      }
      if (!msg.isEmpty()) {
        Log.clearFindings();
        throw new DslException("Failed to parse " + model + ". ERROR: " + msg);
      }
    }

    assert ast != null;
    Log.clearFindings();
    return ast.getSymbol();
  }

  public void parseFdlsInDir(String directory) {
    File dir = new File(directory);
    assert dir.exists() && dir.isDirectory() && dir.canRead();
    FdlTool cli = new FdlTool();
    for (File f : dir.listFiles()) {
      String[] split = f.getName().split("\\.");
      if (split[split.length - 1].equals("fdl")) {
        System.out.println("Trying: " + f.getName());
        try {
          FdlArtifactSymbol sym = cli.fullParse(f.getAbsolutePath(), 1);
          if (sym != null) {
            //System.out.println("Success");
          }
        } catch (Exception e) {
          System.out.println("Failure");
          e.printStackTrace();
        }
      }
    }
  }
}
