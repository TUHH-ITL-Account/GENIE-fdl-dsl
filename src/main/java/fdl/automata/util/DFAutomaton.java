package fdl.automata.util;

import fdl.automata._ast.ASTAutomaton;
import fdl.automata._ast.ASTConstantsAutomata;
import fdl.automata._ast.ASTRelevancy;
import fdl.automata._ast.ASTState;
import fdl.automata._ast.ASTTransition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class DFAutomaton {

  private final List<State> states = new ArrayList<>();
  /**
   * fromState -> toState -> input so: q1->q2->"qwertz"
   **/
  private final Map<String, Map<String, String>> transitions = new HashMap<>();
  private State initState;
  private Relevancy relevancy;

  public DFAutomaton(ASTAutomaton node) {
    for (ASTState stateNode : node.getStateList()) {
      State tmp = new State(stateNode);
      states.add(tmp);
      if (tmp.isInitial()) {
        initState = tmp;
      }
    }

    for (ASTTransition transNode : node.getTransitionList()) {
      String fromState = transNode.getFrom();
      String input = transNode.getInput();
      String toState = transNode.getTo();
      transitions.putIfAbsent(fromState, new HashMap<>());
      transitions.get(fromState).put(toState, input);
    }

    for (ASTRelevancy relNode : node.getRelevancyList()) {
      if (relNode.getRelevancy() == ASTConstantsAutomata.ALL) {
        relevancy = Relevancy.ALL;
      } else {
        relevancy = Relevancy.INPUTS_ONLY;
      }
      break;
    }
  }

  public List<State> getStates() {
    return states;
  }

  public Map<String, Map<String, String>> getTransitions() {
    return transitions;
  }

  public Relevancy getRelevancy() {
    return relevancy;
  }

  public boolean isValidInputRun(List<String> inputs) {
    State currentState = initState;
    for (String inp : inputs) {
      Map<String, String> targets = transitions.get(currentState.getName());
      if (targets == null) {
        return false;
      }
      String nextState = "";
      for (var entry : targets.entrySet()) {
        if (entry.getValue().equals(inp)) {
          nextState = entry.getKey();
          break;
        }
      }
      currentState = getStateByName(nextState);
      if (currentState == null) {
        throw new RuntimeException();
      }
    }
    return currentState.isFinal();
  }

  public boolean isValidRun(List<String> run) {
    State currentState = initState;
    for (int i = 0; i < run.size(); i = i + 2) {
      if (!currentState.getName().equals(run.get(i))) {
        return false;
      }
      if (i + 1 < run.size()) {
        Map<String, String> t = transitions.get(currentState.getName());
        if (t == null) {
          return false;
        }
        String in = t.get(run.get(i + 2));
        if (in == null) {
          return false;
        }
        if (!in.equals(run.get(i + 1))) {
          return false;
        }
        currentState = getStateByName(run.get(i + 2));
        if (currentState == null) {
          return false;
        }
      }
    }
    return currentState.isFinal();
  }

  /**
   * Returns a Lists of Strings representing an automaton transition chain. E.g.: q1 or q1, "a", q2
   * Strings alternate between states and inputs. Valid means the last state is final/accepting.
   * Second argument determines if state names or state labels are used. THIS MAY PERFORM POORLY ON
   * UNREASONABLY LARGE AND COMPLEX AUTOMATA.
   *
   * @param maxSteps Maximum number of transitions.
   * @param mode     0: names, 1: labels
   * @return Set of List of Strings
   */
  public Set<List<String>> getValidRunsUpTo(int maxSteps, int mode) {
    Set<List<String>> valids = new HashSet<>();
    if (initState.isFinal()) {
      valids.add(new ArrayList<>());
    }

    List<DFAutomatonRun> runs = new ArrayList<>();
    runs.add(new DFAutomatonRun(states, transitions, initState));
    int steps = 0;
    while (!runs.isEmpty() && steps < maxSteps) {
      List<DFAutomatonRun> nextRuns = new ArrayList<>();
      for (DFAutomatonRun run : runs) {
        Set<String> inps = run.possibleNextInputs();
        for (String inp : inps) {
          DFAutomatonRun copy = run.softCopy();
          copy.performStep(inp);
          if (copy.getCurrentState().isFinal()) {
            List<String> stateInputList = new ArrayList<>();
            for (int i = 0; i < copy.getRunStates().size(); i++) {
              stateInputList.add(mode == 0 ? copy.getRunStates().get(i).getName()
                  : copy.getRunStates().get(i).getLabel());
              if (i < copy.getRunInputs().size()) {
                stateInputList.add(copy.getRunInputs().get(i));
              }
            }
            valids.add(stateInputList);
          }
          if (!copy.possibleNextInputs().isEmpty()) {
            nextRuns.add(copy);
          }
        }
      }
      steps++;
      runs = nextRuns;
    }
    return valids;
  }

  public State getStateByName(String name) {
    for (State state : states) {
      if (state.getName().equals(name)) {
        return state;
      }
    }
    return null;
  }

  public enum Relevancy {
    ALL, INPUTS_ONLY
  }

  public static class DFAutomatonRun {

    private final List<State> states;
    private final Map<String, Map<String, String>> transitions;
    private final State initState;
    private List<State> runStates = new ArrayList<>();
    private State currentState;
    private List<String> runInputs = new ArrayList<>();
    private Random rand;

    public DFAutomatonRun(List<State> states,
        Map<String, Map<String, String>> transitions, State initState) {
      this.states = states;
      this.transitions = transitions;
      this.initState = currentState = initState;
      runStates.add(initState);
    }

    public Set<String> possibleNextInputs() {
      Map<String, String> availableTransitions = transitions.get(currentState.getName());
      return availableTransitions == null ? new HashSet<>()
          : new HashSet<>(availableTransitions.values());
    }

    public boolean performStep(String input) {
      Map<String, String> availableTransitions = transitions.get(currentState.getName());
      if (availableTransitions == null) {
        return false;
      }
      for (var entry : availableTransitions.entrySet()) {
        if (entry.getValue().equals(input)) {
          runInputs.add(input);
          currentState = getStateByName(entry.getKey());
          runStates.add(currentState);
          return true;
        }
      }
      return false;
    }

    /**
     * Returns a new instance with the same references of states and transitions.
     *
     * @return An AutomatonRun instance
     */
    public DFAutomatonRun softCopy() {
      DFAutomatonRun copy = new DFAutomatonRun(states, transitions, initState);
      copy.setRunStates(new ArrayList<>(runStates));
      copy.setRunInputs(new ArrayList<>(runInputs));
      copy.setCurrentState(currentState);
      if (rand != null) {
        copy.setRand(rand);
      }
      return copy;
    }

    public State getCurrentState() {
      return currentState;
    }

    public void setCurrentState(State currentState) {
      this.currentState = currentState;
    }

    public List<String> getRunInputs() {
      return runInputs;
    }

    public void setRunInputs(List<String> runInputs) {
      this.runInputs = runInputs;
    }

    public List<State> getRunStates() {
      return runStates;
    }

    public void setRunStates(List<State> runStates) {
      this.runStates = runStates;
    }

    public void setRand(Random rand) {
      this.rand = rand;
    }

    private State getStateByName(String name) {
      for (State state : states) {
        if (state.getName().equals(name)) {
          return state;
        }
      }
      return null;
    }
  }
}
