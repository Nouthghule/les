package nouth.les;

public class PowerCruncherConsolide extends Cruncher{
	
public int crunch(Ex e){
	
	boolean applicable = false;

	if((e instanceof PowerEx)&&(e.getSubEx(0) instanceof PowerEx)){
		applicable = true;
		}
	
	if(!applicable){
		return 0;
		}

	Ex newBase = e.getSubEx(0).getSubEx(0);
	Ex newPower = e.getSubEx(0).getSubEx(1);
	Ex multiBy = e.getSubEx(1);

	e.replaceTarget(0, newBase);
	e.replaceTarget(1, newPower.multi(multiBy));
	e.polish();

	Cruncher cr = new MultiCruncherPlain();

	int ret = 1;
	ret += cr.crunch(e.getSubEx(1));
	

	return ret;
	
	}
}

