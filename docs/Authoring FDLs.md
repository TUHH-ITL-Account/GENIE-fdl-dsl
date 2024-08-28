# Authoring FDLs / User Guide to FDLs

## What are FDLs

'Formally Defined Subject Matter' (german: 'Formal Definierte Lerninhalte') is a concept used to 
formalise expertise/knowledge (from university courses). It defines a syntax 
which allows the formal definition of different types of knowledge. The resulting models can be 
parsed by a machine and enable further processing of the contents, specifically creating exercises 
in regard to the GENERATING project.

## Rules and Conventions

- Each (FDL) model is intended to be written in a separate file. 
- By convention the file name should equal the model name and indicate the FDL type. 
- The file ending is `.fdl`. 
- Different content blocks are conventionally indented with 2 spaces. Editors such as Notepad++ or Visual Studio Code can help 
by automatically indenting new lines or replacing tabs with spaces.
- File names should not be too long, since Windows has a file name length limit. Be short but precise.
- FDL names must start with a letter or underscore ('_').
- Names and file names may not contain ':'.
- Decimal numbers use '.' and **NOT** ','.

## FDL Types

Every FDL has the same encapsulating structure:
```
FDL --name-- {
  --content--
}
```

Depending on the type of knowledge a different syntax is used for the content, inidcated by 
different keywords.

FDL types are implemented as needed. This means certain types of knowledge cannot be represented 
yet. Using FDL types to represent other types of knowledge than they are intended to is __NOT__ 
advised and/or encouraged.

### Definitions

Definitions are simple mappings from a certain term to its definition / defining sentence.

Abstract syntax:
```
DEFINITION [term:String] := [definition:String]
```

Example: Unstetigförderer_Def.fdl
```
FDL Unstetigförderer_Def {
  DEFINITION "Unstetigförderer" := "Unstetigförderer sind Förderanlagen, bei denen das Fördergut auf einem flexiblen oder definierten Förderweg von Aufgabe- zu Abgabestelle diskontinuierlich, mit wechselnder Geschwindigkeit bewegt wird."
}
```

### Examples
Examples are sets of objects indicating a concrete example for the mainObject.
A list of categories the examples fit into can be added for a better distractor search.
Optionally attributes can be added to each example entry. If done, it should be done uniformly.
Image references can be added to each example entry. If done, it should be done uniformly.

Abstract syntax:
```
EXAMPLES [mainObject:String] [[categories:List<String>]]? := { ([object:String] [properties:List<String>]? || ;)+  }
```

Example: Führungstechniken_Ex.fdl
Advanced example:
```
FDL Führungstechniken_Ex_advanced {
  EXAMPLES "Führungstechniken" ["Technik", "Algorithmus"] := {
    "reale Leitlinie" { 
      PROPERTIES: ["magnetische Führung","induktive Führung","optische Führung","Koppelnavigation"] ;
      IMAGES: ["bild12.png"]
    } ;
    "virtuelle Leitlinie" { 
      PROPERTIES: ["Trägheitsnavigation","Laserscanner und Positionsbaken","Umweltabbildung","Koppelnavigation"] ;
      IMAGES: ["bild13.png", "bild14.png"]
    }
  }
}
```
ATTENTION (19.08.22): The format for advanced examples changed! Nesting with {} + keywords and , instead of ; in lists.

Simple Example:
```
FDL Führungstechniken_Ex {
  EXAMPLES "Führungstechniken" := {
    "reale Leitlinie"  ;
    "virtuelle Leitlinie" 
  }
}
```

### Characteristics
Characteristics are essentially a set of (extensive) attributes or properties of a thing.
A list of categories the examples fit into can be added for a better distractor search.
Formulating the single characteristics without mentioning the object 
itself, as if it were known, is highly recommended. This also includes referencing the object with
some pronouns. 

Abstract syntax:
```
CHAR [object:String] [[categories:List<String>]]? := { ([String] || ;)+  } (EXPLICIT_FALSE {([String] || ;)+})?
```

Example: Bandförderer_Char.fdl
```
FDL Bandförderer_Char {
  CHAR "Bandförderer" ["Fördermittel", "Maschine"] := {
    "gleitet auf Tragrollen oder einer glatten Unterlage.";
    "werden über Reibschluss von einer Antriebstrommel angetrieben.";
    "haben eine große Bedeutung in der Logistik, z.B.: Bei der Lkw Entladung, Schüttguttransport,Stückguttransport ";
    "Fördermenge bis 20.000 t/h";
    "Geschwindigkeit bis 3 m/s";
    "Förderlänge bis 500 m, in Ausnahmefällen bis 5.000 m";
  }
  EXPLICIT_FALSE {
    "können einfach verfahren werden.";
  }
}
```

### Trade-Offs
Trade-Offs describe advantages and disadvantages of something. Either block can be left empty, 
although not recommended.

Abstract syntax:
```
TRADE-OFF [object:String] := + { ([String] || ;)* } - { ([String] || ;)* }
```

Example: Regalbediengeräte_TO.fdl
```
FDL Regalbediengeräte_TO {
  TRADE-OFF "Regalbediengeräte" :=
    + {
      "Vergleichsweise schmale Arbeitsgänge";
      "Gute Automatisierbarkeit";
      "Höhere Förderleistungen als Gabelstapler"
    }
    - {
      "Geringe Flexibilität";
      "Beschränkung der Bewegungsfreiheit durch Schienen"
    }
}
```

### Sequences
Sequences are a simple list of strings surrounded by `[ ]`. The order of contents are central to 
the underlying semantics.

Abstract syntax:
```
SEQUENCE [object:String] := [ ([String] || ;)+ ]
```

Example: Schritte_Kommissioniervorgang_Seq.fdl
```
FDL Schritte_Kommissioniervorgang_Seq {
  SEQUENCE "Schritte eines Kommissioniervorgangs" := [
    "Vorgabe der Transportinformation für Transport der Güter zum Bereitstellort";
    "Transport der Güter zum Bereitstellort";
    "Bereitstellung der Güter";
    "Bewegung des Kommissionierers zum Bereitstellort";
    "Vorgabe der Entnahmeinformation";
    "Entnahme der Entnahmeeinheit(en) durch den Kommissionierer";
    "Abgabe der Entnahmeeinheit(en)";
    "Quittierung des Entnahmevorgangs bzw. der Entnahmevorgänge";
    "Transport der Entnahmeeinheit(en) zur Abgabe der Sammeleinheit(en)";
    "Vorgabe der Transportinformation für die angebrochene(en) Bereitstelleinheiten";
    "Transport der angebrochenen Bereitstelleinheiten"
  ]
}
```

### Constants
Constants are simple definitions of some alias and identifier to some value with optional units.

Abstract syntax:
```
CONSTANT [term:String] [identifier:ExtendedName] := [value:FdlNumber] [unit:Expression]?
```

Example: Erdbeschleunigung_Const.fdl
```
FDL Erdbeschleunigung_Const {
  CONSTANT "Erdbeschleunigung" g := 9.81 m/s^{2}
}
```

### Equations
Equations are defined using a nesting of different (mathematical) expressions. At the maximum depth 
identifiers or numbers need to be present. Identifiers allow for certain visual flavors:

`MyVar^{"." | "^" | "~" | "*" | "-"}` allows for a symbol directly above the identifier.

`MyVar_{"text"}` defines a subscript for the identifier.

After defining the equation itself a 3-part addendum is required to give further information on
the used identifiers.

Inside the `ALIASES` block each identifier needs a description of their meaning / what they stand for.

(WIP) In the `SPECIFICATIONS` block identifiers can receive a unit specification. In case of 
the base SI dimensions (time, length, mass) these are to be used instead of directly inputting some 
unit. It is highly recommended to use standard units (e.g. N, kg, s, Pa).

The `REFERENCES` block is used to define common value ranges, units and value spaces for each 
identifier. When defining value ranges it is recommended to first define the right-sided identifiers 
of the equation and then setting the range of the left-sided identifier to the min/max of the right side. 
Furthermore, the value spaces and trailing digits should be set according to the defined ranges. 
E.g. in large ranges like `200-2000` `INT` should be sufficient for most cases. In ranges like `1-10` `FLOAT[1]` 
could make sense as it yields more possible numbers to choose from. Lastly small ranges like 
`0.05-1` should inherently use 2 trailing digits.

Abstract syntax:
```
EQUATION [Expression] WITH:
  ALIASES ([Identifier] = [term:String] || ;)+
  SPECIFICATIONS ([Identifier] = Expression || ;)+
  REFERENCES ([Identifier] = (RangeSpecification|Parameter|ConstantRef) || ;)+ 
```
where
```
SpaceSpecification = "IN" Spaces force:["!"]? ("[" decimalplace:Digits "]")?

Parameter = "PARAMETER" SpaceSpecification ;
RangeSpecification = start:FdlNumber ("-" end:FdlNumber)? unit:Expression? SpaceSpecification?
ConstantRef = "CONSTANT" ref:String?
```

Example: Antriebsleistung_Eq.fdl
```
FDL Antriebsleistung_Eq {
  EQUATION P_{"m"} = ( F_{"wr"} * v_{"s"} ) / m_{"ges"}
  WITH:
    ALIASES:
      F_{"wr"} = "Kraft zur Überwindung des Rollwiderstandes";
      v_{"s"} = "maximale Geschwindigkeit";
      P_{"m"} = "Antriebleistung";
      m_{"ges"} = "Wirkunsgrad Motor";

    SPECIFICATIONS:
      F_{"wr"} = "N";
      v_{"s"} = "length" / "time";
      P_{"m"} = "W";

    REFERENCES:
      F_{"wr"} = 300-600 N IN INT;
      v_{"s"} = 0.2-1 m/s IN INT;
      P_{"m"} = 600 - 10000 W IN FLOAT[2];
      m_{"ges"} = 0.5-0.9 IN FLOAT[1];
}
```
Example: Kraft_pro_Rad_Eq.fdl
```
FDL Kraft_pro_Rad_Eq {
  EQUATION F_{"R"} = ( M * g ) / A_{"R"}
  WITH:
    ALIASES:
      F_{"R"} = "Maximale Kraft in einem Rad";
      M = "Gewicht der Bauteil auf dem Rad";
      g = "Erdbeschleunigung";
      A_{"R"} = "Anzahl der Räder";

    SPECIFICATIONS:
      F_{"R"} = "N";
      M = "mass";
      g = "length" / "time"^2;

    REFERENCES:
      F_{"R"} = 735-1982 N IN INT;
      M = 300-800 kg IN INT;
      g = CONSTANT "Erdbeschleunigung";
      A_{"R"} = 4 IN INT;
}
```

### (Labeled) Figures
Labeled figures follow the idea that given some figure/image there are labels which can be applied
to certain positions as defined by their pixel coordinates. The corresponding image file needs to 
be added to the 'imgs' directory in complete knowledge models.

Abstract syntax:
```
FIGURE FILE [filename:String] LABELS { ([term:String] : ([x:Digits] ; y:Digits) || ;)+ }
```

Example: Unstetigförderer_Def.fdl
```
FDL Grundbegriffe_Fördertechnik_Fig {
  FIGURE
  FILE
    "SystematikA1.png"
  LABELS {
    "Senke" : (547;116) ;
    "Fördern" : (274;115) ;
    "Fördertechnik" : (175;188);
    "Fördermittel": (278;22);
    "Quelle": (52;118);
  }
}
```

### Hierarchies
Hierarchies enable a certain structuring. Terms with the same number of '--' are on the same depth level.

Abstract syntax:
```
HIERARCHY [term:String] := { (--)* [level:String] \n }
```

Example: Automatische_Verladesysteme_Hie.fdl
```
FDL Automatische_Verladesysteme_Hie {
  HIERARCHY "Automatische Verladesysteme" := {
    "Verladesysteme"
    --"automatisch"
    ----"ohne Ladeflächentechnik"
    ------"stetig"
    --------"Rollenteppich"
    --------"Hubkettenförderer"
    ------"unstetig"
    --------"Gabelverladeautomat"
    --------"Modulfahrzeug"
    ----"mit Ladeflächentechnik"
    ------"stetig"
    --------"Rollenförderer"
    --------"Rollboden"
    ----"Spezialsysteme"
    ------"Automatische Rampe"
  }
}

```

### Progressions
Progressions are intended to contain a series of data points, most commonly a series of the form (value, time).

Abstract syntax:
```
PROGRESSION "Example Progression via Series" := { (X-AXIS = [String])? (Y-AXIS =[String])?  (SERIES [String] { (([Number];[Number])||,)+ })+ }
```

Example: Example_Pro.fdl
```
FDL Example_Pro {
  PROGRESSION "Example Progression via Series" := {
    X-AXIS = "Bananas eaten";
    Y-AXIS = "Showers taken";
    SERIES "Line1" {
      (0;0),(10;15),(20;25),(30;40),(40;60),(60;95)
    };
    SERIES "Line2" {
      (0;0),(10;15),(20;25),(30;40),(40;60),(60;95)
    };
  }
}

```

### Patterns
Patterns are modeled using automata. A word is part of the pattern's language, if there is a run starting in any initial state, taking transitions as per letters of the word and end in a final state. If the relvancy is "ALL" both states and inputs are considered part of the word. If the relevancy is "INPUTS_ONLY" only inputs are considered.

As an example, the pattern below forces all words to start with 'Q', end with 'S', etc. An example word of this language would be "Q Vorlauf SP Hauptlauf VP Nachlauf S"

Abstract syntax:
```
PATTERN [String] := AUTOMATON { ((State | Transition | Relevancy) ";")* }
```

Example: Example_Pro.fdl
```
FDL Standardfrachtketten {
  PATTERN "Standardfrachtketten" := AUTOMATON {
    RELEVANCY = ALL;

    STATE q0 "Q" <<INIT>>;
    STATE q1 "S" <<FIN>>;
    STATE q2 "UP";
    STATE q3 "SP";
    STATE q4 "VP";
    STATE q5 "UP";

    q0 - "Direktlauf" > q1;
    q0 - "Vor-/Zulauf" > q2;
    q2 - "Hauptlauf" > q2;
    q2 - "Nach-/Auslauf" > q1;
    q0 - "Vorlauf" > q3;
    q3 - "Hauptlauf" > q4;
    q3 - "Zulauf" > q5;
    q5 - "Hauptlauf" > q5;
    q5 - "Ablauf" > q4;
    q4 - "Nachlauf" > q1;
  }
}

```

## Planned Types
### Graphs

## Verifying Models
Coming soon(tm)
