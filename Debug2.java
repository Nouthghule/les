import java.util.*;
public class Debug2{

public static void main(String[] args){

Ex left = new DivEx();
left.multi(new VarEx("V"));
left.div(new VarEx("R"));

Ex right = new MultiEx();
right.multi(new VarEx("t1"));
right.multi(new VarEx("t2"));

EqEx equestria = new EqEx(left,right);

String bc = equestria.report();
System.out.println(bc);

ArrayList<String> s = new ArrayList<String>();
ArrayList<Operator> o = new ArrayList<Operator>();

o = equestria.suggest();


for(Operator op:o){
	Ex e = equestria.copy();
	System.out.println("trying " + op);
	if(op.execute(e)>0){
	s.add(e.report() + "				(a " + op + ", sah !)");
	}
}

System.out.println("This is what it started as :");
System.out.println(bc);
System.out.println("==And this is what we've thought of==");
for(String st : s){
	System.out.println(st);
	}
}
}
