import java.util.*;
public class Debug2{

public static void main(String[] args){



Ex left = new AddEx();
left.add((new VarEx("x")).multi(new PlainEx(20)));
left.add((new VarEx("y")).multi(new PlainEx(40)));

Ex right = (new PlainEx(20).div(new PlainEx(40)));

EqEx equestria = new EqEx(left,right);
equestria.polish();
String bc = equestria.report();
State starter = new State(equestria);
System.out.println("The equation is as follows : " + equestria.report());
System.out.println("Please enter which variable you would like to find :");
//String input = System.console().readLine();
String input = "x";
StateSearcher searchie = new StateSearcher(starter);
State foundState = searchie.find(input);

System.out.println("This is what it started as :");
System.out.println(bc);
System.out.println("==And this is what I've thought of==");
System.out.println(foundState.stateEx.report());
}
}
