## Tiralabra

Toteutin tekoälyn peliin, jossa tarkoituksena on rakentaa seiniä tietokonehahmon ympärille tämän yrittäessä pois pelialueelta. ([tähän tyyliin](https://www.crazygames.com/game/circle-the-cat))

### Dokumentaatio

[Viikkoraportit](https://github.com/selsama/tiralabra/tree/master/Dokumentaatio/viikkoraportit) //// [Viikko 1](https://github.com/selsama/tiralabra/blob/master/Dokumentaatio/viikkoraportit/viikko1.md) - [Viikko2](https://github.com/selsama/tiralabra/blob/master/Dokumentaatio/viikkoraportit/viikko2.md) - [Viikko3](https://github.com/selsama/tiralabra/blob/master/Dokumentaatio/viikkoraportit/viikko3.md) - [Viikko4](https://github.com/selsama/tiralabra/blob/master/Dokumentaatio/viikkoraportit/viikko4.md) - [Viikko5](https://github.com/selsama/tiralabra/blob/master/Dokumentaatio/viikkoraportit/viikko5.md) - [Viikko6](https://github.com/selsama/tiralabra/blob/master/Dokumentaatio/viikkoraportit/viikko6.md)

[Määrittelydokumentti](https://github.com/selsama/tiralabra/blob/master/Dokumentaatio/m%C3%A4%C3%A4ritteludokumentti.md)

[Toteutusdokumentti](https://github.com/selsama/tiralabra/blob/master/Dokumentaatio/toteutusdokumentti.md)

[Testausdokumentti](https://github.com/selsama/tiralabra/blob/master/Dokumentaatio/testausdokumentti.md)

[Käyttöohje](https://github.com/selsama/tiralabra/blob/master/Dokumentaatio/kayttoohje.md)


### Release

[versio 4](https://github.com/selsama/tiralabra/releases/tag/v4)

### Koodi

[Karkaava kissa](https://github.com/selsama/tiralabra/tree/master/KarkuKatti)


### Komentorivikomennot

Koodin suoritus

    mvn compile exec:java -Dexec.mainClass=karkukatti.Main

Jarin generointi ja suoritus

    mvn package
    java -jar target/KarkuKatti-1.0-SNAPSHOT.jar

Testikattavuusraportti

    mvn test jacoco:report
    
löytyy: *target/site/jacoco/index.html*

Checkstyle

    mvn jxr:jxr checkstyle:checkstyle
    
löytyy: *target/site/checkstyle.html*

Javadoc

    mvn javadoc:javadoc
    
löytyy : *target/site/apidocs*
