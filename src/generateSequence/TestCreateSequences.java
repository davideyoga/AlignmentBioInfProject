package generateSequence;
public class TestCreateSequences{
	
	public static void main(String args[]) throws Exception{
		
		String route = "/home/davide/eclipse-workspace/AlignmentBioinformatic/data.txt";
		
		long startTime = System.currentTimeMillis();
		
		CreateATCGSequences.CreateSequences(200, 10, route);
		
		long endTime = System.currentTimeMillis();
		startTime = endTime - startTime;
		System.out.println(startTime);
		
	}
}