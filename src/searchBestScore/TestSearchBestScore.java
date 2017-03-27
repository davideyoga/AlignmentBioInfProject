package searchBestScore;

import java.io.BufferedReader;
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
	
	public static void main(String args[]) throws Exception{
		
		System.out.println("Inserire il percorso assoluto al file excel di tipo xls: ");
		String routeExcel = getkeyboardData();
		
		
		MatrixDecodificationByExcel matrix = new MatrixDecodificationByExcel(routeExcel);
		
		System.out.println("Inserire la directory con percorso assoluto in cui verranno creati i file txt contenenti le sequenze: ");
		String routeSequnceData = getkeyboardData();
		
		String s = CreateSequences.getNewSequence( 4 ,4);
		
		for(int i = 1; i <= 201; i = i + 20 ){		
			
			String nameData = routeSequnceData + "data" + i;  
			
			CreateSequences.CreateSequences2( 4, i, 4, nameData ); // creo un file con sequenze di 4 caratteri, con numero di sequenze i e lunghe 4  
			
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