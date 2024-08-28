# Authoring Knowledge Models

Course-based knowledge models are not part of the FDL-dsl per se, but build upon its concept 
to provide the generator with a formal representation of the course's contents.

Knowledge models are essentially tree structures with different types of nodes: topic nodes and
FDL nodes. They are stored as two lists in a `.yaml` file, which leads to the basic structure:

Excerpt TechnischeLogistikSS22.yaml:
```
---
fdlnodes:
  - Stetigförderer_Def:
      id: fdl3
      filename: Stetigförderer_Def.fdl
      prereq: []
  - Gesamtwiderstand_Eq2:
      id: fdl4
      filename: Gesamtwiderstand_Eq2.fdl
      prereq: [Multiplikation, Addition]
  --other fdl nodes--
wmnodes:
  - Fördermaschinen:
      name: "Fördermaschinen"
      id: wm4
      children: [Fördersysteme]
      fdls: [Funktionen_von_Fördermaschinen_Ex_advanced]
  - Fördersysteme:
      name: "Fördersysteme"
      id: wm5
      children: [Stetigförderer, Unstetigförderer]
      fdls: [Unterscheidung_Stetigförderer_und_Unstetigförderer_TO, Häufige_Fördermittel_für_Fördergüter_Ex_advanced]
  --other wm nodes--
```

FDL nodes are an extra encapsulation layer for FDLs, allowing the addition of relevant attributes.

WM nodes (wm (ger): 'Wissensmodel') represent topics. Each topic can have an arbitrary number of FDLs 
attached to it.

The `children` relation of wm-nodes defines a rough 'is part of' relation between the specific course's
topics, spanning the tree. Note that this means cycles being forbidden.

A topic node having multiple parents is forbidden and a FDL node being part of different topics is
to be used with caution.
