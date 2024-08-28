package fdl.fdl._symboltable;

import java.util.ArrayList;
import java.util.List;

public class HierarchySymbol extends HierarchySymbolTOP {

  private String object;
  private HierarchyNode hierarchyRoot;

  public HierarchySymbol() {
    super("Hierarchy");
  }

  public HierarchySymbol(String n) {
    this();
  }

  @Override
  public String getName() {
    return "Hierarchy";
  }

  public String getObject() {
    return object;
  }

  public void setObject(String object) {
    this.object = object;
  }

  public HierarchyNode getHierarchyRoot() {
    return hierarchyRoot;
  }

  public void setHierarchyRoot(HierarchyNode hierarchyRoot) {
    this.hierarchyRoot = hierarchyRoot;
  }

  public static class HierarchyNode {
    private String content;
    private List<HierarchyNode> children = new ArrayList<>();
    private HierarchyNode parent;
    private int id;
    private String structure;

    public HierarchyNode(String content) {
      this(0, content);
    }

    public HierarchyNode(int id, String content) {
      this.id = id;
      this.content = content;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public List<HierarchyNode> getChildren() {
      return children;
    }

    public void setChildren(List<HierarchyNode> children) {
      this.children = children;
    }

    public HierarchyNode getParent() {
      return parent;
    }

    public void setParent(HierarchyNode parent) {
      this.parent = parent;
    }

    public int getId() {
      return id;
    }

    public String getStructure() {
      if(structure == null) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for(HierarchyNode child : children) {
          sb.append(child.getStructure());
        }
        this.structure = sb.append(")").toString();
      }
      return structure;
    }

    @Override
    public boolean equals(Object other) {
      if(!(other instanceof HierarchyNode)) {
        return false;
      }
      return id == ((HierarchyNode)other).getId() && content.equals(((HierarchyNode)other).getContent());
    }
  }
}
