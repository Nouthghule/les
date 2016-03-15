import java.util.*;

public class MultiEx extends Ex{

////////////////////////////////////////////////////////////////////
public MultiEx(){
	reportSeparator = "*";
	if(exList.size()==0){
		PlainEx initEx = new PlainEx(1);
		this.multi(initEx);
		}
	}
/*
public static MultiEx create(){
	MultiEx myEx = new MultiEx();
	return myEx;
	}
//*//////////////////////////////////////////////////////////////////

@Override
public Ex multi(Ex argEx){
	
	/*
	commenting this out as it causes trouble with AddCruncherEqual and is easy 
	to miss.
	TODO : make multiExes ignore 1s in their lists when reporting.
	int i = exList.size();
	
	if((i==1)&&(exList.get(0).reportForChecks().equals("1"))){
		exList.remove(0);
		}
	*/
	exList.add(argEx);
	argEx.master = this;
	this.sort();
	return this;
	}

public Ex multi(ArrayList<Ex> argExList){
	for(Ex theEx:argExList){
		this.multi(theEx);	
		}
	return this;
	}

@Override
public void appendSubEx(Ex argEx){
	this.multi(argEx);
	}

}
