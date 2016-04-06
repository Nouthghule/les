import java.util.*;

public class DebugS{
	
public static void main(String[] args){	

Ex overseer = new AddEx();

Ex jack = new AddEx();
overseer.add(jack);
jack.add((new VarEx("j")).multi(new PlainEx(7)));
jack.add((new VarEx("p")).multi(new PlainEx(14)));
jack.add(new PlainEx(49));

String before = overseer.report();

ArrayList<Operator> map = jack.suggest(true);

ArrayList<String> jacksChildren = new ArrayList<String>();

for(Operator o : map){
	System.out.println("incoming : " + o);
	Ex master = new AddEx();
	Ex e = jack.copy();
	master.add(e);
	if(o.execute(e)>0){
		jacksChildren.add(master.report());
		jacksChildren.add("that was "+o );
		}
	}

System.out.println("Before : " + before);
for(String s : jacksChildren){
System.out.println("And what about this ? : " + s);
}

}	

}
