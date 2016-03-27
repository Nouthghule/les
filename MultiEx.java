import java.util.*;

public class MultiEx extends Ex{

////////////////////////////////////////////////////////////////////
public MultiEx(){
	reportSeparator = "*";
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
	Commenting this back in because it fucks everything up either way
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
