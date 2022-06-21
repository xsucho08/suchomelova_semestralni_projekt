package sem_suchomelova;
import typyStudentu.*;
import schopnosti.*;
import sql.connect.DBConnection;
import sql.connect.InsertQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Databaze{
	
	public static  Map<Integer,Student>  prvkyDatabaze;
	public static  Map<Integer,Znamky>  databazeZnamek; /*Je to databaze souctu hodnot znamek a poctu znamek pro ID(studenta), ne jednotlivych znamek*/
	protected int ID;
	
	public Databaze(){	
		prvkyDatabaze=new HashMap<Integer,Student>();
		databazeZnamek=new HashMap<Integer,Znamky>();
	}
	
	/*Do HashMapy je pridan key ID a nove vytvorena instance s predanymi hodnotami (pokud ta stejna instance uz neexistuje).*/
	public  boolean pridaniStudenta(String obor, int ID, String jmeno, String prijmeni, String datumNarozeni) {		
		if (obor.equalsIgnoreCase("Technicky") && prvkyDatabaze.put(ID,new Technicky(ID,jmeno,prijmeni,datumNarozeni))==null) {
			return true;
		}
		else if (obor.equalsIgnoreCase("Humanitni") && prvkyDatabaze.put(ID,new Humanitni(ID,jmeno,prijmeni,datumNarozeni))==null) {
			return true;
		}
		else if (obor.equalsIgnoreCase("Kombinovany") && prvkyDatabaze.put(ID,new Kombinovany(ID,jmeno,prijmeni,datumNarozeni))==null) {
			return true;
		}
		else{
			return false;
		}
	}
	
	/*Kazdemu studentovi je vytvorena instance tridy Znamky a ta je pridana do HashMapy (klicem je ID).*/
	public  boolean vytvorZnamky(int ID, int soucet, int pocet) {
		if (databazeZnamek.put(ID, new Znamky(ID, soucet, pocet))==null)
			return true;
		else 
			return false;
	}
	
	/*Pridanim znamky se zvysi soucet a pocet znamek, ale nevytvari se uplne nova znamka v databazi.*/
	public  boolean setZnamku(int ID, int znamka){
		if (studentJeVDatabazi(ID)) {
			getStudentoviZnamky(ID).setNovaZnamka(znamka);
			return getStudent(ID).setStudijniPrumer(getStudentoviZnamky(ID).getPrumer());
		}
		else {
			System.out.println("Znamka nebyla zapocitana");
			return false;
		}
	}
	
	 /* Ziskany pocet neni pocet studentu v databazi, ale pocet vytvorenych studentu. 
	  * Pomoci tohoto ziskavaji studenti sve ID a zadnemu studentovi nebude prirazeno ID drive odstraneneho studenta  */
	public int getPocetStudentu() {					
		return	Student.getPocetStudentu();			
	}
	
	/*Metoda vytiskne text a pocet studentu daneho oboru, pomoci zjisteni velikosti seznamu studentu dane tridy)*/
	public void getPocetVeSkupinach() {
		System.out.println("Technicky obor: " + vytvorList(Technicky.class, prvkyDatabaze).size());
		System.out.println("Humanitni obor: " + vytvorList(Humanitni.class, prvkyDatabaze).size());
		System.out.println("Kombinovany obor: " + vytvorList(Kombinovany.class, prvkyDatabaze).size());
	}
		
	/*Metoda podle ID (klice v HashMape) vraci instanci Znamek (hodnotu pro dany klic)*/
	public static  Znamky getStudentoviZnamky(int ID) {	
		return databazeZnamek.get(ID);	
	}
	
	/*Metoda podle ID (klice v HashMape) vraci instanci Studenta (hodnotu pro dany klic)*/
	public  Student getStudent(int ID) {	
		return prvkyDatabaze.get(ID);	
	}
	
	/*Metoda overi, jestli je student v databazi, vytiskne informace o studentovy a vola metodu schopnost(), kterou si kazda trida implementuje podle sveho. */
	public void schopnostStudenta(int ID) {
		if (studentJeVDatabazi(ID)) {
			printStudent(ID);
			getStudent(ID).schopnost(getStudent(ID).getDatumNarozeni());
		}
	}
	
	/*Vraci booleanovou hodnotu, zjistuje jestli je student v databazi.*/
	public boolean studentJeVDatabazi(int ID) {
		if (getStudent(ID)!=null)
			return true;
		else {
			System.out.println("Student s ID "+ ID + " neni v databazi");
			return false;
		}
	}
	
	/*Metoda vraci nazev tridy instance*/
	public String getObor() {
		return getStudent(ID).getClass().getName();
	}
	
	/*Podle ID vyhleda studenta a pomoci metod implementavanych v tride Student vypise informace v danem formatu.*/								
	public void printStudent(int ID) {
		if (studentJeVDatabazi(ID)) {
			System.out.printf("%-7d %-12s %-15s %-14s %-1s %.2f ", getStudent(ID).getID(), getStudent(ID).getJmeno(), getStudent(ID).getPrijmeni() , getStudent(ID).getDatumNarozeni() , "prumer:",  getStudent(ID).getStudijniPrumer());
			System.out.println("");		
		}
	}
	
	/*Pokud studenta podle ID najde, je odstranen z databaze.*/
	public boolean vymazStudenta(int ID){
		if(studentJeVDatabazi(ID)) {
			printStudent(ID);
			prvkyDatabaze.remove(ID);
			return true;
			}
		else 
			return false;
	}
	
	/*Vypsani cele databaze*/
	public void vypisDatabaze() {
		Set<Integer> seznam= prvkyDatabaze.keySet();
		for(Integer ID:seznam)
			printStudent(ID);
	}
 
	/* Metode seradSeznamAVypis se preda text, ktery ma byt pred vypisem a seznam studentu v jednom oboru. Ta seznam seradi a vypise. */
	public void seradVsechny() {									
	    seradSeznamAVypis("\nStudenti technickeho oboru:", vytvorList(Technicky.class, prvkyDatabaze));
	    seradSeznamAVypis("\nStudenti humanitniho oboru:", vytvorList(Humanitni.class, prvkyDatabaze));
	    seradSeznamAVypis("\nStudenti kombinovaneho oboru:", vytvorList(Kombinovany.class, prvkyDatabaze));																
	}
	
	/*Metoda vytvori z HashMap seznam studentu v danem oboru*/
	public ArrayList<Student> vytvorList(Class<?> skoumanaTrida, Map<Integer,Student> mapa) {
		List<Student> seznamStudentu  = new ArrayList<>();
		Set<Integer> seznam= mapa.keySet();
		for(Integer ID:seznam) {
			if(skoumanaTrida.isInstance(mapa.get(ID))) {
				seznamStudentu.add((Student) getStudent(ID));
			}	 
		}
		return (ArrayList<Student>) seznamStudentu;
	}
	
	/*Metoda seradi seznam podle prijmeni*/
	public  void seradSeznamAVypis(String text,List<Student> o) {
		 o.sort(Comparator.comparing(e -> e.getPrijmeni()));
		 System.out.println(text);	
		 vypisSeznam(o);
	}
	
	/*Metoda vypise studenty ze seznamu*/
	public  void vypisSeznam(List<Student> o) {
		    for(int i = 0; i < o.size(); i++)   
		    	printStudent(o.get(i).getID());
	}

	/* Metoda zadava, pro ktere tridy (obory) maji byt spoctene spolecne prumery a jaky text ma byt vypsany.*/
	public void getSpolecnePrumery() {														
		spoctiZnamky(Technicky.class, prvkyDatabaze, "\nStudenti technickeho oboru:");
		spoctiZnamky(Humanitni.class, prvkyDatabaze, "\nStudenti humanitniho oboru:");
		spoctiZnamky(Kombinovany.class, prvkyDatabaze, "\nStudenti kombinovaneho oboru:");
	}

	/* Metoda zjisti celkovy soucet a pocet studijnich prumeru (nenulovych) v zadane tride.
	 *  Vytiskne ji predany text, preda udaje metode, ktera je videli a vypise.*/
	public void spoctiZnamky(Class<?> zkoumanaTrida, Map<Integer,Student> mapa, String text) {
		float prumer = 0;
		float pocet = 0;							
		float a = 0;
		Set<Integer> seznam= mapa.keySet();
		for(Integer ID:seznam) {
			a = getStudent(ID).getStudijniPrumer();
			if(zkoumanaTrida.isInstance(mapa.get(ID)) && a!= 0.0f ) {
				prumer = prumer + a;
				pocet++;		
			}	 
		}
		System.out.println(text);
		Znamky.vypisPrumery(prumer,pocet);
	}

	/* Metoda prozkouma celou databazi pomoci klicu a pro kazdeho studenta
	 *  preda informace o nem metode vlozZaznam(), ktera je pak vlozi do SQL tabulky.*/
	public void vlozStudentyDoSQL() {
		Set<Integer> seznam= prvkyDatabaze.keySet();
		for(Integer ID:seznam) {
			Student o = getStudent(ID);
			int soucet = getStudentoviZnamky(ID).getSoucetZnamek();
			int pocet = getStudentoviZnamky(ID).getPocetZnamek();
			InsertQueries i2 = new InsertQueries();
			i2.vlozZaznam(o.getID(), o.getJmeno(),o.getPrijmeni(), o.getDatumNarozeni(), o.getObor(o), soucet, pocet);
		}
		System.out.println("Data byly ulozeny do databaze");
	}

	/*Pokud metoda dostane informace o instanci, kterou jeste nema v databazi, vytvori ji (studenta a znamky pro daneho studenta).
	 * Pokud uz stejny student v databazi je, nastavi mu soucet znamek a pocet znamek, ktere dostala jako parametr.	 
	 * V obou pripadech nastavi studentovi prumer jeho znamek*/	
	public void nactiStudenta(int ID, String jmeno, String prijmeni, String datumNarozeni, String obor, int soucetZnamek, int pocetZnamek) {	  
		  if(pridaniStudenta(obor, ID,  jmeno,  prijmeni,  datumNarozeni)) {
			  vytvorZnamky(ID, soucetZnamek, pocetZnamek);
		  }
		  else {
			  getStudentoviZnamky(ID).setSoucetZnamek(soucetZnamek);
			  getStudentoviZnamky(ID).setPocetZnamek(pocetZnamek); 
		  }
		  getStudent(ID).setStudijniPrumer(getStudentoviZnamky(ID).getPrumer()); 
	}
	
	/*Metoda pro kazny zaznam zavola metodu nactiStudenta() a preda ji vsechny informace o danem studentovi jako parametr. */
	 public  boolean nactiVsechnyStudentyZSQL() {
		 Connection conn = DBConnection.connect();
		 String selectAllstudents = "SELECT * FROM dbstudentu";
		 try (PreparedStatement prStmt = conn.prepareStatement(selectAllstudents);
		      ResultSet rs = prStmt.executeQuery()) {
		      while (rs.next()) {
		    	  nactiStudenta(rs.getInt("ID"),rs.getString("jmeno"), rs.getString("prijmeni"),  rs.getString("datumNarozeni"), rs.getString("obor"), rs.getInt("soucetZnamek"), rs.getInt("pocetZnamek") );
		      }
		      return true;
		 } catch (SQLException e) {
		     e.printStackTrace();
		     return false;
		 }
	}

	 /*Metoda se pokusi najit v SQL tabulce zaznam, kde jmeno a prijmeni odpovida parametrum, ktere obdrzela metoda.
	  * Pokud takovy zaznam/zaznamy najde, zavola nactiStudenta a preda mu jako parametr vsechny udaje o nem.  */
	 public boolean pridejStudentaPodleJmena(String jmeno, String prijmeni) {
		    Connection conn = DBConnection.connect();
		    String selectStudentByName = "SELECT * FROM dbstudentu  WHERE  (jmeno = ? AND prijmeni = ?)";
		    try {
		    	PreparedStatement prStmt = conn.prepareStatement(selectStudentByName);
		    	prStmt.setString(1, jmeno);
		    	prStmt.setString(2, prijmeni);
		    	ResultSet rs = prStmt.executeQuery(); 
		    	while (rs.next()) {
		    	  nactiStudenta(rs.getInt("ID"),rs.getString("jmeno"), rs.getString("prijmeni"),  rs.getString("datumNarozeni"), rs.getString("obor"), rs.getInt("soucetZnamek"), rs.getInt("pocetZnamek") );
		    	} 
		    	return true;
		    } catch (SQLException e) {
		      e.printStackTrace();
		      return false;
		    }
	}
	
	
}