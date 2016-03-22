

public class AlteratorMulti extends Alterator{
	
public AlteratorMulti(Ex argEx){
	super(argEx);
	}

@Override
public int alter(Ex targetEx){
	targetEx.multi(altEx);
	return 1;
}

}
