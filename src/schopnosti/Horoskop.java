package schopnosti;


public class Horoskop {
	
private static String znameni;

	/*Overi, ze cisla ktere obdrzela nejsou nuly. Podle mesice (switch), porovna den
	  a vysledek ulozi do znameni, ktere pak vytiskne.	 */
	public static void znameniZverokruhu(int den, int mesic) {
		if(den  == 0 || mesic == 0)
			znameni = "zadan spatny format";
		else {
			switch (mesic) {
				case 1: znameni = (den <= 19) ? "Kozoroh" : "Vodnar";
					break;
				case 2: znameni = (den <= 17) ? "Vodnar" : "Ryby";
					break;
				case 3: znameni = (den <= 19) ? "Ryby": "Beran";
					break;
				case 4: znameni = (den <= 19) ? "Beran" : "Byk";
					break;
				case 5: znameni = (den <= 20) ? "Byk" : "Blizenci";
					break;
				case 6: znameni = (den <= 20) ? "Blizenci" : "Rak";
					break;
				case 7: znameni = (den <= 22) ? "Rak" : "Lev";
					break;
				case 8: znameni = (den <= 22) ? "Lev" : "Panna";
					break;
				case 9: znameni = (den <= 22) ? "Panna": "Vahy";
					break;
				case 10: znameni = (den <= 22) ? "Vahy": "Stir";
					break;
				case 11: znameni = (den <= 21) ? "Stir" : "Strelec";
					break;
				case 12: znameni = (den <= 21) ? "Strelec" : "Kozoroh";
					break;
				default: znameni = "Doslo k chybe";
					break;
			}
		}
	System.out.println("Znameni zadaneho studenta je: " + znameni);
	}
}
