package typyStudentu;
						
/*Abstraktni trida, poskytuje spolecne metody pro odvozene tridy (Technicky, Humanitni a Kombinovany)*/
public abstract class Student implements Comparable<Student>{
	
	protected int ID;
	private String jmeno;
	private String prijmeni;
	private String datumNarozeni;
	private float studijniPrumer;
	private static int pocetStudentu;
	    
	public Student(int ID,String jmeno,String prijmeni, String datumNarozeni){
			this.ID=ID;
			this.jmeno=jmeno;
			this.prijmeni=prijmeni;
			this.datumNarozeni=datumNarozeni;
			pocetStudentu++;
	}
		
	/*Abstraktni metoda, kterou si kazda trida implementuje sama*/
	public abstract void schopnost(String datumNarozeni);
		 
	public String getJmeno(){
		return jmeno;
	}
		
	public String getPrijmeni(){
		return prijmeni;
	}
		
	public String getDatumNarozeni(){
		return datumNarozeni;
	}
		
	public int getID(){
		return ID;
	}
					
	public float getStudijniPrumer() {
		return studijniPrumer;
	}
		
	public static int getPocetStudentu() {
		return pocetStudentu;
	}
				
	public boolean setStudijniPrumer(float studijniPrumer) {
		if (studijniPrumer<0 || studijniPrumer>5){
			System.out.println("Chybny prumer");
			return false;
		}
		this.studijniPrumer = studijniPrumer;
		return true;
	}
		
	public String getObor(Student a) {
		if(a instanceof Technicky) {
			return "Technicky";
		}
		else if (a instanceof Humanitni) {
			return "Humanitni";
		}
		else if (a instanceof Kombinovany) {
			return "Kombinovany";
		}
		else 
			return "zadny obor";
		}
			
	/*Porovnava String(primeni) s objektem student, vraci cislo ktere oznacuje, na jake pozici v poradi je oproti zadanemu objektu.*/
	 @Override 
	 public int compareTo(Student o) {
	    return this.prijmeni.compareTo(o.getPrijmeni());
	  }
	  
	
}
