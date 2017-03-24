package generateSequence;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class CreateATCGSequences{ 
	
	static char[] alphabet = { 'A', 'T', 'C', 'G' };
	
	public static int CreateSequences( int numString, int lenghtSequence, String route ) throws Exception{ // CREA DELLE SEQUENZE CON I NUMERI DEFINITI, OGNI QUAL VOLTA VIENE CHIAMATA ELIMINA I PRECEDENTI		
		
		try{
			
			FileWriter fW = new FileWriter(route);
			BufferedWriter bW = new BufferedWriter(fW);
			PrintWriter out1 = new PrintWriter(bW);
			
			for( int countLine = 0; countLine < numString; countLine++ ){
				
				String sequence = getNewSequence(lenghtSequence);
				
				out1.println(sequence);

			}
			
			out1.close();
			
			return 0;
			
		}catch(IOException e){
			System.out.println("errore: " + e);
			return -1;
		}
		
	}
	
	private static String getNewSequence( int lenghtSequence){		
		
		String result = new String();
		
		for( int count = 0; count < lenghtSequence; count++){
			result = result + CreateATCGSequences.alphabet[ThreadLocalRandom.current().nextInt(4)];
		}
		
		return result;
	}
	
	public static void main(String args[]){
		//System.out.println(getNewSequence( 26 , 80 )); getNewSequence() ok
	}
	
}
