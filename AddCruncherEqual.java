package nouth.les;
	
import java.util.*;

public class AddCruncherEqual extends Cruncher{
	
private ArrayList<Ex> workList = new ArrayList<Ex>();

public boolean dontCycle = false;

protected boolean workDone = false;

public AddCruncherEqual(){
	}

public int crunch(Ex targetEx){
	
	if(!(targetEx instanceof AddEx)){
		return 0;
		}
	workList.clear();
	workDone = false;
	int i,e;
	for(i=0;i<targetEx.size();i++){
		Ex rlEx = targetEx.getSubEx(i); //TODO replace comment refs to subEx with rlex
//		System.out.println("target subEx         =" + subEx.report());
	//	System.out.println("Okay,not gonna multi " + rlEx.report() + "by 1");
		rlEx = rlEx.multi(new PlainEx(1)); //Make sure that each subEx has a plainEx multiplier
	//	System.out.println("result : " + rlEx.report());
		//Get the resulting multiEx. Cannot just keep using subEx here as
		//it is now inside the multiEx. 
		// 13/3/16 - would be possilbe to use return value from .multi. Not using as not
		//sure if it'll stay the way it is now (MultiEx)
//		System.out.println("Target subEx times 1 =" + targetEx.getSubEx(i).report());
		
		//TODO make crunchers reusable (wipe list)
		MultiCruncherPlain cr = new MultiCruncherPlain();
		
//		System.out.println("gonna crunch " + rlEx.report());
		
		Ex thyEx = rlEx;
	//	System.out.println("Crunching: " + thyEx.report());
		int success = cr.crunch(thyEx); //Make sure that there is only one plainEx in the multiEx
	//	System.out.println("post crunch " + thyEx.report());
			
		if(!(success>0)){
			Ex thineEx = rlEx.getSubEx(0);
	//		System.out.println("But it is not a multiEx. Redirected crunch to it's first subEx  " + thineEx.report());
			cr = new MultiCruncherPlain();
			cr.crunch(thineEx);
	//		System.out.println(">which is thus crunched into " + thineEx.report());
		}
		

		addToList(rlEx);
		}
	
	if(!workDone){
		return 0;
		}
	
	targetEx.wipe();
	targetEx.add(workList);
	targetEx.sort();
	return 1;
	}




public void addToList(Ex rlEx){
	
	String argReport = getReportWithoutFirst(rlEx);
	int j;
	boolean consumed = false;
	
//	System.out.println("ŦŦŦ now going to add " + rlEx.report() + " to the list");

	for(j=0;j<workList.size();j++){
		Ex resident = workList.get(j);
		String residentReport = getReportWithoutFirst(resident);
		
		System.out.println("comparing" + residentReport + " to " + argReport);
		
		if(residentReport.equals(argReport)){
			workDone = true;
			//The Exes are equal, ignoring PlainEx
//			System.out.println("ŦŦŦ found it's equal without first : " + resident.report());
//			System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; ");
			
			if(dontCycle){
				int result =  ( ((PlainEx)rlEx.getSubEx(0)).value + ((PlainEx)resident.getSubEx(0)).value );
				PlainEx resultEx = new PlainEx(result);
				//replace multiplier of the MultiEx in list by the result
//				System.out.println("Replacing " + resident.getSubEx(0).report() + " by " + resultEx.report());
				resident.getSubEx(0).replaceSelf(resultEx); 
				}
			else{
				Ex target = resident.getSubEx(0).add(rlEx.getSubEx(0));
//				System.out.println("ŦŦŦ created a target to crunch up : " + target.report());
				AddCruncherEqual crunchy = new AddCruncherEqual();
				crunchy.dontCycle = true; //<----
				crunchy.crunch(target);
				target.polish();
			}
			
//			System.out.println("--------------------------------------;; ");

			consumed = true;
			break;
			}

		}
	if(!consumed){
		workList.add(rlEx);
		}
		
	}

public String getReportWithoutFirst(Ex rlEx){
		rlEx.getSubEx(0).silent = true;
		String report = rlEx.report();
		rlEx.getSubEx(0).silent = false;
		return report;
	}

}
