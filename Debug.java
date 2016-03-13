import java.util.*;

public class Debug{

public static void main(String[] args){
	System.out.print("><><><><><><><><><><>\n");
	Ex theBoss = new BossEx();
	
	Ex ex1 = new AddEx();
	ex1.add(new PlainEx(1));
	ex1.add(new VarEx("f"));
	
	MultiEx ex2 = new MultiEx();
	ex2.multi(new PlainEx(2));
	ex2.multi(new PlainEx(3));
	ex2.multi(new PlainEx(2));
	ex2.multi(new VarEx("t"));
	ex2.multi(ex1.copy().add(new PlainEx(2)));

	Ex ex3 = new DivEx();
	ex3.multi(ex1.copy());
	ex3.multi(new PlainEx(5));
	ex3.div(new PlainEx(777));
	ex3.div(new VarEx("b"));
	
	theBoss.add(ex1);
	theBoss.add(ex2);
	theBoss.add(ex3);
	theBoss.sort();
	System.out.print(theBoss.report());

	MultiRes resolver = new MultiRes();
	Ex dolly = resolver.execute(ex2);

	System.out.print("=====================\n");
	System.out.print(dolly.report());
	System.out.print("\n\n");
	}


}


