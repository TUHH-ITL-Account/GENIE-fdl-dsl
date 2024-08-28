/* (c) https://github.com/MontiCore/monticore */
package fdl.types.units;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import org.junit.Test;

public class LengthTest {

  @Test
  public void testLengthConversions() {
    Length unit = new Length();
    BigDecimal inKm = new BigDecimal(42);
    assertEquals(42000d, unit.convert(inKm, "km", "m").doubleValue(), 0.1);
    assertEquals(42000000d, unit.convert(inKm, "km", "mm").doubleValue(), 0.1);
    assertEquals(0.000042d, unit.convert(inKm, "km", "Gm").doubleValue(), 0.0000001);
  }

}
