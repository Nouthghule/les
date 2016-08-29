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
	closed.clear();
	int i = 0;
	int childBestHf;
	while(true){
		i++;
		ArrayList<State> children = new ArrayList<State>();
		ArrayList<State> roughChildren = new ArrayList<State>();
		childBestHf = -1;

		System.out.println("ComputeSearcher loop " + i + " start ,bestHf " + bestHf + " bestChildHf " + childBestHf + " open size " + open.size() + " closed size " + closed.size());
		for(State openState : open){
			System.out.println("State " + openState.stateEx.reportForTex() + " propagating");
			openState.propagate();
			for(State child : openState.children){
				roughChildren.add(child);
				if(childBestHf ==-1){
					childBestHf = hf(child);
					}
				child.hfVal = hf(child);
				if(child.hfVal<=childBestHf){
					childBestHf = child.hfVal;
					}
				}
			}
		
		for(State ch : roughChildren){
			if((ch.hfVal==childBestHf)&&(isNew(ch))){
				children.add(ch);
				}
			}

		System.out.println("ComputeSearcher loop " + i + " end bestHf " + bestHf + " bestChildHf " + childBestHf + " open size " + open.size() + " closed size " + closed.size());

		if(children.size()==0){ //no more children
			System.out.println("No more children");
			return(finish());	
			}

		if(childBestHf > bestHf){ //only worse children
			System.out.println("only worse children");
			return(finish());
			}
			
		if(childBestHf == bestHf){
			closed.addAll(open);
			}

		if(childBestHf< bestHf){ //only better children will be added -> no need to check closed
			closed.clear();
			}
		
		bestHf = childBestHf;

		open = children;
				}
		}
	

private boolean isNew(State s){
	String str = s.stateEx.report();
	for(State state : open){
		if(state.stateEx.report().equals(str)){
			return false;
			}//MINDNOTE: Cursor position
		}
	for(State xtate : closed){
		if(xtate.stateEx.report().equals(str)){
			return false;
			}
		}
	return true;
	}

private State finish(){
	return open.get(0);
	}

int hf(State s){
	return s.stateEx.subExTotal();
	//This is going to fail to appreciate partial solutions to sqroots [eg. sqrt(160) -> sqrt(10)*4]. This might be handled as special case somehow in search().
	}

}
