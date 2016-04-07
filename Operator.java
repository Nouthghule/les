import java.util.*;

public abstract class Operator{

public int execute(Ex targetEx){
ArrayList<Ex> subExList = targetEx.getSubExList();
int i = 0;
for(Ex e : subExList){
	i += executeShallow(e);
	}
i += executeShallow(targetEx);
return i;
};

public abstract int executeShallow(Ex targetEx);
	

}
