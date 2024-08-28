package fdl.types.containers;

import fdl.fdl._ast.ASTExample;
import fdl.fdl._ast.ASTExampleExtension;
import fdl.fdl._ast.ASTExampleExtensionElement;
import fdl.fdl._ast.ASTExampleImages;
import fdl.fdl._ast.ASTExampleProperties;
import java.util.ArrayList;
import java.util.List;

public class Example {

  private final String name;
  private final List<String> properties = new ArrayList<>();
  private final List<String> images = new ArrayList<>();

  public Example(ASTExample node) {
    this.name = node.getObject();
    if(node.isPresentExampleExtension()) {
      for(ASTExampleExtensionElement ext : node.getExampleExtension().getExampleExtensionElementList()) {
        if(ext instanceof ASTExampleProperties) {
          properties.addAll(((ASTExampleProperties)ext).getStringList());
        }
        if(ext instanceof ASTExampleImages) {
          images.addAll(((ASTExampleImages)ext).getStringList());
        }
      }
    }
  }

  public String getName() {
    return name;
  }

  public List<String> getProperties() {
    return properties;
  }

  public boolean hasProperties() {
    return properties.isEmpty();
  }

  public List<String> getImages() {
    return images;
  }

  public boolean hasImages() {
    return !images.isEmpty();
  }
}
