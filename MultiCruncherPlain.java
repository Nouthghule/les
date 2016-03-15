import java.util.*;

public class MultiCruncherPlain{
	
private ArrayList<Ex> workList = new ArrayList<Ex>();

public MultiCruncherPlain(){
	}

public void crunch(MultiEx targetEx){
	
	System.out.println("MCPlain : START crunch"); 	

	int i,e;
	for(i=0;i<targetEx.size();i++){
		
		System.out.println(" looking at " + targetEx.getSubEx(i).report()); 	
		if((workList.size()>0)&&(targetEx.getSubEx(i) instanceof PlainEx)){
			PlainEx subEx = (PlainEx) targetEx.getSubEx(i);
			boolean consumed = false;
			System.out.println(": It's a plainEx."); 	
			
			for(e=0;e<workList.size();e++){
				
				if(workList.get(e) instanceof PlainEx){
					PlainEx alreadyEx = (PlainEx) workList.get(e);
					int result = ((subEx.value) * (alreadyEx.value));
					
					System.out.println(": multiplied ." + subEx.report() + " by " + alreadyEx.report()); 	
					PlainEx resultEx = new PlainEx(result);
					workList.set(e, resultEx);
					consumed = true;
					break;
					}
				}
				
			if(!consumed){
				System.out.println("not consumed. Adding " + subEx.report());
				workList.add(subEx);
				}
			}
		else{
			//When it is not a plainEx OR it is the first element
			
			System.out.println("Worklist size not > 0 (actually :  " + workList.size() + " ) or " + targetEx.getSubEx(i).report() + " not a plainEx. Adding it.");
			workList.add(targetEx.getSubEx(i));
			}
		}
	
	targetEx.wipe();
	targetEx.multi(workList);
	System.out.println("ACPlain : END<<");
	}


}
