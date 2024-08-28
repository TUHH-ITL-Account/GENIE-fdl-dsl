package fdl.fdl._symboltable;

import java.util.List;
import java.util.Map;

public class ProgressionSymbol extends ProgressionSymbolTOP {

  private String description;

  private String xAxis;
  private String yAxis;

  private Map<String, List<Integer[]>> series;

  public ProgressionSymbol() {
    super("Progression");
  }

  public ProgressionSymbol(String n) {
    this();
  }

  @Override
  public String getName() {
    return "Progression";
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getxAxis() {
    return xAxis;
  }

  public void setxAxis(String xAxis) {
    this.xAxis = xAxis;
  }

  public String getyAxis() {
    return yAxis;
  }

  public void setyAxis(String yAxis) {
    this.yAxis = yAxis;
  }

  public Map<String, List<Integer[]>> getSeries() {
    return series;
  }

  public void setSeries(Map<String, List<Integer[]>> series) {
    this.series = series;
  }
}
