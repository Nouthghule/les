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

	int base = ((PlainEx)e.getSubEx(0)).value;
	int exponent = ((PlainEx)e.getSubEx(1)).value;
	int result = 1;
	
	if(exponent<0){
		return 0;
		}
	
	for(;exponent>0;exponent--){
		result = result*base;	
		}

	e.replaceTarget(0, new PlainEx(result));
	e.replaceTarget(1, new PlainEx(1));
	e.polish();
	return 1;
	}

	}


