public class Debug4{
	
public static void main(String[] args){
	String seed = "(((((-1)*((10)+(x)))/((2)*(x)))+((3)/(2)))=(0))";
	System.out.println(seed);
	TextParser tp = new TextParser();
	Ex result = tp.parse(seed);
	System.out.println("...");
	System.out.println(result.report());
	}

}
