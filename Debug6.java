

public class Debug6{
public static void main(String[] args){

Ex overseer = new AddEx();
Ex fr = new DivEx();
overseer.add(fr);

Ex num = new AddEx();
Ex n1 = new DivEx();
n1.div(new PlainEx(2));
Ex n2 = n1.copy();
n1.multi(new PlainEx(7));
n2.multi(new PlainEx(9));
num.add(n1);
num.add(n2);

Ex den = new AddEx();
Ex d1 = new DivEx();
d1.div(new PlainEx(16));
Ex d2 = d1.copy();
d2.multi(new PlainEx(5));
d1.multi(new PlainEx(15));
den.add(d1);
den.add(d2);

fr.multi(num);
fr.div(den);

System.out.println(overseer.report());
Cruncher cr = new AddCruncherEqual();
cr.crunch(num);
cr.crunch(den);

System.out.println("======");
System.out.println(overseer.report());
fr.polish();
System.out.println(overseer.report());
cr = new DivCruncherEmergeDivs();
cr.crunch(fr);
System.out.println(overseer.report());
}

}
