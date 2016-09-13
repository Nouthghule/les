package nouth.les;
import java.lang.Math.*;

public class PowerCruncherProcessRaise extends Cruncher{
	
public int crunch(Ex e){
	if(!(e instanceof PowerEx)){
		return 0;
		}
	
	for(Ex sub : e.getSubExList()){
		if(!(sub instanceof PlainEx)){
			return 0;
			}
		}
	//now we know that both exponent and base are integers

	double base = (double)((PlainEx)e.getSubEx(0)).value;
	double exponent = (double)((PlainEx)e.getSubEx(1)).value;

	double result = Math.pow(base, exponent);

	if ((result == Math.floor(result)) && !Double.isInfinite(result)) {
		int rlt = (int) result;
		e.replaceTarget(0, new PlainEx(rlt));
		e.replaceTarget(1, new PlainEx(1));
		e.polish();
		return 1;
		}
	else{
		return 0;
		}

	}

}
