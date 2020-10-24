## Suorituskykytestien tuloksia

#### Ennen alpha-betaa, manuaalisella taulukon kopioinnilla, syvyys 2:

Pelin alustuksen keston mediaani 10x10-laudalla: 0.011387 ms
Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 6.768882 ms

Pelin alustuksen keston mediaani 100x100-laudalla: 0.045482 ms
Tekoälyn siirron valinnan keston mediaani 100x100-laudalla: 4.829528 ms

Pelin alustuksen keston mediaani 1000x1000-laudalla: 0.329014 ms
Tekoälyn siirron valinnan keston mediaani 1000x1000-laudalla: 1252.566476 ms

Syvyys 4:

Pelin alustuksen keston mediaani 10x10-laudalla: 0.009682 ms
Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 841.218353 ms

Pelin alustuksen keston mediaani 100x100-laudalla: 0.028213 ms
Tekoälyn siirron valinnan keston mediaani 100x100-laudalla: 35.911289 ms
--
Pelin alustuksen keston mediaani 10x10-laudalla: 0.013084 ms
Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 802.231797 ms

Pelin alustuksen keston mediaani 100x100-laudalla: 0.028577 ms
Tekoälyn siirron valinnan keston mediaani 100x100-laudalla: 37.774597 ms

Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 705.87352 ms

#### Käytetty System.arraycopyä, ei vielä alpha-betaa:

Syvyys 2:

Pelin alustuksen keston mediaani 10x10-laudalla: 0.01222 ms
Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 7.912659 ms

Pelin alustuksen keston mediaani 100x100-laudalla: 0.028855 ms
Tekoälyn siirron valinnan keston mediaani 100x100-laudalla: 4.853749 ms

Pelin alustuksen keston mediaani 1000x1000-laudalla: 0.330622 ms
Tekoälyn siirron valinnan keston mediaani 1000x1000-laudalla: 1275.027075 ms

Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 1.281463 ms

Syvyys 4:

Pelin alustuksen keston mediaani 10x10-laudalla: 0.012295 ms
Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 740.165105 ms

Pelin alustuksen keston mediaani 100x100-laudalla: 0.031469 ms
Tekoälyn siirron valinnan keston mediaani 100x100-laudalla: 46.996081 ms

Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 887.621288 ms

--> Ei huomattavaa eroa, palautetaan manuaalinen taulukon kopiointi käyttöön

#### Lisätty alpha-beta karsinta

Syvyys 2:

Pelin alustuksen keston mediaani 10x10-laudalla: 0.01327 ms
Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 4.412537 ms

Pelin alustuksen keston mediaani 100x100-laudalla: 0.035688 ms
Tekoälyn siirron valinnan keston mediaani 100x100-laudalla: 5.680892 ms

Pelin alustuksen keston mediaani 1000x1000-laudalla: 0.3573 ms
Tekoälyn siirron valinnan keston mediaani 1000x1000-laudalla: 1402.516528 ms

Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 1.432489 ms

Syvyys 4:

Pelin alustuksen keston mediaani 10x10-laudalla: 0.011142 ms
Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 244.912678 ms

Pelin alustuksen keston mediaani 100x100-laudalla: 0.026116 ms
Tekoälyn siirron valinnan keston mediaani 100x100-laudalla: 38.1103 ms

Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 209.205917 ms

--> Ero on huomattava. 

Huomataan, että siirron valinta on hitaampaa 10x10 laudalla kuin 100x100. Algoritmi luultavasti "jumittaa" jotakin yrittäessään tutkia tilannetta monta rekursiota yli siitä, kun peli päättyy. Ihmetellään, mistä tämä voisi johtua.

### Poistettu ylimääräisiä taulukon kopiointeja

Syvyys 2:

Pelin alustuksen keston mediaani 10x10-laudalla: 0.010888 ms
Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 3.26335 ms

Pelin alustuksen keston mediaani 100x100-laudalla: 0.035451 ms
Tekoälyn siirron valinnan keston mediaani 100x100-laudalla: 5.360339 ms

Pelin alustuksen keston mediaani 1000x1000-laudalla: 0.316787 ms
Tekoälyn siirron valinnan keston mediaani 1000x1000-laudalla: 1214.74785 ms

Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 2.407825 ms

Syvyys 4:

Pelin alustuksen keston mediaani 10x10-laudalla: 0.01039 ms
Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 302.595567 ms

Pelin alustuksen keston mediaani 100x100-laudalla: 0.029831 ms
Tekoälyn siirron valinnan keston mediaani 100x100-laudalla: 37.337349 ms

Tekoälyn siirron valinnan keston mediaani 10x10-laudalla: 244.375101 ms



## Tasapainotestien tuloksia: 

Seiniä laudan leveyden verran:

40 pelin otoksesta 10x10-laudalla kissa voitti 8
40 pelin otoksesta 15x15-laudalla kissa voitti 1
40 pelin otoksesta 20x20-laudalla kissa voitti 2

seiniä 1

40 pelin otoksesta 10x10-laudalla kissa voitti 26
40 pelin otoksesta 15x15-laudalla kissa voitti 3
40 pelin otoksesta 20x20-laudalla kissa voitti 2

seiniä 5

40 pelin otoksesta 10x10-laudalla kissa voitti 18
40 pelin otoksesta 15x15-laudalla kissa voitti 2
40 pelin otoksesta 20x20-laudalla kissa voitti 2

seiniä 4

40 pelin otoksesta 13x13-laudalla kissa voitti 12
40 pelin otoksesta 15x15-laudalla kissa voitti 1
40 pelin otoksesta 11x11-laudalla kissa voitti 20

Tutkitaan lautoja 11x11 ja 13x13

Seiniä 2:
40 pelin otoksesta 11x11-laudalla kissa voitti 24
40 pelin otoksesta 13x13-laudalla kissa voitti 10

Seiniä 3:
40 pelin otoksesta 11x11-laudalla kissa voitti 18
40 pelin otoksesta 13x13-laudalla kissa voitti 11

Seiniä 4:
40 pelin otoksesta 11x11-laudalla kissa voitti 11
40 pelin otoksesta 13x13-laudalla kissa voitti 7

Seiniä 7:
40 pelin otoksesta 11x11-laudalla kissa voitti 12
40 pelin otoksesta 13x13-laudalla kissa voitti 10


Valitaan pelilaudan kooksi 11x11
