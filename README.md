# I. Feladatleírás:

### Banki adminisztráció

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

# II. Funkcionális követelmények: 
#### VENDÉG:

	• Bejelentkezés felhasználónévvel, jelszóval, számlaszámmal és PIN-kóddal
	• Bejelentkezés biztonsági móddal
	• Számla(k) egyenlegének lekérdezése
	• Számla(k) történetének lekérdezése
	• Új utalás indítása	

#### ALKALMAZOTT:

	• Bejelentkezés
	• Ügyfél és számlaszám kiválasztása
	• Bankszámlára betétel
	• Bankszámláról kivétel
	• Átutalás
	• Számla zárolása
	• Számla zárolásának feloldása
		
#### Nem funkcionális követelmények:

	• Biztonság: Belépés után szükséges az ellenőrző PIN-kód megadása is. Továbbá lehetősége van az ügyfélnek minden művelet utáni  megerősítést kérni.
	• Megbízhatóság
	• Gyorsaság
	• Felhasználóbarát felület

#### Szakterületi fogalmak:

	• Egyenleg: Adott számlaszámon jelenleg rendelkezésre álló pénzösszeg.
	• Átutalás: Két számlaszám közti pénzösszeg mozgatása. Szükséges egy küldő számlaszám, egy cél számlaszám, célszámla tulajdonosa 		     illetve az átutalás összege.	
	• Betét: Adott számlaszámra bizonyos összeg rátétele. Ekkor a számlaszám egyenlege nő a betét összegével.
	• Kivét: Adott számlaszámról bizonyos összeg kivétele. Ekkor a számlaszám egyenlege csökken a kitét összegével.

#### Szerepkörök:

	• Ügyfél 
	• Alkalmazott 

# III. Backend megvalósítása

### 1. Fejlesztői környezet:
#### a) Felhasznált eszközök:
	• NetBeans fejlesztői környezet
	• Spring MVC – Spring Boot technológia
	• Maven 
	• H2 adatbázis
	• Git, GitHub verziókövető rendszer
#### b) Projekt indítása:
A projektkönyvtárban Projekt Files -> **pom.xml** fájl tartalmazza a szükséges Spring Boot függőségeket:

	• JPA
	• Web
	• DevTools
	• H2 database
	• Lombok
	• Test 
	• Thymeleaf
	
Az adatbázis beállításához szükséges a Other Sources -> src/main/resources/-> **application.properties** fájl módosítása:

	spring.datasource.platform=h2

	spring.h2.console.enabled=true

	spring.h2.console.path=/h2
	
	spring.datasource.url=jdbc:h2:mem:mydb

Projekt futtatása a pom.xml fájl segítségével:
pom.xml -> Jobb klikk -> Run Mavel -> Goals -> Goals: **spring-boot:run** 

Ezután a böngészőben a http://localhost:8080 címen érhető el az applikáció.

### 2. Adatbázis terv
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/db_uml.jpg)

Az UML diagramban a táblák közötti kapcsolat esetén a sok kapcsolatot a vonal végén található fekete gömb jelzi.

### 3. Könyvtárszerkezet:
	•main
	   •java
		•hu
		   •elte
			•bank
			    •BankApplication.java
			    •api
			    	•AccountApiController.java
				•TransactionApiController.java 
			    •controller
				•AccountController.java
				•ClientController.java
				•EmployeeController.java
				•TransactionController.java
			    •entity
				•Account.java
				•Client.java
				•Employee.java
				•Transaction.java
			    •repository
				•AccountRepository.java
				•ClientRepository.java
				•EmployeeRepository.java
				•TransactionRepository.java
			    •service
				•AccountService.java
				•ClientService.java
				•EmployeeService.java
				•TransactionService.java
			  	•exceptions
					•ClientNotValidException.java
					•EmployeeNotValidException.java
	   •resources
	   	•application.properties
		•import.sql
### 4. Végpontok:
##### Employee:
	• GET /employee/greet : Alkalmazott üfvözlése
	• GET /employee/logout : Alkalmazott kijelentkezése
	• POST /employee/login :  ALkalmazott bejelentkezése
	
##### Client:
	• GET /client/greet : Felhasználó üdvözlése
	• GET /client/logout : Felhasználó kijelentkezése
	• POST /client/login :  Felhasználó bejelentkezése

##### Account:
	• GET /api/account/balance : Egyenleg lekérdezése
	• GET /api/account/transactions : Számlatörténet lekérdezése
	• PUT /api/account/transfer/{transferAmount} : Új átutalás
	• PUT /api/account/deposit/{amount} : Új betét
	• PUT /api/account/withdraw/{amount} : Új kivétel
	• PUT /api/account/lock : Számla zárolása
	• PUT /api/account/unlock : Számla zárolásának feloldása
	
#### Transaction:
	• GET /api/transaction/list : Összes tranzakció listázása
	• GET /api/transaction/list/{sourceAccountNumber} : Saját tranzakciók listázása
	• POST /api/transaction/create : Új tranzakció indítása



	
		
