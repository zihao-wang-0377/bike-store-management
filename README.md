# Software Engineering Projekt - Gruppe10

## Projektübersicht

* Die Implementierung eines einfachen, deutsch lokalisierten Vertriebsunterstützungssystems für die interne Verwendung in einem Unternehmen. Das Projekt befindet sich derzeit in der zweiten Iteration.
* Aktueller Stand: In der aktuellen Iteration ist es möglich, Daten aus der vorhandenen Datenbank zu lesen und sie in einer webbasierten Anwendung anzuzeigen. Die Webseite enthält eine tabellarische Darstellung der Daten, die über mehrere Seiten paginiert ist. Benutzer haben die Möglichkeit, zwischen den Seiten zu navigieren und die Anzahl der angezeigten Einträge pro Seite anzupassen.
* Geplante Funktionen für zukünftige Iterationen: In den kommenden Iterationen werden Funktionen zur Datenmanipulation (Erstellen, Lesen, Aktualisieren, Löschen) implementiert.

## Installation und Bedienung
Für dieses Projekt nutzen wir WildFly 31, Java 21 und Postgres 15. UTF-8 als Zeichen-Codierung.

## Projektstatus
1. Iteration (22.03.2024 – 05.04.2024): Einfache Vertriebsunterstützung
2. Iteration (05.04.2024 – 25.04.2024): Vertriebsunterstützung Bike-Stores

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

## Autoren
* Zihao Wang
* Zijian Ying
* Jinang Lyu
* Felix Bischoff