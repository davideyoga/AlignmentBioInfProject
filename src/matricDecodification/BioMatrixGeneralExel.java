package matricDecodification;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class BioMatrixGeneralExel{
	
	
	HashMap<String, Integer> matrixValue = new HashMap<String, Integer>(); // chiave valore in cui inserisco la coppia di carqatteri e il corrispettivo valore  
	
	String[] columsCharacter; // array che contiene l'alfabeto delle colonne 
	String[] rowsCharaqcter; // array chje contiene l'alfabeto delle righe
	
	String error; // contiene eventuali errori in fase di codifica della matrice
	
	
	public BioMatrixGeneralExel( String routeExcel) throws IOException{
		
		File fileExcel = new File (routeExcel);
		
		Sheet sheet = getSheet(fileExcel);
		
		
	}
	

	
	private void ceckMatrixCharacter( Sheet sheet) throws Exception{
		
		ArrayList<String> charRows = new ArrayList<String>(); // lista in cui verranno inseriti i caratteri della prima riga, utilizzato per controllare la non esistenza di due caratteri uguali
		ArrayList<String> charColumns = new ArrayList<String>(); // stessa cosa di sopra ma per le colonne 

		HashMap<String, Integer> charCaracters = new HashMap<String, Integer>(); // a differenza di prima il controllo viene effettuato tenendo conto sia dei caratteri delle  righe che della colonne
																				 // una matrice con caratteri A,B,C,D sulle righe e E,F,G,H sulle colonne, andando a controllare solo righe e colonne singolarmente
																				 // porterebbe a non tener conto di tale problematica 
		
		
		this.columsCharacter = new String[sheet.getRows() - 1]; //inizializzo l'array dei caratteri delle righe con il numero delle colonne meno lo spazio dei caratteri delle colonne
		this.rowsCharaqcter = new String[sheet.getColumns() - 1]; // // colonne
		
		if (columsCharacter.length != rowsCharaqcter.length){//se le lunghezze sono diverse la matrice non e' quadrata
        	
			System.out.println("Matrice non quadrata");
        	throw new Exception("Matrice non quadrata");
		}
		
		for(int i = 1; i < sheet.getRows(); i++){ //ciclo la prima riga (in corrispondenza dei simboli)
			
			Cell cell = sheet.getCell(0, i); // prendo la cella (0, i-esima)
            String cellValue = cell.getContents(); // prendo il valore della cella //POSSIBILE ERRORE DI CONVERSIONE
            
            if( charRows.contains(cellValue) ){ // se charRows contiene gia il carattere
            	
            	System.out.println("Matrice non corretta, due caratteri uguali nelle righe");
            	throw new Exception("Matrice non corretta, due caratteri uguali nelle righe");
        	
            }else{
            	
            	charRows.add(cellValue); // se charRows gia' non lo contiene lo aggiunge
            	charCaracters.put(cellValue, 1); // aggiungo a charCaracter la stringa della cella
            }
		}
		
		for(int i = 1; i < sheet.getColumns(); i++){ //cicloo la prima colonna (in corrispondenza dei simboli )
			
			Cell cell = sheet.getCell(i, 0); // prendo la cella (i-esima, 0)
            String cellValue = cell.getContents(); // prendo il valore della cella //POSSIBILE ERRORE DI CONVERSIONE
            
            if( charColumns.contains(cellValue) ){ // se charColumns contiene gia il carattere
            	
            	System.out.println("Matrice non corretta, due caratteri uguali nelle colonne");
            	throw new Exception("Matrice non corretta, due caratteri uguali nelle colonne");
        	
            }else{ 
            	
            	charColumns.add(cellValue); // se charRows gia' non lo contiene lo aggiunge
            	
            	if( charCaracters.get(cellValue) == 1 ){
            		charCaracters.put(cellValue, 2); // aggiungo a charCaracter la stringa della cella
            	}else{
            		 // le possibilita' arrivati a questo punto e' che la stringa cellValue e' presente solo nelle colonne ( la possibilita che sia presente due volte nelle colonne e' gia stata risolta sopra)  
            		
            		if(charCaracters.get(cellValue) == 0) System.out.println("Matrice non corretta, il carattere: " + cellValue + " e' presente solo nelle colonne");
            		
                	throw new Exception("Matrice non corretta, due caratteri uguali nelle colonne"); // in tutte e due i casi sollevo un eccezzione
            	}
            	
            	
            }
		}
		
		
	
		
		
		
		for(int j = 0; j < sheet.getColumns(); j++ ){
			for( int i = 0; i < sheet.getRows(); i++){
                
				Cell cell = sheet.getCell(j, i);
                CellType type = cell.getType();
                if (type == CellType.LABEL) {
                        System.out.println("I got a label "
                                        + cell.getContents());
                }

                if (type == CellType.NUMBER) {
                        System.out.println("I got a number "
                                        + cell.getContents());
                }
			}
		}
		
	}
	
	
	
	
	private Sheet getSheet( File routeExcel) throws IOException{
		
		Sheet sheet = null;
		
		try{
			Workbook workbook = Workbook.getWorkbook(routeExcel);
			
			sheet = workbook.getSheet(0);	
			
		}catch(BiffException e){
			System.out.println("Error: " + e);
			e.printStackTrace();
		}
		
		return sheet;
	}
	
	public static void main(String args[]) throws IOException{
		
		String routeExcel = "/home/davide/Scrivania/pippo.xls";
		
		File fileExcel = new File (routeExcel);
		
		Workbook workbook;

		try{
			workbook = Workbook.getWorkbook(fileExcel);
			
			Sheet sheet = workbook.getSheet(0);
			
			for(int j = 0; j < sheet.getColumns(); j++ ){
				for( int i = 0; i < sheet.getRows(); i++){
                    
					Cell cell = sheet.getCell(j, i);
                    CellType type = cell.getType();
                    
                            System.out.println("I got a label "
                                            + cell.getContents());

                    /*
                    if (type == CellType.NUMBER) {
                            System.out.println("I got a number "
                                            + cell.getContents());
                    }
                    */
				}
			}
			
		}catch(BiffException e){
			System.out.println("Error: " + e);
			e.printStackTrace();
		}
		
	}
}