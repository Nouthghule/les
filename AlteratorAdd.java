

public class AlteratorAdd extends Alterator{
	
public AlteratorAdd(Ex argEx){
	super(argEx);
	}

@Override
public int alter(Ex targetEx){
	
	AddEx afterAddition = targetEx.add(altEx);
	System.out.println("@@@@@@@ alterator gonna equalcrunch " + afterAddition.report());	
//	AddCruncherEqual crunchy = new AddCruncherEqual();
	int val = 1;
//	val = crunchy.crunch(afterAddition);
	if(val>-1){
		return (1+val);
		}
	else{
		return 1;
		}
	}

}
