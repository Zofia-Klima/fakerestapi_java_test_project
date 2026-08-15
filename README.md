# Wymagania

Projekt wymaga zainstalowanej Javy i Google Chrome.

Biblioteki zewnętrzne znajdują się w katalogu `lib`.

Są to m.in.:

- Gson
- Selenium WebDriver
- zależności wymagane przez Selenium

## Kompilacja

Aby skompilować projekt w systemie Windows, należy wykonać:

``` bash
javac -cp ".;lib/*" Main.java Author.java AuthorThread.java AuthorApiClient.java AuthorUiTest.java
```

## Uruchomienie

Aby uruchomić projekt:

``` bash
java -ea -cp ".;lib/*" Main
```
Opcja `-ea` włącza asercje Javy

## Struktura projektu

- `Main.java` - tworzy autorów, uruchamia cztery równoległe wątki i wykonuje końcowe porównanie danych.
- `Author.java` - model danych autora.
- `AuthorThread.java` - wykonuje operacje POST i GET dla pojedynczego autora.
- `AuthorApiClient.java` - pobiera listę wszystkich autorów bezpośrednio z API.
- `AuthorUiTest.java` - obsługuje Swagger UI za pomocą Selenium i pobiera dane autorów wyświetlone w UI.
- `lib/` - zawiera Gson, Selenium WebDriver i wymagane zależności.

## Selenium

Prosty kod Selenium wykorzystany jest z własnej inicjatywy ze względu na punkt 3 zadania:
>(...)
>3. Następnie otwórz UI tej samej strony i porównaj autorów wyświetlanych w UI z danymi pobranymi z API.

Domyślam się, że w zamyśle nie było otworzenie UI przy użyciu Selenium, jednak zrobiłam to w ramach przetestowania jak działa Selenium w Javie.

Kod zawarty w pliku AuthorUiTest.java otwiera stronę https://fakerestapi.azurewebsites.net/index.html, rozwija endpoint GET /api/v1/Authors, następnie klika w przyciski "Try it out" oraz "Execute", oraz zapisuje wyświetlone ciało odpowiedzi w zmiennej responseBody.

Dzięki temu mogę porównać dane pobrane bezpośrednio przez żądanie GET z danymi zwróconymi przez ten sam endpoint uruchomiony w Swagger UI.

## Dodatkowe uwagi

Komentarze w kodzie zostały napisane w języku angielskim, w celu zachowania dobrej praktyki oraz spójności w kodzie.