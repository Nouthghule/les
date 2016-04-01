import java.util.*;

public class AddCruncherFactor extends Cruncher{
//This file is sorta messy. Proceed at own risk.

protected ArrayList<Ex> inList = new ArrayList<Ex>();
protected ArrayList<String> inStrings = new ArrayList<String>();
protected ArrayList<Ex> sourceList = new ArrayList<Ex>();
protected ArrayList<PlainEx> plainList = new ArrayList<PlainEx>();

@Override
public int crunch(Ex targetEx){
	
if(!(targetEx instanceof AddEx)){
	return 0;
	}
inList.clear();
workList.clear();
inStrings.clear();
plainList.clear();
sourceList = targetEx.getSubExList(); 
Cruncher theCru = new MultiCruncherPlain();

for(Ex subEx : sourceList){
	theCru.crunch(subEx); //We only want a single plainEx
	addToList(subEx);
	}

for(Ex e : inList){
	if(isInAll(e)){
		workList.add(e);
		}
	}

//sort out plains
int cd = plainList.get(0).value;
int i;
for(i=0;i<plainList.size();i++){
	System.out.println("° current gcd is " + cd + " calculating next from it and " + plainList.get(i).value);
	cd = gcd(cd,plainList.get(i).value);
	}
System.out.println("°= final gcd is " + cd );


MultiEx factor = new MultiEx();
factor.multi(inList);
System.out.println("factor (before cd) is : " +factor.report());
factor.multi(new PlainEx(cd));
System.out.println("factor is : " +factor.report());

Cruncher cr = new DivCruncherSimplify();
for(Ex e : sourceList){
	e = e.div(factor.copy());
	System.out.println("CruncherFactor : pre sub crunch : " + e.report());
	cr.crunch(e);
	}

targetEx.multi(factor);

return 1; //TODO implement zero condition

}

public void addToList(Ex argEx){

if(argEx instanceof DivEx){
	argEx = argEx.getSubEx(0); //use numerator if fraction
	}
if(argEx instanceof MultiEx){
	List<Ex> subList = argEx.getSubExList();
	for(Ex e : subList){
		if(e instanceof PlainEx){
			plainList.add((PlainEx)e);	
			}
		else{
			subAddToList(e);
			}
		}
	}
else{
	subAddToList(argEx);
	}


}

public void subAddToList(Ex theEx){
if(!inStrings.contains(theEx.report())){
	inList.add(theEx.copy());
	inStrings.add(theEx.report());
	}
}

public boolean isInAll(Ex theEx){
	int i = 0;
	for(Ex e : sourceList){
		ArrayList<Ex> theSubList = e.getSubExList();
		for(Ex eS : theSubList){
			if(eS.report().equals(theEx.report())){
				i++;
				break;
				}
			}
		}
	if(i==sourceList.size()){
		return true;
		}
	return false;
	}

}

