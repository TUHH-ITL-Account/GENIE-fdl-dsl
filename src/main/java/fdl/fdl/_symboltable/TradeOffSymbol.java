package fdl.fdl._symboltable;

import java.util.List;

public class TradeOffSymbol extends TradeOffSymbolTOP {

  private String object;
  private List<String> advantages;
  private List<String> disadvantages;

  public TradeOffSymbol() {
    super("Trade-Off");
  }

  public TradeOffSymbol(String n) {
    this();
  }

  @Override
  public String getName() {
    return "Trade-Off";
  }

  public List<String> getAdvantages() {
    return advantages;
  }

  public void setAdvantages(List<String> advantages) {
    this.advantages = advantages;
  }

  public List<String> getDisadvantages() {
    return disadvantages;
  }

  public void setDisadvantages(List<String> disadvantages) {
    this.disadvantages = disadvantages;
  }

  public String getObject() {
    return object;
  }

  public void setObject(String object) {
    this.object = object;
  }
}
