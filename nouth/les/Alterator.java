package nouth.les;

public abstract class Alterator extends Operator{

public Ex altEx;

public Alterator(Ex argEx){
	altEx = argEx;
	}
	
@Override
public int execute(Ex targetEx){
	return alter(targetEx);	
	}

public abstract int alter(Ex targetEx);
	


}
