package nouth.les;
import java.util.*;

public class Solution{

public State startingState = new State(new VoidEx()); //first state.
public State foundState= new State(new VoidEx()); ; //state found by StateSearcher or such.

public Ex variable = new VoidEx(); //a copy of the variable sought, eg. x
public ArrayList<Ex> conditions=new ArrayList<Ex>(); //conditions that need to be correct for a solution to be valid, eg. for 2/x = 1 -> [x != 0]

public ArrayList<Ex> legitValues=new ArrayList<Ex>(); //list of exes that are true solutions. Theese should be directly substituable for the VarEx, eg. (1), NOT (x=1) 
public ArrayList<State> legitValuesStates = new ArrayList<State>();
public ArrayList<Ex> allValues=new ArrayList<Ex>(); //same as Values, but including solutions that are ruled out by conditions.
public ArrayList<State> allValuesStates = new ArrayList<State>();


}
