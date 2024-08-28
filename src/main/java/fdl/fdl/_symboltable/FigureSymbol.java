package fdl.fdl._symboltable;

import java.util.Map;

public class FigureSymbol extends FigureSymbolTOP {

  private String filename;
  private Map<String, Integer[]> labelMap;

  public FigureSymbol() {
    super("Figure");
  }

  public FigureSymbol(String n) {
    this();
  }

  @Override
  public String getName() {
    return "Figure";
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public Map<String, Integer[]> getLabelMap() {
    return labelMap;
  }

  public void setLabelMap(Map<String, Integer[]> labelMap) {
    this.labelMap = labelMap;
  }
}
