import java.util.*;

public class AddCruncherPlain{
	
private ArrayList<Ex> workList = new ArrayList<Ex>();

public AddCruncherPlain(){
	}

public void crunch(AddEx targetEx){

	int i,e;
	for(i=0;i<targetEx.size();i++){
			
		
		if((workList.size()>0)&&(targetEx.getSubEx(i) instanceof PlainEx)){
			PlainEx SubEx = (PlainEx) targetEx.getSubEx(i);
			boolean consumed = false;
			
			for(e=0;e<workList.size();e++){
			
				if(workList.get(e) instanceof PlainEx){
					PlainEx alreadyEx = (PlainEx) workList.get(e);
					int result = ((SubEx.value) + (alreadyEx.value));
					PlainEx resultEx = new PlainEx(result);
					workList.set(e, resultEx);
					consumed = true;
					break;
					}
				}
				
			if(!consumed){
				workList.add(SubEx);
				}
			}
		else{
			//When it is not a plainEx OR it is the first element
			workList.add(targetEx.getSubEx(i));
			}
		}
	
	targetEx.wipe();
	targetEx.add(workList);
	}


}
