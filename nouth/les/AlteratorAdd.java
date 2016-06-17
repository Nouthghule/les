package nouth.les;


public class AlteratorAdd extends Alterator{
	
public AlteratorAdd(Ex argEx){
	super(argEx);
	}

@Override
public int alter(Ex targetEx){
	
	Ex over = targetEx.add(altEx.copy());
	over.polish();
	Cruncher cr = new AddCruncherEqual();
	cr.execute(over);

	return 1;
	}

}
