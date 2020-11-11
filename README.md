# I-ASOS Zadanie 2
![Java CI with Maven](https://github.com/Interes-Group/i-asos-assignment2/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)
![License MIT](https://img.shields.io/badge/License-MIT-green)
![Java 1.8](https://img.shields.io/badge/Java-1.8-blue)

Cieľom zadania je overiť základné znalosti používania webových služieb typu SOAP a ich definovania pomocou WSDL.

Pomocou jazyka Java a frameworku Spring rozšírte prvé zadanie. Vytvorte XSD schému pre dátový model a WSDL pre webové služby. 
Vygenerujte zdrojové kódy endpointov z WSDL a implementujte webové služby pre jednotlivé funkcie API.

## XSD
V priečinku `src/main/resources/xsd` vytvorte XSD schému pre svoje doménové triedy z predchádzajúceho zadania 
(pozri kapitolu Dátový model zo zadania 1). Dedenie jednotliých tried musí zodpovedať nasledovnému UML diagramu vychádzajúcemu z prvého zadania:

![XSD UML diagram](https://lh3.googleusercontent.com/ipQo-Toi0UYb8d9Yn-lJvniKGJVlm6l5oCwxHk4L4REkgJbwPfHrTqGembXL9MOqJnbvFwoab8RkUX0zYCJZQpAmo1ym8T51mb4JTJ3P1ZvTPGxIczDSYU_SJrg4QJahMl7gdd_c)

V XSD schéme definujte správne typy elementov (string, float, atď.) a vytvorte vlastné dátové typy tam kde je to potrebné 
(nezáporné hodnoty, číselníkové hodnoty, atď.).

## WSDL
V priečinku `src/main/resources/wsdl` vytvorte WSDL dokument pre svoje SOAP webové služby. Implementujte nasledovné webové služby:

- Správa osôb:
  - Pridanie novej osoby
  - Aktualizácia existujúcej osoby
  - Vylistovanie všetkých osôb v systéme
  - Nájdenie konkrétnej osoby

- Správa poistných zmlúv:
  - Založenie poistnej zmluvy osobe
  - Aktualizácia existujúcej zmluvy
  - Vylistovanie všetkých poistných zmlúv
  - Vylistovanie poistných zmlúv pre daného poisťovateľa (podľa unikátneho číselného identifikátora osoby) - 
  vylistuje len zmluvy, kde je daná osoba poisťovateľom!

## SOAP
Pomocou maven pluginu `jaxws-maven-plugin` vygenerujte zdrojové kódy do priečinku `target/generated-sources/main/`. 
Implementujte jednotlivé webové služby vygenerované týmto pluginom. Webové služby prepojte so Spring beanami z prvého zadania.

## Inštrukcie v vypracovaniu
Pred začatím vypracovania zadania zmeňte v `pom.xml` v elemente `<developer>` id na svoje AIS a meno na vaše.
Pre úspešné implementovanie zadania je potrebné implementovať `InsuranceContractService` implementujúc rozhranie 
`sk.stuba.fei.uim.asos.assignment2.insurance.service.IInsuranceContractService`. Keďže triedu zmluvy máte vytvoriť v rámci
tohto zadania pomocou XSD schémy nemohli sme vám poskytnúť konkrétnu implementáciu service triedy. Môžte sa pokojne inšpirovať
poskytnutou `UserService`, alebo repozitárom vzorového riešenia Zadania 1. Táto trieda nebude braná do úvahy v hodnotení
tohto zadania.

#### Optional
Pre úpravu odznaku o statuse testov (hneď pod názvom), stačí v README.md upraviť prvý link pri 'Java CI with Maven' na
`https://github.com/Interes-Group/asos-zadanie-2-<TVOJ GITHUB USERNAME>/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master`
 (samozrejme bez < > znakov).

## Hodnotenie
Zadanie je hodnotené **25 bodmi**. _**Zadanie je nutné odovzdať do 22.11. 23:59!**_

Zadanie si naklonujte z repozitára zadania. Svoje vypracovanie nahrajte do vášho repozitára pre toto zadanie pomocou programu Git 
(git commit + git push). Vypracovanie môžete “pusho-vať” priebežne.

Automaticky sa bude hodnotiť iba master branch, na ktorej budú spustené aj hodnotiace testy pri každej aktualizácii kódu. 
Testy je prísne zakázané upravovať!  Kvôli testom je nutné dodržať cesty (xsd, wsdl, generované classy),
 ktoré sú stanovené v zadaní! Iba body získané z poslednej verzie vypracovania (t.j. z posledného commitu) sa berú do úvahy. 
 Okrem testov sa bude kód kontrolovať aj ručne.

Manuálne hodnotenie bude brať do úvahy správne použitie XSD schémy a WSDL. Správne dedenie, prepoužitie kódu, použité typy elementov, 
vhodné pomenovanie elementov a služieb.


## Dátový model zo zadania 1

### Osoba
O každej osobe systém eviduje nasledovné dáta:

 - unikátny číselný identifikátor (ID),
 - meno,
 - priezvisko,
 - rodné číslo,
 - e-mail,
 - adresa trvalého pobytu,
 - korešpondenčná adresa,
 - zoznam zmlúv (zmluvy uzatvorené osobou)

Pokiaľ korešpondenčná adresa osoby nie je definovaná, použije sa jeho adresa trvalého pobytu. Adresa sa skladá z:

 - PSČ,
 - názvu obce,
 - názvu ulice,
 - orientačného čísla domu

### Typy zmlúv
Systém eviduje dva typy zmlúv: životné poistenie a neživotné poistenie. Každá zmluva má priradené:

 - unikátne číslo zmluvy (ID),
 - dátum vzniku,
 - ID poisťovateľa (osoba, ktorá zmluvu uzavrela),
 - dátum začiatku a konca poistenia,
 - výšku poistného plnenia (suma, na ktorú sa poisťuje),
 - výšku mesačnej splátky.

#### Životné poistenie
Medzi zmluvy životného poistenia patria:

 - Cestovné poistenie:
   - ID poistenca
   - v rámci EU / mimo EU
   - účel cesty (referenciu číselníková hodnota, povolené sú: pracovne, rekreačne, šport, a pod.)
 - Úrazové poistenie:
   - referenciu na poistenca
   - trvalé následky úrazu (poistná suma v eurách)
   - Smrť v dôsledku úrazu (poistná suma v eurách)
   - Denné odškodné za hospitalizáciu (poistná suma v eurách)
   - Územná platnosť (číselníková hodnota, povolené hodnoty sú: “Slovensko”, “Svet”, “Svet + Slovensko”)

#### Neživotné poistenie:
Medzi zmluvy neživotného poistenia patria:

 - Poistenie domácnosti:
   - typ nehnuteľnosti (číselníková hodnota, povolené hodnoty sú: "Byt", "Rodinný dom - murovaný", "Rodinný dom - drevený")
   - adresa nehnuteľnosti
   - hodnota nehnuteľnosti v eurách
   - hodnota zariadenia domácnosti v eurách
 - Poistenie domu a bytu:
   - typ nehnuteľnosti (číselníková hodnota, povolené hodnoty sú: "Byt", "Rodinný dom - murovaný", "Rodinný dom - drevený")
   - adresa nehnuteľnosti
   - hodnota nehnuteľnosti v eurách
   - pripoistenie garáže (áno / nie)