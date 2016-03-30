import java.util.*;

public class MultiCruncherPlain extends Cruncher{
	
private ArrayList<Ex> workList = new ArrayList<Ex>();

public MultiCruncherPlain(){
	}

public int crunch(Ex targetEx){
	
//	System.out.println("MCPlain : START crunch"); 	
	
	if(!(targetEx instanceof MultiEx)){
//		System.out.println(">>MCPlain : it ain't a MultiEx ! Returning 0."); 	
		return 0;
		}
	workList.clear();
	int i,e;
	for(i=0;i<targetEx.size();i++){
		
//		System.out.println(" looking at " + targetEx.getSubEx(i).report()); 	
		if((workList.size()>0)&&(targetEx.getSubEx(i) instanceof PlainEx)){
			PlainEx subEx = (PlainEx) targetEx.getSubEx(i);
			boolean consumed = false;
//			System.out.println(": It's a plainEx."); 	
			
			for(e=0;e<workList.size();e++){
				
				if(workList.get(e) instanceof PlainEx){
					PlainEx alreadyEx = (PlainEx) workList.get(e);
					int result = ((subEx.value) * (alreadyEx.value));
					
//					System.out.println(">>MCPlain : multiplied " + subEx.report() + " by " + alreadyEx.report()); 	
					PlainEx resultEx = new PlainEx(result);
					workList.set(e, resultEx);
					consumed = true;
					break;
					}
				}
				
			if(!consumed){
//				System.out.println(">>MCPlain : not consumed. Adding " + subEx.report());
				workList.add(subEx);
				}
			}
		else{
			//When it is not a plainEx OR it is the first element
			
//			System.out.println(">>MCPlain : Else ! Adding " + targetEx.getSubEx(i).report());
			workList.add(targetEx.getSubEx(i));
			}
		}
	
	targetEx.wipe();
	targetEx.multi(workList); //This is a multiEx. Were it not, it'd return a zero at line 16
//	System.out.println("MCPlain : END<<");
	return 1;
	}


}
