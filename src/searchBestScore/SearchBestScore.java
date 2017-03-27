package searchBestScore;

import java.io.File;

import matrixDecodification.MatrixDecodificationByExcel;




public class SearchBestScore{
	
	public SearchBestScore( ){
		
	}
	
	public SearchBestScore( File data, MatrixDecodificationByExcel matrix){
		
	}
	
	private void fase1( String s, String t){
		
		int countInizioS = 0;
		int countFineS = 1;
		
		int countInizioT = 0;
		int countFineT = -1;
		
		int countIterazioni = 0;
		
		while( countIterazioni != t.length() ){ //se condizione vera mi trovo ancora nella prima fase dello scorrimento
			
			countIterazioni++; //incremento le iterazioni
			
			countInizioS++;
			
			countFineT++;

			System.out.println("countInizio: " + (s.length() - countInizioS ) );
			System.out.println("countFine: " + ( s.length() - countFineS ) );
			System.out.println("countIterazioni: " + countIterazioni);
			System.out.println("countInizioT: " + countInizioT);
			System.out.println("countFineT: " + countFineT);
			System.out.println("\n");
			

		}
		
		System.out.println("chiamato fase2\n");
		                                                                                                                      
		fase2( s, t,countInizioS, countFineS, countInizioT, countFineT );
	}
	
	
	private void fase2( String s, String t, int countInizioS, int countFineS, int countInizioT, int countFineT ){
		
		int countIterazioni = 0;
		
		
		
		while( countIterazioni != (s.length() - t.length())){ // se la condizione e' vera sono ancora nella fase 2 dello scorrimento 
			
			countIterazioni++;
			
			countInizioS++;
			countFineS++;
			
			System.out.println("countInizio: " + (s.length() - countInizioS ));
			System.out.println("countFine: " + ( s.length() - countFineS ));
			System.out.println("countIterazioni: " + countIterazioni);
			System.out.println("countInizioT: " + countInizioT);
			System.out.println("countFineT: " + countFineT);
			System.out.println("\n");
			

		}
		System.out.println("chiamato fase3\n");
		fase3( s, t, countInizioS, countFineS, countInizioT, countFineT );
	}
	
	private void fase3(String s, String t, int countInizioS, int countFineS, int countInizioT, int countFineT){
		

		
		while( countInizioS != countFineS){
			
			countFineS++;
			
			countInizioT++;
			
			System.out.println("countInizio: " + ( s.length() - countInizioS ));
			System.out.println("countFine: " + ( s.length() - countFineS ));
			System.out.println("countInizioT: " + countInizioT);
			System.out.println("countFineT: " + countFineT);
			System.out.println("\n");
			
		}
	}
	
	public static void main(String args[]){
		
		String S = "pipo";
		String T = "pip";
		
		SearchBestScore sbs = new SearchBestScore();
		
		sbs.fase1(S, T);
		
		
	}
}