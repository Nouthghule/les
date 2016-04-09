import java.util.*;
public class StateSearcher{

//Likely subject to change

protected State startingState;

public ArrayList<State> open;
public ArrayList<State> closed;
public String targetString = "Slepice";
public int lookBackDistance = 3;

public StateSearcher(State theState){
startingState = theState;
theState.parent = theState;
open = new ArrayList<State>();
closed = new ArrayList<State>();
}

public State find(String str){
	int generation = 0;
	open.clear();
	closed.clear();
	startingState.children.clear();
	open.add(startingState);
	ArrayList<State> temp;
	targetString = str;
	if(!startingState.stateEx.varContains(targetString)){
		return startingState;
		}
	
	while(open.size()>0){
		generation ++;
		System.out.println("=========================================Generation number " + generation  + " of opens ! ");
		ListIterator<State> iter = open.listIterator();
		State currState = iter.next();
		System.out.println("CurrState is " + currState.stateEx.report());
		System.out.println("It's hf is " + hf(currState));
		if(hf(currState)==1){
			return currState;
			}

		currState.propagate();
		temp = currState.children;
		String deb = "<";
		for(State s :temp){
			deb+= s.stateEx.report() + " ; ";
			}
		System.out.println("It just propagated. Children : " +deb);
		closed.add(currState);
		iter.remove();
		for(State s : temp){
			System.out.println("Checking " + s.stateEx.report());
			boolean isNew = true;
			for(State o : open){
				if(equalStates(o,s)){
					isNew = false;
					}
				}
			for(State c : closed){
				if(equalStates(c,s)){
					isNew = false;
					}
				}

			if(isNew){
				System.out.println("It's new.");
				}

			if(isNew && didImprove(s,lookBackDistance)){
				System.out.println("adding " + s.stateEx.report() + " to open.");
				open.add(s);
				}
			else{
				System.out.println("Not adding it.");	
				}
			}
		}
	System.out.println("No more opens.");
	return startingState;
	}

public boolean equalStates(State x, State y){
	String a = x.stateEx.report();
	String b = y.stateEx.report();
	if(a.equals(b)){
		return true;
		}
	return false;
	}

public int hf(State state){
	return state.stateEx.varDepth(targetString);
	}

public boolean didImprove(State theState, int lookBack){
	if(theState == startingState){
		return true;
		}
	int i;
	State theParent = theState.parent;
	for(i = (lookBack - 1); i>0; i--){
			theParent = theParent.parent;
		}
	System.out.println("checking if it improved against it's grand-something parent " + theParent.stateEx.report());
	
	if(theParent == startingState){
		return true; //Need this to get past the start
		}

	if(hf(theParent)>hf(theState)){
		return true;
		}
	return false;

	}

}
