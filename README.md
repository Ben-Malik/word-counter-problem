

## word-counter-core

Een implementatie van het Word Count-probleem als een afhankelijkheid.
### Tech stack

1. Java 17
2. Apache Maven 3.8.6
3. JDK: temurin-17.0.7
4. JUnit
5. Mockito

### Class Definitions

1. `public class WordCounterConstants` De klasse die bestaat uit alle constante waarden en foutmeldingen.
2. `public class WordCounterException extends Exception` Door de bibliotheek gegooid met correcte berichten.
3. `public interface WordFrequency extends RandomAccess` Een inkapseling(encapsulation) van de (woord, frequentie) paren.
4. `public class SimpleWordFrequency` Een implementatie van `WordFrequency`
5. `public interface WordFrequencyAnlayzer` De interface die alle drie de bewerkingen op WordFrequency definieert.
6. `public class RegularWordFrequencyAnalyzer` Een implementatie van `WordFrequencyAnalyzer`.
7. `public class WordFrequencyComparator implements Comparator<WordFrequency>` implementeert de `compare()`
   en `reversed()` methoden.
8. `public WordFrequencyUtil` Een hulpprogrammaklasse voor het project.

### Unit Tests

1. `regular.CalculateFrequencyForWordTest`
2. `regular.CalculateHighestFrequencyTest`
3. `regular.CalculateMostNFrequencyWordsTest`
4. `regular.GetSortedWordFrequenciesTest`

### Used Abstract Data Types, Algorithms & Why

1. **MapTree**
   `MapTree` heeft de voorkeur boven `HashMap` omdat ondanks het feit dat we voldoende geheugen hebben en dat de toegang tot elementen
   zou O(1) kunnen zijn, de rehash kosten O(n).
   De boom kent een O(log n)-prestatie toe en items worden tijdens de analyse consistent aan de boom toegevoegd.

2. **Binary Search**
   Gegeven een gesorteerde lijst van `WordFrequency`, `Collections.binarySearch(List<T> list, K key) met
   een complexiteit van O(log n) in het ergste geval.
3. Array is used, Best complexity O(1), worst and average O(log n)
4. Approach for Order-agnostic Binary Search
   The implementation is similar to binary search except that we need to identify whether the array is sorted in ascending order or descending order. This then lets us make the decision about whether to continue the search in the left half of the array or the right half of the array.
5. Op bomen gebaseerd zoeken: Verschillende boomdatastructuren, zoals binaire zoekbomen (BST), AVL-bomen of B-bomen, kunnen worden gebruikt voor efficiënt zoeken. Deze structuren leggen een ordening op aan de elementen en zorgen voor snelle zoek-, invoeg- en verwijderbewerkingen. De tijdscomplexiteit van boomgebaseerde zoekalgoritmen hangt af van de hoogte van de boom en kan in het ergste geval variëren van O(log n) tot O(n).

3. **RandomAccess**
   De interface `WordFrequency` breidt `RandomAccess` alleen uit om aan te geven dat het snelle (doorgaans constante)
   tijd) willekeurige toegang, zodat generieke algoritmen hun gedrag veranderen terwijl ze sequentiële toegang uitvoeren
   collecties.

Unittests zijn geschreven met een lijndekking van 100%.


* Gebruik @Mock als u de functionaliteit alleen extern wilt testen zonder die methode daadwerkelijk aan te roepen.
* Gebruik @Spy als u de functionaliteit extern + intern wilt testen met dezelfde methode die wordt aangeroepen.

Zoekt in de opgegeven lijst naar het opgegeven object met behulp van het binaire zoekalgoritme. De lijst moet gesorteerd zijn
* in oplopende volgorde volgens de natuurlijke volgorde van de elementen (zoals via de sort(List)-methode) voorafgaand aan
* deze oproep doen. Als het niet is gesorteerd, zijn de resultaten niet gedefinieerd. Als de lijst meerdere elementen bevat die gelijk zijn aan
* het opgegeven object, er is geen garantie welke gevonden zal worden.
* Deze methode werkt in log(n)-tijd voor een "willekeurige toegang"-lijst (die positionele toegang op vrijwel constante tijd biedt).
* Als de opgegeven lijst de RandomAccess-interface niet implementeert en groot is, zal deze methode een
* op iterator gebaseerde binaire zoekactie die O(n)-linktraversals en O(log n)-elementvergelijkingen uitvoert.
* Params:
* lijst – de lijst waarin moet worden gezocht. sleutel – de sleutel waarnaar moet worden gezocht.
* Geeft terug:
* de index van de zoeksleutel, als deze in de lijst voorkomt; anders (-(invoegpunt) - 1). De invoeging
* punt wordt gedefinieerd als het punt waarop de sleutel in de lijst zou worden ingevoegd: de index van het eerste element
* groter dan de sleutel, of list.size() als alle elementen in de lijst kleiner zijn dan de opgegeven sleutel. Merk op dat dit
* garandeert dat de retourwaarde >= 0 zal zijn als en alleen als de sleutel wordt gevonden.
* @param tekst De tekst waarin de frequentie gezocht moet worden.
* @param word Het woord waarvan de frequentie wordt gezocht.

### `interface WordFrequencyAnalyzer`

```
1. getWordFrequency: Berekent de frequentie van het gegeven woord binnen de gegeven invoertekst.
2. getHighestWordFrequency: Berekent de hoogste frequentie gegeven een tekst.
3. getMostNWordFrequencies: Vindt de <code>n</code> meest voorkomende woorden in een bepaalde stringtekst.
```