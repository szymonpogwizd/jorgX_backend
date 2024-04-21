# JorgX Backend

## Opis
`jorgX_backend` to backend i API napisane w Java, wspierające aplikację JorgX, która ma na celu analizę sentymentu opinii o restauracjach. Używa wytrenowanego modelu w Pythonie do oceny, czy recenzje są pozytywne czy negatywne.

## Technologie
- Java 17
- Spring Boot 3.0.5
- SpringDoc OpenAPI
- PostgreSQL
- Docker

## Uruchomienie projektu
1. Sklonuj repozytorium na lokalny komputer.
2. Uruchom gradlew bootRun (dla systemów Unix) lub gradlew.bat bootRun (dla Windows) w katalogu głównym projektu.
3. Aplikacja będzie dostępna pod domyślnym adresem http://localhost:8080.

## Uruchomienie modelu analizy sentymentu

### Krok 1: Budowanie obrazu Docker

Przejdź do pliku Dockerfile a następnie go uruchom

### Krok 2: Uruchamianie kontenera

Sprawdź ID obrazu, które zbudowałeś:

```bash
docker ps
```

Po zbudowaniu obrazu, uruchom kontener:

```bash
docker run -p 5000:5000 nazwa-twojego-obrazu
```

### Krok 3: Sprawdzenie działania

 Jeśli Twój kontener działa, możesz przetestować API używając `curl`:

```bash
curl -X POST -H "Content-Type: application/json" -d "{\"text\":\"Polecam.\"}" http://localhost:5000/predict
```

Odpowiedź z serwera powinna zawierać wynik przewidywania modelu.
