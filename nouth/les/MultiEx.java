package nouth.les;
import java.util.*;

public class MultiEx extends Ex{

////////////////////////////////////////////////////////////////////
public MultiEx(){
	reportSeparator = '*';
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
public boolean requireBrackets(Ex e){
	if(e instanceof AddEx){
		return true;
		}
	if((e instanceof PlainEx)&&(((PlainEx)e).value < 0)){
		return true;
		}
	return false;
	}


@Override
public void unwrap(){
        boolean isNull = false;
	if(this.size()==1){
		this.replaceSelf(getSubEx(0));
		}
	else{
		ArrayList<Ex> submerged = new ArrayList<Ex>();
		int minuses = 0;
		ListIterator<Ex> iter = exList.listIterator();
		while (iter.hasNext()){
			Ex e = iter.next();
			if(e instanceof MultiEx){
				submerged.add(e.copy());
				iter.remove();
				updatePoses();
			//	System.out.println("MultiEx: submerged " + e.copy() + ", I'm now " + this.report());
				}
			}


		for(Ex s : submerged){
			ArrayList<Ex> submarines = s.getSubExList();
			for(Ex sub : submarines){
				this.multi(sub);
				}
			}


		iter = exList.listIterator();
		while(iter.hasNext()&&(exList.size()>1)){
				Ex sub = iter.next();
				if(sub instanceof PlainEx){
					PlainEx rlSub = (PlainEx) sub;
					if(rlSub.value==1){
						iter.remove();	
						}
					else if(rlSub.value==-1){
						iter.remove();
						minuses++;
						}
					else if(rlSub.value==0){
						isNull = true;
						break;
						}
					}
			}
		
		if(!(minuses%2==0)){
			//Do this if an uneven number of -1s has been removed.
			Ex p = new PlainEx(-1);
			exList.add(p);
			p.master = this;
			p.posInMaster = exList.size()-1;
			}

		if(this.size()==1){
			this.replaceSelf(getSubEx(0));
			}
		}
	if(isNull){
		this.replaceSelf(new PlainEx(0));	
		}
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
        l.add(new MultiCruncherNumerator());
        l.add(new MultiCruncherPowerise());
        return l;
        }
@Override
public ArrayList<Alterator> suggestAlterators(){
        ArrayList<Alterator> l = new ArrayList<Alterator>();
	System.out.println("MultiEx suggestAlts : " + this.report() + " suggesting now.");
	List<String> vars = this.varList();

	for(String var : vars){
		Ex overseer = new AddEx();
		Ex divBy = new MultiEx();
		overseer.add(divBy);

		boolean change = false;
		for(Ex e : exList){
			System.out.println("ME sA checking " + e.report());
			if(!(e.varContains(var))){
				divBy.multi(e);
				change = true;
				System.out.println("ME sA multied by " + e.report() + " into " + divBy.report());
				}
			}
		if(change){
		overseer.polish();
		divBy = overseer.getSubEx(0);
		l.add(new AlteratorDiv(divBy));
		}
		}

	return l;
        }

public String reportForTex(){
	String s = "";
	int i = 0;
	for(Ex e : exList){
		if(!e.silent){
			if((i!=0)){
				s+= "\\\\\\cdot ";
				}
			s += e.reportForTex();
			i++;
		}
		}
	return s;
	}

}



