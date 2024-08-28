package fdl.fdl;

import org.junit.Test;

public class FdlCliTest {

  @Test
  public void testParseDir() {
    String dir = "./src/test/resources/fdl/examples";
    FdlTool cli = new FdlTool();
    cli.parseFdlsInDir(dir);
  }


}
