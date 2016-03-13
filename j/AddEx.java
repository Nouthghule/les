import java.util.*;

public class AddEx extends Ex{

////////////////////////////////////////////////////////////////////
private AddEx(){
	reportSeparator = "+";
	PlainEx initEx = PlainEx.create(0);
	this.add(initEx);
	}

public static AddEx create(){
	AddEx myEx = new AddEx();
	return myEx;
	}
////////////////////////////////////////////////////////////////////

@Override
public Ex add(Ex argEx){
	int i = exList.size();
	if((i==1)&&(exList.get(0).reportForChecks().equals("0"))){
		exList.remove(0);
		}
	exList.add(argEx);
	argEx.posInMaster = i;
	argEx.master = this;
	this.sort();
	return this;
	}


public Ex add(ArrayList<Ex> argExList){
	for(Ex theEx:argExList){
		this.add(theEx);	
		}
	this.sort();
	return this;
	}

}
