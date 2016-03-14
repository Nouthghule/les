import java.util.*;

public class Debug{

public static void main(String[] args){
	System.out.print("><><><><><><><><><><>\n");
	Ex theBoss = new BossEx();
	
/*	AddEx ex1 = new AddEx();
	ex1.add(new PlainEx(1));
	ex1.add(new VarEx("f"));
	ex1.add(new PlainEx(6));
	
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
	theBoss.add(ex3);*/

	AddEx nest = new AddEx();
	AddEx subNest = new AddEx();
	subNest.add(new PlainEx(3));
	subNest.add(new VarEx("g"));
	
	nest.add(subNest);
	nest.add(subNest.copy());
	nest.add(subNest.copy().multi(new PlainEx(6)));

	theBoss.add(nest);

	theBoss.sort();
	System.out.print(theBoss.report());

/*	MultiCruncherPlain multiCrunchy = new MultiCruncherPlain();
	multiCrunchy.crunch(ex2);
	
	AddCruncherPlain addCrunchy = new AddCruncherPlain();
	addCrunchy.crunch(ex1);
*/
	AddCruncherEqual cry = new AddCruncherEqual();
	cry.crunch(nest);
	
	
	System.out.print("=====================\n");
	System.out.print(theBoss.report());
	System.out.print("\n\n");
	}


}


