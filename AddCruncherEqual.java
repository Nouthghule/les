import java.util.*;

public class AddCruncherEqual{
	
private ArrayList<Ex> workList = new ArrayList<Ex>();

public AddCruncherEqual(){
	}

public void crunch(AddEx targetEx){

	MultiCruncherPlain cr = new MultiCruncherPlain();
	int i,e;
	for(i=0;i<targetEx.size();i++){
		Ex subEx = targetEx.getSubEx(i);
		System.out.println(subEx.report());
		subEx.multi(new PlainEx(1)); //Make sure that each subEx has a plainEx multiplier
		
		//Get the resulting multiEx. Cannot just keep using subEx here as
		//it is now inside the multiEx. 
		// 13/3/16 - would be possilbe to use return value from .multi. Not using as not
		//sure if it'll stay the way it is now (MultiEx)
		System.out.println(targetEx.getSubEx(i).report());
		MultiEx rlEx = (MultiEx) targetEx.getSubEx(i);

		cr.crunch(rlEx); //Make sure that there is only one plainEx in the multiEx

		addToList(rlEx);
		}
	
	
	targetEx.wipe();
	targetEx.add(workList);
	}




public void addToList(MultiEx rlEx){
	
	String argReport = getReportWithoutFirst(rlEx);
	int j;
	boolean consumed = false;
	
	for(j=0;j<workList.size();j++){
		MultiEx resident = (MultiEx) workList.get(j);
		String residentReport = getReportWithoutFirst(resident);
		
			System.out.println("comparing" + residentReport + " by " + argReport);

		if(residentReport.equals(argReport)){
			//The Exes are equal, ignoring PlainEx
			
			int result =  ( ((PlainEx)rlEx.getSubEx(0)).value + ((PlainEx)resident.getSubEx(0)).value );
			PlainEx resultEx = new PlainEx(result);

			//replace multiplier of the MultiEx in list by the result
			System.out.println("Replacing " + resident.getSubEx(0).report() + " by " + resultEx.report());
			resident.getSubEx(0).replaceSelf(resultEx); 
			consumed = true;
			break;
			}

		}
	if(!consumed){
		workList.add(rlEx);
		}
	
	}

public String getReportWithoutFirst(MultiEx rlEx){
	rlEx.getSubEx(0).silent = true;
	String report = rlEx.report();
	rlEx.getSubEx(0).silent = false;
	return report;	
	}

}
