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
	nest.add((new PlainEx(24)).multi(new VarEx("v")));
	nest.add(new VarEx("v"));

	theBoss.add(nest);

	theBoss.sort();
	String oldRep = theBoss.report();
	System.out.print(oldRep);
	int i;

	for(i=0;i<nest.size();i++){
		if(nest.getSubEx(i).varContains("v")){
			System.out.println("Ex " + nest.getSubEx(i).report() + " contains v !");
			}
		else if(nest.getSubEx(i).varContains("g")){
			System.out.println("Ex " + nest.getSubEx(i).report() + " contains g !");
			}
		}
	
	System.out.print("\n\n");
		

	AddCruncherEqual cry = new AddCruncherEqual();
	cry.crunch(nest);
	
	
	System.out.print(oldRep);
	System.out.print("=====================\n");
	System.out.print(theBoss.report());
	
	for(i=0;i<nest.size();i++){
		if(nest.getSubEx(i).varContains("v")){
			System.out.println("Ex " + nest.getSubEx(i).report() + " contains v !");
			}
		else if(nest.getSubEx(i).varContains("g")){
			System.out.println("Ex " + nest.getSubEx(i).report() + " contains g !");
			}
		}

	List<String> listOfVars = nest.varList();

	
	System.out.println("Nest contains following variables :");
	for(String s : listOfVars){
		System.out.println(s);
		}
	
	System.out.print("\n\n");
	}


}


