package sem_suchomelova;
/*
import typyStudentu.*;
import schopnosti.*;
import sem_suchomelova.*;
			*/
import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.util.Scanner;

public class Volba {
	static Scanner ss=new Scanner(System.in);
	
	/*Vypise do konzole akce, ye kterych si uzivatel muse vybrat*/
	public static void menu() {
		System.out.println("___________________________________________________________________");	
		System.out.println("Vyberte pozadovanou cinnost:");		
		System.out.println("1 ... vlozeni noveho studenta");
		System.out.println("2 ... pridani znamky studentovi");
		System.out.println("3 ... vypis informace o studentovi");
		System.out.println("4 ... odstraneni studenta ");
		System.out.println("5 ... vypis vsech studentu ");
		System.out.println("6 ... schopnost studenta");
		System.out.println("7 ... abecedni serazeni studentu podle oboru");
		System.out.println("8 ... vypsani spolecneho prumeru podle oboru");
		System.out.println("9 ... vypsani poctu studentu ve skupinach");
		System.out.println("10 .. nacteni vsech udaju z SQL databaze");
		System.out.println("11 .. ulozeni vsech udaju do SQL databaze");
		System.out.println("12 .. vymazani studenta z SQL databaze ");
		System.out.println("13 .. naètení studenta z SQL databáze (podle jména a pøíjmení) ");
		System.out.println("14 .. vypsani cele SQL databaze");
		System.out.println("15 .. ukonceni aplikace");
		System.out.println("___________________________________________________________________");	
	
	}
	
	/*Jen prevede cislo (reprezentujici obor) na String*/
	public static String zvoleniOboru(int obor) {
		String oborString = null;
		switch(obor) {
			case 1:
				oborString = "Technicky";
				break;
			case 2:
				oborString = "Humanitni";
				break;
			case 3:
				oborString = "Kombinovany";
				break;
			default:
		}
		return oborString;
	}
	
	/*Overi zda zadane znaky jsou pouze cela cisla, kdyz ne, bude vyzadovat zadani celych cisel. Vraci odpovidajici cislo*/
	public static int pouzeCelaCisla(Scanner sc) {
		int cislo = 0;
		try{
			cislo = sc.nextInt();
		}
		catch(Exception e){
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("Zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}

	/*Overi zda zadane znaky jsou pouze cela v danem rozsahu kdyz ne, bude vyzadovat zadani novych cisel. 
	 * Vraci cislo, ktere patri do spravneho intervalu*/
	public static int pouzeCislaOdDo(Scanner sc, int od, int doo) {
		int cislo = 0;
		try{
			cislo = sc.nextInt();
		}
		catch(Exception e){
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("Zadejte prosim cislo od " + od + " do " + doo);
			sc.nextLine();
			cislo =  pouzeCislaOdDo(sc,od,doo);
		}
		if(cislo<od || cislo>doo){
				System.out.println("Zadejte prosim cislo od " + od + " do " + doo);
				sc.nextLine();
				cislo = pouzeCislaOdDo(sc,od,doo);
		 }
		return cislo;
	}
		
	/*Overi, zda zandany String je ve spravnem formatu a jestli to muze byt datum, pokud ne, 
	 * vyzada si zadani noveho Stringu. Vraci String, ktery odpovida datumovemu formatu*/
	public static String pouzeDatum(String datum) {
        SimpleDateFormat formatData = new SimpleDateFormat("dd.MM.yyyy");
        formatData.setLenient(false);
        try {
        	formatData.parse(datum.trim());
        } catch (ParseException pe) {
        	System.out.println("Zadejte prosim datum ve spravnem formatu");
        	datum= Volba.pouzeDatum(ss.next());
        }
        return datum;
    }
		
}
