package nouth.les;
import java.util.*;

public class MultiCruncherDivs extends Cruncher{
	
public int crunch(Ex targetEx){

if(!(targetEx instanceof MultiEx)){
	return 0;
	}

workList = targetEx.getSubExListCopy();

Ex firstDiv = null;
ListIterator<Ex> iter = workList.listIterator();
Ex e;


while(iter.hasNext()){
	e = iter.next();
	if(e instanceof DivEx){
		System.out.println("mcd e = " + e.report());
		if(firstDiv == null){
			firstDiv = e.copy();
			}
		else{
			firstDiv.multi(e.getSubEx(0).copy());
			firstDiv.div(e.getSubEx(1).copy());
			}
		iter.remove();
		}
		/*
	else{
		if(firstDiv == null){
			firstDiv = e.copy();
			}
		else{
			firstDiv.multi(e.copy());
			}
		}
	*/
	}

for(Ex g : workList){
	System.out.println("mcd worklist " + g.report());
	}


if(firstDiv == null){
	return 0;
	}

System.out.println("mcd " + targetEx.report());
targetEx.wipe();
for(Ex g : workList){
	System.out.println("mcd worklist " + g.report());
	}
targetEx.multi(workList);
System.out.println("mcd " + targetEx.report());
targetEx.multi(firstDiv);
System.out.println("mcd " + targetEx.report());
return 1;

}
}
