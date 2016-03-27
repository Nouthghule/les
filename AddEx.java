import java.util.*;

public class AddEx extends Ex{

////////////////////////////////////////////////////////////////////
public AddEx(){
	reportSeparator = "+";
	VoidEx initEx = new VoidEx();
	exList.add(initEx);
	}
/*
public static AddEx create(){
	AddEx myEx = new AddEx();
	return myEx;
	}
//*//////////////////////////////////////////////////////////////////

@Override
public AddEx add(Ex argEx){
	int i = exList.size();
	if((i==1)&&(exList.get(0).report().equals("#VOID#"))){
		exList.remove(0);
		}
	exList.add(argEx);
	argEx.posInMaster = i;
	argEx.master = this;
	this.sort();
	return this;
	}


public AddEx add(ArrayList<Ex> argExList){
	for(Ex theEx:argExList){
		this.add(theEx);	
		}
	this.sort();
	return this;
	}

@Override
public void appendSubEx(Ex argEx){
	this.add(argEx);
	}

////////////////////////////////////////////////////////////////////////

@Override
public ArrayList<Operator> propose(boolean isFirst){
	ArrayList<Operator> fullList = new ArrayList<Operator>();
	ArrayList<Operator> gottenList;
	for(Ex e : exList){
		gottenList = e.propose(false);
		for(Operator op : gottenList){
			if(!fullList.contains(op)){
				fullList.add(op);
				}
			}
		}
	
	
	
	return fullList;	
	}



}
