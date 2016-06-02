import java.util.*;
public class Debug2{

public static void main(String[] args){


String inEq = args[0];

TextParser tp = new TextParser();
Ex made = tp.parse(inEq);
String bc =made.report();
System.out.println(bc);
State startState = new State(made);
StateSearcher searchie = new StateSearcher(startState);
String input = "x";
if(args.length>1){
	input = args[1];
	}
State foundState = searchie.find(input);

System.out.println("         <===o===>"        );
System.out.println("     <===============>"    );
System.out.println(" <========================>");
System.out.println("This is what it started as :");
System.out.println(bc);
System.out.println("==And this is what I've thought of==");
System.out.println(foundState.stateEx.report());

System.out.println("And this is how we got here : ");
State papa = foundState;
LinkedList<String> as = new LinkedList<String>();
while(true){
	as.add((papa.stateEx.report()));
	papa = papa.parent;
	if(papa == startState){
	as.add((papa.stateEx.report()));
		break;
		}
	}
int i;
for(i=as.size()-1;i>=0;i--){
	System.out.println(as.get(i));
	}
}
}
