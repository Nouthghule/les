package nouth.les;
import java.util.*;

public class DivCruncherEmergeDivs extends Cruncher{

public ArrayList<DivEx> inNumerator = new ArrayList<DivEx>();
public ArrayList<DivEx> inDenominator = new ArrayList<DivEx>();

@Override
public int crunch(Ex argEx){
	
if(!(argEx instanceof DivEx)){
	return 0;
	}

int workDone = 0;
inNumerator.clear();
inDenominator.clear();

workList.clear();
if(argEx.getSubEx(0) instanceof MultiEx){
	workList = argEx.getSubEx(0).copy().getSubExList();
	argEx.replaceTarget(0, new PlainEx(1));
	}
else if(argEx.getSubEx(0) instanceof DivEx){
	inNumerator.add((DivEx)argEx.getSubEx(0).copy());
	argEx.replaceTarget(0, new PlainEx(1));
	}

for(Ex e : workList){
	if(e instanceof DivEx){
		inNumerator.add((DivEx)e);
		}
	}


workList.clear();
if(argEx.getSubEx(1) instanceof MultiEx){
	workList = argEx.getSubEx(1).copy().getSubExList();
	argEx.replaceTarget(1, new PlainEx(1));
	}
else if(argEx.getSubEx(1) instanceof DivEx){
	inDenominator.add((DivEx)argEx.getSubEx(1).copy());
	argEx.replaceTarget(1, new PlainEx(1));
	}

for(Ex e : workList){
	if(e instanceof DivEx){
		inDenominator.add((DivEx)e);
		}
	}		

workDone = (inDenominator.size() + inNumerator.size()); //TODO consider moving this further on

for(DivEx d : inNumerator){
	argEx.multi(d.getSubEx(0));
	argEx.div(d.getSubEx(1));
	}
for(DivEx d : inDenominator){
	argEx.multi(d.getSubEx(1));
	argEx.div(d.getSubEx(0));
	}

return workDone;

}
}
