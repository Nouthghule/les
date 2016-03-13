import java.util.*;

public class BossEx extends Ex{

private Ex subEx;

public BossEx(){
	subEx = new PlainEx(0);
	subEx.master = this;
}

@Override 
public void replaceTarget(Ex argEx,int pos){
	subEx = argEx;
	argEx.master = this;
	}
@Override
public Ex add(Ex argEx){
	subEx.add(argEx);
	return this;
	}
@Override
public Ex div(Ex argEx){
	subEx.div(argEx);
	return this;
	}
@Override
public Ex multi(Ex argEx){
	subEx.multi(argEx);
	return this;
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
