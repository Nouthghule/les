import java.util.*;
public class Debug2{

public static void main(String[] args){

Ex left = new DivEx();
left.multi(new VarEx("V"));
left.div(new VarEx("R"));

Ex right = new VarEx("I");

EqEx equestria = new EqEx(left,right);
String bc = equestria.report();
State starter = new State(equestria);
System.out.println("The equation is as follows : " + equestria.report());
System.out.println("Please enter which variable you would like to find :");
String input = System.console().readLine();
StateSearcher searchie = new StateSearcher(starter);
State foundState = searchie.find(input);

System.out.println("This is what it started as :");
System.out.println(bc);
System.out.println("==And this is what I've thought of==");
System.out.println(foundState.stateEx.report());
}
}
