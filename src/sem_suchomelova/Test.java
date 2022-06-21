package sem_suchomelova;
import java.util.Scanner;

import sql.connect.DBConnection;
import sql.connect.DeleteQueries;
import sql.connect.InsertQueries;
import sql.connect.SelectQueries;


public class Test {
	
	
	public static void main(String[] args) {	
		
		Databaze mojeDatabaze=new Databaze();
		Scanner sc=new Scanner(System.in);
		int navyseniID =  25837;	//Aby studentum nazacinaly ID od 1
		int ID, znamka, volba, volbaOboru;
		String jmeno, prijmeni, datumNarozeni;
		boolean run=true;
		
		if(!InsertQueries.createTable())
		System.out.println("tabulka SQL nevytvorena");
	
		//////////// TEST DATA /////////////////
		mojeDatabaze.pridaniStudenta("Technicky",23,"Petr", "Cely", "25.09.1990");
		mojeDatabaze.pridaniStudenta("Humanitni",54,"Stepanka", "Junglingova", "17.03.200");
		mojeDatabaze.pridaniStudenta("Kombinovany",89,"Mildred", "Vyrvalova", "09.12.1995");
		mojeDatabaze.pridaniStudenta("Kombinovany",22,"Zloduch", "Otravny", "03.16.1990");
		mojeDatabaze.pridaniStudenta("Technicky",66,"Zloduch", "Potouchly", "3.11.1888");
		mojeDatabaze.vytvorZnamky(23, 0, 0);
		mojeDatabaze.vytvorZnamky(54, 0, 0);
		mojeDatabaze.vytvorZnamky(89, 0, 0);
		mojeDatabaze.vytvorZnamky(22, 0, 0);
		mojeDatabaze.vytvorZnamky(66, 0, 0);
			
		mojeDatabaze.setZnamku(23,3);
		mojeDatabaze.setZnamku(54,1);
		mojeDatabaze.setZnamku(54,2);
		mojeDatabaze.setZnamku(66,5);
		mojeDatabaze.setZnamku(22,4);
		mojeDatabaze.setZnamku(22,1);
		//////////////////////////////////////////////////////////
			
		while(run){
			Volba.menu();
			volba=Volba.pouzeCelaCisla(sc);
			switch(volba){
			case 0:
				DBConnection.disconnect();
			break;
				case 1:
					System.out.println("Vyberte, do které skupiny bude patøit student.");
					System.out.println("Technika = 1");		
					System.out.println("Humanitni = 2");
					System.out.println("Kombinace = 3");
					volbaOboru=Volba.pouzeCislaOdDo(sc,1,3);
					System.out.println("Zadejte prosim jméno studenta.");
					jmeno=sc.next();
					System.out.println("Zadejte prosim prijmeni studenta.");
					prijmeni=sc.next();
					System.out.println("Zadejte datum narození studenta ve tvaru DD.MM.RRRR");	
					datumNarozeni = Volba.pouzeDatum(sc.next());	
					ID = mojeDatabaze.getPocetStudentu() + navyseniID;
					if(mojeDatabaze.pridaniStudenta(Volba.zvoleniOboru(volbaOboru),ID,jmeno, prijmeni, datumNarozeni)) {
						System.out.println("Student pridan do databaze, bylo mu prideleno ID: " + ID);
						mojeDatabaze.vytvorZnamky(ID, 0, 0);
					} else
						System.out.println("Student nebyl pridan do databaze");
					break;
				case 2:
					System.out.println("Zadejte id studenta, kteremu chcete pridat znamku");
					ID=Volba.pouzeCelaCisla(sc);
					System.out.println("Zadejte znamku");
					znamka = Volba.pouzeCislaOdDo(sc,1,5);
					mojeDatabaze.setZnamku(ID,znamka);
					break;
				case 3:
					System.out.println("Zadejte id studenta");
					ID=Volba.pouzeCelaCisla(sc);
					mojeDatabaze.printStudent(ID);
					break;
				case 4:
					System.out.println("Zadejte id studenta, ktereho chcete odstranit");
					ID=Volba.pouzeCelaCisla(sc);
					if (mojeDatabaze.vymazStudenta(ID))
						System.out.println("Student odstranen ");
					break;
				case 5:
					mojeDatabaze.vypisDatabaze();
					break;
				case 6:
					System.out.println("Zadejte id studenta, ktery ma predvest svoji schopnost");
					ID=Volba.pouzeCelaCisla(sc);
					mojeDatabaze.schopnostStudenta(ID);
					break;
				case 7:
					mojeDatabaze.seradVsechny();
					break;
				case 8:
					mojeDatabaze.getSpolecnePrumery();
					break;
				case 9:
					mojeDatabaze.getPocetVeSkupinach();
					break;	
				case 10:
					if(mojeDatabaze.nactiVsechnyStudentyZSQL())
						System.out.println("Studenti byli nacteni");
					else
						System.out.println("Studenti nebyli nacteni");
					break;
				case 11:
					mojeDatabaze.vlozStudentyDoSQL();
					break;					
				case 12:
					System.out.println("Zadejte id studenta, ktereho chcete odstranit.");
					ID=Volba.pouzeCelaCisla(sc);
			        DeleteQueries d = new DeleteQueries();
			        if( d.deleteByID(ID))
			           System.out.println("Student odstranen");
			        else
			        	System.out.println("Studenta nebylo mozne odstranit");  
					break;
				case 13:
					System.out.println("Zadejte prosim jméno studenta.");
					jmeno=sc.next();
					System.out.println("Zadejte prosim prijmeni studenta.");
					prijmeni=sc.next();
					if(mojeDatabaze.pridejStudentaPodleJmena(jmeno, prijmeni))
					System.out.println("Student pridan");
					else System.out.println("Student nebyl pridan");
					break;						
				case 14:
					SelectQueries se = new SelectQueries();
			         se.getAllUsers();
					break;			
				case 15:
					run=false;
					DBConnection.disconnect();
					break;	
				default:
					System.out.println("Pro tuto volbu neni nabizena zadna akce.");
					break;
					
					
					
				
			}
			
		}
	}

}
