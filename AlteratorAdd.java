

public class AlteratorAdd extends Alterator{
	
public AlteratorAdd(Ex argEx){
	super(argEx);
	}

@Override
public int alter(Ex targetEx){
	
	AddEx afterAddition = targetEx.add(altEx);
	System.out.println("@@@@@@@ alterator gonna equalcrunch " + afterAddition.report());	
	afterAddition.polish();
	AddCruncherEqual crunchy = new AddCruncherEqual();
	int val = crunchy.crunch(afterAddition);
	return 1;
	}

}
