import java.util.*;
public class MultiCruncherNumerator extends Cruncher{
	
@Override
public int crunch(Ex targetEx){
	
	if(!(targetEx instanceof MultiEx)){
		return 0;
		}

	int max = targetEx.size();
	int i;
	Ex luckyDiv = null;
	ArrayList<Ex> rest = new ArrayList<Ex>();

	for(i=0; i<max;i++){
		Ex yakko = targetEx.getSubEx(i);
		if(yakko instanceof DivEx){
			yakko.replaceSelf(new PlainEx(1));
			luckyDiv = yakko;
			}
		else{
			rest.add(yakko);
			}
		}
	
	if(luckyDiv == null){
		return 0;
		}
	
	for(Ex e : rest){
		e.replaceSelf(new PlainEx(1));
		}
	

	Cruncher crunchy = new MultiCruncherPlain(); //get rid of 1s
	crunchy.crunch(targetEx);

	luckyDiv.multi(rest);
	targetEx.multi(luckyDiv);
	
	return 1;
	
	
	
	}

}
