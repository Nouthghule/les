import java.util.*;

public abstract class Ex implements Comparable<Ex> {

public Ex master; //the Ex in which this Ex is nested.

public int posInMaster = 0;

public ArrayList<Ex> exList = new ArrayList<Ex>();

protected String reportSeparator = "$";

public boolean silent = false;

/////////////////////////////////////////////////////////////////ú/

public void replaceSelf(Ex argEx){
	if(master==null){
		return;
		}
	master.replaceTarget(argEx, posInMaster);
	}

public void replaceTarget(Ex argEx, int pos){
	argEx.master = this;
	argEx.posInMaster = pos;
	exList.set(pos, argEx);
	}

//-----------------------

public final Ex copy(){
	Ex copyOfThis;
	try{
		//TODO consider getting rid of reflection.
		copyOfThis= this.getClass().getConstructor().newInstance();
	}
	catch(Exception e){
		//TODO actually handle this ?	
		System.out.println("Oops, exception <" + e.getClass().getName() + "> at copy() of " + this.report());
		return null;
		}
	return processCopy(copyOfThis);
	}


protected Ex processCopy(Ex theCopy){
	int i;
	for(i=0; i<this.size();i++){
//		System.out.println("gonna append " + this.getSubEx(i).report() + " to " + theCopy.report());	
		theCopy.appendSubEx(this.getSubEx(i).copy());
		} 	
	theCopy.sort(); //also sets posInMasters
	return theCopy;
	}

//*/////////////////////////////////////////////////////////////////

public Ex simplify(){
	return this;
	}

public void wipe(){
	exList.clear();
	}

//////////////////////////////////


/*//////////////////////////////////////////////////////////////////
TODO decide what to do with this
public PlainEx getPlain(){
	if(this.getPlainExCount()==1){
		for (Ex e : exList){
			if(e instanceof PlainEx){
				return e;
				}
			}
		return null;
		}
	this.reducePlains();
	return this.getPlain();
	}

private int getPlainExCount(){
	int count = 0;
	for(Ex e : exList){
		if(e instanceof PlainEx){
			count++;
			}
		}
	return count;
	}

//public abstract void reducePlains();

*/
/////////////////////////////////////////////////////////////////

public boolean varContains(String argString){
	
	for(Ex e:exList){
		if(e.varContains(argString)){
		return true;
			}
		}
	return false;
	}

public int varDepth(String argString){
	//Return highest depth at which found or -1 if not found.
	
	int depth = -2;
	int tempDepth;
	for(Ex e : exList){	
		tempDepth = e.varDepth(argString);
		if((tempDepth>depth)&&(tempDepth>-1)){
			depth = tempDepth;
			}
	}
	
	depth ++;
	return depth;
	}

public List<String> varList(){
	List<String> list = new ArrayList<String>();
	for(Ex e : exList){
		List<String> tempList = e.varList();
		for(String s : tempList){
			if(!list.contains(s)){
				list.add(s);
				}
			}
		}
	return list;
	}
//*/////////////////////////////////////////////////////////////////

public AddEx add(Ex argEx){
	AddEx newlyMade = new AddEx();
	this.replaceSelf(newlyMade);
	newlyMade.add(this);
	newlyMade.add(argEx);
	newlyMade.sort();
	return newlyMade;
	};

//! DO NOT change return type to MultiEx. Multiplying a DivEx returns
// a DivEx
public Ex multi(Ex argEx){
	MultiEx newlyMade = new MultiEx();
	this.replaceSelf(newlyMade);
	newlyMade.multi(this);
	newlyMade.multi(argEx);
	newlyMade.sort();
	return newlyMade;
	}

public DivEx div(Ex argEx){
	DivEx newlyMade = new DivEx();
	this.replaceSelf(newlyMade);
	newlyMade.multi(this);
	newlyMade.div(argEx);
	newlyMade.sort();
	return newlyMade;
	}

//------------------------------/

public Ex add(int argNum){
	PlainEx madeEx = new PlainEx(argNum);
	return add(madeEx);
	}

public Ex multi(int argNum){
	PlainEx madeEx = new PlainEx(argNum);
	return multi(madeEx);
	}

public Ex div(int argNum){
	PlainEx madeEx = new PlainEx(argNum);
	return div(madeEx);
	}

//////////////////////////////////////////////////

public int size(){
	return exList.size();
	}

public Ex getSubEx(int index){
	return exList.get(index);
	}

public void appendSubEx(Ex argEx){
	this.exList.add(argEx);
	};


/////////////////////////////////////////////////

public int compareTo(Ex argEx){
	if(argEx instanceof PlainEx){
		return 1;
		}
	return this.report().compareToIgnoreCase(argEx.report());
	}

/////////////////////////////////////////////////

public void sort(){
	Collections.sort(exList);
	int i;
	for(i=0;i<exList.size();i++){
		exList.get(i).posInMaster = i;
		}
	}

/////////////////////////////////////////////////

//Important - Overwrite this if your method makes use of multiple arraylists !
public String report(){
	int i;
	if(!silent){ //This check should be redundant.

		String statement = "(";
		for(i=0;i<exList.size();i++){
			
			if(!exList.get(i).silent){
					
				statement += exList.get(i).report();
				if(i+1!=exList.size()){
				statement += this.reportSeparator;
						}
					}
			}
		statement += ")";
	return statement;
	}
	return "";
}

//To be overriden and used in cases where non-changing output format is required.
public String reportForChecks(){
	return this.report();
}

}
