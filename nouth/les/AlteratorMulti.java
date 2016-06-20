package nouth.les;


public class AlteratorMulti extends Alterator{
	
public AlteratorMulti(Ex argEx){
	super(argEx);
	}

@Override
public int alter(Ex targetEx){
	Ex over = targetEx.multi(altEx.copy());
	System.out.println("AM 1/4: " + over.report()); 
	over.polish();
	System.out.println("AM 1/4: " + over.report()); 
	Cruncher cr = new MultiCruncherPlain();
	cr.execute(over);
	System.out.println("AM 1/4: " + over.report()); 
//	cr = new DivCruncherSimplify();
//	cr.execute(over);
	System.out.println("AM 1/4: " + over.report()); 

	return 1;
}

}
