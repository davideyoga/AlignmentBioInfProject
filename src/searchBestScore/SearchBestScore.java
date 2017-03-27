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
		
		ArrayList<String> stringByData = extractData(data); // l'ArrayList contiene le stringhe prese dal file che verranno confrontate con s, s e' la string acon cui effettuo il confronto
		
		
		
		for( int i = 0; i < stringByData.size(); i++ ){ // ciclo le sequenze del file
			
			long localtime = System.currentTimeMillis(); // per prendere il tempo dell' allineamnto di una singola stringa con s 
			
			String string1;
			String string2;
			
			if( s.length() >= stringByData.get(i).length()){ //l'algoritmo scritto permette solamente il confronto tra una stringa piu lunga o uguale alla seconda
				string1 = s;
				string2 = stringByData.get(i);
			}else{
				string2 = s;
				string1 = stringByData.get(i);
			}
			
			int sequenceScore = fase1(string1, string2); //prendo lo score migliore tra gli allineamneti di s e la stringa corrente
			
			long supportLocalTime = System.currentTimeMillis(); // fine tempo per prendere il tempo dell' allineamnto di una singola stringa con s 
			localtime = supportLocalTime - localtime; //conteggio per il tempo locale 
			
			if(sequenceScore > this.bestScore ){ // se lo score della sequenza analizzata e' migliore di quello attuale allora lo sotiutuisce cosi come il tempo di analisi 
				
				this.bestScore = sequenceScore; // se lo score e' migliore lo aggiorno
				this.bestSequence = stringByData.get(i); //se lo score e' migliore cambio la stringa 
				
				this.timeExecBestScore = localtime; // sostituisco il tempo 
			}			
		}
		
		
		
		long supportTimeExec = System.currentTimeMillis(); //prende il tempo attuale
		
		this.timeExec = supportTimeExec - this.timeExec; // fa la differenza dei due tempi per ottenere il tempo totale impiegato dall'algoritmo per scansionare un file
	}
	
	
	
	private int fase1( String s, String t) throws Exception{ 
		
		int localBestScore = 0;
		int currentScore = 0;
		
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

			currentScore = getScoreByString( sottoStringaS, sottoStringaT); // prendo lo score dell'allineamento corrente
			
			if(currentScore >= localBestScore){ //se lo score di tale allineamento e' migliore di quello dell'attuale lo sostituisco 
				localBestScore = currentScore;
			}
				

		}
		                                                                                                                      
		return fase2( s, t,countInizioS, countFineS, countInizioT, countFineT, localBestScore );
	}
	
	private int fase2( String s, String t, int countInizioS, int countFineS, int countInizioT, int countFineT, int localBestScore )throws Exception{
		
		int countIterazioni = 0;
		
		int currentScore = 0;
		
		while( countIterazioni != (s.length() - t.length())){ // se la condizione e' vera sono ancora nella fase 2 dello scorrimento 
			
			countIterazioni++;
			
			countInizioS++;
			countFineS++;
			
			String sottoStringaS = s.substring((s.length() - countInizioS ) , ( s.length() - countFineS ) + 1 );
			
			String sottoStringaT = t.substring( countInizioT, countFineT + 1 );

			currentScore = getScoreByString( sottoStringaS, sottoStringaT);		
			
			if(currentScore >= localBestScore){ //se lo score di tale allineamento e' migliore di quello dell'attuale lo sostituisco 
				localBestScore = currentScore;
			}
			
		}
		return fase3( s, t, countInizioS, countFineS, countInizioT, countFineT, localBestScore );
	}
	
	private int fase3(String s, String t, int countInizioS, int countFineS, int countInizioT, int countFineT, int localBestScore)throws Exception{
		
		int currentScore = 0;
		
		while( countInizioS != countFineS){
			
			countFineS++;
			
			countInizioT++;
			
			String sottoStringaS = s.substring((s.length() - countInizioS ) , ( s.length() - countFineS ) + 1 );
			
			String sottoStringaT = t.substring( countInizioT, countFineT + 1 );

			currentScore = getScoreByString( sottoStringaS, sottoStringaT); // prendo lo score dell' attuale sottostringa 
			
			if(currentScore >= localBestScore){ //se lo score di tale allineamento e' migliore di quello dell'attuale lo sostituisco 
				localBestScore = currentScore;
			}
					
		}
		
		return localBestScore;
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