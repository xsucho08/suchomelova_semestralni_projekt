package schopnosti;

public class Prestupny {
	
	/*Pro rok, ktery obdrzi jako parametr, zkouma jestli je rok delitelny 4 a neni delitelny 100 ->true,
	 * pokud je deliteny 100 i 400 -> true, jinak vraci false.
	 */
	public static boolean overeniPrestupnosti(int rok) {
		if (rok % 4 == 0 && rok % 100 != 0)
			return true;
		else {
			if (rok % 400 == 0)
				return true;
			else 
				return false;
		}
	}
	
	/*Vyhodnoti booleanove hodnoty metody overeniPrestupnosti() a podle nich napise, zda rok byl
	 * nebo nebyl prestupny.*/
	public static void prestupny(int rok) {
		if (overeniPrestupnosti(rok))
			System.out.println("Rok, ve kterem se student narodil byl prestupny");
		else
			System.out.println("Rok, ve kterem se student narodil nebyl prestupny");
	}
}
