import java.util.*;

public class BossEx extends Ex{

/* WATCH OUT
This class behaves non-standartly in it's
div, add and multi methods in that it returns
the subEx and not the object that is at it's 
place after method call.
TODO : Replace this class with something that is
less of a joke.
*/

private Ex subEx;

public BossEx(){
	subEx = new PlainEx(0);
	subEx.master = this;
}

@Override
public AddEx add(Ex argEx){
	return subEx.add(argEx);
	}
@Override
public DivEx div(Ex argEx){
	return subEx.div(argEx);
	}
@Override
public Ex multi(Ex argEx){
	return subEx.multi(argEx);
	}
@Override
public String report(){
	String statement = "BossExpression : [";
	statement += subEx.report();
	statement += "]\n";
	return statement;
	}
@Override
public Ex getSubEx(int i){
	if(i==0){
		return subEx;
		}
	return null;
	}

@Override
public void sort(){
	subEx.sort();
	}
@Override
protected Ex processCopy(Ex theCopy){
	BossEx rlCopy = (BossEx) theCopy;
	rlCopy.subEx = this.subEx.copy();
	return rlCopy;
	}


}
