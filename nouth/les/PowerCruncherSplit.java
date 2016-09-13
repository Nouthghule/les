package nouth.les;
import java.util.*;

public abstract class PowerCruncherSplit extends Cruncher{

public boolean applicable(Ex e){
	if(!(e instanceof PowerEx)){
		return false;
		}
	
	if(e.getSubEx(1) instanceof DivEx){
		if((e.getSubEx(1).getSubEx(0) instanceof PlainEx)&&(((PlainEx)e.getSubEx(1).getSubEx(0)).value==1)){
			return false;
			}
		return true;
		}

	return false;

	}

public ArrayList<Ex> getExponents(Ex e){
	return getExponentsNum(e);
	//returns arraylist ordered for use by PCSplitNum. For PCSplit Den, this is overridden to reverse the order.
	}

public ArrayList<Ex> getExponentsNum(Ex e){
	Ex divido = e.getSubEx(1);
	ArrayList<Ex> arr = new ArrayList<Ex>();
	arr.add(divido.getSubEx(0).copy());
	arr.add((new PlainEx(1)).div(divido.getSubEx(1).copy()));
	return arr;
	}

public int crunch(Ex e){
	if(!applicable(e)){
		return 0;
		}
	
	Ex base = e.getSubEx(0).copy();
	
	ArrayList<Ex> exps = getExponents(e);

	e.replaceTarget(0, base.toPower(exps.get(0)));
	e.replaceTarget(1, exps.get(1));
	
	return 1;
	}

}
