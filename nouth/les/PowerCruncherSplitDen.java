package nouth.les;
import java.util.*;

public class PowerCruncherSplitDen extends PowerCruncherSplit{

@Override
public ArrayList<Ex> getExponents(Ex e){
	ArrayList<Ex> ret = new ArrayList<Ex>();
	ArrayList<Ex> gotten = getExponentsNum(e);
	ret.add(gotten.get(1));
	ret.add(gotten.get(0));
	return ret;
	}
	}
