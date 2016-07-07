package nouth.les;
import java.util.*;

public class DivCruncherSimplifyNew extends Cruncher{

public int crunch(Ex targetEx){
	
	if(!(targetEx instanceof DivEx)){
		return 0;
		}
	System.out.println("DCSN in : " + targetEx.report());
	//First things first : deal with PlainExes.

	dealWithPlainEx(targetEx);
	System.out.println("DCSN dealt : " + targetEx.report());
	
	Cruncher cr = new PowerCruncherExpand();
	MultiCruncherPowerise pw = new MultiCruncherPowerise();
	pw.curbTriv = false;
	
	ArrayList<Ex> num=new ArrayList<Ex>();
	ArrayList<Ex> den=new ArrayList<Ex>();
	
	String as = "";

	int i=0;
	while(i<=1){
		Ex pro = targetEx.getSubEx(i).copy();
		ArrayList<Ex> list = new ArrayList<Ex>();
	
		if(pro instanceof MultiEx){
			cr.execute(pro);
			pro.polish();
			ArrayList<Ex> arr = pro.getSubExList();
			pw.crunch(pro);
			System.out.println("DCSN list is subexlist of " + pro.report());
			list = pro.getSubExList(); 
			}
		else{
			Ex holder = new AddEx();
			holder.add(pro);
			pro.toPower(new PlainEx(1));
			pro = holder.getSubEx(0);
			list.add(pro);
			System.out.println("DCSN listadd" + pro.report());

			}
			
		if(i==0){
			num = list;
			}
		else{
			den = list;
			}		
		

		i++;
		}
	//ASSUMPTION : at this point there's a maximum of tow exes of each base (one in numerator, one in denominator).
	//Also, all Exes under readyDivs multis are PowerExes.

	boolean done = false;
	Cruncher addy = new AddCruncherEqual();
	//Cruncher multi = new MultiCruncherPlain();

	for(Ex numEx : num){
//		System.out.println("DCSN new num " + numEx.report());
		String baseRep = numEx.getSubEx(0).report();
		
		for(Ex denEx : den){
			//TODO add condition to breakout and stop pointless loops once exhausted
//			System.out.println("DCSN new den " + denEx.report());
			if(denEx.getSubEx(0).report().equals(baseRep)){
//				System.out.println("DCSN new den matches");
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
//					System.out.println("MCSN adding to " +denEx.getSubEx(1).report() + " in "+denEx.master.report() +"  and "+  numEx.getSubEx(1).report() +"in"+numEx.master.report() +" ex " + ce.report()); 
					System.out.println(denEx.getSubEx(1).add(ce.copy()).report());
					System.out.println(numEx.getSubEx(1).add(ce.copy()).report());
					addy.crunch(denEx.getSubEx(1));
					addy.crunch(numEx.getSubEx(1));
//					denEx.polish();
//					numEx.polish();
					done = true;
					}
				}
			}
		
		
		}
	
	targetEx.wipe();
	targetEx.multi(num);
	targetEx.div(den);
	return 1;
	}

public void dealWithPlainEx(Ex targetEx){
//	System.out.println("DCSN dealing with : " + targetEx.report());
	Ex a = targetEx.getSubEx(0);
	Ex b = targetEx.getSubEx(1);
	if(!(((a instanceof TinyEx)||(a instanceof MultiEx))&&((b instanceof TinyEx)||(b instanceof MultiEx)))){
		return;
		}
	Cruncher c = new MultiCruncherPlain();
	c.crunch(a);
	c.crunch(b);
	targetEx.polish();
	Ex uno;
	Ex duo;
	try{
		uno = targetEx.getSubEx(0).getSubEx(0);
	}
	catch(Exception e){
		uno = targetEx.getSubEx(0);
		}

	try{
		duo = targetEx.getSubEx(1).getSubEx(0);
	}
	catch(Exception e){
		duo = targetEx.getSubEx(1);
		}
	if(!((uno instanceof PlainEx)&&(duo instanceof PlainEx))){
		return;	
		}
	int e = ((PlainEx)uno).value;
	int f = ((PlainEx)duo).value;
	int g = gcd(e,f);
	
	e = e/g;
	f = f/g;

	uno.replaceSelf(new PlainEx(e));
	duo.replaceSelf(new PlainEx(f));

	targetEx.polish();
//	System.out.println("DCSN dealt with it.");
	}

}
