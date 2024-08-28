package fdl.fdl._symboltable;

import java.util.ArrayList;
import java.util.List;

public class CharacteristicsSymbol extends CharacteristicsSymbolTOP {

  private String object;
  private List<String> characteristics;
  private List<String> categories;
  private List<String> explicitFalse = new ArrayList<>();
  private List<String> superFdls = new ArrayList<>();
  private List<String> subFdls = new ArrayList<>();
  private List<String> overlapFdls = new ArrayList<>();
  private List<String> opposingFdls = new ArrayList<>();

  public CharacteristicsSymbol() {
    super("Characteristics");
  }

  public CharacteristicsSymbol(String n) {
    this();
  }

  @Override
  public String getName() {
    return "Characteristics";
  }

  public List<String> getCharacteristics() {
    return characteristics;
  }

  public void setCharacteristics(List<String> characteristics) {
    this.characteristics = characteristics;
  }

  public String getObject() {
    return object;
  }

  public void setObject(String object) {
    this.object = object;
  }

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }

  public List<String> getExplicitFalse() {
    return explicitFalse;
  }

  public void setExplicitFalse(List<String> explicitFalse) {
    this.explicitFalse = explicitFalse;
  }

  public List<String> getSuperFdls() {
    return superFdls;
  }

  public void setSuperFdls(List<String> superFdls) {
    this.superFdls = superFdls;
  }

  public List<String> getSubFdls() {
    return subFdls;
  }

  public void setSubFdls(List<String> subFdls) {
    this.subFdls = subFdls;
  }

  public List<String> getOverlapFdls() {
    return overlapFdls;
  }

  public void setOverlapFdls(List<String> overlapFdls) {
    this.overlapFdls = overlapFdls;
  }

  public List<String> getOpposingFdls() {
    return opposingFdls;
  }

  public void setOpposingFdls(List<String> opposingFdls) {
    this.opposingFdls = opposingFdls;
  }
}
