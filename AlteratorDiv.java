

public class AlteratorDiv extends Alterator{
	
public AlteratorDiv(Ex argEx){
	super(argEx);
	}

@Override
public int alter(Ex targetEx){
	
	targetEx.div(altEx);
	
	return 1;
	}

}
