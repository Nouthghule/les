import java.util.*;

public abstract class Cruncher extends Operator{

/**Names of Cruncher subclasses shall follow this simple pattern :
[ex type]Cruncher[operation description]
ommiting the square brackets, thus resulting in eg MultiCruncherPlain
- a cruncher for MultiEx which multiplies the plainExes in it.
*/

public ArrayList<Ex> workList = new ArrayList<Ex>();

@Override
public int execute(Ex targetEx){
	return crunch(targetEx);
	}

public abstract int crunch(Ex targetEx);
/*This should retunrn:
-a value equal to 0 when not applicable
-a value greater than 0 when it has been successfully executed.
this value should be used to represent the complexity of the task
*/
	
	




}
