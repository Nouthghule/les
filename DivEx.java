import java.util.*;

public class DivEx extends Ex{

private Ex numerator;
private Ex denominator;

////////////////////////////////////////////////////////////////////
public  DivEx(){
	reportSeparator = "/";

	Ex defEx1 = new PlainEx(1);
	numerator = defEx1;

	Ex defEx2 = new PlainEx(1);
	denominator = defEx2;

	}
/*
public static DivEx create(){
	DivEx myEx = new DivEx();
	return myEx;
	}
//*//////////////////////////////////////////////////////////////////

@Override
public Ex processCopy(Ex argEx){
	DivEx rlCopy = (DivEx) argEx;
	rlCopy.multi(this.numerator.copy());
	rlCopy.div(this.denominator.copy());
	return rlCopy;
	}

///////////////////////////////////////////////

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

@Override
public String report(){
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


//////////////////////////////////////////////////////////////////////////ú

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

@Override
public int size(){
	return 2;
	} 

@Override
public void appendSubEx(Ex argEx){
	//TODO decide if intended behavior
	numerator.appendSubEx(argEx);
	}

///////////////////////////////////////////////////////////////////////////////////////

@Override
public Ex multi(Ex argEx){
	if(numerator.reportForChecks().equals("1")){
		numerator = argEx;
		argEx.posInMaster = 0;
		argEx.master = this;
		}
	else{
		numerator.multi(argEx);
		}
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
	
	if(denominator.reportForChecks().equals("1")){
		denominator = argEx;
		argEx.posInMaster = 1;
		argEx.master = this;
		}
	else{
		denominator.multi(argEx);
		}
	return this;
	}

public DivEx div(ArrayList<Ex> argExList){
	for(Ex theEx:argExList){
		this.div(theEx);	
		}
	return this;
	}

}
