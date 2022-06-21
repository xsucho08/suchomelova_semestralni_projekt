package typyStudentu;
//import typyStudentu.*;
import schopnosti.*;

public class Kombinovany extends Student{
	
	public Kombinovany(int ID,String jmeno,String prijmeni, String datumNarozeni){
			super(ID, jmeno, prijmeni, datumNarozeni);
	}
	
	/*Implementuje metodu, ktera byla v abstrakntni tride abstraktni. Vola pomoci datumuNarozeni dane instance 
	 * metodu znameniZverokruhu() i prestupny();	 */
	@Override
	public void schopnost(String datumNarozeni) {
		Horoskop.znameniZverokruhu(Datum.tellDen(datumNarozeni), Datum.tellMesic(datumNarozeni));
		Prestupny.prestupny(Datum.tellRok(datumNarozeni));
	}
}


