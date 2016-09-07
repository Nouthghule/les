package nouth.les;

public class Debug{
	
public static void main(String[] args){
	TexParser tp = new TexParser();
	Ex a = tp.parse(args[0]);
	SolutionMaker sm = new SolutionMaker();
	sm.solve(new State(a), "x");
	}

}
