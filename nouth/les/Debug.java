import java.lang.Math;

public class Debug{
	
public static void main(String[] args){
	int a = Integer.parseInt(args[0]);
	int b = Integer.parseInt(args[1]);
	
	double base = (double) a;
	double g = 1;
	double h = (double) b;

	double exponent = g/h;

	double result = Math.pow(base, exponent);

	System.out.println(result);

	}

}
