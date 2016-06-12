import java.util.*;
public class Outputter{ 
	TexParser t = new TexParser();

public String getOutput(String in, String var){
	Ex e = t.parse(in);
	State startState = new State(e);
	StateSearcher searchie = new StateSearcher(startState);
	State foundState = searchie.find(var);

	String res = "";	

	State papa = foundState;
	LinkedList<String> as = new LinkedList<String>();
	while(true){
		as.add((papa.stateEx.reportForTex()));
		papa = papa.parent;
		if(papa == startState){
		as.add((papa.stateEx.reportForTex()));
			break;
			}
		}
	int i;
	for(i=as.size()-1;i>=0;i--){
	res += as.get(i) + "\n";	
	}

	return res;
	}


}
