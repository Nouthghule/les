
public class Debug4{
	
public static void main(String[] args){
	Ex overseer = new AddEx();
	Ex nest = new MultiEx();
	overseer.add(nest);
	Ex kepler = new DivEx();

	kepler.div(new PlainEx(20));
	Ex gutenberg = kepler.copy();
	Ex zimmerman = kepler.copy();
	gutenberg.div(new VarEx("bible, biatch"));
	gutenberg.multi(new PlainEx(2));
	kepler.multi(new VarEx("k"));
	zimmerman.multi(new VarEx("z"));
	nest.multi(kepler);
	nest.multi(gutenberg);
	nest.multi(zimmerman);
	
	String uno = nest.report();
	
	Cruncher crunchy = new MultiCruncherDivs();
	System.out.println(crunchy.crunch(nest));
	String duo = nest.report();

	System.out.println("Input : " + uno);
	System.out.println("===================");
	System.out.println("Output : " + duo);

	}

}
