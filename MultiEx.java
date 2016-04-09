import java.util.*;

public class MultiEx extends Ex{

////////////////////////////////////////////////////////////////////
public MultiEx(){
	reportSeparator = "*";
	}
/*
public static MultiEx create(){
	MultiEx myEx = new MultiEx();
	return myEx;
	}
//*//////////////////////////////////////////////////////////////////

@Override
public Ex multi(Ex argEx){
	
	/*
	commenting this out as it causes trouble with AddCruncherEqual and is easy 
	to miss. 
	TODO : make multiExes ignore 1s in their lists when reporting.
	Commenting this back in because it fucks everything up either way
	*/
//	System.out.println("MultiEx speaking. Adding this to my exlist : " + argEx.report());
	exList.add(argEx);
	argEx.master = this;
	this.sort();
//	System.out.println("===> " + this.report());
	return this;
	}

public Ex multi(ArrayList<Ex> argExList){
	for(Ex theEx:argExList){
		this.multi(theEx);	
		}
	return this;
	}

@Override
public void unwrap(){
        if(this.size()==1){
		this.replaceSelf(getSubEx(0));
		}
	ArrayList<Ex> submerged = new ArrayList<Ex>();
	ListIterator<Ex> iter = exList.listIterator();
	while (iter.hasNext()){
		Ex e = iter.next();
		if(e instanceof MultiEx){
			submerged.add(e);
			iter.remove();
			}
		}
	for(Ex e : submerged){
		this.multi(e);
		}
	iter = exList.listIterator();
	while(iter.hasNext()){
		if(exList.size()>1){
			Ex sub = iter.next();
			if(sub instanceof PlainEx){
				PlainEx rlSub = (PlainEx) sub;
				if(rlSub.value==1){
					iter.remove();	
					}
				}
			}
		}
	}

private void unwrapMe(){
}

@Override
public void appendSubEx(Ex argEx){
	this.multi(argEx);
	}

///////////////////////////////////////////////////////

@Override
public ArrayList<Operator> suggestCrunchers(){
        ArrayList<Operator> l = new ArrayList<Operator>();
        l.add(new MultiCruncherPlain());
        l.add(new MultiCruncherExpand());
        l.add(new MultiCruncherDivs());
        return l;
        }
@Override
public ArrayList<Alterator> suggestAlterators(){
        ArrayList<Alterator> l = new ArrayList<Alterator>();
	
	List<String> vars = this.varList();

	for(String var : vars){
		Ex divBy = new MultiEx();
		boolean change = false;
		for(Ex e : exList){
			if(!(e.varContains(var))){
				divBy.multi(e);
				change = true;
				}
			}
		if(change){
		l.add(new AlteratorDiv(divBy));
		}
		}

	return l;
        }

}



