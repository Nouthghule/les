
public class Debug3{
	
public static void main(String[] args){
	Ex overseer = new AddEx();
	Ex nest = new AddEx();
	overseer.add(nest);
	Ex kepler = new MultiEx();
	nest.add(kepler);

	kepler.multi(new PlainEx(20));
	kepler.multi(new VarEx("x"));

	Ex mae = new MultiEx();
	nest.add(mae);
	mae.multi(new PlainEx(2));
	mae.multi(new VarEx("x"));
	mae.multi(new VarEx("x"));
	
	Ex pluto = kepler.copy();
	nest.add(pluto);
	String oldie = nest.report();
	Cruncher crunchy = new AddCruncherFactor();
	crunchy.crunch(nest);

	System.out.println(oldie);
	System.out.println("========================");
	System.out.println(overseer.report());
	

	}

}
