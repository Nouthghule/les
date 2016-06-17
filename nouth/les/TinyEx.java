package nouth.les;
import java.util.*;
public abstract class TinyEx extends Ex{

/**A class for the lowest exes that have no subExes (unless something
goes horribly wrong.) */

@Override
public ArrayList<Operator> suggestCrunchers(){
        ArrayList<Operator> l = new ArrayList<Operator>();
        return l;
        }
@Override
public ArrayList<Alterator> suggestAlterators(){
        ArrayList<Alterator> l = new ArrayList<Alterator>();
        return l;
        }

@Override
public int subExTotal(){
	return 1;
	}
}
