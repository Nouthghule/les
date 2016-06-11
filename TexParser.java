import java.util.*;
public class TexParser{
	
Map<String, Integer> ops;
Stack<String> operators;
Stack<String> members;
String lastOp;


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
	String s = texToNorm(input);
	s = normToRPN(s);
	System.out.println(s);
	return new AddEx();
	}

public String texToNorm(String s){
		s = s.replaceAll("\\\\frac\\{([^}]*)\\}\\{([^}]*)\\}","($1)/($2)");
		s = s.replaceAll("\\\\sqrt\\{([^}]*)\\}","($1)^(1/2)");
		s = s.replaceAll("\\\\sqrt\\[([^]]*)\\]\\{([^}]*)\\}","(($2)^(1/$1))");
		s = "( " + s + " )";
		String before = "";
		while(!s.equals(before)){
			before = s;
			s = s.replaceAll("([^\\s])([-+=\\(\\)/*^])","$1 $2"); //add spaces around operators where missing
			s = s.replaceAll("([-+=\\(\\)/*^])([^\\s])","$1 $2");
		}
	return s;
	}

public String normToRPN(String s){

	operators = new Stack<String>();
	members = new Stack<String>();
	
	s = s + " ";

	int i;
	int l = s.length();
	
	String str = "";
	String read;
	for(i=0;i<l;i++){
		read = Character.toString(s.charAt(i));
		
		if((read.equals(" "))&&(!str.equals(""))){
			if(ops.containsKey(str)){
				operators.push(str);
				operators.peek();
				opAdded();
				}
			else{
				members.add(str);
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
	while(members.size()>0){
		res += members.pop();
		}
	return res;

	}

public void opAdded(){
	if(lastOp.equals("")){
		lastOp = operators.peek();
		return;
		}
	String newOp = operators.peek();

	if(newOp.equals(")")){
		operators.pop();
		while(!operators.peek().equals("(")){
			executeMember(operators.pop());
			}
		operators.pop(); //kill the other bracket.
		return;
		}

	if(ops.get(newOp).intValue()<=ops.get(lastOp).intValue()){
		String hold = operators.pop();
		String next = operators.pop();
		if(next.equals("(")){
			operators.add(next);
			operators.add(hold);
			return;
			}
		executeMember(next);
		operators.add(hold);
		}
	
	}

public void executeMember(String s){
	String b = members.pop();
	String a = members.pop();
	String res = a +" "+ b +" "+ s;
	members.add(res);
	}

}



