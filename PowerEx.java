import java.util.*;
public class PowerEx extends Ex{

public PowerEx(){
	reportSeparator = '^';
	Ex a = new VoidEx();
	Ex b = new VoidEx();
	a.master = this;
	b.master = this;
	exList.add(a);
	exList.add(b);
	updatePoses();
	}

public PowerEx(Ex e){
	reportSeparator = '^';
	exList.add(e);
	e.master = this;
	Ex uno = new PlainEx(1);
	uno.master = this;
	exList.add(uno);
	updatePoses();
	}

public PowerEx(Ex e, Ex p){
	reportSeparator = '^';
	e.master = this;
	exList.add(e);
	p.master = this;
	exList.add(p);
	updatePoses();
	}
@Override
public Ex toPower(Ex e){
	if(getSubEx(1) instanceof VoidEx){
		replaceTarget(1, e);
		e.master = this;
		return this;
		}
	if((e instanceof PlainEx)&&(((PlainEx)e).value==1)){
		return this;
		}
	getSubEx(1).multi(e);
	return this;
	}

@Override
public ArrayList<Operator> suggestCrunchers(){
	ArrayList<Operator> crs = new ArrayList<Operator>();
	crs.add(new PowerCruncherExpand());
	return crs;
	}

@Override
public ArrayList<Alterator> suggestAlterators(){
	ArrayList<Alterator> alts = new ArrayList<Alterator>();
	return alts;
	}

@Override
public Ex processCopy(Ex theCopy){
	Ex base = exList.get(0).copy();
	Ex power = exList.get(1).copy();
	theCopy.replaceTarget(0, base);
	theCopy.replaceTarget(1, power);
	return theCopy;
	}

@Override
public void sort(){
	
	}
}
