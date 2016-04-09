import java.util.*;
public class Debug3{

public static void main(String[] args){

Ex left = new DivEx();
left.multi(new VarEx("V"));
left.div(new VarEx("R"));

Ex right = new MultiEx();
right.multi(new VarEx("t1"));
right.multi(new VarEx("t2"));

EqEx equestria = new EqEx(left,right);

String bc = equestria.report();
System.out.println(equestria.subExTotal());
System.out.println(bc);

State startState = new State(equestria);

/*
startState.propagate();
startState.children;
*/
System.out.println("This is what it started as :");
System.out.println(bc);
System.out.println("==And this is what we've thought of==");
}
}
