## Toteutusdokumentti

### Ohjelman yleisrakenne

Ohjelman yleisrakenne on seuraava:![rakenne](https://github.com/selsama/tiralabra/blob/master/Dokumentaatio/rakenne.jpeg)
Käyttöliittymä-luokka rakentaa graafisen käyttöliittymän hyödyntäen JavaFX:ää Se kutsuu Peli-luokkaa ja hyödyntää apuluokkaa Sijainti. Peli-luokka hallinnoi pelin kulkua. Se tarjoaa metodit siirtojen tekemiselle ja pelitilanteen tarkastelulle. Sillä on yhtenä luokkamuuttujista oma Tekoäly-instanssi. Tekoäly-luokka koostuu metodeista, joiden avulla se arvioi pelitilannetta ja päättää, minkä siirron tekee. Näistä tärkein on minMax, joka on minimax-algoritmi alpha-beta-karsinnalla. Tilanteen hyvyyttä arvioi metodi laskeTilanteenHyvyys, joka laskee leveyshaulla kissan etäisyyden kustakin reunaruudusta. Tekoäly-luokasta löytyy myös metodi pelin päättymisen tarkastamiseen.
Näiden pääluokkien lisäksi ohjelmassa on useampia pikkuluokkia: Sijainti (joka tallettaa x- ja y-koordinaatin pelilaudalla), Siirto (joka tallettaa siirtoa vastaavan Sijainnin, ja arvon kuinka hyvä siirto on), sekä omat tietorakenteet Jono (vastaava kuin Javan ArrayDeque), Lista (vastaava kuin ArrayList) ja Lomitusjärjestäjä, joka järjestää annetun Listan lomitusjärjestämisellä.

## Aika- ja tilavaativuudet

Seuraavassa n on pelilaudan koko (siis esim. 10x10 -laudalla n = 100).

Metodi laskeTilanteenHyvyys kutsuu leveyshakua (aikavaativuus n+m: Tiedetään, että jokaisella pelilaudan ruudulla on max 4 kaarta, joten m = 4n, joten kokonaisaikavaativuus on 5n -> O(n). Tilavaativuus 3n -> O(n)) ja etäisyydetUlos-metodia (aikavaativuus: käy taulukon jokaisen reunan läpi kerran -> 4 x √n ja tallentaa ne Listaan, keskimäärin O(1) -> O(√n), tilavaativuus: olemassaolevan taulukon lisäksi max. 4 x √n kokoinen lista -> O(√n) ja käyttää muuten vakioaikaisia ja -kokoisia operaatioita -> kokonaisaikavaativuus O(n), kokonaistilavaativuus O(n) .

Metodi minMaxinVikaTaso kutsuu metodia laskeTilanteenHyvyys ja käyttää muuten vakioaikaisia operaatioita: aikavaativuus O(n). Tilavaativuus vakio + parametrina saatu taulukko (jonka tilavaativuus lasketaan tuonnempana): O(1).

Metodia minMax kutsutaan rekursiivisesti 3 kertaa. Sen pahimman tapauksen ("alpha-beta karsinta ei auta mitään") aikavaativuus on siis 1.krsMahdSiirrot x (2.krsMahdSiirrot x (3.krsMahdSiirrot x O(n))). Nyt kissan siirrolla 1. ja 3. kierroksen mahdollisten siirtojen määrä on vakio, max. 4, ja 2. kierroksen siirtojen määrä on max. n: 4xnx4xn = 16n² -> O(n²). Jos taas on seinäpelaajan siirtovuoro, 1. ja 3. kierroksen siirtojen määrä on n, ja toisen 4: nx4xnxn = 4n³ -> O(n³).
Tilavaativuus: Metodi tarvitsee joka kierroksella uuden taulukon mahdollisille siirroille, ja muuten sen tilavaativuus on vakio + parametrina saatu taulukko (sama, jonka myös minMaxinVikaTaso saa parametrina, lasketaan tuonnempana). Koska mahdolliset siirrot on tallennettu taulukkoon, ne vievät yhtä paljon tilaa kuin aikaakin, ja metodin kokonaistilavaativuus on kissan aloittaessa O(n²) ja seinäpelaajan aloittaessa O(n³).

Metodi laskeSiirto laskee ensin mahdolliset siirrot 1. kierrokselle ajassa O(1) ja tilassa O(n), sitten kopioi saamansa taulukon ajassa O(n) ja tilassa O(n), ja sitten kutsuu minMaxia. Siis metodin aikavaativuus on O(n³) ja tilavaativuus O(n³).

Siis tekoäly valitsee siirron ajassa O(n³) ja vieden tilaa O(n³).

## Työn puutteet ja parannusajatukset

Pelin tasapainoa voisi parantaa muuttamalla pelilaudan kuusikulmioiksi, jolloin kissalla olisi kuusi liikkumissuuntaa. Tällöin pelilaudan tallentaminen olisi kuitenkin pitäny miettiä uudestaan, enkä siksi lähtenyt siihen asian huomattuani.

Tekoälyltä vie enemmän aikaa laskea siirto pienellä laudalla kuin suurella. Tulkitsin tämän merkkinä siitä, että se menee rekursiossa pelin loppumista pidemmälle, ja käyttää siihen turhaan aikaa. En kuitenkaan keksinyt tapaa vahtia seinäpelaajan voittoa järkevästi (=siten, että se ei vie hirveästi aikaa turhaan) minMaxissa. Kissan osalta lisäsin tarkistuksen voitosta, ja se nopeutti laskentaa hieman.

Pelin edetessä, kun mahdollisten siirtojen määrä vähenee ja siten rekursio ei räjähdä ihan yhtä pahasti käsiin, rekursion syvyyttä voisi ehkä kasvattaa.

## Lähteet
[Ohjelmistotekniikan kurssimateriaali](https://github.com/mluukkai/ohjelmistotekniikka-kevat-2020)

[Ohpe- ja ohjamateriaali](https://ohjelmointi-s19.mooc.fi/)

[Javadoc-dokumentaatiot](https://docs.oracle.com/javase/7/docs/api/overview-summary.html)

[Johdatus tekoälyyn, syksy 2013 (Alpha-beta-karsinta)](https://www.cs.helsinki.fi/u/ejaasaar/johtek/alphabeta.html)

Tiran kurssimateriaali (kevät 2020)
