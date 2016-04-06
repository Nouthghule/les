
public class Debug5{
	
public static void main(String[] args){
	Ex background = new AddEx();

	background.add(new PlainEx(5));
	background.add(new VarEx("s"));
	
	String uno = background.report();

	Operator o = new AlteratorAdd(new VarEx("s"));
	o.execute(background);
	String duo = background.report();

	System.out.println("Input : " + uno);
	System.out.println("===================");
	System.out.println("Output : " + duo);

	}

}
