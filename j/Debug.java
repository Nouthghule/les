import java.util.*;

public class Debug{

public static void main(String[] args){
	Ex theBoss = new BossEx();
//	theBoss.add(13);
//	theBoss.add(-3);
//	Ex lilEx = PlainEx.create(7);
//	theBoss.add(lilEx);
	Ex ex1 = PlainEx.create(6);
	Ex ex2 = ArgEx.create("c");
	Ex ex3 = ArgEx.create("b").multi(PlainEx.create(42));
	Ex pipka = MultiEx.create();
	Ex evilEx = MultiEx.create();
	evilEx.multi(PlainEx.create(40));
	evilEx.multi(ArgEx.create("z"));
	
	pipka.multi(PlainEx.create(20));
	pipka.multi(PlainEx.create(24));
	pipka.multi(evilEx);
	pipka.multi(PlainEx.create(24));
	pipka.multi(PlainEx.create(24));
	
	theBoss.add(pipka);
	theBoss.add(ex1);
	theBoss.add(ex2);
	theBoss.add(ex3);
	theBoss.sort();
	System.out.print(theBoss.report());
	}


}


