import java.util.*;

public class MultiCruncherExpand extends Cruncher{	

public int crunch(Ex targetEx){	

if (!(targetEx instanceof MultiEx)){
	System.out.println("MCE target not multiex 0");
	return 0;
	}

workList = targetEx.getSubExList();
ArrayList<Ex> addList = new ArrayList<Ex>();
ArrayList<Ex> constList = new ArrayList<Ex>();

for(Ex e : workList){
	if(e instanceof AddEx){
		addList.add(e.copy());
		}
	else{
		constList.add(e.copy());	
		}
	}

if(addList.size()==0){
	System.out.println("MCE addlist size 0");
	return 0;
	}

int i;
AddEx result = (AddEx)addList.get(0);
ArrayList<Ex> resultList;
ArrayList<Ex> waitList;
ArrayList<Ex> tempList = new ArrayList<Ex>();
Ex t = new VoidEx();
System.out.println("{{{starting result = " + result.report());
for(i=1;i<addList.size();i++){
	resultList = result.getSubExList();
	waitList = addList.get(i).getSubExList();
	tempList.clear();
	for(Ex r : resultList){
		for(Ex w : waitList){
			t = r.copy().multi(w.copy());
			System.out.println("{{{w*r = " + t.report());
			tempList.add(t);
			}
			System.out.println("{{{t = " + t.report());
		}
	for(Ex exie : tempList){
		System.out.println("Templist : " + exie.report());	
		}
	result.wipe();
	result.add(tempList);
	System.out.println("{{{current result = " + result.report());
	}

resultList = result.getSubExList();
for(Ex r : resultList){
	for(Ex c : constList){
		r = r.multi(c.copy());
		System.out.println("{{{" + r.report() + " (multied by) " + c.report());
		}
	}

targetEx.wipe();
targetEx.multi(result);
targetEx.polish();

System.out.println(targetEx.report() + " is the result.");

return 1;

}


}

