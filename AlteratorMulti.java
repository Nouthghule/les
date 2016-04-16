

public class AlteratorMulti extends Alterator{
	
public AlteratorMulti(Ex argEx){
	super(argEx);
	}

@Override
public int alter(Ex targetEx){
	Ex over = targetEx.multi(altEx.copy());
	over.polish();
	Cruncher cr = new MultiCruncherPlain();
	cr.execute(over);
	cr = new DivCruncherSimplify();
	cr.execute(over);

	return 1;
}

}
