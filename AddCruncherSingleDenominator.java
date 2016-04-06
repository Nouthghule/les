import java.util.*;

public class AddCruncherSingleDenominator extends Cruncher{
//TODO think of a name that is not ridiculously long

protected ArrayList<Ex> workList = new ArrayList<Ex>();

@Override
public int crunch(Ex targetEx){

if(!(targetEx instanceof AddEx)){
	return 0;
	}

workList.clear();
AddEx argEx = (AddEx) targetEx;
ArrayList<Ex> subExList = argEx.getSubExList();

for(Ex exie : subExList){
	addDenominatorFrom(exie);
	}

AddEx unitedNumerator = new AddEx();
MultiEx unitedDenominator = (MultiEx)(new MultiEx()).multi(workList);
unitedDenominator.polish();
System.out.println("Constructed uniDenominator. it is " + unitedDenominator.report());
Cruncher crunchy = new DivCruncherSimplify();

for(Ex exie : subExList){
	Ex theEx = exie.copy();
	Ex multiBy = unitedDenominator.copy();
	if(theEx instanceof DivEx){
		DivEx holderEx = new DivEx();
		holderEx.multi(multiBy);
		holderEx.div(theEx.getSubEx(1));
		System.out.println("Going to crunch " + holderEx.report());
		crunchy.crunch(holderEx);
		multiBy = holderEx.getSubEx(0);
		theEx = theEx.getSubEx(0);
		}
	theEx = theEx.multi(multiBy);	
	unitedNumerator.add(theEx);
	}


Ex newDiv = new DivEx();
newDiv.multi(unitedNumerator);
newDiv.div(unitedDenominator);
newDiv.polish();
if((newDiv.getSubEx(1) instanceof PlainEx)&&(((PlainEx)newDiv.getSubEx(1)).value==1)){
	return 0;	
	}
System.out.println("Single denom replacing self @ " + targetEx.report() + " with " + newDiv.report());
targetEx.replaceSelf(newDiv);
return 1;
}

private void addDenominatorFrom(Ex theEx){

Ex denominator;
if(theEx instanceof DivEx){
	denominator = theEx.getSubEx(1).copy();
	}
else{
	denominator = new PlainEx(1);
	}

System.out.println("resident loop Start with denominator " + denominator.report());
Cruncher crunchy = new DivCruncherSimplify();
for(Ex resident : workList){
	DivEx holderEx = new DivEx();
	holderEx.multi(denominator);
	holderEx.div(resident.copy());
	System.out.println("Going to crunch : " + holderEx.report());
	crunchy.crunch(holderEx);
	denominator = holderEx.getSubEx(0);
	System.out.println("denominator is : " + denominator.report());
	}
System.out.println("Resident loop end ! denominator is : " + denominator.report());
workList.add(denominator);
}

}


