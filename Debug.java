import java.util.*;

public class Debug{

public static void main(String[] args){
	System.out.print("><><><><><><><><><><>\n");
	Ex theBoss = new BossEx();


	
		
	Ex subNest = new AddEx();
	Ex tempMaster = new BossEx();
	tempMaster.add(subNest);
	
	subNest.add(new PlainEx(3));
	subNest.add(new VarEx("g"));
	
	int a = subNest.posInMaster;
	Ex master = subNest.master;

	subNest.div(new PlainEx(20));
	
	subNest = master.getSubEx(a);
	
	AddEx nest = new AddEx();
	nest.add(subNest);
	nest.add(subNest.copy());
	nest.add(subNest.copy().multi(new PlainEx(6)));

	theBoss.add(nest);

	theBoss.sort();
	String oldRep = theBoss.report();
	System.out.print(oldRep);

	
	AddCruncherEqual cry = new AddCruncherEqual();
	cry.crunch(nest);
	
	
	System.out.print(oldRep);
	System.out.print("=====================\n");
	System.out.print(theBoss.report());
	System.out.print("\n\n");
	}


}


