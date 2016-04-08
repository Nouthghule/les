import java.util.*;

public class AddEx extends Ex{

////////////////////////////////////////////////////////////////////
public AddEx(){
	reportSeparator = "+";
	VoidEx initEx = new VoidEx();
	exList.add(initEx);
	}
/*
public static AddEx create(){
	AddEx myEx = new AddEx();
	return myEx;
	}
//*//////////////////////////////////////////////////////////////////

@Override
public AddEx add(Ex argEx){
	int i = exList.size();
	if((i==1)&&(exList.get(0).report().equals("#VOID#"))){
		exList.remove(0);
		}
	exList.add(argEx);
	argEx.posInMaster = i;
	argEx.master = this;
	this.sort();
	return this;
	}


public AddEx add(ArrayList<Ex> argExList){
	for(Ex theEx:argExList){
		this.add(theEx);	
		}
	this.sort();
	return this;
	}

@Override
public void appendSubEx(Ex argEx){
	this.add(argEx);
	}

////////////////////////////////////////////////////////////////////////
@Override
public void unwrap(){
	if(this.size()==1){
		this.replaceSelf(this.getSubEx(0));
		}
	else{
		ArrayList<Ex> toBeAdded = new ArrayList<Ex>();
		Iterator iter = exList.iterator();
		while(iter.hasNext()){
			Ex currEx = (Ex) iter.next();
			if(currEx instanceof AddEx){
				iter.remove();
				int i;
				for(i=0;i<currEx.size();i++){
					toBeAdded.add(currEx.getSubEx(i));
					}
				}
			}
		this.add(toBeAdded);
		}
	}

///////////////////////////////////////////////////////////////////////

@Override
public ArrayList<Operator> suggestCrunchers(){
	ArrayList<Operator> l = new ArrayList<Operator>();
	l.add(new AddCruncherEqual());
	l.add(new AddCruncherFactor());
	l.add(new AddCruncherSingleDenominator());
	return l;
	}
@Override
public ArrayList<Alterator> suggestAlterators(){
	ArrayList<Alterator> l = new ArrayList<Alterator>();
	
	List<String> vars = varList();

	
	for(String var : vars){
                Ex substract = new AddEx();
                boolean change = false;
                for(Ex e : exList){
                        if(!(e.varContains(var))){
                                substract.add(e.copy().multi(new PlainEx(-1)));
                                }
			else{
                                change = true;
				/*Leaving this in for future - I may want to change the vars
				arraylist before going through this (eg : only do it for x).
				*/		
				}
                        }
                if(change){
                l.add(new AlteratorAdd(substract));
                }
                }
	return l;
	}
}
