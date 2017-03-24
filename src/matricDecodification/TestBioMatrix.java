package matricDecodification;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestBioMatrix{
	
	public static void main(String args[]) throws Exception {
		
		/*
		String regex = "[0-999]";
		String input = "0,1,2,3";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		
		while (matcher.find())
			System.out.println(matcher.group()); 
		*/
		
		File route = new File("/home/davide/eclipse-workspace/AlignmentBioinformatic/matrix.txt");
		
		new BioATCGMatrix(route);
	}
}	