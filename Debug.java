import java.util.*;

public class Debug{

public static void main(String[] args){
	Ex theBoss = new BossEx();
	
	Ex ex1 = new AddEx();
	ex1.add(new PlainEx(1));
	ex1.add(new VarEx("f"));
	
	Ex ex2 = new MultiEx();
	ex2.multi(new PlainEx(15));
	ex2.multi(new VarEx("t"));

	Ex ex3 = new DivEx();
	ex3.multi(ex1.copy());
	ex3.div(new PlainEx(777));
	ex3.div(new VarEx("b"));
	
	theBoss.add(ex1);
	theBoss.add(ex2);
	theBoss.add(ex3);
	theBoss.sort();
	System.out.print(theBoss.report());
	
	Ex dolly = theBoss.copy();
	System.out.print("Okay now, copied the expression.\n=====================\n");
	System.out.print(dolly.report());
	}


}


