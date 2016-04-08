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
	unwrapMe();
	Iterator<Ex> i = exList.iterator();
	Ex checkee;
	while(this.size()>1){
		if(i.hasNext()){
			checkee =i.next();
			if(checkee instanceof PlainEx){
				if(((PlainEx)checkee).value==1){
					i.remove();
					this.updatePoses();
					}
				else if(((PlainEx)checkee).value==0){
					exList.clear();
					this.multi(checkee);
					break;
					}
				}
			}
		else{
			break;
			}
		}
	unwrapMe();
        }

private void unwrapMe(){
              	System.out.println("{{{{{{{{{{{{unwrapMe multiEx : unwrapping " + this.report());
        if(this.size()==1){
		this.replaceSelf(this.getSubEx(0));
                }
        else{
                ArrayList<Ex> toBeAdded = new ArrayList<Ex>();
                Iterator iter = exList.iterator();
                while(iter.hasNext()){
                        Ex currEx = (Ex) iter.next();
                        if(currEx instanceof MultiEx){
                                iter.remove();
                                int i;
                                for(i=0;i<currEx.size();i++){
                                        toBeAdded.add(currEx.getSubEx(i));
                                        }
                                }
                        }
                this.multi(toBeAdded);
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



