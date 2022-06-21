
 Java project (2019/2020)
 
<h2>Účel programu</h2>
Program slouží k ukládání studentů do databáze, ve které se jednotliví studenti dají vyhledat podle
svých ID. Studenti mohou být různých oborů, z čeho se odvíjí jejich schopnosti. Studentům je možno
přidávat známky (a vypočítat průměr), vypsat informace o studentovi, vymazat ho z databáze a předvést
jeho schopnost (všechno zadáním jeho ID). Program umožňuje vypsat studenty v oborech seřazené
abecedně, vypsat společné průměry studentů a počet studentů v jednotlivých oborech. Je možné načíst
a uložit všechny údaje z/do SQL databáze, odstranit studenta (podle ID) nebo ho načíst (podle jména a
příjmení) z SQL tabulky.

<h2>Popis implementace</h2>
<p.Studenti jsou ukládáni do hash mapy, jejich ID je klíč. Známky jsou uchovávány v další hash mapě, kde
ID je opět klíčem.</p>
<p>Nejsou uchovávány jednotlivé známky, ale součet všech známek, jenž student dostal a jejich celkový
počet. Díky uchovávání dat o známkách tímhle způsobem lze kdykoliv přidat studentovi známku a
vypočítat jeho aktuální studijní průměr a to i tehdy, pokud je student načten z SQL databáze. Žádná
data si při vkládání studentů do SQL databáze neztrácí a studenty je možné z SQL databáze znovu
vložit do hash mapy.</p>
<p>Pokud při načítání studenta z SQL databáze už student se stejným ID v hash mapě je, obnoví se data o
součtu a počtu známek a nový student se nevytváří. Pokud ještě v hash mapě žádný student s tímhle ID
není, student se vytvoří pomocí metody přidatStudenta().</p>
<p>Pokud se z hash mapy má do SQL databáze přidat student, který už v SQL tabulce je, není přidán nový
záznam, ale použije se update na jeho součet a počet známek, aby informace o něm nebyly zastaralé.
Při zvolení ukončení aplikace je provedené odpojení od SQL databáze.</p>

<h2>Třídy a metody</h2>

<h4>Abstraktní třída Student</h4>
 <ul>➔ Třída Technicky</ul>
 <ul>➔ Třída Humanitni</ul>
 <ul>➔ Třída Kombinovany</ul>

<h4>Třída Známky</h4>
<li>metody pracující se součtem a počtem známek daného studenta</li>

<h4>Třída Test</h4>
<li>metoda main - dokud uživatel aplikaci neukončí, jsou mu nabízeny akce, které může provést</li>

<h4>Třída Volba</h4>
<li> metoda menu - tiskne do konzole seznam akcí</li>
<li> metody k ověření, zda uživatel zadal správné hodnoty</li>

<h4>Třída Databaze</h4>
<li> metody pracující s hash mapami</li>

<h4>Třída Datum</h4>
 
● metody zjišťující číslo na pozici (den, měsíc, rok) vzhledem k tečkám ve Stringu datum
(dd.mm.rrrr)

<h4>Třída Horoskop</h4>
● metoda vyhodnocující ze dne a měsíce narození, co za znamení student je

<h4>Třída Prestupny</h4>
● metoda vyhodnocující jestli rok narození byl přestupný

<h4>Třídy DBConnection, InsertQueries, UpdateQueries, SelectQueries, DeleteQueries</h4>
● metody pracují s SQLlite databází

<h3>Podrobnější popis tříd a metod, kterých význam nemusí být zřejmý z názvu</h3>
<p>Třída Student je abstraktní a implementuje metody společné pro všechny studenty. Deklaruje jednu
abstraktní metodu schopnost().</p>
<p>Odvozené třídy (Technicky, Humanitni, Kombinovany) představují obory, do kterých studenti
mohou patřit, Každá odvozená třída si implementuje metodu schopnost() podle toho, jakou schopnost
studenti daného oboru mají.</p>
<p>Metoda vytvorList() vytváří seznam instancí (studentů) dané třídy (oboru). Jako parametr je jí
předáván název třídy a hash mapa. Metoda třídí studenty podle metody isInstance().</p>
<p>Seřazení studentů podle abecedy probíhá pomocí metody seradSeznamAVypis(). Abecedně seřadí
seznam studentů. který je jí předán. Jsou využívány metody compareTo() a getPrijmení(). Je nutné
jí předat seznam studentů, jen konkrétního oboru.</p>
<p>getSpolecnePrumery() funguje podobně. Pomocí seznamu je počítán společný studijní průměr všech
studentů v daném oboru. Započítávány jsou jen nenulové průměry.</p>
<p>getPocetVeSkupinach() zjišťuje počet studentů v seznamu (velikost seznamu). Je jí předáván seznam
studentů jen daného oboru.</p>
<p>Metody, které vypisují všechny studenty v mapě a celu SQL databázi jsou pro kontrolu vložených údajů</P.
