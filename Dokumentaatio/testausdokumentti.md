## Testausdokumentti

Käyttöliittymä on testattu käsin.

### Yksikkötestit JUnitilla

Käyttöliittymää ja suorituskyky- ym testaukseen käytettäviä luokkia ei ole yksikkötestattu ja ne on jätetty pois testikattavuusraportista. Testikattavuus on n. 90 %.

Kaikki luokat on yksikkötestattu sitä mukaa kuin ne on luotu. Testit testaavat, että luokat toimivat niinkuin niiden pitää. Peli- ja Tekoäly-luokan testit testaavat oikeita, pelin kannalta olennaisia toiminnallisuuksia. Yksikkötesteissä ei ole juurikaan satunnaisuutta tai suuria toistomääriä, mutta erilaiset tilanteet on pyritty huomioimaan yksittäistapausten avulla.

Testikattavuutta laskevat Peli-luokan getterit, jotka ovat hyvin yksinkertaisia. Ne ovat tarpeellisia käyttöliittymän kannalta, mutta niitä ei ole tarvittu testatessa, eikä niitä ole myöskään lähdetty erikseen testaamaan. Lisäksi joidenkin metodien erityistapaushaaroja on jäänyt testien ulkopuolelle. 

### Ohjelmalliset testit

Projektissa on kaksi ajettavaa testiluokkaa, jotka löytyvät pakkauksesta karkukatti/testaus. Testejä on ajettu erilaisilla arvoilla, lisäksi välillä muuttaen itse ohjelmakoodia. Parametreja, tuloksia ja niiden herättämiä ajatuksia on koottu erilliseen [dokumenttiin](https://github.com/selsama/tiralabra/blob/master/Dokumentaatio/testituloksia.md) (hyvin sekavasti, alunperin vain omaksi muistilapukseni, mutta lisäsin sen kuitenkin nähtäväksi).

Ohjelman tasapainoisuutta testaa TasapainoTestaaja-luokka, sen lisäksi että sitä on testailtu käsin.

Peli ei ole tasapainoinen; kun tekoäly pelaa tekoälyä vastaan, seinäpelaaja voittaa useammin. Tämä vastaa käsin testaillessa saatua "mututuntumaa" siitä, että kissalla seiniä vastaan pelatessa on mahdollista voittaa vain, jos kissa aloittaa sopivan läheltä reunaa ja aloitusseinät arpoontuvat toiselle laidalle. Peliin on tuotu vaihtelua ja mielenkiintoisuutta lisäämällä aloitukseen satunnaistusta. Tasapainotusta yritettiin parantaa antamalla kissalle mahdollisuus liikkua myös vinottain, mutta tämä teki kissasta ylivoimaisen (niin ylivoimaisen, että ei edes testattu ohjelmallisesti, tulos oli päivänselvä). Tasapainoa testeillessa havaittiin, että pelilaudan kasvaessa seinäpelaajan voitto-osuus kasvaa. Toisaalta lautaa ei haluttu pienentää kovin paljoa, koska käsin testaillessa tämä tuntui tylsältä. Tasapainotestien perusteella päädyttiin laudan kokoon 11x11, koska tässä koossa kissalla oli vielä selvästi havaittava mahdollisuus voittaa, vaikka pelaaja muuttaisi alussa asetettavien seinien määrää.

Ohjelman suorituskykyä testaa SuorituskykyTestaaja-luokka.

Suorituskykytestejä on ajettu erilaisilla parametreilla. Lisäksi ohjelmakoodiin on testatessa tehty muutoksia ja katsottu, miten ne vaikuttavat. Luokka on ollut hieman erilainen tuolloin.

Lopullisella ohjelmalla ja testaajaluokalla saatiin seuraavat tulokset (rekursion syvyys 3, otoksen koko 100):

    Pelin alustuksen keston mediaani 10x10-laudalla: 0.005828 ms
    Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 118.402732 ms
    Tekoälyn siirron valinnan keston keskiarvo 10x10-laudalla: 66.787029 ms

    Pelin alustuksen keston mediaani 100x100-laudalla: 0.019361 ms
    Tekoälyn siirron valinnan keston mediaani 100x100-laudalla: 16.619479 ms
    Tekoälyn siirron valinnan keston keskiarvo 100x100-laudalla: 13.741666 ms

    Pelin alustuksen keston mediaani 1000x1000-laudalla: 0.225891 ms
    Tekoälyn siirron valinnan keston mediaani 1000x1000-laudalla: 4254.428839 ms
    Tekoälyn siirron valinnan keston keskiarvo 1000x1000-laudalla: 3619.886416 ms

    Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 157.565223 ms
    Tekoälyn siirron valinnan keston keskiarvo 10x10-laudalla: 89.521622 ms

Huomataan, että analyysien mukaisesti laudan koon kasvaessa tekoäly alkaa hidastua dramaattisesti. Huomataan kuitenkin, että algoritmi ei vie aivan pahimman aikavaativuuden (O(n³)) veroisesti aikaa, vaan sitä on saatu tehostettua. Huomataan myös, että jostain syystä 10x10 -laudalla laskeminen on hitaampaa kuin 100x100. (Sen varmistamiseksi, ettei kyse ole vain siitä, että se on ensimmäisenä, 10x10 on laskettu viimeisenä uudestaan.) Algoritmiin on siis todennäköisesti jäänyt jonkinlainen bugi tai epätehokkuus, joka tulee vastaan kun lauta on pieni. Yrityksistä huolimatta en kuitenkaan saanut tätä paikannettua.

