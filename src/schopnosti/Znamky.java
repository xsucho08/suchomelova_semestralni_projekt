package schopnosti;				

public class Znamky {
	
	protected int ID;
	private int pocetZnamek;
	private int soucetZnamek;
	
	/* Instance Znamek obsahuji pouze ID, soucet znamek a pocet znamek, neukladaji jednotlive znamky*/
	public Znamky(int ID,int soucetZnamek,int pocetZnamek){
		this.ID=ID;
		this.setSoucetZnamek(soucetZnamek);
		this.setPocetZnamek(pocetZnamek);
	}

	public  int getSoucetZnamek(){
		return soucetZnamek;
	}
	
	public  int getPocetZnamek(){
		return pocetZnamek;
	}
	
	/*Overeni, jestli je ji predana znamka znamka ve spravnem rozmezi.
	 * Pokud ano zvedne pocet znamek o jedna a soucet o hodnotu, ktera byla predana.	 */
	public  void setNovaZnamka(int znamka) {
		if (znamka<1 || znamka>5){
			System.out.println("Chybna znamka");
		}
		else {
			setPocetZnamek(getPocetZnamek() + 1);
			setSoucetZnamek(getSoucetZnamek() + znamka);	
		}
	}
	
	/* Vypocitava jednotlive prumery, pokud dana instance nema zadnou znamku, vraci 0)*/
	public float getPrumer() {
		if (getPocetZnamek()>0) {
			float a = (float) getSoucetZnamek() / (float)getPocetZnamek();
			return a;
		}
		else if((getPocetZnamek()==0))
			return 0;
		else {
			System.out.println("problem s ctenim znamky");
			return 0;
		}
	}
	
	public void setSoucetZnamek(int soucetZnamek) {
		this.soucetZnamek = soucetZnamek;
	}

	public  void setPocetZnamek(int pocetZnamek) {
		this.pocetZnamek = pocetZnamek;
	}
	
	/*Metoda podeli hodnoty, ktere jsou ji predany, pokud by to neznamenalo delit nulou.*/
	public static void vypisPrumery(float prumer, float pocet) {
		if (pocet!=0) {
			float prumerSpolecny = prumer/pocet;
			System.out.printf("Spolecny prumer studentu je %.2f%n", prumerSpolecny);
		}
		else 
			System.out.println("Spolecny prumer studentu nejde spocitat");
	}

}
