package nouth.les;
import java.util.*;

public class Simplifier{

public Ex simplify(Ex theEx){
	Ex e = theEx.copy();
	String rep = "";
	while(!rep.equals(e.report())){
		rep = e.report();
		if(e instanceof EqEx){
			//This is necessary due to the less than ideal implementation of suggestCrunchers over in EqEx
			simplify(e.getSubEx(0));
			simplify(e.getSubEx(1));
			}
		else{
			ArrayList<Operator> opList = e.suggestCrunchers();
			for(Operator o : opList){
				o.execute(e);
				}
			}
		}
	return e;
	}

}
