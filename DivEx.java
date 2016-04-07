import java.util.*;

public class DivEx extends Ex{

////////////////////////////////////////////////////////////////////
public  DivEx(){
	reportSeparator = "/";
	exList.add(new VoidEx());
	exList.add(new VoidEx());
	}
/*
public static DivEx create(){
	DivEx myEx = new DivEx();
	return myEx;
	}
//*//////////////////////////////////////////////////////////////////

@Override
public Ex processCopy(Ex argEx){
	Ex numerator = exList.get(0);	
	Ex denominator = exList.get(1);	
	
	DivEx rlCopy = (DivEx) argEx;
	rlCopy.multi(numerator.copy());
	rlCopy.div(denominator.copy());
	return rlCopy;
	}




///////////////////////////////////////////////
/*
@Override
public void replaceTarget(Ex argEx, int pos){
	argEx.master = this;
	argEx.posInMaster = pos;
	if(pos==0){
		numerator = argEx;
		}
	else if(pos==1){
		denominator = argEx;
		}
	}
*/

@Override
public void unwrap(){
	if((getSubEx(1) instanceof PlainEx)&&(java.lang.Math.abs(((PlainEx)getSubEx(1)).value) == 1)){
		MultiEx newOne = new MultiEx();
		newOne.multi(this.getSubEx(0));
		newOne.multi(this.getSubEx(1));
		this.replaceSelf(newOne);
		}
	}
@Override
public void sort(){
	getSubEx(0).sort();
	getSubEx(1).sort();
	}

@Override
public String report(){
	Ex numerator = exList.get(0);	
	Ex denominator = exList.get(1);	
	String statement = "(";
	statement += numerator.report();
	statement += ")";
	statement += reportSeparator;
	statement += "(";
	statement += denominator.report();
	statement += ")";
	return statement;
	}

///////////////////////////////////////////////////////////////////////////////////////
/*
@Override
public boolean varContains(String argString){
	if(numerator.varContains(argString)||(denominator.varContains(argString))){
		return true;
		}
	return false;
	}

@Override
public int varDepth(String argString){
	int depth = -2;
	int tempDepth;

	tempDepth = numerator.varDepth(argString);
	if((tempDepth>depth)&&(tempDepth>-1)){
		depth = tempDepth;
		}	
	tempDepth = denominator.varDepth(argString);
	if((tempDepth>depth)&&(tempDepth>-1)){
		depth = tempDepth;
		}	
	depth++;
	return depth;
	}

@Override
public List<String> varList(){
        List<String> list = new ArrayList<String>();
                
	List<String> tempList = numerator.varList();
               	for(String s : tempList){
                        if(!list.contains(s)){
                                list.add(s);
                                }
                        }
        tempList = denominator.varList();
	
      		 for(String s : tempList){
                        if(!list.contains(s)){
                                list.add(s);
                                }
			}
	return list;
	}

*/
//////////////////////////////////////////////////////////////////////////ú
/*
@Override
public Ex getSubEx(int index){
	if(index==0){
		return numerator;
		}
	else if(index==1){
		return denominator;
		}
	else{
		return null;
		}
	}
*/
@Override
public int size(){
	return 2;
	} 

@Override
public void appendSubEx(Ex argEx){
	Ex numerator = exList.get(0);		
	//TODO decide if intended behavior
	numerator.appendSubEx(argEx);
	}

///////////////////////////////////////////////////////////////////////////////////////

@Override
public Ex multi(Ex argEx){
	Ex numerator = exList.get(0);	
	if(numerator.report().equals("#VOID#")){
		exList.set(0, argEx);
		argEx.master = this;
		argEx.posInMaster = 0;
		return this;
		}
	System.out.println("DivEx multi speaking. gonna multi my numerator " + numerator.report() + " by " + argEx.report());
	numerator.multi(argEx);
	return this;	
	}

public Ex multi(ArrayList<Ex> argExList){
	for(Ex theEx:argExList){
		this.multi(theEx);	
		}
	return this;
	}

@Override
public DivEx div(Ex argEx){
	Ex denominator = exList.get(1);	
	if(denominator.report().equals("#VOID#")){
		exList.set(1, argEx);
		argEx.master = this;
		argEx.posInMaster = 1;
		return this;
		}
	denominator.multi(argEx);
	return this;
	}

public DivEx div(ArrayList<Ex> argExList){
	for(Ex theEx:argExList){
		this.div(theEx);	
		}
	return this;

	}

@Override
public void wipe(){
	exList.clear();
	exList.add(new VoidEx());
	exList.add(new VoidEx());
	}
///////////////ú//úúúúúúúúúúúúúú///////

@Override
public ArrayList<Operator> suggestCrunchers(){
        ArrayList<Operator> l = new ArrayList<Operator>();
        l.add(new DivCruncherSimplify());
        l.add(new DivCruncherEmergeDivs());
        return l;
        }
@Override
public ArrayList<Operator> suggestAlterators(){
        ArrayList<Operator> l = new ArrayList<Operator>();
        
	Ex denominator = getSubEx(1).copy();
	l.add(new AlteratorMulti(denominator));

	return l;
        }

}
