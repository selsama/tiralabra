## Testausdokumentti

Käyttöliittymä on testattu käsin.

### Yksikkötestit JUnitilla

Käyttöliittymää ja suorituskyky- ym testaukseen käytettäviä luokkia ei ole yksikkötestattu ja ne on jätetty pois testikattavuusraportista. Testikattavuus on n. 90 %.

Kaikki luokat on yksikkötestattu sitä mukaa kuin ne on luotu. Testit testaavat, että luokat toimivat niinkuin niiden pitää. Peli- ja Tekoäly-luokan testit testaavat oikeita, pelin kannalta olennaisia toiminnallisuuksia. Yksikkötesteissä ei ole juurikaan satunnaisuutta tai suuria toistomääriä, mutta erilaiset tilanteet on pyritty huomioimaan yksittäistapausten avulla.

Testikattavuutta laskevat Peli-luokan getterit, jotka ovat hyvin yksinkertaisia. Ne ovat tarpeellisia käyttöliittymän kannalta, mutta niitä ei ole tarvittu testatessa, eikä niitä ole myöskään lähdetty erikseen testaamaan. Lisäksi joidenkin metodien erityistapaushaaroja on jäänyt testien ulkopuolelle. 

### Ohjelmalliset testit

Ohjelman tasapainoisuutta testaa TasapainoTestaaja-luokka, sen lisäksi että sitä on testailtu käsin.

Tälle on syötetty erilaisia arvoja ja tallennettu saatuja tuloksia erilliseen (dokumenttiin)[]. Peli ei ole tasapainoinen; kun tekoäly pelaa tekoälyä vastaan, seinäpelaaja voittaa useammin. Tämä vastaa käsin testaillessa saatua "mututuntumaa" siitä, että kissalla seiniä vastaan pelatessa on mahdollista voittaa vain, jos kissa aloittaa sopivan läheltä reunaa ja aloitusseinät arpoontuvat toiselle laidalle. Peliin on tuotu vaihtelua ja mielenkiintoisuutta lisäämällä aloitukseen satunnaistusta. Tasapainotusta yritettiin parantaa antamalla kissalle mahdollisuus liikkua myös vinottain, mutta tämä teki kissasta ylivoimaisen (niin ylivoimaisen, että ei edes testattu ohjelmallisesti, tulos oli päivänselvä). Tasapainoa testeillessa havaittiin, että pelilaudan kasvaessa seinäpelaajan voitto-osuus kasvaa. Toisaalta lautaa ei haluttu pienentää kovin paljoa, koska käsin testaillessa tämä tuntui tylsältä. Tasapainotestien perusteella päädyttiin laudan kokoon 11x11, koska tässä koossa kissalla oli vielä selvästi havaittava mahdollisuus voittaa, vaikka pelaaja muuttaisi alussa asetettavien seinien määrää.

Ohjelman suorituskykyä testaa SuorituskykyTestaaja-luokka.

Suorituskykytestejä on ajettu erilaisilla parametreilla. Lisäksi ohjelmakoodiin on testatessa tehty muutoksia ja katsottu, miten ne vaikuttavat. 

