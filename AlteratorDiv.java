

public class AlteratorDiv extends Alterator{
	
public AlteratorDiv(Ex argEx){
	super(argEx);
	}

@Override
public int alter(Ex targetEx){
	
	Ex over = targetEx.div(altEx.copy());
	over.polish();
	Cruncher cr = new DivCruncherSimplify();
	cr.execute(over);

	return 1;
	}

}
