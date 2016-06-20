package nouth.les;
import java.util.*;
public class TexParser{
	
Map<String, Integer> ops;
Stack<String> operators;
Stack<String> members;
String lastOp;
boolean negateNext = false;
boolean negateable = false;


public TexParser(){
	ops = new HashMap<String, Integer>();
	ops.put("^",new Integer(4));
	ops.put("*",new Integer(3));
	ops.put("/",new Integer(3));
	ops.put("+",new Integer(2));
	ops.put("-",new Integer(2));
	ops.put("(",new Integer(1));
	ops.put(")",new Integer(1));
	ops.put("=",new Integer(0));
	
	lastOp = "";
	}


public Ex parse(String input){
	lastOp="";
	String s = texToNorm(input);
	s = normToRPN(s);
	System.out.println(s);
	s = RPNToBrackets(s);
	System.out.println("Parsing ex from " + s);
	TextParser tp = new TextParser();
	Ex e = tp.parse(s);
	System.out.println("Parsed ex as : " + e.report());
	return e;
	}

public String texToNorm(String s){
//(2+(3)s+s^2) x(s)+(2+s)y(s) =&(4+2)+(3+2)s+(3)s^2+s^3
		System.out.println(s);
		s = s.replaceAll("\\\\frac\\{([^}]*)\\}\\{([^}]*)\\}","($1)/($2)");
		System.out.println(s);
		s = s.replaceAll("\\\\sqrt\\{([^}]*)\\}","($1)^(1/2)");
		s = s.replaceAll("\\\\sqrt\\[([^]]*)\\]\\{([^}]*)\\}","(($2)^(1/$1))");
		s = s.replaceAll("&","");
		s = "( " + s + " )";
		String before = "";
		while(!s.equals(before)){
			before = s;
			s = s.replaceAll("([^\\s])([-+=\\(\\)/*^])","$1 $2"); //add spaces around operators where missing
			s = s.replaceAll("([-+=\\(\\)/*^])([^\\s])","$1 $2");
		}
		before = "";
		while(!s.equals(before)){
			before = s;
			s = s.replaceAll("([^=+\\-\\(/*^])\\s([^\\-+\\)=/*^])","$1 * $2");
			}
		before = "";
			s = s.replaceAll("([0-9]+)([^0-9^+\\-\\(\\)/* ])","$1 * $2");
			System.out.println("tparser " + s);
			s = s.replaceAll("\\\\cdot","\\*");
			System.out.println("tparser " + s);
		//s = s.replaceAll("([^\\-+=\\)/*^])\\s([^\\-+=\\)\\(/*^])","$1 * $2");
	return s;
	}

public String normToRPN(String s){

	operators = new Stack<String>();
	members = new Stack<String>();
	
	s = s + " ";
	System.out.println("ready S : " + s );

	int i;
	int l = s.length();
	
	String str = "";
	String read;
	for(i=0;i<l;i++){
		read = Character.toString(s.charAt(i));
		
		if((read.equals(" "))&&(!str.equals(""))){
			if(ops.containsKey(str)){
				if(str.equals("(")){
					negateable = true;
					}
				if(str.equals(")")){
					negateable = false;
					}
				operators.push(str);
				System.out.println("operators push " + str);
				opAdded();
				}
			else{
				negateable = false;
				if(negateNext){
					negateNext = false;
					str = "-"+str;
					}
				members.add(str);
				System.out.println("members push " + str);
				}
			str = "";
			}
		else{
			str += read;
			}

		}
	while(operators.size()>0){
		members.add(operators.pop());
		}
	
	String res = "";
	Stack<String> rel = new Stack<String>();
	while(members.size()>0){
		rel.push(members.pop());
		}
	while(rel.size()>0){
		res += rel.pop();
		}
	return res;

	}

public void doPop(){
	System.out.println("operators pop : " + operators.pop());
	}

public void opAdded(){
	if(lastOp.equals("")){
		lastOp = operators.peek();
		return;
		}
	String newOp;
	newOp = operators.peek();

	if(negateable&&lastOp.equals("(")&&newOp.equals("-")){
		negateNext = true;
		doPop();
		return;
		}

	if(newOp.equals(")")){
		doPop();
		while(!operators.peek().equals("(")){
			executeMember(operators.pop());
			System.out.println("operators pop into execute");
			}
		doPop();
		try{
			lastOp = operators.peek();
			}
		catch(Exception e){
			lastOp = "";
			}
		return;
		}
	System.out.println("new op " +newOp+ " vs last op " +lastOp);
	if(ops.get(newOp).intValue()<=ops.get(lastOp).intValue()){
		String hold = operators.pop();
		System.out.println("operators pop " + hold);
		String next = operators.pop();
		System.out.println("operators pop " + next);
		if(next.equals("(")||hold.equals("(")){
			operators.add(next);
			operators.add(hold);
			lastOp = operators.peek();
			System.out.println("operators return back :  " +next + " " + hold);
			return;
			}
		executeMember(next);
		operators.add(hold);
		System.out.println("operators undo pop before the last pop.");
		lastOp = operators.peek();
		opAdded();
		}
	lastOp = operators.peek();
	}

public void executeMember(String s){
	if(members.size()<2){
		return;
		}
	String b = members.pop();
	String a = members.pop();
	String res = a +" "+ b +" "+ s;
	members.add(res);
	}

public String RPNToBrackets(String s){
	int i = 0;
	s = " " + s + " ";
	int g = 0;
	while(i<s.length()){
		String read = Character.toString(s.charAt(i));
		if(read.equals(" ")){
			//do nothing
			}
		else if(read.equals("-")&&s.charAt(i+1)!=' '){
			//do nothing cause it's an unary operator
			}
		else if(ops.containsKey(read)){
			//no need to worry about parentheses, they don't appear in RPN.

			int a = i-1;
			
			
			//found first member before.
			
			s = s.substring(0,i-1) + "))" + s.substring(i+1);
			
			while(s.charAt(a)!=' '){
				a--;
				}
			//found blank before preceding member
			int b = a;
			a--;
			while(s.charAt(a)!=' '){
				a--;
				}
			s = s.substring(0,b) +")"+ read+"(" + s.substring(b+1);

			//found blank before pre preceding member
			s = s.substring(0,a) +" ((" + s.substring(a+1);
			System.out.println(s);

			i+=4;
			}
			i++;
		}
		return s;
		}
	

	}





