import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.nio.charset.StandardCharsets;
public class les{
	
	public static void main(String[] args){
		if(args.length == 0){
			System.out.println("Please specify target file.");
			return;
			}

		String path = System.getProperty("user.dir");
		path += "/" + args[0];
			
		File f = new File(path);
		if(!f.exists()||f.isDirectory()){
			System.out.println("Target is either a directory or does not exist.");
			return;
			}
			String content;
		try{
			content = new String(Files.readAllBytes(Paths.get(path)),StandardCharsets.UTF_8);
			}	
		catch(Exception e){
			e.printStackTrace();
			return;
			}

		Pattern p = Pattern.compile("%les ([^;]+);(([^;]);)?");
		
		Matcher m = p.matcher(content);
		
		Outputter o = new Outputter();

		while(m.find()){
			String input = m.group(1);
			String var;
			try{
				var = m.group(2);
				}
			catch(Exception e){
				var = "x";
				}
			
			String output = o.getOutput(input, var);
			System.out.println("LES " +output);
			}
		}

	}
