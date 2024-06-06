# Software Engineering Projekt - Gruppe10

## Projektübersicht

* Die Implementierung eines einfachen, deutsch lokalisierten Vertriebsunterstützungssystems für die interne Verwendung in einem Unternehmen.
* Das System ermöglicht es, Kunden, Produkte, Bestellungen, Marken, Kategorien und Mitarbeiter zu verwalten.
* Das System unterstützt die Benutzerauthentifizierung und vergibt auf Basis von Master- und Detail-Seiten entsprechende Datenzugriffsrechte an entsprechende Benutzer.
* Integrationstests wurden implementiert, um die Funktionalität des Programms zu überprüfen.

## Installation und Bedienung

- Für dieses Projekt nutzen wir WildFly 31, Java 21 und Postgres 15. UTF-8 als Zeichen-Codierung.
- Die Datenbankstruktur und Datenbeispiele basieren auf: https://www.sqlservertutorial.net/sql-server-sample-database/

### Befehle für Ausführung

- Projektbereitstellung und Start
  - `mvn clean package -P development`
  - `cd target`
  - `cp team-10.war /path/to/wildfly/standalone/deployments`
  - `cd /path/to/wildfly/bin`
  - `./standalone.sh`
  - http://localhost:8080/team-10

- Integrationstests
  - `mvn clean verify -P integration-tests `
  - oder`mvn wildfly:shutdown clean verify -P integration-tests`
  - 
  - alternativ können sie einzelne Testklassen laufen lassen, mit den Befehle
  - `mvn clean verify -Pintegration-tests -Dgroups=customer` 
  - `mvn clean verify -Pintegration-tests -Dgroups=category`
  - `mvn clean verify -Pintegration-tests -Dgroups=brand`
  - `mvn clean verify -Pintegration-tests -Dgroups=login`
  - `mvn clean verify -Pintegration-tests -Dgroups=order`
  - `mvn clean verify -Pintegration-tests -Dgroups=orderitem`
  - `mvn clean verify -Pintegration-tests -Dgroups=product`
  - `mvn clean verify -Pintegration-tests -Dgroups=staff`
  

## Projektstatus
1. Iteration (22.03.2024 – 05.04.2024): Einfache Vertriebsunterstützung
2. Iteration (05.04.2024 – 25.04.2024): Vertriebsunterstützung Bike-Stores
3. Iteration (25.04.2024 – 16.05.2024): Vertriebsunterstützung Bikes-Stores vervollständigen
4. Iteration (16.05.2024 – 06.06.2024): Vertriebsunterstützung Bikes-Shop überarbeiten

## Zusammenfassung und Ergebnisse aller Iterationen

### Iteration 1 - Einfache Vertriebsunterstützung

Es wurden Funktionen implementiert, um neue Kunden, Produkte und Bestellungen anzulegen sowie bestehende Daten in einer Listenansicht darzustellen. Das System wurde als Prototyp entwickelt und diente dazu, die grundlegenden Funktionen zu demonstrieren.

#### Hauptseite

Die Startseite besteht aus zwei Abschnitten: Anzeigen und Anlegen.
Jeweils enthält vier Präsentationsseiten für Tabellen und Formulare.
Benutzer können auf die Startseiten-Schaltflächen klicken, um zur entsprechenden Webseite zu gelangen.

#### Tabellen

Die Titelzeile wird fett dargestellt, um sie hervorzuheben.
Die Tabellen haben abwechselnde Zeilenfarben, um die Lesbarkeit zu verbessern.

#### Formular

Es werden geeignete Eingabevalidierungen verwendet, und bei ungültigen Eingabewerten wird eine entsprechende Meldung auf der Webseite angezeigt.
Das Eingabefeld für das Bestelldatum enthält auf der rechten Seite eine Schaltfläche, mit der das aktuelle Datum automatisch eingefügt werden kann, um die Benutzerfreundlichkeit zu verbessern.

### Iteration 2 - Vertriebsunterstützung Bike-Stores

Von „bikes-db.sql“ Daten in die Datenbank „bikes“ importieren und den Inhalt der Datenbank im Web-Frontend anzeigen. Die Tabellen werden paginiert dargestellt, sodass Benutzer die Gesamtzahl der Zeilen sehen, die Anzahl der Zeilen pro Seite auswählen und zu einer bestimmten Seite springen können.

### Iteration 3 - Vertriebsunterstützung Bikes-Stores vervollständigen

Die Implementierung von Funktionen zur Datenmanipulation (Erstellen, Lesen, Aktualisieren, Löschen) wurde abgeschlossen. Benutzer können neue Einträge hinzufügen, vorhandene Einträge bearbeiten und löschen. Die Benutzeroberfläche wurde verbessert, um die Benutzerfreundlichkeit zu erhöhen. Jede Tabelle verfügt über eine entsprechende Suchfunktion, die das Auffinden von Daten erleichtert. Das System unterstützt die Benutzerauthentifizierung und vergibt auf Basis von Master- und Detail-Seiten entsprechende Datenzugriffsrechte an entsprechende Benutzer.

### Iteration 4 - Vertriebsunterstützung Bikes-Shop überarbeiten

#### Optimierung
Durch das Entfernen unnötiger Abfragen und die Optimierung der Abfragelogik wird die Leistung des Programms verbessert.
Der Code für die Titelleiste oben wird neu geschrieben, um ein benutzerfreundlicheres Verhalten mit Maus-Hover zur Anzeige des Menüs zu ermöglichen.

#### Integrationstests
Integrationstests wurden implementiert, um die Funktionalität des Programms zu überprüfen. Die Tests umfassen die Anmeldung, das Anzeigen von Daten und das Erstellen, Aktualisieren und Löschen von Daten.

## Weitere Informationen
- Leerzeichenempfindlichkeit bei der Anmeldung
- Passwortbeispiel: Telefon: (831) 555-5554 -> Passwort: 555-5554
- Beim Löschen des Eintrags werden auch die damit verbundenen Daten gelöscht (Zum Beispiel werden beim Löschen einer Marke alle damit verbundenen Produkte und Bestellungen gelöscht.)
- Alle Tests wurden mit dem Chromedriver durchgeführt. Ein Austausch durch Firefox kann in der Test-Klasse vorgenommen werden (`webDriver = new FirefoxDriver();`).

## Autoren
* Zihao Wang
* Zijian Ying
* Jinang Lyu
* Felix Bischoff