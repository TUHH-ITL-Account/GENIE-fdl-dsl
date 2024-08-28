package fdl.fdl._symboltable;

import fdl.types.containers.Example;
import java.util.ArrayList;
import java.util.List;

public class ExamplesSymbol extends ExamplesSymbolTOP {

  private String object;
  private List<Example> examples;
  private List<String> categories;
  private List<String> explicitFalse = new ArrayList<>();
  private List<String> superFdls = new ArrayList<>();
  private List<String> subFdls = new ArrayList<>();
  private List<String> overlapFdls = new ArrayList<>();
  private List<String> opposingFdls = new ArrayList<>();

  public ExamplesSymbol() {
    super("Examples");
  }

  public ExamplesSymbol(String n) {
    this();
  }

  @Override
  public String getName() {
    return "Examples";
  }

  public List<Example> getExamples() {
    return examples;
  }

  public void setExamples(List<Example> examples) {
    this.examples = examples;
  }

  public boolean hasExamplesWithAttributes() {
    for (Example ex : examples) {
      if (ex.hasProperties()) {
        return true;
      }
    }
    return false;
  }

  public boolean allExamplesWithAttributes() {
    for (Example ex : examples) {
      if (!ex.hasProperties()) {
        return false;
      }
    }
    return true;
  }

  public boolean hasExamplesWithImages() {
    for (Example ex : examples) {
      if (ex.hasImages()) {
        return true;
      }
    }
    return false;
  }

  public boolean allExamplesWithImages() {
    for (Example ex : examples) {
      if (!ex.hasImages()) {
        return false;
      }
    }
    return true;
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
