import java.util.*;
public class StateSearcher{

//Likely subject to change

protected State startingState;

public LinkedList<State> open;
public LinkedList<State> closed;
public String targetString = "Slepice";
public int lookBackDistance = 3;

public StateSearcher(State theState){
startingState = theState;
theState.parent = theState;
open = new LinkedList<State>();
closed = new LinkedList<State>();
}

public State find(String str){
	int stateNum = 0;
	open.clear();
	closed.clear();
	startingState.children.clear();
	open.add(startingState);
	ArrayList<State> temp = new ArrayList<State>();
	targetString = str;
	if(!startingState.stateEx.varContains(targetString)){
		return startingState;
		}
	
	while(open.size()>0){
		State currState = open.remove();
		stateNum ++;
		int gen =0;
		State genState = currState;
		while(true){
			if(genState==startingState){
				break;
				}
			gen ++;
			genState = genState.parent;
			}
		System.out.println("=====================================STATE " + stateNum + " , generation " + gen) ;
		System.out.println("CurrState is " + currState.stateEx.report());
		System.out.println("It's hf is " + hf(currState));
		if(hf(currState)==1){
			return currState;
			}

		currState.propagate();
		System.out.println("It just propagated. Gonna check it's children.");
		closed.add(currState);
		ArrayList<State> newChildren = new ArrayList<State>();
		for(State s : currState.children){
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
//				System.out.println("It's new.");
				}

			if(isNew && didImprove(s,lookBackDistance)){
				System.out.println("adding " + s.stateEx.report() + " to open.");
				open.add(s);
				newChildren.add(s);
				}
			else{
				System.out.println("Not adding it.");	
				}
			}
		
		String deb = "Legit new children : <";
		for(State s : newChildren){
			deb+= s.stateEx.report() + " ; ";
			}
		deb+= ">";
		System.out.println(deb);
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
