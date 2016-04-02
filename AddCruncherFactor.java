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
int cd = plainList.get(0).value; //Starting this with 1 does not, for some arcane reason, work. Hence this daftness
int negative = 0;
if(cd<0){
	cd = java.lang.Math.abs(cd);
	negative ++;
	}
int i,b;
for(i=1;i<plainList.size();i++){
	b = plainList.get(i).value;
	if(b<0){
		b = java.lang.Math.abs(b);	
		negative ++;
		}
	System.out.println("° current gcd is " + cd + " calculating next from it and " + plainList.get(i).value + " (actually : "+b+")");
	cd = gcd(cd,b);
	}
if(negative>(i/2)){
	cd = cd * (-1);
	}
System.out.println("°= final gcd is " + cd );

int workDone = 0;
MultiEx factor = new MultiEx();
if((cd!=1)||(inList.size()>0)){
	workDone = 1;
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
}

return workDone;

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

