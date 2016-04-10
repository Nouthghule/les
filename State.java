import java.util.*;

public class State{

public Ex stateEx;

public State parent;

public ArrayList<State> children;

public State(Ex theEx){
	stateEx = theEx;
	children = new ArrayList<State>();
	}

public int propagate(){
	System.out.println("I'm " + stateEx.report() + " and I'm about to propagate.");
	ArrayList<Operator> suggestions = stateEx.suggest();
	int fullOp = 0;
	for(Operator o : suggestions){
	System.out.println(o + " is messing around now.");
		Ex child = stateEx.copy();
		int opVal = o.execute(child);
		if(opVal>0){
			System.out.println(o + " has brought me a new dirty child : " + child.report());
			child.polish();
			System.out.println("I've polished the child and now it looks like this : " + child.report());
			State childState = new State(child);
			childState.parent = this;
			children.add(childState);
			fullOp = fullOp + opVal;

			}
		}
	return fullOp;
	}


}


