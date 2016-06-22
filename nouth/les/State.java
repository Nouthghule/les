package nouth.les;
import java.util.*;

public class State{

public Ex stateEx;

public State parent;

public ArrayList<State> children;

public Operator stateOp;

public int hfVal;

public boolean allowAlterators = true;

public State(Ex theEx){
	stateEx = theEx;
	children = new ArrayList<State>();
	stateOp = new DefOperator();
	hfVal = -777; //The most magic one of all.
	}

public int propagate(){
	System.out.println("I'm " + stateEx.report() + " and I'm about to propagate.");
	ArrayList<Operator> suggestions = stateEx.suggest();
	int fullOp = 0;
	for(Operator o : suggestions){
	if((!allowAlterators)&&(o instanceof Alterator)){
		continue;
		}
	System.out.println(o + " is messing around now.");
		if(o instanceof Alterator){
			System.out.println("It's altEx is " + ((Alterator)o).altEx.report());
			}
		Ex child = stateEx.copy();
		System.out.println("on child " + child.report());
		int opVal = o.execute(child);
		System.out.println("into val " + opVal);
		if(opVal>0){
			System.out.println(o + " has brought me a new dirty child : " + child.report());
			
			String ancestry = "<< ";
			int c;
			State praetor = this.parent;
			for(c=0;true;c++){
				ancestry += praetor.stateEx.report();
				ancestry += praetor.stateOp;
				ancestry += "<< ";
				if(praetor == praetor.parent){
					break;
					}
				praetor = praetor.parent;
				}
	//		System.out.println("It's ancestry is : " + ancestry);
			System.out.println("State polishing child : " + child.report());
			child.polish();
	//		System.out.println("I've polished the child and now it looks like this : " + child.report());
			State childState = new State(child);
			childState.stateOp = o;
			childState.allowAlterators = this.allowAlterators;
			childState.parent = this;
			children.add(childState);
			fullOp = fullOp + opVal;

			}
		}
	return fullOp;
	}


}


