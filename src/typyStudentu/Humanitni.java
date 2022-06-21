package typyStudentu;

import schopnosti.*;


public class Humanitni extends Student{
		
		public Humanitni (int ID,String jmeno,String prijmeni, String datumNarozeni){
			super(ID, jmeno, prijmeni, datumNarozeni);
		}
		
		/*Vola metodu znameniZverokruhu() pro datumNarozeni dane instance*/
		@Override
		public void schopnost(String datumNarozeni) {
			Horoskop.znameniZverokruhu(Datum.tellDen(datumNarozeni), Datum.tellMesic(datumNarozeni));
		}
}

