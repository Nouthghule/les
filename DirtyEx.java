
import java.util.*;
public class DirtyEx extends Ex{

public String value = "";

@Override
public Ex add(Ex e){
	exList.add(e);
	e.master = this;
	e.posInMaster = exList.size() -1;
	return this;
	}

@Override
public void unwrap(){
	if(!value.equals("")){
		if(isNumeric(value)){
			this.replaceSelf(new PlainEx(Integer.parseInt(value)));
			System.out.println("DE replaceself numeric " + value);
			}
		else{
			this.replaceSelf(new VarEx(value));
			System.out.println("DE replaceself " + value);
			}
		return;
		}
	else{
		System.out.println("DE Blank value with rs of " + reportSeparator);
		if((reportSeparator == '$')&&(this.size()==1)){
			this.replaceSelf(this.getSubEx(0));
			System.out.println("DE replacing self with only child!");
			return;
			}
		Ex ne = new VoidEx(); //wonder if this will work.
		switch(reportSeparator){
			case '+' :	ne = new AddEx();
					ne.add(exList);
					break;
			case '*' : 	ne = new MultiEx();
					ne.multi(exList);
					break;
			case '/' : 	ne = new DivEx();
					ne.multi(exList.get(0));
					ne.div(exList.get(1));
					if(exList.size()>2){
						System.out.println("DIRTYEX : Warning, creating binary ex ("+reportSeparator+")out of exList of size " + exList.size());
						}
					break;
			case '=' :	ne = new EqEx(exList.get(0), exList.get(1));
					if(exList.size()>2){
						System.out.println("DIRTYEX : Warning, creating binary ex ("+reportSeparator+") out of exList of size " + exList.size());
						}
					break;
			}
		if(ne instanceof VoidEx){
			System.out.println("DE oops.");
			}
		this.replaceSelf(ne);
		}
	}

@Override
public ArrayList<Alterator> suggestAlterators(){
	return new ArrayList<Alterator>();
	}
@Override
public ArrayList<Operator> suggestCrunchers(){
	return new ArrayList<Operator>();
	}
public boolean isNumeric(String str){
	return str.matches("-?\\d+(\\.\\d+)?");  //taken from CraigTP on swagOverflow
	}
}
