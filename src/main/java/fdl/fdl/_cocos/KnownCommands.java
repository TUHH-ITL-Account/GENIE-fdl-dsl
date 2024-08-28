package fdl.fdl._cocos;

import de.se_rwth.commons.logging.Log;
import fdl.fdl._ast.ASTSymbolCommand;
import java.util.Arrays;

public class KnownCommands implements FdlASTSymbolCommandCoCo {

  protected String[] commands = {"alpha", "beta", "Gamma", "gamma", "Delta", "delta", "epsilon",
      "varepsilon",
      "zeta", "eta", "Theta", "theta", "vartheta", "iota", "kappa", "Lambda", "lambda", "mu", "nu",
      "Xi", "xi", "pi", "Pi", "rho", "varrho", "Sigma", "sigma", "tau", "Upsilon", "upsilon", "Phi",
      "phi", "varphi", "chi", "Psi", "psi", "Omega", "omega", "euler", "infty"};

  @Override
  public void check(ASTSymbolCommand node) {
    String cmd = node.getName();
    if (Arrays.stream(commands).noneMatch(cmd::equals)) {
      Log.error("0xC001: Unknown command '" + cmd + "'.");
    }
  }
}
