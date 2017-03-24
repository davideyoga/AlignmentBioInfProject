package utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Utils{
public static void main(String args[]) throws IOException{
	
		//PRENDERE IN INPUT DEI DATI DA TASTIERA
		InputStreamReader reader = new InputStreamReader (System.in); // per prendere dati da tastiera 
	
		BufferedReader myInput = new BufferedReader (reader); 
	
		String str = new String();
	
		try{
			System.out.println("scrivi: ");
			str = myInput.readLine();
		}catch (IOException e) {
			System.out.println("errore:" + e);
			System.exit(-1);
		}
	
		System.out.println("hai scritto: " + str);
		
		
		//scrivo sul file sovrascrivendo cio' che c' e' contenuto nel file  
		try{
			
			FileWriter fW = new FileWriter("/home/davide/eclipse-workspace/AlignmentBioinformatic/data.txt");
			BufferedWriter bW = new BufferedWriter(fW);
			PrintWriter out1 = new PrintWriter(bW);
			
			int linecount = 1;

			String s; 
			while( linecount <= 4){ //vado a sovrascrivere il testo che avevo precedentemente, 
				out1.println( "scritto linea: " + linecount++ ); // scrivo una riga 
			}
			
			out1.close();
			
		}catch(IOException e){
			System.out.println("errore: " + e);
		}
		
		
		////scrivo di seguito delle stringhe 
		try{
			FileWriter fW = new FileWriter("/home/davide/eclipse-workspace/AlignmentBioinformatic/data.txt", true);
			BufferedWriter bW = new BufferedWriter(fW);
			PrintWriter out1 = new PrintWriter(bW);
			
			int linecount = 1;

			String s; 
			while( linecount <= 4){ //vado a sovrascrivere il testo che avevo precedentemente, 
				out1.println( "scritto linea: " + linecount++ ); // scrivo una riga 
			}
			
			out1.close();
			
		}catch(IOException e){
			System.out.println("error: " + e);
		}
		
		
		//LETTURA metodo con il buffer 	
		BufferedReader in = new BufferedReader(
				new FileReader("/home/davide/eclipse-workspace/AlignmentBioinformatic/data.txt"));
	
		String s1 = new String();
		String s2 = new String();
	
		while( (s1 = in.readLine()) != null ){ // readLine() legge il file fino alla fine 
			s2 = s2 + s1 + "\n";
		}
	
		in.close();
		
		System.out.println(s2);		
		
		

		
	}
}