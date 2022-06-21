package schopnosti;

public class Datum {

	static  int a;
	static int den;
	static int mesic;
	static int rok;

	/*Metoda ze Stringu datum vezme cast pred prvni teckou a udela z ni cislo = den */
	public static int tellDen(String datumS) {
		String denS = datumS.split("\\.")[0];
		den =Integer.parseInt(denS); 
		return den;
	}
	
	/*Metoda ze Stringu vezme cast mezi privni a druhou teckou a udela z ni cislo = mesic */
	public static int tellMesic(String datumS) {
		String mesicS = datumS.split("\\.")[1];
		mesic =Integer.parseInt(mesicS);
		return  mesic;
	}
	
	/*Metoda ze Stringu vezme cast za druhou teckou a udela z ni cislo = rok */
	public static int tellRok(String datumS) {
		String rokS = datumS.split("\\.")[2];
		rok =Integer.parseInt(rokS);  			
		return rok;
	}
	
	/*Pokud zadany String neni mozne rozsekat na den, mesic a rok, metoda vraci false. Jinak vypise datum a vraci true*/
	public boolean vypisR(String datumS) {
		if (tellDen(datumS) ==0 || tellMesic(datumS) == 0 || tellRok(datumS) == 0) {
			System.out.println("Student ma spatne vlozene datum narozeni, neni mozne z neho zjistit informace");		
			return false;
		}
		else{
			System.out.println("Den: " + tellDen(datumS)  + ". mesic: "+ tellMesic(datumS) + ". rok: "+ tellRok(datumS));		
			return true;
		}
	}
	
}
