
public class Debug4{
	
public static void main(String[] args){
	Ex nest = new AddEx();
	Ex kepler = new DivEx();
	nest.add(kepler);
	System.out.println(nest.report());

	kepler.multi(new PlainEx(20));
	kepler.multi(new VarEx("Pipka"));
	kepler.multi(new VarEx("Pipka"));
	System.out.println(nest.report());
	kepler.div(new PlainEx(-4));
	kepler.div(new VarEx("Pipka"));
	String uno = nest.report();
	
	Cruncher crunchy = new DivCruncherSimplify();
	System.out.println(crunchy.crunch(kepler));
	String duo = nest.report();

	

	System.out.println("Input : " + uno);
	System.out.println("=====================");
	System.out.println("Output : " + duo);

	}

}
