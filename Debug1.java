import java.util.*;
public class Debug1{

public static void main(String[] args){

Ex left = new AddEx();
Ex l1 = new DivEx();
l1.multi(new PlainEx(3));
l1.div(new PlainEx(2));
left.add(l1);

Ex l2 = new DivEx();
Ex l2a = new AddEx();
l2a.add(new PlainEx(10));
l2a.add(new VarEx("x"));
l2.multi(l2a);
Ex l2b = new MultiEx();
l2b.multi(new VarEx("x"));
l2b.multi(new PlainEx(2));
l2.div(l2b);
l2.multi(new PlainEx(-1));
left.add(l2);

Ex right = new PlainEx(0);

EqEx equestria = new EqEx(left,right);

String bc = equestria.report();

State startState = new State(equestria);
StateSearcher searchie = new StateSearcher(startState);
System.out.println(bc);
System.out.println("Enter desired variable");
String input = System.console().readLine();
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
while(true){
	System.out.println(papa.stateEx.report());
	papa = papa.parent;
	if(papa == startState){
	System.out.println(papa.stateEx.report());
		break;
		}
	}

}
}
