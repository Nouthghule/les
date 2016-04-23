import java.util.*;
public class Debug3{

public static void main(String[] args){

Ex left = new DivEx();
Ex subLeft = new AddEx();
Ex subSubLeft = new MultiEx();
Ex subSubSubLeft = new AddEx();
subSubSubLeft.add(new PlainEx(10));
subSubSubLeft.add(new PlainEx(10));
subSubLeft.multi(subSubSubLeft);
subSubLeft.multi(new PlainEx(3));
subLeft.add(subSubLeft);
subLeft.add(new PlainEx(5));
left.multi(subLeft);
left.div(new PlainEx(12));

Ex right = new MultiEx();
Ex subRight = new AddEx();
subRight.add(new PlainEx(8));
subRight.add(new PlainEx(16));
right.multi(subRight);
right.multi(new PlainEx(3));

EqEx equestria = new EqEx(left,right);

System.out.println(equestria.report());
Cruncher cr = new MultiCruncherExpand();
cr.execute(equestria);

String bc = equestria.report();
System.out.println(equestria.subExTotal());
System.out.println(bc);

System.out.println("This is what it started as :");
System.out.println(bc);
System.out.println("==And this is what we've thought of==");
}
}
