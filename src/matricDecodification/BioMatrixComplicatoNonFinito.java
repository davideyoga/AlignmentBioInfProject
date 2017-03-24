package matricDecodification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * QUANDO SI INVOCA IL COSTRUTTRE E SI PASSA IL FILE VA CONTROLLATO PRIMA DELL'ESISTENZA DEL FILE, ALTRIMENTI VERRA LANCIATA UNA ECCEZZIONE CONTROLLATA 
 */
/*
 * CONTROLLI:
 * 1) quandi caratteri uguali sono stati inseriti 
 * 2) controllare se la matrice e' quadrata 
 */
public class BioMatrixComplicatoNonFinito{
	
	HashMap quantitySimbol; // HasMap che contiene come indice il carattere e come argomento il numero di volte in cui e' stato dichiarato
	HashMap matrix;
	
	String routeFile;	
	String text;
	String error;
	
	
	public BioMatrixComplicatoNonFinito( File routeFile) throws IOException{
		
		this.error = null;
		
		
		BufferedReader in = null;
		
		try{
			FileReader fr = new FileReader(routeFile);
			in = new BufferedReader(fr);
			
		}catch(FileNotFoundException e){
			System.out.println("Error, file not found");
			this.error = error + "\nFile not found";
		}
		
		
		List<String> records = null;
		if( error == null ){ // se non ho incontrato eccezzioni nell' apertura del file mi prendo il testo contenuto nel file 
			
			records = extractData(in); // mi prendo tutte le righe del mio file 
		}
		in.close();
		
		
		
	}
	
	private List<String> extractData(BufferedReader in){
		
		String line = null;
		List<String> records = new ArrayList<String>();
		
	    try {
	    	
		while ((line = in.readLine()) != null) // legge fino a che non ha letto fino all'ultima riga 
			{
			    records.add(line); // aggiungo la riga alla mi alista 
			}
		} catch (IOException e) {
			System.out.println("Error: " + e);
			this.error = error + e;
		}
	    
	    return records;
	}
	
	private boolean ceckString( List<String> records){ // controllo che il file non sia vuoto o che il numero delle righe sia diverso da quello di una matrice quadrata
		
		if(!records.isEmpty()){
			this.error = "Empty file";
			return false;
		}
		
		float x = records.size();
		float y = records.size();
		
		x = (float) Math.sqrt(x);
		y = (float) Math.sqrt(y);

		x = (int)x; // casto x per fargli perdere il valore dopo la virgola
		
		float c = y - x; // se c e' != da 0 la matrice non e' quadrata
		
		if( c != 0){ 
//			searchError(records);
			return false;
		}
		
		return true;
	}
	
//	public String searchError(List<String> records){
		

//	}
	
	
	
	
	
}