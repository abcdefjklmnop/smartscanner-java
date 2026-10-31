# SmartScanner – Java-konsollapp

Konsollapp som slår opp produkter på kode eller navn, viser produktinformasjon og holder historikk over oppslagene.

## Hva den gjør

Et lite, komplett program uten rammeverk: les input, slå opp i et katalog, vis resultat, husk hva som er slått opp.

- Oppslag på produktkode eller navn
- Produktinformasjon med lagerstatus
- Historikk over gjennomførte oppslag
- Enkel meny med avslutning

## Teknisk

| | |
|---|---|
| Språk | Java (én fil, ingen avhengigheter) |
| Bygg | `javac` |
| Inngangspunkt | `src/SmartScannerApp.java` |

## Kom i gang

```sh
mkdir -p build
javac -d build src/SmartScannerApp.java
java -cp build SmartScannerApp
```

Skriv en produktkode (for eksempel `1001`) eller et produktnavn, og følg menyen.

## Testing

`javac`-kompilering er kjørt uten feil. En interaktiv røykprøve er gjennomført med produkt `1001`, historikk og avslutning. Detaljer i [`VERIFISERING.md`](VERIFISERING.md).

Det finnes ingen automatiserte tester. Katalogen er hardkodet i kildekoden.

## Begrensninger

Skole- og øvingseksempel. Ingen kamera, strekkodeskanning, database eller ekstern integrasjon – oppslagene går mot en katalog som ligger i kildekoden. Ingen persistens mellom kjøringer.

## Bakgrunn

Prosjektet inngår i Karos Mokrians portefølje og er utviklet med KI-assistanse. Beskrivelsene over gjelder det koden faktisk demonstrerer.

## Lisens

MIT – se [LICENSE](LICENSE).
