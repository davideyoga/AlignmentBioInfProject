package generateSequence;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class CreateSequences{ 
	
	static char[] alphabet = {'Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M'};
	
	public static int CreateSequences2( int numAlphabet, int numString, int lenghtSequence, String route ) throws Exception{ // CREA DELLE SEQUENZE CON I NUMERI DEFINITI, OGNI QUAL VOLTA VIENE CHIAMATA ELIMINA I PRECEDENTI
		
		//CONTROLLI SU INPUT 
		if(numAlphabet < alphabet.length){
			System.out.println("maxed out number characters of quarty alpahbet");
			throw new Exception("maxed out number characters of quarty alpahbet");
		}
		
		
		try{
			
			FileWriter fW = new FileWriter(route);
			BufferedWriter bW = new BufferedWriter(fW);
			PrintWriter out1 = new PrintWriter(bW);
			
			for( int countLine = 0; countLine < numString; countLine++ ){
				
				String sequence = getNewSequence(numAlphabet, lenghtSequence);
				
				out1.println(sequence);
				
				System.out.println("ho scritto");
			}
			
			out1.close();
			
			return 0;
			
		}catch(IOException e){
			System.out.println("errore: " + e);
			return -1;
		}
		
	}
	
	private static String getNewSequence( int numAlphabet, int lenghtSequence){		
		
		String result = new String();
		
		for( int count = 0; count < lenghtSequence; count++){
			result = result + CreateSequences.alphabet[ThreadLocalRandom.current().nextInt(numAlphabet)];
		}
		
		return result;
	}
	
	public static void main(String args[]){
		//System.out.println(getNewSequence( 26 , 80 )); getNewSequence() ok
	}
	
}
