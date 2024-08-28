package fdl.types.units;

import static java.util.Map.entry;

import java.math.BigDecimal;
import java.util.Map;

public class Mass extends PhysicalQuantity {

  private final String baseUnit = "kg";
  private final Map<String, Integer> factorMap = Map.ofEntries(
      entry("yg", -24), entry("zg", -21), entry("ag", -18), entry("fg", -15),
      entry("pg", -12), entry("ng", -9), entry("ug", -6), entry("mg", -3),
      entry("cg", -2), entry("dg", -1), entry("g", 0), entry("dag", 1),
      entry("hg", 2), entry("kg", 3), entry("Mg", 6), entry("Gg", 9),
      entry("Tg", 12), entry("Pg", 15), entry("Eg", 18), entry("Zg", 21),
      entry("Yg", 24)
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
