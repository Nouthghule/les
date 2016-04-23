public class Debug4{
	
public static void main(String[] args){
	String seed = "(((2)+(((a)+(b))*((a)+(c))))=(((g)+(h))*((g)+(r))))";
	TextParser tp = new TextParser();
	Ex result = tp.parse(seed);
	String bc = result.report();
	Cruncher cr = new MultiCruncherExpand();
	cr.execute(result);
	System.out.println(bc);
	System.out.println("...");
	System.out.println(result.report());
	}

}
