package typyStudentu;
						

import schopnosti.*;

public class Technicky extends Student{
		
	public Technicky (int ID,String jmeno,String prijmeni, String datumNarozeni){
		super(ID, jmeno, prijmeni, datumNarozeni);
	}
	
	/*Vola metodu prestupny() pro datumNarozeni dane instance*/
	@Override
	public void schopnost(String datumNarozeni) {
		Prestupny.prestupny(Datum.tellRok(datumNarozeni));
	}

}
