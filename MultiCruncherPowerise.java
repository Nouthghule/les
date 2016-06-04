import java.util.*;
public class MultiCruncherPowerise extends Cruncher{	
	
	public boolean curbTriv = true; //Set to false when conversion of x to x^1 is desired
	

public int crunch(Ex targetEx){
	
	if(!(targetEx instanceof MultiEx)){
		return 0;
		}
	
	ArrayList<String> elemStr = new ArrayList<String>();
	ArrayList<Ex> exList = targetEx.getSubExListCopy();
	ArrayList<Ex> elems = new ArrayList<Ex>();

	for(Ex e : exList){
		if(!(elemStr.contains(e.report()))){
			elemStr.add(e.report());
			elems.add(e.copy());
			System.out.println("MCP adding " + e.report());
			}
		}
	
	Ex result = new MultiEx();

	for(Ex e : elems){
		int power = 0;
		String rep = e.report();
		for(Ex joe : exList){
			if(joe.report().equals(rep)){
				power++;
				}
			}
		Ex pw;
		if((power==1)&&(curbTriv)){
			pw = e.copy();
			}
		else{
			pw = new PowerEx(e.copy());
			pw.toPower(new PlainEx(power));
			}
		result.multi(pw);
		}

	System.out.println("MCP res " +result.report());
	targetEx.wipe();
	targetEx.multi(result.getSubExList());
	return 1;
	
	}

}
