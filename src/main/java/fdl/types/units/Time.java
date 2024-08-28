package fdl.types.units;

import static java.util.Map.entry;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class Time extends PhysicalQuantity {

  private final String baseUnit = "s";

  // note: time SI units are all derivatives of "second", values are factors of *10^x from base unit
  private final Map<String, Integer> factorMap = Map.ofEntries(
      entry("ys", -24), entry("zs", -21), entry("as", -18), entry("fs", -15),
      entry("ps", -12), entry("ns", -9), entry("us", -6), entry("ms", -3),
      entry("cs", -2), entry("ds", -1), entry("s", 0), entry("das", 1),
      entry("hs", 2), entry("ks", 3), entry("Ms", 6), entry("Gs", 9),
      entry("Ts", 12), entry("Ps", 15), entry("Es", 18), entry("Zs", 21),
      entry("Ys", 24)
  );

  // note: values are divisors from base unit
  private final Map<String, Integer> alternativeUnits = Map.ofEntries(
      entry("m", 60), entry("h", 3600), entry("day", 86400)
  );

  //todo: test
  @Override
  public BigDecimal convert(BigDecimal value, String fromUnit, String toUnit) {
    if (this.alternativeUnits.containsKey(fromUnit)) {
      if (this.alternativeUnits.containsKey(toUnit)) {
        return toBaseUnit(value, fromUnit).divide(
            BigDecimal.valueOf(this.alternativeUnits.get(toUnit)), RoundingMode.HALF_UP);
      } else if (this.factorMap.containsKey(toUnit)) {
        return toBaseUnit(value, fromUnit).movePointLeft(this.factorMap.get(toUnit));
      }
    } else if (this.factorMap.containsKey(fromUnit)) {
      if (this.alternativeUnits.containsKey(toUnit)) {
        return toBaseUnit(value, fromUnit).divide(
            BigDecimal.valueOf(this.alternativeUnits.get(toUnit)), RoundingMode.HALF_UP);
      } else if (this.factorMap.containsKey(toUnit)) {
        return value.movePointLeft(this.factorMap.get(toUnit) - this.factorMap.get(fromUnit));
      }
    }
    // either of the units is not defined
    return null;
  }

  @Override
  public boolean containsUnit(String toCheck) {
    return this.factorMap.containsKey(toCheck) || this.alternativeUnits.containsKey(toCheck);
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
    if (this.factorMap.containsKey(fromUnit)) {
      return value.movePointLeft(this.factorMap.get(fromUnit));
    } else if (this.alternativeUnits.containsKey(fromUnit)) {
      return value.multiply(BigDecimal.valueOf(this.alternativeUnits.get(fromUnit)));
    }
    return null;
  }
}
