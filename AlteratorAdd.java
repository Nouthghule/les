

public class AlteratorAdd extends Alterator{
	
public AlteratorAdd(Ex argEx){
	super(argEx);
	}

@Override
public int alter(Ex targetEx){
	
	AddEx afterAddition = targetEx.add(altEx);
	
	AddCruncherEqual crunchy = new AddCruncherEqual();
	int val;
	val = crunchy.crunch(afterAddition);

	if(val>-1){
		return (1+val);
		}
	else{
		return 1;
		}
	}

}
