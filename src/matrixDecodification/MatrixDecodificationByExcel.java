package matrixDecodification;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class MatrixDecodificationByExcel{
	
	
	HashMap<String, Integer> matrixValue = new HashMap<String, Integer>(); // chiave valore in cui inserisco la coppia di carqatteri e il corrispettivo valore  
	
	ArrayList<String> charRows;
	ArrayList<String> charColumns;
	
	String error; // contiene eventuali errori in fase di codifica della matrice
	
	//COSTRUTTORE
	public MatrixDecodificationByExcel( String routeExcel) throws Exception{
		
		File fileExcel = new File (routeExcel);
		
		
		Sheet sheet = getSheet(fileExcel);		
		
		ceckMatrixCharacter(sheet);
		
		getValueByMatrix(sheet);
		
	}
	

	
	private void ceckMatrixCharacter( Sheet sheet) throws Exception{
		
		this.charRows = new ArrayList<String>(); // lista in cui verranno inseriti i caratteri della prima riga, utilizzato per controllare la non esistenza di due caratteri uguali
		this.charColumns = new ArrayList<String>(); // stessa cosa di sopra ma per le colonne 

		HashMap<String, Integer> charCaracters = new HashMap<String, Integer>(); // a differenza di prima il controllo viene effettuato tenendo conto sia dei caratteri delle  righe che della colonne
																				 // una matrice con caratteri A,B,C,D sulle righe e E,F,G,H sulle colonne, andando a controllare solo righe e colonne singolarmente
																				 // porterebbe a non tener conto di tale problematica 
		
		
		if(sheet.getRows() == 0 || sheet.getColumns() == 0){  //controllo per matrice vuota
			System.out.println("Matrice vuota ");
			this.error = "Matrice vuota";
        	throw new Exception("Matrice vuota");
		}
		
		if( sheet.getRows() != sheet.getColumns() ){//se le lunghezze sono diverse la matrice non e' quadrata
			System.out.println("Matrice non quadrata, numero colonne: " + sheet.getColumns() + ", numero righe:" + sheet.getRows() );
			this.error = "Matrice non quadrata";
        	throw new Exception("Matrice non quadrata");
		}
		
		for(int i = 1; i < sheet.getRows(); i++){ //ciclo per scorrere la prima riga contenente le stringhe
			
			Cell cell = sheet.getCell(i, 0); // prendo la cella (0, i-esima)
            String cellValue = cell.getContents(); // prendo il valore della cella 
            
            if( charRows.contains(cellValue) ){ // se charRows contiene gia il carattere
            	
            	System.out.println("Matrice non corretta," + cellValue + " compare due volte nelle righe");
            	this.error = "Matrice non corretta," + cellValue + " compare due volte nelle righe";
            	throw new Exception("Matrice non corretta, due caratteri uguali nelle righe");
        	
            }else{
            	
            	charRows.add(cellValue); // se charRows gia' non lo contiene lo aggiunge
            	charCaracters.put(cellValue, 1); // aggiungo a charCaracter la stringa della cella
            }
		}
		
		for(int i = 1; i < sheet.getColumns(); i++){ //ciclo per scorrere la prima colonna contenente le stringhe
			
			Cell cell = sheet.getCell(0, i); // prendo la cella (i-esima, 0)
            String cellValue = cell.getContents(); // prendo il valore della cella 
            
            if( charColumns.contains(cellValue) ){ // se charColumns contiene gia il carattere
            	
            	System.out.println("Matrice non corretta, " + cellValue +  " compare due volte nelle colonne");
            	this.error = "Matrice non corretta, " + cellValue +  " compare due volte nelle colonne";
            	throw new Exception("Matrice non corretta, due caratteri uguali nelle colonne");
        	
            }else{ 
            	
            	charColumns.add(cellValue); // se charRows gia' non lo contiene lo aggiunge
            	
            	//e se ancora non lo contiene??
            	
            	if( !charCaracters.containsKey(cellValue)){ // arrivati a questo punto se la stringa cellValue non e' contenuta in charCaracters significa che una stringa che si trova nelle colonne non si trova nelle righe
            		System.out.println("Matrice non corretta, il carattere: " + cellValue + " e' presente solo nelle colonne");
            		this.error = "Matrice non corretta, il carattere: " + cellValue + " e' presente solo nelle colonne";
            		throw new Exception("Matrice non corretta, il carattere: " + cellValue + " e' presente solo nelle colonne"); 
            	}
            	
            	if( charCaracters.get(cellValue) == 1 ){
            		charCaracters.put(cellValue, 2); // aggiungo a charCaracter la stringa della cella
            	}else{            		
            		// le possibilita' arrivati a questo punto e' che la stringa cellValue e' presente solo nelle colonne ( la possibilita che sia presente due volte nelle colonne e' gia stata risolta sopra)  
            		System.out.println("Matrice non corretta, il carattere: " + cellValue + " e' presente solo nelle colonne");
            		this.error = "Matrice non corretta, il carattere: " + cellValue + " e' presente solo nelle colonne";
                	throw new Exception("Matrice non corretta, il carattere: " + cellValue + " e' presente solo nelle colonne"); 
            	}
            	
            	
            }
		}
		
		System.out.println(charRows + "\n" + charColumns);
	}
	
	private void getValueByMatrix( Sheet sheet ) throws NumberFormatException{
		
		//CODIFICO COME (COLONNA, RIGA)
		
		for(int countRows = 1; countRows <= charRows.size(); countRows++){ //ciclo per correre orizzontalmente i valori
			for( int countColumns = 1; countColumns <= charColumns.size(); countColumns++){ //ciclo per scorrere verticalmente i valori
				
				
				Cell cell = sheet.getCell(countColumns, countRows); // prendo la cella (countRows, countColumns)
				
				try{ //blocco per catturare l'errore di conversione da stringa a intero: nel caso in cui ci sia un carattere nelle celle che dovrebbero un valore intero
					int value = Integer.parseInt(cell.getContents()); // prendo il valore della cella e la converto in intero
					
		            String nameValue = charRows.get(countColumns - 1) + charColumns.get(countRows - 1); // unisco il carattere della riga con quello della colonna 
		            
		            matrixValue.put( nameValue, value); // inserisco il nome riga-colonna ed il corrispondente valore nella mappa
				
				}catch ( NumberFormatException e) {
			
					System.out.println("Errore: nella cella con colonna: "+ (countColumns + 1) + ", riga: " + (countRows + 1) + " non e' presente un numero");
					this.error = "Errore: nella cella con colonna: "+ (countColumns + 1) + ", riga: " + (countRows + 1) + " non e' presente un numero";
					throw new NumberFormatException("Errore: nella cella con colonna: "+ (countColumns + 1) + ", riga: " + (countRows + 1) + " non e' presente un numero");
				}

		
			} //fine ciclo colonne
		}//fine ciclo righe
		
		System.out.println(matrixValue);
	}
	
	
	private Sheet getSheet( File routeExcel) throws IOException{
		
		Sheet sheet = null;
		
		try{
			Workbook workbook = Workbook.getWorkbook(routeExcel);
			
			sheet = workbook.getSheet(0);	
			
		}catch(BiffException e){
			System.out.println("Matrice non trovata");
			System.out.println("Error: " + e);
			e.printStackTrace();
		}
		
		return sheet;
	}
	
	public int getValueString( String s) throws Exception{
		

		
		if(!matrixValue.containsKey(s)){
			System.out.println("Il carattere: " + s + " non e' presente nalla matrice ");
			throw new Exception("Il carattere: " + s + " non e' presente nalla matrice ");
		}
		
		return matrixValue.get(s);
	}

}