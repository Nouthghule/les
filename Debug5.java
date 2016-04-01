
public class Debug5{
	
public static void main(String[] args){
	Ex background = new AddEx();
	Ex nest = new AddEx();
	background.add(nest);
	Ex kepler = new DivEx();
	nest.add(kepler);
	System.out.println(nest.report());

	kepler.multi(new PlainEx(23));
	System.out.println(nest.report());
	kepler.div(new PlainEx(64));
	
	Ex galileo = new DivEx();
	galileo.multi(new VarEx("g"));
	galileo.div(new PlainEx(16));

	Ex planc = new VarEx("p");

	nest.add(planc);
	nest.add(galileo);

	String uno = background.report();
	
	Cruncher crunchy = new AddCruncherSingleDenominator();
	System.out.println(crunchy.crunch(nest));
	String duo = background.report();

	System.out.println("Input : " + uno);
	System.out.println("=======Simplifying fraction============");
	System.out.println("Output : " + duo);

	}

}
