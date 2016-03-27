
public class Debug3{
	
public static void main(String[] args){
	Ex nest = new AddEx();
	Ex kepler = new DivEx();
	nest.add(kepler);
	System.out.println(nest.report());

	kepler.multi(new PlainEx(20));
	System.out.println(nest.report());
	kepler.div(new VarEx("k"));
	System.out.println(nest.report());


	}

}
