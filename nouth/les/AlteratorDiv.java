package nouth.les;


public class AlteratorDiv extends Alterator{
	
public AlteratorDiv(Ex argEx){
	super(argEx);
	}

@Override
public int alter(Ex targetEx){
	
	Ex over = targetEx.div(altEx.copy());
	System.out.println("AD polishing " + over.report());
	over.polish();
	System.out.println("AD polished into " + over.report());
//	Cruncher cr = new DivCruncherSimplify();
//	cr.execute(over);

	return 1;
	}

}
