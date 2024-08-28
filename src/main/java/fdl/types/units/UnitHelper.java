package fdl.types.units;

import static java.util.Map.entry;

import java.util.Map;

public class UnitHelper {

  public static final Map<String, String[]> popularUnits = Map.ofEntries(
      entry("time", new String[]{"s", "m", "h"}),
      entry("length", new String[]{"cm", "m", "km"}),
      entry("mass", new String[]{"g", "kg"})
  );

  private static final Time staticTimeObject = new Time();
  private static final Mass staticMassObject = new Mass();
  private static final Length staticLengthObject = new Length();
  public static final Map<String, PhysicalQuantity> unitObjectsMap = Map.ofEntries(
      entry("time", staticTimeObject),
      entry("length", staticLengthObject),
      entry("mass", staticMassObject)
  );

  public static final Map<String, String> baseUnitMap = Map.ofEntries(
      entry("time", staticTimeObject.getBaseUnit()),
      entry("length", staticLengthObject.getBaseUnit()),
      entry("mass", staticMassObject.getBaseUnit())
  );
}
