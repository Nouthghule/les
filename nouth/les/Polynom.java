package nouth.les;
import java.util.*;

public class Polynom{

//returns rank of polynom or null when not eligible or when rank is zero (eg. "2+15" for x)
public static Integer rankOf(Ex e, String var){
	if(!(e instanceof AddEx)){
		return null;
		}
	
	if(!e.varContains(var)){
		return null;
		}
	
	Ex add = e.copy();
	add.polish();

	ArrayList<Ex> withVar = new ArrayList<Ex>();


	ArrayList<Ex> noVar = new ArrayList<Ex>();

	for(Ex sub : add.getSubExList()){
		if(sub.varContains(var)){
			withVar.add(sub);
			}	
		else{	
			noVar.add(sub);
			}
		}
	
	
	return null;


	}

public static Ex exponentOfMember(Ex e, String var){
	if(!e.varContains(var)){
		return null;
		}

	//if it's a DivEx, get the important ex out and carry out as if it were input
	if(e instanceof DivEx){
		if(e.getSubEx(1).varContains(var)){
			return null; 
			//this could be determined but I'd rather not as getting the constant out of it seems annoying. I'd rather have searchers find nicer state
			}
		Ex nextE = e.getSubEx(0);
		if((nextE instanceof VarEx)||(nextE instanceof MultiEx)||(nextE instanceof PowerEx)){
			e = nextE;
			}
		else{
			return null; //there's an AddEx or some other monstrosity in the DivEx numerator.
			}
		}
	
	//If it's a multiEx, we don't care abot the non-var containing exes -> first find which one contains var and carry on as if it were input
	if(e instanceof MultiEx){
		Ex nextE = null;
		for(Ex sub : e.getSubExList()){
			if(sub.varContains(var)){
				if(nextE!=null){
					//two subExes of the multiEx contain the variable. ain't got time to deal with that shit, let stateSearcher find a better result
					return null;
					}
						
				if((sub instanceof VarEx)){
					nextE = sub;
					}
				
				if(sub instanceof PowerEx){
					//careful here - this will let x^x through. check for variable in exponent* needs to occur elsewhere as it might someday
					//desirable to find the case of var in exponent.
					// * - eg for purposes of solving quadratic equations
					if(sub.getSubEx(0).varContains(var)){
						nextE = sub;
						}
					else{
						return null;
						}
					}	

				}
			}
		//nextE should never be null at this point, as that implies no var in multiEx, which would return null right at start of this method.
		e = nextE;
		}

		
	if(e instanceof VarEx){
		return new PlainEx(1);
		}
	
	if(e instanceof PowerEx){
		return e.getSubEx(1).copy();
		}

	return null;
	}

public static Ex constantOfMember(Ex e, String var){
	if(!e.varContains(var)){
		return e.copy();
		}
	

	return new VoidEx();
	}

}
