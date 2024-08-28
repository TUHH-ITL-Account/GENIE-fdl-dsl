package fdl.fdl._ast;

public class ASTFdlArtifact extends ASTFdlArtifactTOP {

  @Override
  public String getName() {
    /*
    if(isPresentFdlName()) {
      return this.getFdlName();
    } else { //if(isPresentFdlName2()) {
      return this.getFdlName2();
    }
     */
    return this.getFdlName();
  }
}
