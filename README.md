Banki adminisztráció

Készítsük el egy bank ügyfelek kezelését, és az ügyfelekkel kapcsolatos tevékenységek adminisztrálását elősegítő rendszert.

A webes felületen keresztül az ügyfelek érik el a bankolási funkciókat.
	• A főoldalon lehetőségünk van bejelentkezésre. Bejelentkezéskor meg kell adnunk a felhasználónevünket, jelszavunkat, számlaszámunkat (ha több van, akkor a legelsőt), valamint ellenőrző PIN kódunkat. Ezen felül a felhasználó választhat biztonságos üzemmódot is, ekkor minden művelet (számlatörténet lekérdezés, illetve átutalás) előtt a weblapismét bekéri a felhasználó jelszavát. A bejelentkezést követően bármikor kijelentkezhet az ügyfél.	
	• Sikeres bejelentkezés esetén lehetősége nyílik megtekinteni a számlái egyenlegeit (egy ügyfélhez legalább egy, de tetszőlegesen sok számla tartozhat), valamint azok történetét (átutalások, betétek, kivétek listája) egy hónapra visszamenőleg.	
	• Lehetősége van új átutalást megadni (ha a számla nincs zárolva), ekkor ki kell tölteni az összeget, célszámla tulajdonosát, valamint számlaszámát, majd elküldhetjük az utalást, ekkor az összeg azonnal levonódik az egyenlegből (az átutalandó összeg nem lehet nagyobb a rendelkezésre álló egyenlegnél). Amennyiben a célszámla is a banknál van, akkor ott is meg kell jelennie az összegnek, mint befolyó utalás.
	
A grafikus felületet a banki alkalmazottak használják a tranzakciók (betétek, kivételek és átutalások) kezelésére.
	• Az alkalmazottnak be kell jelentkeznie az alkalmazásba, ezt követően kiválaszthatja az ügyfelet, illetve annak bankszámláját.	
	• Adott bankszámlára végezhet betétet, kivétet, illetve átutalást. Az első két esetben csak az összeget kell megadnia, míg a harmadik esetben (a webes felülethez hasonlóan) a célszámla adatait (számlaszám, tulajdonos) is. A tranzakció elküldésével az összeg azonnal levonódik/hozzáadódik az egyenleghez (átutaláskor, illetve kivételkor az összeg nem lehet nagyobb a rendelkezésre álló egyenlegnél). Amennyiben a célszámla is a banknál van, akkor ott is meg kell jelennie az összegnek, mint befolyó utalás.	
	• Egy számla zárolható is, ekkor nem lehet semmilyen tevékenységet kezdeményezni a számlán (sem a grafikus, sem a webes felületen), amíg a zárolást fel nem oldja valamely alkalmazott. A zároláshoz a program kérjen megerősítést.
	
Az adatbázis az alábbi adatokat tárolja (ezek még nem feltétlenül a fizikai adattáblák):
	• ügyfelek (teljes név, felhasználónév, jelszó, PIN kód);
	• alkalmazottak (teljes név, felhasználónév, jelszó);
	• számlák (felhasználó, számlaszám, létrehozás dátuma);
	• tranzakciók (típus, forrás számlaszám, cél számlaszám, cél tulajdonos neve, dátum, összeg).

Funkcionális követelmények: 
VENDÉG:
•	Bejelentkezés felhasználónévvel, jelszóval, számlaszámmal és PIN-kóddal
•	Bejelentkezés biztonsági móddal
•	Számla(k) egyenlegének lekérdezése
•	Számla(k) történetének lekérdezése
•	Új utalás indítása	
ALKALMAZOTT:
•	Bejelentkezés
•	Ügyfél és számlaszám kiválasztása
•	Bankszámlára betétel
•	Bankszámláról kivétel
•	Átutalás
•	Számla zárolása
•	Számla zárolásának feloldása
		
Nem funkcionális követelmények:

•	Biztonság: Belépés után szükséges az ellenőrző PIN-kód megadása is. Továbbá lehetősége van az ügyfélnek minden művelet utáni  megerősítést kérni.
•	Megbízhatóság
•	Gyorsaság
•	Felhasználóbarát felület

Szakterületi fogalmak:

•	Egyenleg: Adott számlaszámon jelenleg rendelkezésre álló pénzösszeg.
•	Átutalás: Két számlaszám közti pénzösszeg mozgatása. Szükséges egy küldő számlaszám, egy cél számlaszám, célszámla tulajdonosa 		     illetve az átutalás összege.	
•	Betét: Adott számlaszámra bizonyos összeg rátétele. Ekkor a számlaszám egyenlege nő a betét összegével.
•	Kivét: Adott számlaszámról bizonyos összeg kivétele. Ekkor a számlaszám egyenlege csökken a kitét összegével.

Szerepkörök:
•	Ügyfél 
•	Alkalmazott 
