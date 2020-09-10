Tiralabra-projekti: Karkaava kissa (tai muu otus)

Sonja Salmi, TKT, projekti suomeksi.

Toteutan yhden pelaajan pelin, jota pelasin vuosia sitten jollain nettisivulla. Idea on seuraava: Pelilautana toimii ruudukko. Lähtötilanteessa lauta on tyhjä ja sen keskellä on kissa. Pelaajan tavoitteena on estää kissaa karkaamasta pelilaudalta muuttamalla yksi kerrallaan ruutuja seiniksi. Peli etenee siten, että pelaaja napsauttaa hiirellä valitsemaansa ruutua laudalla, joka muuttuu seinäksi. Tämän jälkeen kissa siirtyy yhden ruudun ylös, alas, vasemmalle tai oikealle. Kissa ei voi siirtyä seinäruutuun eikä pelaaja voi muuttaa ruutua, jossa kissa on, seinäksi.

Toteutan työhön min-max-algortimin, jota tekoäly (kissa) käyttää parhaan siirron valitsemiseen. En vielä tiedä, kuinka monta tasoa alaspäin tekoäly tutkisi vaihtoehtoja (vaikuttaa aikavaativuuteen, varmaan kokeilen muutamaa vaihtoehtoa, mikä tuntuisi sopivan "fiksulta" ja toisaalta tarpeeksi nopealta. O(n^2), O(n^3) luokassa varmaankin, missä n on pelilaudan koko.). Min-max-algoritmi tarvitsee arvion kunkin tilanteen "hyvyydestä" tekoälyn kannalta, ja tämän arvioimiseen käyttäisin jonkinnäköistä yhdistelmää jäljellä olevien lyhimpien reittien määrää ja pituutta. Niiden laskemiseen algoritmi käyttää leveyshakua. Ohjelma tarvitsee tietorakenteina ainakin listaa, jonoa, ja taulukkoa. Lisäksi jonkinlainen satunnaistaminen tullee tarpeeseen. 

Ohjelma saa syötteenä käyttäjältä klikkauksia.
