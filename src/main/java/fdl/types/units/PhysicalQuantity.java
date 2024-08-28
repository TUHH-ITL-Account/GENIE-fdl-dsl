package fdl.types.units;

import java.math.BigDecimal;

public abstract class PhysicalQuantity {

  public PhysicalQuantity() {
  }

  public abstract BigDecimal convert(BigDecimal value, String fromUnit, String toUnit);

  public abstract boolean containsUnit(String toCheck);

  public abstract String getBaseUnit();

  public abstract BigDecimal toBaseUnit(BigDecimal value, String fromUnit);
}
