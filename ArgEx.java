import java.util.*;

public class ArgEx extends Ex{

public Ex master; //the Ex in which this Ex is nested.

public int posInMaster;

public String unit;

////////////////////////////////////////
//TODO : cleanup artifacts from PlainEx (if any)

public ArgEx(String argUnit){
	unit = argUnit;
	}

public ArgEx(){
	unit = "defUnit";
	}

@Override
protected Ex processCopy(Ex theCopy){
	Var
	}
////////////////////////////////////////

//TODO : decide how to handle comparing. (put after PlainEx, before anything else ?)

//Put this ahead of anything that isn't a PlainEx too.
/*
@Override 
public int compareTo(Ex argEx){
	
	if(argEx instanceof PlainEx){
        	return this.report().compareToIgnoreCase(argEx.report());
		}
	
	return -1;
	}	
*/
////////////////////////////////////////

@Override
public Ex getSubEx(int index){
	if(index==0){
		return this;
		}
	System.out.println("Attempting to access " + index + ". element of ArgEx <" + this.report() + "> in {" + master.report() + "}");
	return null;
	}

@Override
public int size(){
	return 1;
	}

@Override
public void appendSubEx(Ex argEx){
	//TODO decide if this is a silly idea or not
	this.replaceSelf(argEx);
	}
/////////////////////////////////////

@Override
public String report(){
//	System.out.print("{preparing report at value of " & String.valueOf(value) & "}");
	String statement = "(";
	statement += unit;
	statement += ")";
	return statement;
}

@Override
public String reportForChecks(){
	String stableStatement = "";
	stableStatement += unit;
	return stableStatement;
	}


}
