import java.util.*;

public class DivCruncherSimplifyNew extends Cruncher{

public int crunch(Ex targetEx){
	
	if(!(targetEx instanceof DivEx)){
		return 0;
		}

	Ex proxy = targetEx.copy();
	
	Cruncher cr = new PowerCruncherExpand();
	MultiCruncherPowerise pw = new MultiCruncherPowerise();
	pw.curbTriv = false;
	
	Ex readyDiv = new DivEx();

	int i=0;
	while(i<=1){
		Ex pro = proxy.getSubEx(i);
		readyDiv.replaceTarget(i, pro);
		
		if(pro instanceof MultiEx){
			ArrayList<Ex> elist = pro.getSubExList();
			for(Ex e : elist){
				cr.crunch(e);
				}
			pro.polish();
			pw.execute(pro);
			
			}
		else{	
			Ex m = new MultiEx();
			pro.replaceSelf(m);
			m.multi(pro);
			pro.toPower(new PlainEx(1));
			}

		i++;
		}
	//System.out.println("DCSN " + readyDiv.report());
	//ASSUMPTION : at this point there's a maximum of tow exes of each base (one in numerator, one in denominator).
	//Also, all Exes under readyDiv are PowerExes.

	ArrayList<Ex> num = readyDiv.getSubEx(0).getSubExList();
	ArrayList<Ex> den = readyDiv.getSubEx(1).getSubExList();
	boolean done = false;
	Cruncher addy = new AddCruncherPlain();

	for(Ex numEx : num){
		String baseRep = numEx.getSubEx(0).report();
		
		for(Ex denEx : den){
			//TODO add condition to breakout and stop pointless loops once exhausted
			if(denEx.getSubEx(0).report().equals(baseRep)){
				int c = 0;
				Ex ce;
				if((denEx.getSubEx(1) instanceof PlainEx)&&(numEx.getSubEx(1) instanceof PlainEx)){
					int a = ((PlainEx) denEx.getSubEx(1)).value;
					int b = ((PlainEx) numEx.getSubEx(1)).value;
					if(a>b){
						c = -b;
						}
					else{
						c= -a;
						}
					ce = new PlainEx(c);
					}
				else if(numEx.getSubEx(1) instanceof PlainEx){
					c = -((PlainEx)numEx.getSubEx(1)).value;
					ce = new PlainEx(c);
					}
				else if(denEx.getSubEx(1) instanceof PlainEx){
					c = -((PlainEx)denEx.getSubEx(1)).value;
					ce = new PlainEx(c);
					}
				else{
					c = -1;	
					ce = new MultiEx();
					ce.multi(denEx.getSubEx(1).copy());
					ce.multi(new PlainEx(-1));
					}
				if(c!=0){
					denEx.getSubEx(1).add(ce);
					numEx.getSubEx(1).add(ce);
					addy.crunch(denEx.getSubEx(1));
					addy.crunch(numEx.getSubEx(1));
					done = true;
					}
				}
			}
		
		
		}
	
	targetEx.wipe();
	targetEx.multi(readyDiv.getSubEx(0));
	targetEx.div(readyDiv.getSubEx(1));
	return 1;
	}


}
