import java.util.*;

public class EqEx extends Ex{



public EqEx(Ex left, Ex right){
	reportSeparator = "=";
	exList.add(left);
	exList.add(right);
	left.posInMaster = 0;
	right.posInMaster = 1;
	left.master = this;
	right.master = this;

	}
public EqEx(){
	this(1);
	}
public EqEx(int i){
	this(new PlainEx(i),new PlainEx(i));
	}

@Override
public void sort(){} //Can afford to override like this because subExes get sorted anyway in polish.
@Override
public void unwrap(){}

@Override
public Ex copy(){
EqEx copy = new EqEx(getSubEx(0).copy(),getSubEx(1).copy());
return copy;
}

@Override
public void wipe(){
	exList.set(0, new VoidEx());
	exList.set(1, new VoidEx());
	}


/*Following code is rather confusing. Consult Ex/suggest.
Asking equalEx to suggest crunchers instead has it return
alterators of it's children, which are never returned elsewhere.
*/

@Override
public ArrayList<Alterator> suggestAlterators(){
return new ArrayList<Alterator>();	
}

@Override
public ArrayList<Operator> suggestCrunchers(){
ArrayList<Alterator> altList = new ArrayList<Alterator>();

ArrayList<Alterator> a;
boolean in;
for(Ex e : exList){
	a = e.suggestAlterators();
	for(Alterator alt : a){
		in = false;
		for(Alterator alterosaurus : altList ){
			if((alterosaurus.getClass()==alt.getClass())&&(alterosaurus.altEx.report().equals(alt.altEx.report()))){
				in = true;
				}
			}
		if(!in){
			altList.add(alt);
			}
		}
	}
ArrayList<Operator> rlList = new ArrayList<Operator>();
rlList.addAll(altList);
return rlList;
}

@Override
public Ex add(Ex e){
	getSubEx(0).add(e.copy());
	getSubEx(1).add(e.copy());
	return this;
	}


@Override
public Ex multi(Ex e){
	getSubEx(0).multi(e.copy());
	getSubEx(1).multi(e.copy());
	return this;
	}


@Override
public Ex div(Ex e){
	getSubEx(0).div(e.copy());
	getSubEx(1).div(e.copy());
	return this;
	}
}
