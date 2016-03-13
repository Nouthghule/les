import java.util.*;

public class Debug{

public static void main(String[] args){
	Ex theBoss = new BossEx();
//	theBoss.add(13);
//	theBoss.add(-3);
//	Ex lilEx = PlainEx.create(7);
//	theBoss.add(lilEx);
	Ex ex1 = new PlainEx(6);
	Ex ex2 = new PlainEx(60);
//	Ex ex2 = new VarEx("c");
//	Ex ex3 = new VarEx("b").multi(new PlainEx(42));
/*	Ex pipka = new MultiEx();
	Ex evilEx = new MultiEx();
	evilEx.multi(new PlainEx(40));
	evilEx.multi(new VarEx("z"));
	
	pipka.multi(new PlainEx(20));
	pipka.multi(new PlainEx(24));
	pipka.multi(evilEx);
	pipka.multi(new PlainEx(24));
	pipka.multi(new PlainEx(24));
	
	theBoss.add(pipka);
*/	theBoss.add(ex1);
	theBoss.add(ex2);
	//theBoss.add(ex3);
	theBoss.sort();
	System.out.print(theBoss.report());
	
	Ex dolly = theBoss.copy();
	System.out.print("Okay now, copied the expression.\n=====================\n");
	System.out.print(dolly.report());
	}


}


