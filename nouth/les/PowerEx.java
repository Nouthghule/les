package nouth.les;
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
	this(e);
	this.toPower(p);
	}

@Override
public void unwrap(){
	if(((getSubEx(1) instanceof PlainEx)&&(((PlainEx)getSubEx(1)).value==0))||((getSubEx(0) instanceof PlainEx)&&(((PlainEx)getSubEx(0)).value==1))){
		this.replaceSelf(new PlainEx(1));
		return;
		}
	if(((getSubEx(1) instanceof PlainEx)&&(((PlainEx)getSubEx(1)).value==1))){
		this.replaceSelf(getSubEx(0));
		return;
	}
	}


@Override
public Ex toPower(Ex e){
	e.master = this;
	if((getSubEx(1) instanceof VoidEx)||((getSubEx(1) instanceof PlainEx)&&(((PlainEx)getSubEx(1)).value==1))){
		replaceTarget(1, e);
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
	crs.add(new PowerCruncherConsolide());
	crs.add(new PowerCruncherProcessRaise());
	crs.add(new PowerCruncherSplitDen());
	crs.add(new PowerCruncherSplitNum());
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

@Override
public boolean requireBrackets(Ex e){
	if((e instanceof PlainEx)&&(((PlainEx)e).value<0)){
		return true;
		}
	if(e instanceof PowerEx){
		return true;
		}
	if(e instanceof MultiEx){
		return true;
		}
	return false;
	}

@Override
public String reportForTex(){
	if(silent){
		return "";
		}
	String statement = "";

	if(isRoot()){
		
		Ex den = getSubEx(1).getSubEx(1);
		Ex num = getSubEx(1).getSubEx(0);
		
		statement += "\\\\\\sqrt";

		if(den.reportForTex().equals("2")){
			statement += "{";
			}
		else{
			statement += "[";
			statement += den.reportForTex();
			statement += "]{";
			}
		Ex actor = getSubEx(0).copy();
		actor.toPower(num.copy());

		statement += actor.reportForTex();
		statement += "}";
		}
	else{
		Ex child = getSubEx(0);
		if(requireBrackets(child)){
			statement += "(";
			}
		statement += child.reportForTex();
		if(requireBrackets(child)){
			statement += ")";
			}
		
		statement += "^";
		
		int len = getSubEx(1).reportForTex().length();

		if(len>1){
			statement+="{";
			}

		child = getSubEx(1);
		statement += child.reportForTex();
		if(len>1){
			statement += "}";
			}
	}
	return statement;


	}

public boolean isRoot(){
	Ex power = getSubEx(1);
	
	if(!(power instanceof DivEx)){
		return false;
		}
	
	Ex den = power.getSubEx(1); 

	if((den instanceof PlainEx)&&((((PlainEx)den).value==0)||(((PlainEx)den).value==1))){
		return false;
		}

	return true;
	
	}

}
