# I. Feladatleírás:

### Banki adminisztráció

Készítsük el egy bank ügyfelek kezelését, és az ügyfelekkel kapcsolatos tevékenységek adminisztrálását elősegítő rendszert.

A webes felületen keresztül az ügyfelek érik el a bankolási funkciókat.

	• A főoldalon lehetőségünk van bejelentkezésre. Bejelentkezéskor meg kell adnunk a felhasználónevünket, jelszavunkat, számlaszámunkat (ha több van, akkor a legelsőt), valamint ellenőrző PIN kódunkat. A bejelentkezést követően bármikor kijelentkezhet az ügyfél.	
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

Az **import.sql** fájl tartalmazza a kezdeti SQL utasításokat. Enélkül a H2 adatbázis mindig üresen indulna el.

Projekt futtatása a pom.xml fájl segítségével:
pom.xml -> Jobb klikk -> Run Mavel -> Goals -> Goals: **spring-boot:run** 

Ezután a böngészőben a http://localhost:8080 címen érhető el az applikáció.

### 2. Adatbázis terv
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/db_uml.jpg)

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
				•EmployeeApiController.java
				•ClientApiController.java
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
	• GET /employee : Bejelentkezett alkalmazottak 
	• POST /employee/login :  ALkalmazott bejelentkezése
	• POST /employee/logout : Alkalmazott kijelentkezése
	
##### Client:
	• GET /client/greet : Felhasználó üdvözlése
	• POST /client/login :  Felhasználó bejelentkezése
	• POST /client/logout : Felhasználó kijelentkezése

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
	• GET /api/transaction/list/{status} : Tranzakciók listázása státusz szerint
	• POST /api/transaction/create : Új tranzakció indítása

### 5. Szekvenciadiagram - Ügyfél bejelentkezése
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/sequence_UML.JPG)

# IV. Frontend megvalósítása	

### 1. Használati esetek:	
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/usecase1.PNG)
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/usecase2.PNG)

### 2. Felhasznált eszközök:
	
	A frontend fejlesztése az alábbi eszközökkel készült:
	
	• Git és Github a verziókövetésért
	• Visual Studio Code fejlesztői környezet
	• Typescript programozási nyelv 
	• Angular keretrendszer Node.js és NPM környezettel

### 3. Fejlesztőkörnyezet:	

	1. A banki alkamlazás frontend-je a Githubon a https://github.com/tankaaniko/alkfejl_bank_prototype 
	repositoryban található.
	2. A projektet vagy a Githubon történő Clone or Download lehetőséggel
	vagy a git clone https://github.com/tankaaniko/alkfejl_bank_prototype.git paranccsal elérhetővé tesszük a lokális gépen.
	3. Projektkönyvtárat megnyitjuk a Visual Studio Code-ban
	4. ctrl + Ö billentyűkombinációval megjelenik a parancsablak, melybe az alábbi parancsokat kell begépelni
		npm install
		npm start
	5. npm start parancs után a projekt elérhető a http://localhost:4200/ -es címen	
	
### 4. Könyvtárszerkezet:
	bank-app/src/app
		account-list : Számlák listázása
			account-list.component.css
			account-list.component.html
			account-list.component.spec.ts
			account-list.component.ts
		client-accounts: Felhasználó saját számlái
			client-accounts.component.css
			client-accounts.component.html
			client-accounts.component.spec.ts
			client-accounts.component.ts
		client-transaction: Felhasználó saját tranzakciói
			client-transaction.component.css
			client-transaction.component.html
			client-transaction.component.spec.ts
			client-transaction.component.ts
		login-client: Felhasználó bejelentkezése
			login-client.component.css
			login-client.component.html
			login-client.component.spec.ts
			login-client.component.ts
		login-employee: Alkalmazott bejelentkezése
			login-employee.component.css
			login-employee.component.html
			login-employee.component.spec.ts
			login-employee.component.ts
		main-page: Főoldal
			main-page.component.css
			main-page.component.html
			main-page.component.spec.ts
			main-page.component.ts
		menu: Menü
			menu.component.css
			menu.component.html
			menu.component.spec.ts
			menu.component.ts
		routing: Útvonalak
			routing-module.ts
		transaction-form: Új tranzakció
			transaction-form.component.css
			transaction-form.component.html
			transaction-form.component.spec.ts
			transaction-form.component.ts
		transaction-list: Tranzakciók listázása
			transaction-list.component.css
			transaction-list.component.html
			transaction-list.component.spec.ts
			transaction-list.component.ts
			
		account.ts: Számla
		client.ts: Felhasználó
		transaction.ts: Tranzakciók : betét,kivét,utalás
	
		account-service.ts
		auth-service.ts
		client-service.ts
		transaction-service.ts
		
		app-module.ts
		app-component.html
		app-component.css
		app-component.ts
		app-component.spec.ts
		
	bank-app/src/e2e: tesztelés
		all-accounts.e2e-spec.ts
		all-transactions.e2e-spec.ts
		app.e2e-spec.ts
		app.po.ts
		client-accounts.e2e-spec.ts
		client-transactions.e2e-spec.ts
		login-employee.e2e-spec.ts
		login.e2e-spec.ts
		new-transaction.e2e-spec.ts	
		
### 5. Felületi tervek:
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/clientlogin.PNG)
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/employeelogin.PNG)
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/clienttrans.PNG)
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/clientaccount.PNG)
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/newtransa.PNG)
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/employeeaccount.PNG)
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/employeetransa.PNG)

### 6. Megvalósítás:	
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/client-login.PNG)
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/client-accounts.PNG)
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/client-transactions.PNG)
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/new-transaction.PNG)
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/employee-login.PNG)
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/employee-accounts.PNG)
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/employee-transactions.PNG)
![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/login.PNG)

### 7. Tesztelés:	

Az kliens tesztelése e2e teszteléssel történt. Ehhez szükség volt a protractor nevű programra.
A tesztfájlok a könyvtárszerkezet **bank-app/e2e** mappájában találhatóak. Az egyes tesztesetek az oldalak közötti árirányítást ellenőrzik a h1 css szelektorok alapján.

Az tesztek a következő eseteket fedik le:
	Főoldal betöltése
	Felhasználó bejelentkezése helytelen és helyes adatokkal
	Alkalmazott  bejelentkezése helytelen és helyes adatokkal
	Új tranzakció indítása
	Alkalmazott által látott összes tranzakció és összes számlaszám adatainak megjelenése
	Felhasználó által látott saját tranzakciók és számlák megjelenése

![alt text](https://github.com/tankaaniko/alkfejl_bank/blob/master/tests.PNG)

