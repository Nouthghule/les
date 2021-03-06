package nouth.les;
import java.util.*;
import static java.lang.Math.abs;

//TODO think of a better name !
public class DivCruncherSimplify extends Cruncher{

public ArrayList<Ex> numeratorList = new ArrayList<Ex>();
public ArrayList<Ex> denominatorList = new ArrayList<Ex>();

public int crunch(Ex targetEx){

if(!(targetEx instanceof DivEx)){
	return 0;
	}
numeratorList.clear();
denominatorList.clear();

System.out.println("DCS start " + targetEx.report());

boolean doneWork = false;

Ex numSubEx = targetEx.getSubEx(0).copy();
Ex denSubEx = targetEx.getSubEx(1).copy();

if(numSubEx instanceof MultiEx){
	numeratorList = numSubEx.getSubExList();
	}
else{
	numeratorList.add(numSubEx);
	}
if(denSubEx instanceof MultiEx){
	denominatorList = denSubEx.getSubExList();
	}
else{
	denominatorList.add(denSubEx);
	}

ListIterator<Ex> numIterator = numeratorList.listIterator();

//Divide out equal Exes
while(numIterator.hasNext()){
	Ex numEx = (Ex)numIterator.next();	
	ListIterator<Ex> denIterator = denominatorList.listIterator();
	
	while(denIterator.hasNext()){
		Ex denEx = (Ex)denIterator.next();
		if(denEx.report().equals(numEx.report())){
			System.out.println("DCS crossing out " + denEx.report());
			numIterator.remove();
			denIterator.remove();
			numIterator.add(new PlainEx(1));
			denIterator.add(new PlainEx(1));
			doneWork = true;
			break;
			}
		}
	}

//Get only one plainEx
Cruncher crunchy = new MultiCruncherPlain();
Ex tempA = (new MultiEx()).multi(numeratorList);
Ex tempB = (new MultiEx()).multi(denominatorList);
System.out.println("DCS after crossout before single plain :" + tempA.report() + " / " + tempB.report()); 
crunchy.crunch(tempA);
crunchy.crunch(tempB);
numeratorList = tempA.getSubExList();
denominatorList = tempB.getSubExList();

//Get it into the first place
Collections.sort(numeratorList);
Collections.sort(denominatorList);
System.out.println("DCS single plain :" + tempA.report() + " / " + tempB.report()); 

int n,d;
int na,da;
//Check if there actuall is a plainEx
if(numeratorList.get(0) instanceof PlainEx){
	PlainEx pl = (PlainEx) numeratorList.get(0);
	n = pl.value;
	na = abs(n);
	}
else{
	//When no shortening took place & there was no plainEx
	n = 1;
	na = 1;
	numeratorList.add(new PlainEx(n));
	Collections.sort(numeratorList);
	}
if(denominatorList.get(0) instanceof PlainEx){
	PlainEx pl = (PlainEx) denominatorList.get(0);
	d = pl.value;
	da = abs(d);
	}
else{
	//When no shortening took place & there was no plainEx
	d = 1;
	da = 1;
	denominatorList.add(new PlainEx(d));
	Collections.sort(denominatorList);
	}

int gcd = gcd(na,da);
System.out.println("DCS computing gcd from " + na + " and " + da + " = " + gcd );
if(gcd != 1){
	
	n = n / gcd;
	d = d / gcd;

	numeratorList.set(0, new PlainEx(n));
	denominatorList.set(0, new PlainEx(d));
	doneWork = true;
	}
if(doneWork){
	targetEx.wipe();
	targetEx.multi(numeratorList);
	targetEx.div(denominatorList);
		
	System.out.println("DCS resulting " + targetEx.report());
	return 1;
	}

System.out.println("DCS ZERO return resulting " + targetEx.report());
return 0;
}



}


