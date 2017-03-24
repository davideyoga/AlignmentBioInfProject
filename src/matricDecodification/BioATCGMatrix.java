package matricDecodification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BioATCGMatrix{
	
	 ArrayList<String> records;
	 
	 HashMap<String,Integer> map;
	
	public BioATCGMatrix(File fileName) throws Exception{
		
	    this.records = new ArrayList<String>();

	    this.records = readFile(fileName);
	    
	    this.map = decodificateMatrix(records);
	    
	    System.out.println(this.map);
	}
	
	private ArrayList<String> readFile(File fileName)throws Exception{
			    
		String line = null; // stringa di appoggio
		ArrayList<String> records = new ArrayList<String>(); //lista che conterra' ogni riga del testo


		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName)); // apro un buffer per leggere su file

		while ((line = bufferedReader.readLine()) != null) //leggo fino ad arrivare all'ultima riga 
		    {
		        records.add(line); // aggiungo la riga alla lista
		    }
		  
		bufferedReader.close();//chiudo il buffer 
		
		return records; 
	}


	
	private HashMap<String,Integer> decodificateMatrix(ArrayList<String> list){
		
		String[] alphabet = {"A","T","C","G"};
		
		HashMap<String,Integer> map = new HashMap<String, Integer>(); // mi dichiaro un chiave valore in cui inserisco la coppia e il valore corrispondente
		
		String regular = "[0-999]"; // espressione regolare
		Pattern pattern = Pattern.compile(regular);
		
		for(int i = 0; i < list.size(); i++){
			
			String input = list.get(i);// prendo la riga i-esima della lista
			Matcher matcher = pattern.matcher(input);
			
			for(int e = 0; matcher.find() ;e++){ // ciclo sulla singola riga per estrarre ogni singolo carattere
				
				String pippo = alphabet[i] + alphabet[e];
				
				int foo = Integer.parseInt(matcher.group()); // matcher.group() restituisce una stringa, qindi la devo convertire in int 
				
				map.put(pippo, foo );
			}
		}
		
		return map;
	}
}
