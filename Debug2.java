
public class Debug2{

public static void main(String[] args){

Ex nest = new AddEx();

Ex subNest = new MultiEx();
nest.add(subNest);
subNest.multi(new PlainEx(3));
Ex quido = new AddEx();
quido.add(new VarEx("a"));
quido.add(new VarEx("b"));
subNest.multi(quido);
Ex charlie = new AddEx();
charlie.add(new VarEx("b"));
charlie.add(new VarEx("c"));
subNest.multi(charlie);
Ex fido = new AddEx();
fido.add(new VarEx("g"));
fido.add(new VarEx("y"));
subNest.multi(fido);
String bc = "before crunch : " + nest.report();
Cruncher cr = new MultiCruncherExpand();
cr. crunch(subNest);

System.out.println(bc);
System.out.println("==ééé==");
System.out.println(nest.report());


}
}
