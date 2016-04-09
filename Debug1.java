
public class Debug1{
	
public static void main(String[] args){
	
	Ex j = new MultiEx();
	j.multi(new PlainEx(1));
	j.multi(new VarEx("s"));
	j.multi(new VarEx("g"));
	System.out.println(j.report());
	j.polish();
	System.out.println(j.report());

	}
	
}
