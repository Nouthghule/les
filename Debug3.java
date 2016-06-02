import java.util.*;
public class Debug3{

public static void main(String[] args){
	TextParser tp = new TextParser();
	
	Ex e = tp.parse(args[0]);
	String in = e.report();

	Ex overseer = new AddEx();
	overseer.add(e);

	Cruncher c;
	c = new PowerCruncherExpand();

	int i = c.execute(e);

	String out = overseer.getSubEx(0).report();
	
	overseer.getSubEx(0).polish();

	String after = overseer.getSubEx(0).report();


	System.out.println("-------");
	System.out.println(i);
	System.out.println(in);
	System.out.println(out);
	System.out.println(after);

		

	}
}
