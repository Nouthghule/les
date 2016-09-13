package nouth.les;
import java.util.ArrayList;

public class ComputeSearcher{

private ArrayList<State> open = new ArrayList<State>();
private ArrayList<State> closed = new ArrayList<State>();
private int bestHf = 0;

public State search(State startingState){
	boolean original = startingState.allowAlterators;

	startingState.allowAlterators = false;
	open = new ArrayList<State>();
	closed = new ArrayList<State>();
	open.add(startingState);
	startingState.hfVal = hf(startingState);
	bestHf = startingState.hfVal;
	
	State endState = find();
	startingState.allowAlterators = original;
	endState.allowAlterators = original;

	return endState;
	
	}

private State find(){
	while(true){
		State curr = findBest(open);
		curr.propagate();
	
		ArrayList<State> children = curr.children;
		if(children.size() == 0){
			//do nothing
			}
		else{	
			int best = hf(children.get(0));
			for(State child : children){
				child.hfVal = hf(child);
				if(child.hfVal<best){
					best = child.hfVal;
					}
				}
			ArrayList<State> elite = new ArrayList<State>();
			for(State child : children){
				if(child.hfVal==best){
					elite.add(child);
					}
				}
			
			if(best>curr.hfVal){
				//elite is worse than father, just discard it. 
				}
			else if(best<curr.hfVal){
				//elite is better than father, let it become new open.
				open = elite;
				}
			else if(best==curr.hfVal){
				//elite is equal to father, add new members of elite to open.
				for(State child : children){
					if(isNew(child)){
						open.add(child);
						}
					}
				}
			}	

			open.remove(curr);
			closed.add(curr);
			
			if(open.size()==0){
				return findBest(closed);
				}

			

		}
	}
	

private boolean isNew(State s){
	String str = s.stateEx.report();
	for(State state : open){
		System.out.println("Comsearcher checkagainst : " +state.stateEx.report());
		if(state.stateEx.report().equals(str)){
			return false;
			}//MINDNOTE: Cursor position
		}
	for(State xtate : closed){
		if(xtate.stateEx.report().equals(str)){
			return false;
			}
		}
	System.out.println("Comsearcher is new : " + str);
	return true;
	}

private State finish(){
	return open.get(0);
	}

private State findBest(ArrayList<State> arr){
	if(arr.size()==0){
		return null;
		}
	State ret = arr.get(0);
	for(State s : arr){
		if(s.hfVal< ret.hfVal){
			ret = s;
			}
		}
	return ret;
	}

int hf(State s){
	return s.stateEx.subExTotal();
	//This is going to fail to appreciate partial solutions to sqroots [eg. sqrt(160) -> sqrt(10)*4]. This might be handled as special case somehow in search().
	}

}
