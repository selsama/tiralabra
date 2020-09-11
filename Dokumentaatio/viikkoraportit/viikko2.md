## Viikko 2

Sain palautetta, että alkuperäinen aiheajatukseni on liian suppea, joten käytin lisää aikaa sen miettimiseen. Ajattelin muuttaa tekoälyn toteutusta siten, että se käyttäisikin min-max-algoritmia parhaan siirron valitsemiseen, ja leveyshakua ja jotain vielä tarkentamatonta (pohdinnan alla) muuttujaa pelitilanteen hyvyyden arvioimiseen (päivitin määrittelydokumentin, siellä tarkemmin.). Mietinkin, että tuoko min-max-algoritmi työhön riittävän laajuuden, vai olisiko leveyshaun sijaan toteutettava joka tapauksessa ehdotettu Fringe search tai JPS?
Toinen mikä vielä epäilyttää, on että algoritmin aikavaativuus taitaa kyllä aika nopeasti räjähtää käsiin, kun seiniä asettavalla pelaajalla on niin monta mahdollista siirtoa. Toisaalta kun n on pieni (n. 100), se ei välttämättä muodostu ongelmaksi?

Lisäksi tällä viikolla olen tehnyt sovelluksen käyttöliittymää ja perustoiminnallisuutta ja saanutkin sen pyörimään. Peliä on  nyt mahdollista pelata kahden pelaajan versiona. Peli ei vielä pääty, kun "kissa" (ruskea laatikko) pääsee reunaan. Olen aikeissa toteuttaa pelin päättymisen tarkistamisen tekoälyä pyörittävän luokan puolella, joka onkin sitten ensi viikon aiheena. Käyttöliittymä ei ole kaunis, mutta kun se ei ole tämän kurssin aiheena, niin totesin että laitan siihen enemmän aikaa, jos sitä jää yli. Pääasia, että pyörii! Javadoc ja testaus on myös aloitettu, ja checkstyle toimii.

Ensi viikolla tavoitteena on saada kissaa liikuttava tekoäly toimimaan ainakin jollakin tasolla. Sen lisäksi tarvitsen asetusnäkymään (tehty, mutta vielä tyhjä) mahdollisuuden valita, haluaako pelata tietokonetta vai toista pelaajaa vastaan. Uusi peli-nappula heittää myös jotakin virhekoodia komentoriville, huomasin vasta ihan lopuksi enkä jaksanut enää ruveta korjaamaan (koodatessa olen suorittanut projektia netbeanssissa), joten se pitää myös korjata.

Tällä viikolla käytin työhön aikaa n. 14h.


