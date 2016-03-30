import java.util.*;

public class AddCruncherSingleDenominator{

protected ArrayList<Ex> workList = new ArrayList<Ex>();

@Override
public int crunch(Ex targetEx){

if(!(targetEx instanceof AddEx)){
	return 0;
	}

//workList = new ArrayList<Ex>();
AddEx argEx = (AddEx) targetEx;
ArrayList<Ex> subExList = argEx.getSubExList();

for(Ex exie : subExList){
	addDenominatorFrom(exie);
	}

}

private void addDenominatorFrom(Ex theEx){

Ex denominator;
if(theEx instanceof DivEx){
	denominator = theEx.getSubEx(1).copy();
	}
else{
	denominator = new PlainEx(1);
	}

//TODO 

}

}


