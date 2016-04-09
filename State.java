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
		Ex child = stateEx.copy();
		int opVal = o.execute(child);
		System.out.println("new child : " + child.report());
		if(opVal>0){
			child.polish();
			State childState = new State(child);
			childState.parent = this;
			children.add(childState);
			fullOp = fullOp + opVal;

			}
		}
	return fullOp;
	}


}


