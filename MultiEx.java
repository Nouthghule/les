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

public void unwrap(){
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

}
