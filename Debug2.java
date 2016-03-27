
public class Debug2{

public static void main(String[] args){

Ex nest = new AddEx();

Ex subNest = new AddEx();
Ex altNest = new AddEx();

nest.add(subNest);
nest.add(altNest);

subNest.add(new VarEx("Q"));
subNest.add(new PlainEx(9));
subNest.multi(new PlainEx(3));

AlteratorAdd altA = new AlteratorAdd(new VarEx("Q")); 
AlteratorAdd altB = new AlteratorAdd(new PlainEx(9)); 
altA.execute(altNest);
altB.execute(altNest);
altNest.multi(new PlainEx(3));
System.out.println("sub : " + subNest.report());
System.out.println("alt : " + altNest.report());

System.out.println(nest.report());


}
}
