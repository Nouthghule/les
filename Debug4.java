
public class Debug4{
	
public static void main(String[] args){
	
	Ex kepler = new MultiEx();
	kepler.multi(new PlainEx(13));
	kepler.multi(new VarEx("x"));
	
	Ex pluto = new PlainEx(26);
	
	Ex overseer = new EqEx(kepler,pluto);
	
	String uno = overseer.report();
		
	Operator a = new AlteratorMulti(new PlainEx(13));
	a.execute(overseer);
	String duo = overseer.report();

	System.out.println("Input : " + uno);
	System.out.println("===================");
	System.out.println("Output : " + duo);

	}

}
