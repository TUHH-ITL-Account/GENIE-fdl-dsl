package fdl.types.units;

import static java.util.Map.entry;

import java.math.BigDecimal;
import java.util.Map;

public class Length extends PhysicalQuantity {

  private final String baseUnit = "m";
  private final Map<String, Integer> factorMap = Map.ofEntries(
      entry("ym", -24), entry("zm", -21), entry("am", -18), entry("fm", -15),
      entry("pm", -12), entry("nm", -9), entry("um", -6), entry("mm", -3),
      entry("cm", -2), entry("dm", -1), entry("m", 0), entry("dam", 1),
      entry("hm", 2), entry("km", 3), entry("Mm", 6), entry("Gm", 9),
      entry("Tm", 12), entry("Pm", 15), entry("Em", 18), entry("Zm", 21),
      entry("Ym", 24)
  );

  @Override
  public BigDecimal convert(BigDecimal value, String fromUnit, String toUnit) {
    if (!containsUnit(fromUnit) || !containsUnit(toUnit)) {
      return null;
    }
    return value.movePointLeft(this.factorMap.get(toUnit) - this.factorMap.get(fromUnit));
  }

  @Override
  public boolean containsUnit(String toCheck) {
    return this.factorMap.containsKey(toCheck);
  }

  @Override
  public String getBaseUnit() {
    return this.baseUnit;
  }

  @Override
  public BigDecimal toBaseUnit(BigDecimal value, String fromUnit) {
    if (fromUnit.equals(this.baseUnit)) {
      return value;
    }
    return convert(value, fromUnit, this.baseUnit);
  }
}
