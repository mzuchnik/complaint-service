# Zadanie rekrutacyjne


## Treść zadania

Celem zadania jest stworzenie RESTowego serwisu do zarządzania reklamacjami.
Kluczowe funkcje to:
- dodawanie nowych reklamacji
- edycja treści reklamacji
- zwracanie listy zapisanych wcześniej reklamacji


Reklamacja powinna zawierać takie informacje jak:

- identyfikator produktu (którego reklamacja dotyczy)
- treść reklamacji
- data stworzenia
- zgłaszającego
- kraj
- licznik zgłoszeń

w polu kraj powinien być wpisany kraj, z którego klient dodał reklamację.
Kraj jest wyznaczany na podstawie IP klienta.

Unikalność reklamacji zapewnia para "identyfikator produktu" i "zgłaszający". 
W przypadku dodania duplikatu zwiększyć licznik zgłoszeń o jeden, bez edycji pozostałych danych.

Dane powinny być zapisane w bazie danych (system bazodanowych dowolny). 
Technologia Java lub Kotlin, narzędzie do budowania Maven lub Gradle

## Proces rozwiązywania

Poniżej znajdują się wstępna analiza i doszczegółowienie pewnych tematów związanych z samym zadaniem.

### Wstępna analiza
Celem zadania jest utworzenie serwisu REST, który będzie odpowiedzialny za dodawanie, edycję oraz zwracanie wcześniej zapisanych reklamacji.

W zadaniu nie jest wskazane, jak ma wyglądać zapytanie i odpowiedzi dla danych endpointów.
Jak mają wyglądać same endpointy, jakiego poziomu mają one sięgać? 
Dodatkowo nie jest wskazany czy dane mają być zwracane w JSON, czy w XML. Stąd należy ustalić samemu, jak request i response będzie wyglądał.
Oprócz samej komunikacji trzeba dodatkowo doprecyzować. 
- Czym jest identyfikator produktu reklamowanego np: (czy to liczba całkowita, czy UUID).
- Treść reklamacji - prawdopodobnie String, czy chcemy tutaj jakieś ograniczenia np: długość znaków zgłoszenia? 
- Data stworzenia - Data z offsetem kiedy otrzymamy request od zgłaszającego reklamacje
- zgłaszający - jak rozumieć zgłaszającego? Czy jest to identyfikator konta? może adres email, może UUID?
- Kraj - kraj wyznaczany na podstawie IP klienta, tutaj będzie potrzebna usługa do wyznaczania kraju po IP (a co jeśli usługa będzie w tym czasie niedostępna, albo zwróci błąd?).
- licznik zgłoszeń - pole które będzie "zwiększane o 1" za każdym razem, kiedy otrzymamy duplikat
- Czym jest duplikat: duplikat to identyczna para (identyfikator produktu + zgłaszający)

### Wstępne ustalenia
Na potrzeby zadania sam wywnioskuję, ustalę i doprecyzuję wątpliwości, które wynikły ze wstępnej analizy.

__Na potrzeby zadania:__
- Identyfikator produktu to: UUID
- Treść reklamacji: String z ograniczeniem długości do 1000 znaków (włącznie ze znakami białymi)
- Data stworzenia: Jest to data otrzymania żądania od użytkownika z offsetem.
- Zgłaszający: poprawny adres email (String)
- Kraj: kraj wyznaczony z usługi do geolokalizacji IP. (String) (Jeśli błąd usługi to ustawiam UNKNOWN)
- Licznik zgłoszeń: liczba całkowita, zakładam, że nie przekroczy ona zakresu int'a ;)
- Aplikacja obsługuje tylko żądania JSON
- W przypadku jakiegoś błędu z żądaniem jest zwracany odpowiedni komunikat.
- Zachowane zostaną odpowiednie status HTTP.

__Stos technologiczny__
- Java 21
- Spring Boot
- Hibernate
- Gradle
- PostgresSQL
- Docker / Docker Compose

Dodatkowo zakładam, że api będzie używane na użytek wewnętrzny tzn nie jest to API publiczne stąd,
zastosuję tutaj drugi poziom ("HTTP Verbs") dojrzałości REST.

## Uruchomienie aplikacji
Poniżej znajdują się sposóby na uruchomienie aplikacji.

### Docker
Aby uruchomić aplikację, wystarczy pobrać to repozytorium lokalnie oraz wydać polecenie z głównego katalogu projektu:

``docker compose up``

Za pierwszym razem serwis będzie potrzebował obrazu lokalnego więc pierwszym krokiem będzie zbudowanie go za pomocą dostarczonego pliku ``Dockerfile``.
Następnie uruchomi się dalsza część aplikacji.

### Gradle Wrapper

Należy postawić i udostępnić pod portem 5432 serwer bazodanowy Postgres. Baza danych to domyślna postgres.

Należy wydać polecenie w głównym katalogu projeku ``./gradlew bootRun -DPOSTGRES_USERNAME=... -D POSTGRES_PASSWORD=...``, dodatkowo zmiennymi środowiskowymi trzeba wskazać użytkownika i hasło:
- ``POSTGRES_USERNAME`` - nazwa użytkownika
- ``POSTGRES_PASSWORD`` - hasło do bazy



