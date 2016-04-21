import java.util.*;
public class Debug2{

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

Ex equestria = new EqEx(left,right);


String bc = equestria.report();

String msg = "Enter equation to be evalued or leave blank to use the pre-set " + bc;

while(true){
System.out.println(msg);
String inEq = System.console().readLine();
if(inEq.equals("")){
	System.out.println("Okay. Using default one.");
	break;
	}
TextParser tp = new TextParser();
Ex made = tp.parse(inEq);
msg = "Is this equation equal to that which you desire ?[Y/n] " + made.report();
System.out.println(msg);
String rply = System.console().readLine();
if(!rply.equals("n")){
	System.out.println("Okay then !");
	equestria = made;
	break;
	}
msg = "Please enter equation.";
}
bc = equestria.report();
System.out.println(bc);
State startState = new State(equestria);
StateSearcher searchie = new StateSearcher(startState);
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
