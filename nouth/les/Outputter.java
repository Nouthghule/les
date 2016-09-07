package nouth.les;
import java.util.*;

public class Outputter{ 
	TexParser t = new TexParser();

public String getOutput(String in, String var){
	
	Ex e = t.parse(in);
	State startState = new State(e);

	SolutionMaker sm = new SolutionMaker();
	Solution solution = sm.solve(startState,var);
	
	State papa = solution.foundState;
	System.out.println("outputer found " +papa.stateEx.report());
	LinkedList<String> as = new LinkedList<String>();

	for(State s : solution.legitValuesStates){	
//		System.out.println("adding after line "+s.stateEx.reportForTex());
		as.add(s.stateEx.reportForTex());
		}
	as.add("-----------");

	while(true){
		
		as.add((papa.stateEx.reportForTex())); 
		papa = papa.parent;
		if(papa == startState){
		as.add((papa.stateEx.reportForTex()));
			break;
			}
		}
	

	String res = "";
	int i;
	for(i=as.size()-2;i>=0;i--){
	res += as.get(i);
	if(i>0){
		res+= "\\\\\\\\";
		}
	res += "\n";
	}
	return res;
	}


}
