package nouth.les;
import java.lang.Math;
import java.math.*;

public class PowerCruncherProcessRoot extends Cruncher{

public int crunch(Ex e){
	
	if(!(e instanceof PowerEx)){
		return 0;
		}
	
	if(!((e.getSubEx(0) instanceof PlainEx)&&(((PlainEx)e.getSubEx(0)).value>=0))){
		return 0;
		}
	
	Ex exponent = e.getSubEx(1);

	if(!(exponent instanceof DivEx)){
		return 0;
		}
	
	if(!(((exponent.getSubEx(0) instanceof PlainEx)&&(((PlainEx)exponent.getSubEx(0)).value==1))&&(exponent.getSubEx(1) instanceof PlainEx))){
		return 0;
		}
	
	double base = ((PlainEx)e.getSubEx(0)).value;
	double deno = ((PlainEx)e.getSubEx(1).getSubEx(1)).value;
	double exp = (1.0)/deno;

	double result = Math.pow(base, exp);
	
	String s = (new Double(result)).toString();
	System.out.println(" s is " + s);

	String[] st = s.split("\\.");
	
	char[] arr = st[1].toCharArray();
	char c = arr[0];
	int i;
	int max = 5;
	if(arr.length<5){
		max = arr.length-1;
		}
	
	for(i=0;i<max;i++){
		char ch = arr[i];
		if(ch!=c){
			return 0;  //finding root resulted in non-whole number
			}
		}
	
	int realResult = Integer.parseInt(st[0]);

	if(c=='9'){
		realResult ++;
		}
	else if(c=='0'){
		//do nothing;
		}
	else{
		//it is not a whole number, ergo screw this.
		return 0;
		}
	
	e.replaceTarget(0, new PlainEx(realResult));
	e.replaceTarget(1, new PlainEx(1));
	return 1;

	}
}

