import java.util.*;

public abstract class Ex implements Comparable<Ex> {

public Ex master; //the Ex in which this Ex is nested.

public int posInMaster = 0;

protected ArrayList<Ex> exList = new ArrayList<Ex>();

protected String reportSeparator = "$";

public silent = false;

////////////////////////////////////////////////////////////////////

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
///////////////////////////////////////////////////////////////////

public Ex simplify(){
	return this;
	}


///////////////////////////////////////////////////////////////////

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
			count++
			}
		}
	return count;
	}

//public abstract void reducePlains();

///////////////////////////////////////////////////////////////////

public Ex add(Ex argEx){
	AddEx newlyMade = AddEx.create();
	this.replaceSelf(newlyMade);
	newlyMade.add(this);
	newlyMade.add(argEx);
	this.sort();
	return newlyMade;
	};

public Ex multi(Ex argEx){
	MultiEx newlyMade = MultiEx.create();
	this.replaceSelf(newlyMade);
	newlyMade.multi(this);
	newlyMade.multi(argEx);
	this.sort();
	return newlyMade;
	}

public Ex div(Ex argEx){
	DivEx newlyMade = DivEx.create();
	this.replaceSelf(newlyMade);
	newlyMade.multi(this);
	newlyMade.div(argEx);
	this.sort();
	return newlyMade;
	}

//------------------------------/

public Ex add(int argNum){
	PlainEx madeEx = PlainEx.create(argNum);
	return add(madeEx);
	}

public Ex multi(int argNum){
	PlainEx madeEx = PlainEx.create(argNum);
	return multi(madeEx);
	}

public Ex div(int argNum){
	PlainEx madeEx = PlainEx.create(argNum);
	return div(madeEx);
	}

//////////////////////////////////////////////////

public int size(){
	return exList.size();
	}

public Ex getSubEx(int index){
	return exList.get(index);
	}

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
	String statement = "(";
	for(i=0;i<exList.size();i++){
		if(exList.get(i).silent == false){
			statement += exList.get(i).report();
			if(i+1!=exList.size()){
				statement += this.reportSeparator;
					}
				}
		}
	statement += ")";
	return statement;
}

//To be overriden and used in cases where non-changing output format is required.
public String reportForChecks(){
	return this.report();
}

}
