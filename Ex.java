import java.util.*;

public abstract class Ex implements Comparable<Ex> {

public Ex master; //the Ex in which this Ex is nested.

public int posInMaster = 0;

protected  ArrayList<Ex> exList = new ArrayList<Ex>();

public char reportSeparator = '$';

public boolean silent = false;

/////////////////////////////////////////////////////////////////ú/

public void replaceSelf(Ex argEx){
	if(master==null){
		System.out.println("Warning ! Replaceself attempt on an orphan Ex. Ignoring. I am : " + this.report());
		return;
		}
	master.replaceTarget(posInMaster, argEx);
	}

public void replaceTarget(int pos, Ex argEx){
	argEx.master = this;
	argEx.posInMaster = pos;
	if(master == null){
//		System.out.println("EX:  I'm " + this.report() + ", an orphan,  and I'm replacing subEx at " + pos + " by " + argEx.report());
		}
	else{
//		System.out.println("EX:  I'm " + this.report() + " in "+ master.report()+ " and I'm replacing subEx at " + pos + " by " + argEx.report());
		}
	exList.set(pos, argEx);
	}

public void updatePoses(){
	int i;
	for(i=0;i<this.size();i++){	
		this.getSubEx(i).posInMaster = i;
		}
	}

//-----------------------

public Ex copy(){
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

//remove redundant Exes (eg. (((2)+(1))) -> ((2)+(1))
public void unwrap(){
	if(this.size()==1){
		this.replaceSelf(this.getSubEx(0));	
		}	
	}
///-----------------

public final void polish(){
	//System.out.println("ex polishing : " + this.report());
	for(Ex e : exList){
		e.polish();
		}
	unwrap();
	sort();
	}

//////////////////////////////////

public ArrayList<Operator> suggest(){
	ArrayList<Operator> resultList = new ArrayList<Operator>();
	for(Ex e : exList){
		ArrayList<Operator> childList = e.suggest();
		for(Operator o : childList){
			if(!(resultList.contains(o))){
				resultList.add(o);
				}
			}
		}

	ArrayList<Operator> myList;
	myList = this.suggestCrunchers();
	for(Operator o : myList){
		if(!(resultList.contains(o))){
			resultList.add(o);
			}
		}
	return resultList;
	}

public abstract ArrayList<Operator> suggestCrunchers(); //check implementation in EqEx before changing return type
public abstract ArrayList<Alterator> suggestAlterators();

/////////////////////////////////////////////////////////////úúú

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

public int subExTotal(){
	int total = 1;
	for(Ex e : exList){
		total += e.subExTotal();
		}
	return total;
	}

///////////////////////////////////////////////////////////////////

public Ex add(Ex argEx){
	AddEx newlyMade = new AddEx();
	this.replaceSelf(newlyMade);
	newlyMade.add(this);
	newlyMade.add(argEx);
	newlyMade.sort();
	return newlyMade;
	};

//! do NOT change return type to MultiEx. Multiplying a DivEx returns
// a DivEx
public Ex multi(Ex argEx){
	MultiEx newlyMade = new MultiEx();
	this.replaceSelf(newlyMade);
	newlyMade.multi(this);
	newlyMade.multi(argEx);
	newlyMade.sort();
	return newlyMade;
	}

public Ex div(Ex argEx){
	DivEx newlyMade = new DivEx();
	this.replaceSelf(newlyMade);
	newlyMade.multi(this);
	newlyMade.div(argEx);
	newlyMade.sort();
	return newlyMade;
	}

//------------------------------/

public Ex add(ArrayList<Ex> arrList){
	if(arrList.size() == 0){
		Ex substitute = this.add(0);
		return substitute;
		}
	Ex ref = this;
	for(Ex currEx : arrList){
		ref = ref.add(currEx);	
		}
	return (AddEx)ref;
	}

//As above, do NOT change return type to MultiEx.
public Ex multi(ArrayList<Ex> arrList){
	if(arrList.size() == 0){
		Ex substitute = this.multi(1);
		return substitute;
		}
	Ex ref = this;
	for(Ex currEx : arrList){
		ref = ref.multi(currEx);	
		}
	return ref;
}

public Ex div(ArrayList<Ex> arrList){
	if(arrList.size() == 0){
		Ex substitute = this.div(1);
		return substitute;
		}
	Ex ref = this;
	for(Ex currEx : arrList){
		ref = ref.div(currEx);	
		}
	return (DivEx)ref;
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

public ArrayList<Ex> getSubExList(){
	return exList;
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


}
