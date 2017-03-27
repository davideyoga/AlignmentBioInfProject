package searchBestScore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import generateSequence.CreateSequences;
import matrixDecodification.MatrixDecodificationByExcel;

public class TestSearchBestScore{
	
	public static String getkeyboardData() throws Exception{
		InputStreamReader reader = new InputStreamReader (System.in); // per prendere dati da tastiera 
		
		BufferedReader myInput = new BufferedReader (reader); 
	
		String routeExcel = new String();
	
		try{
			routeExcel = myInput.readLine();
		}catch (IOException e) {
			System.out.println("errore:" + e);
			System.exit(-1);
		}
		
		return routeExcel;
	}
	
	private static String getS( String route) throws IOException{
		
		File fileRoute = new File(route); 
		
		BufferedReader in;
		try {
			in = new BufferedReader(
					new FileReader(fileRoute));
		} catch (FileNotFoundException e) {
			System.out.println("Errore, File: " + route + " non esistente");
			throw new FileNotFoundException("Errore, File: " + route + " non esistente");
		}
	
		String s1 = new String();
		
		s1 = in.readLine(); //prendo la prima riga dal file 
	
		in.close(); //chiudo il buffer
		
		return s1;
	}
	
	public static void main(String args[]) throws Exception{
		
		System.out.println("Inserire il percorso assoluto al file excel di tipo xls: ");
		String routeExcel = getkeyboardData(); //prende da tastiera il path
		routeExcel = routeExcel.trim(); //elimina spazi binchi all'inizio e alla fgine della stringa
		MatrixDecodificationByExcel matrix = new MatrixDecodificationByExcel(routeExcel);
		System.out.println("la matrice risulta cosi codificata: \n"
							+ "righe: " + matrix.charRows + "\n"
							+ "colonne: " + matrix.charColumns + "\n"
							+ "valori assegnati ad ogni accoppiamento: " + matrix.matrixValue + "\n");
		
		
		
		System.out.println("Inserire la directory con percorso assoluto in cui verranno creati i file txt contenenti le sequenze(tale path deve indicare una cartella): ");
		String routeSequenceData = getkeyboardData(); //prende da tastiera il path
		routeSequenceData = routeSequenceData.trim();//elimina spazi binchi all'inizio e alla fgine della stringa
		
		//CREO IL FILE CHE CONTERRA LA SEQUENZA DA CONFRONTARE (S)
		String routeS = routeSequenceData + "data";
		CreateSequences.CreateSequences2( 4, 1, 30, routeS );
		String s = getS(routeS);
		
		
		for(int i = 1; i <= 201; i = i + 20 ){		
			
			String nameData = routeSequenceData + "data" + i;  
			
			CreateSequences.CreateSequences2( 4, i, 30, nameData ); // creo un file con sequenze di 4 caratteri, con numero di sequenze i e lunghe 4  
			
			SearchBestScore bs = new SearchBestScore(nameData, matrix, s);
			
			System.out.println("tempo di esecuzione per il singolo file: " + bs.timeExec + "ms");
			System.out.println("tempo di esecuzione per l'allineamento della stringa migliore: " + bs.timeExecBestScore + "ms");
			System.out.println("sequenza da confrontare: " + s);
			System.out.println("sequenza con il migliore score di somiglianza(secondo matrice dei valori data): " + bs.bestSequence);
			System.out.println("con lo score: " + bs.bestScore);
			System.out.println("\n");
			
		}
		
	}
	
}