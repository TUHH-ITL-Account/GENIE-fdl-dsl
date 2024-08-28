package fdl.fdl._cocos;

public class StandardFdlCocoChecker {

  public static FdlCoCoChecker createStandardChecker() {
    FdlCoCoChecker checker = new FdlCoCoChecker();

    checker.addCoCo(new KnownCommands());
    checker.addCoCo(new KnownPhysicalQuantitiesInSpecification());
    //checker.addCoCo(new KnownUnitsInReference());
    checker.addCoCo(new IdentifiersInWithPartExist());
    checker.addCoCo(new IdentifiersInWithPartExistOnce());

    return checker;
  }
}
