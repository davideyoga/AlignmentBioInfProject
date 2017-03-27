package searchBestScore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import matrixDecodification.MatrixDecodificationByExcel;




public class SearchBestScore{
	
	MatrixDecodificationByExcel matrix;
	
	int bestScore;
	
	long timeExec;
	long timeExecBestScore;
	
	String bestSequence;

	
	//COSTRUTTORE
	public SearchBestScore( String stringRoute, MatrixDecodificationByExcel matrix, String s) throws Exception{
		
		
		this.timeExec = System.currentTimeMillis(); // prende il tempo attuale
		
		this.bestScore = 0;
		
		this.bestSequence = "";
		
		this.matrix = matrix;
		
		File data = new File(stringRoute);
		
		ArrayList<String> stringByData = extractData(data); // l'ArrayList contiene le stringhe prese dal file che verranno confrontate con 
		
		for( int i = 0; i < stringByData.size(); i++ ){
			
			this.timeExecBestScore = System.currentTimeMillis();
			
			int score = fase1(s, stringByData.get(i));
			
			if(score > this.bestScore ){ // se lo score della sequenza analizzata e' migliore di quello attuale allora lo sotiutuisce cosi come il tempo di analisi 
				
				this.bestScore = score;
				this.bestSequence = stringByData.get(i);
				
				long time2 = System.currentTimeMillis();
				
				this.timeExecBestScore = time2 - timeExecBestScore;
			}
		}
		
		long time3 = System.currentTimeMillis(); //prende il tempo attuale
		
		this.timeExec = time3 - this.timeExec; // fa la differenza dei due tempi per ottenere il tempo totale impiegato dall'algoritmo
	}
	
	
	
	private int fase1( String s, String t) throws Exception{ 
		
		int score = 0;
		
		int countInizioS = 0;
		int countFineS = 1;
		
		int countInizioT = 0;
		int countFineT = -1;
		
		int countIterazioni = 0;
		
		while( countIterazioni != t.length() ){ //se condizione vera mi trovo ancora nella prima fase dello scorrimento
			
			countIterazioni++; //incremento le iterazioni
			
			countInizioS++;
			
			countFineT++;
			
			String sottoStringaS = s.substring((s.length() - countInizioS ) , ( s.length() - countFineS ) + 1 );			

			String sottoStringaT = t.substring( countInizioT, countFineT + 1 );

			score = score + getScoreByString( sottoStringaS, sottoStringaT);
				

		}
		                                                                                                                      
		return fase2( s, t,countInizioS, countFineS, countInizioT, countFineT, score );
	}
	
	private int fase2( String s, String t, int countInizioS, int countFineS, int countInizioT, int countFineT, int score )throws Exception{
		
		int countIterazioni = 0;
		
		
		
		while( countIterazioni != (s.length() - t.length())){ // se la condizione e' vera sono ancora nella fase 2 dello scorrimento 
			
			countIterazioni++;
			
			countInizioS++;
			countFineS++;
			
			String sottoStringaS = s.substring((s.length() - countInizioS ) , ( s.length() - countFineS ) + 1 );
			
			String sottoStringaT = t.substring( countInizioT, countFineT + 1 );

			score = score + getScoreByString( sottoStringaS, sottoStringaT);		

		}
		return fase3( s, t, countInizioS, countFineS, countInizioT, countFineT, score );
	}
	
	private int fase3(String s, String t, int countInizioS, int countFineS, int countInizioT, int countFineT, int score)throws Exception{
		

		
		while( countInizioS != countFineS){
			
			countFineS++;
			
			countInizioT++;
			
			String sottoStringaS = s.substring((s.length() - countInizioS ) , ( s.length() - countFineS ) + 1 );
			
			String sottoStringaT = t.substring( countInizioT, countFineT + 1 );

			score = score + getScoreByString( sottoStringaS, sottoStringaT); // prendo lo score dell' attuale sottostringa 
					
		}
		
		return score;
	}

	
	private int getScoreByString(String s, String t) throws Exception{
		
		int score = 0;
		
		if(s.length() != t.length()) {
			System.out.println("Errore, stringhe diverse");
		}
		
		for(int i = 0; i < s.length(); i++){
			
			// mi prendo i caratteri da confrontare e li trasformo in stringhe per sommarli
			String cs = Character.toString(s.charAt(i)); 
			String ct = Character.toString(t.charAt(i));
			
			String res = cs + ct;
			
			score = score + this.matrix.getValueString(res); // aggiungo a score il valore codificato della matrice data in input 
		}
		
		return score;
	}
	
	private ArrayList<String> extractData(File data) throws Exception{
		
		BufferedReader in;
		
		try {
			in = new BufferedReader(
					new FileReader(data));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			throw new Exception("File non leggibile");
		}
		
		ArrayList<String> stringByData = new ArrayList<String>();
		
		String s1 = new String();

		while( (s1 = in.readLine()) != null ){ // readLine() legge il file fino alla fine 
			stringByData.add(s1);
		}
	
		in.close();
		
		return stringByData;
	}
	
}