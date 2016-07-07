package nouth.les;


public class AlteratorDiv extends Alterator{
	
public AlteratorDiv(Ex argEx){
	super(argEx);
	}

@Override
public int alter(Ex targetEx){
	
	System.out.println("AD pre" + targetEx.report());
	Ex over = targetEx.div(altEx.copy());
	System.out.println("AD prep" + targetEx.report());
	over.polish();
	System.out.println("AD presimp" + over.report());
	Cruncher cr = new DivCruncherSimplifyNew();
	cr.execute(over);
	System.out.println("AD post" + over.report());

	return 1;
	}

}
