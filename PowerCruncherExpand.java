

public class PowerCruncherExpand extends Cruncher{
	
public int crunch(Ex target){
	if(!(target instanceof PowerEx)){
		return 0;
		}
	if(!(target.getSubEx(1) instanceof PlainEx)){
		return 0;
		}
	PlainEx powerBearer = (PlainEx)target.getSubEx(1);
	int power = powerBearer.value;
	
	if(power == 0){
		//This might be controversial, but I'll say that zero to the power of zero is one.
		target.replaceSelf(new PlainEx(1));
		return 1;
		}

	if(power < 0){
		target.flip();
		power = power*(-1);
		}
	
	Ex result = new MultiEx();
	Ex base = target.getSubEx(0);
	while(power>0){
		result.multi(base.copy());
		power --;
		}

	target.replaceSelf(result);
	return 1;
	}	

}
