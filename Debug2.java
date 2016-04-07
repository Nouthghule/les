
public class Debug2{

public static void main(String[] args){

Ex nest = new AddEx();

Ex subNest = new MultiEx();
nest.add(subNest);


String bc = "before crunch : " + nest.report();
Cruncher cr = new MultiCruncherExpand();
cr. crunch(subNest);

System.out.println(bc);
System.out.println("==ééé==");
System.out.println(nest.report());


}
}
