package nouth.les;
import java.util.*;

public class PlainEx extends TinyEx{

public Ex master; //the Ex in which this Ex is nested.

public int posInMaster;

public int value;

////////////////////////////////////////
//TODO : clean up artifacts from the time of units

public PlainEx(int argValue){
	value = argValue;
	}

public PlainEx(){
	value = 1;
	}

/*
public static PlainEx create(int argValue){
	PlainEx myEx = new PlainEx(argValue);
	return myEx;
	}

@Override
public static PlainEx create(){}

//*///////////////////////////////////////
/*
@Override

public Ex copy(){
	
	}

/*//////////////////////////////////////

@Override
protected Ex processCopy(Ex theCopy){
	PlainEx rlCopy = (PlainEx) theCopy;
	rlCopy.value = this.value;
	return rlCopy;
	}

////////////////////////////////////////


//Put this ahead of anything that isn't a PlainEx too.
@Override 
public int compareTo(Ex argEx){
	
	if(argEx instanceof PlainEx){
        	return this.report().compareToIgnoreCase(argEx.report());
		}
	
	return -1;
	}	

////////////////////////////////////////

@Override
public Ex getSubEx(int index){
	System.out.println("Attempting to access " + index + ". element of PlainEx <" + this.report() + "> in {" + master.report() + "}");
	return null;
	}

@Override
public int size(){
	return 0;
	}

@Override
public void appendSubEx(Ex argEx){
	//TODO DECIDE WHAT TO DO HERE. Change method name to avoid confusion ?
	this.replaceSelf(argEx);
	}


/////////////////////////////////////////////

@Override
public String report(){
//	System.out.print("{preparing report at value of " & String.valueOf(value) & "}");
	String statement = "(";
	statement += value;
	statement += ")";
	return statement;
}

@Override
public String reportForTex(){
	String statement = "";
	statement += value;
	return statement;
	}

}
