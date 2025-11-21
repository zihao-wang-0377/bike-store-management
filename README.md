# Vertriebsunterstützungssystem

## Projektübersicht

* Die Implementierung eines einfachen, deutsch lokalisierten Vertriebsunterstützungssystems für die interne Verwendung in einem Unternehmen.
* Das System ermöglicht es, Kunden, Produkte, Bestellungen, Marken, Kategorien und Mitarbeiter zu verwalten.
* Das System unterstützt die Benutzerauthentifizierung und vergibt auf Basis von Master- und Detail-Seiten entsprechende Datenzugriffsrechte an entsprechende Benutzer.
* Integrationstests wurden implementiert, um die Funktionalität des Programms zu überprüfen.

\* Dieses Projekt ist Teil des Bachelorstudiums der Informatik an der Ostfalia Hochschule für angewandte Wissenschaften und wird von Prof. Dr. Bernd Müller betreut. Es dient als Übung, um die Konzepte von Jakarta EE praxisnah anzuwenden.

## Installation und Bedienung

- Für dieses Projekt nutzen wir WildFly 33.0.2.Final, Java 22 und Postgres 15.
- Die Datenbankstruktur und Datenbeispiele basieren auf: https://www.sqlservertutorial.net/sql-server-sample-database/.
- Erstellen Sie eine PostgreSQL-Datenbank mit dem Namen `bikes` und importieren Sie die bereitgestellte SQL-Datei `bikes-db.sql` aus dem Ordner `data`.

### Datenbankkonfiguration

> Unter macOS verwendet PostgreSQL, das über Homebrew installiert wurde, oft die Authentifizierungsmethode `trust` für lokale Verbindungen. Dies bedeutet, dass die Datenbank Verbindungen ohne Passwort zulässt.
>
> Wenn Ihre PostgreSQL-Installation die `trust`-Authentifizierung verwendet, müssen Sie den `<security>`-Abschnitt in der Datasource-Konfigurationsdatei unter `src/main/webapp/WEB-INF/sep-ds.xml` auskommentieren:
>
> ```xml
> <datasource jndi-name="java:jboss/datasources/bikes10" pool-name="bikes10" enabled="${enable.datasource.team10}" use-java-context="true">
>     <connection-url>jdbc:postgresql://localhost:5432/bikes</connection-url>
>     <driver>postgresql-42.7.3.jar</driver>
> <!--    <security>-->
> <!--        <user-name>postgres</user-name>-->
> <!--        <password>postgres</password>-->
> <!--    </security>-->
> </datasource>
> ```
>
> Wenn Ihre PostgreSQL-Installation eine Passwort-Authentifizierung verwendet, behalten Sie den `<security>`-Abschnitt bei und geben Sie den korrekten Benutzernamen und das Passwort an.

1. Laden Sie den PostgreSQL JDBC-Treiber von [PostgreSQL JDBC Driver](https://jdbc.postgresql.org/download/postgresql-42.7.3.jar) herunter und kopieren Sie die JAR-Datei in das Verzeichnis `/path/to/wildfly-33.0.2.Final/standalone/deployments`.

2. Starten Sie den WildFly-Server auf macOS / Linux mit:
   ```bash
   /path/to/wildfly-33.0.2.Final/bin/standalone.sh
   ```
   oder unter Windows mit:
   ```powershell
   \path\to\wildfly-33.0.2.Final\bin\standalone.bat
   ```

### Sicherheitskonfiguration

1. WildFly Server starten.

2. WildFly CLI starten und verbinden
   - Öffnen Sie ein neues Terminalfenster, wechseln Sie ins gleiche `bin`-Verzeichnis:
     ```bash
     cd /path/to/wildfly-33.0.2.Final/bin
     ./jboss-cli.sh --connect
     ```
   > Hinweis: Unter Windows starten Sie die CLI mit `jboss-cli.bat`.

3. JACC-Policy im Elytron-Subsystem anlegen
   ```text
   /subsystem=elytron/policy=jacc:add(jacc-policy={})
   ```

4. Undertow-Anwendungs-Sicherheitsdomain konfigurieren

   ```text
   /subsystem=undertow/application-security-domain=other:write-attribute(name=integrated-jaspi, value=false)
   ```

### Befehle für Ausführung

- Projektbereitstellung und Start
  - `mvn clean package -P development`
  - `cd target`
  - `cp team-10.war /path/to/wildfly-33.0.2.Final/standalone/deployments`
  - `cd /path/to/wildfly-33.0.2.Final/bin`
  - `./standalone.sh` oder `standalone.bat` (Windows)
  - Anwendung im Browser öffnen unter: http://localhost:8080/team-10

### Integrationstests

- Alle Tests ausführen:
  ```bash
  mvn clean verify -P integration-tests
  ```

- Alternativ kann auch nur eine einzelne Testgruppe ausgeführt werden, z. B. für Kunden:

  ```bash
  mvn clean verify -Pintegration-tests -Dgroups=customer
  ```


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
Auf jeder Seite befinden sich vier Präsentationsseiten für Tabellen und Formulare.
Benutzer können auf die Startseiten-Schaltflächen klicken, um zur entsprechenden Webseite zu gelangen.

#### Tabellen

Die Titelzeile wird fett dargestellt, um sie hervorzuheben.
Die Tabellen weisen abwechselnde Zeilenfarben auf, um die Lesbarkeit zu verbessern.

#### Formular

Es werden geeignete Eingabevalidierungen verwendet, und bei ungültigen Eingabewerten wird eine entsprechende Meldung auf der Webseite angezeigt.
Das Eingabefeld für das Bestelldatum enthält auf der rechten Seite eine Schaltfläche, mit der das aktuelle Datum automatisch eingefügt werden kann, um die Benutzerfreundlichkeit zu verbessern.

### Iteration 2 - Vertriebsunterstützung Bike-Stores

Von „bikes-db.sql“ Daten in die Datenbank „bikes“ importieren und den Inhalt der Datenbank im Web-Frontend anzeigen. Die Tabellen werden paginiert dargestellt, sodass Benutzer die Gesamtzahl der Zeilen sehen, die Anzahl der Zeilen pro Seite auswählen und zu einer bestimmten Seite springen können.

### Iteration 3 - Vertriebsunterstützung Bikes-Stores vervollständigen

Die Implementierung von Funktionen zur Datenmanipulation (Erstellen, Lesen, Aktualisieren, Löschen) wurde abgeschlossen. Benutzer können neue Einträge hinzufügen, vorhandene Einträge bearbeiten und löschen. Die Benutzeroberfläche wurde verbessert, um die Benutzerfreundlichkeit zu erhöhen. Jede Tabelle verfügt über eine entsprechende Suchfunktion, die das Auffinden von Daten erleichtert. Das System unterstützt die Benutzerauthentifizierung und vergibt auf Basis von Master- und Detail-Seiten die entsprechenden Datenzugriffsrechte an die Benutzer.

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
* Zijian Ying – [z.ying@ostfalia.de](mailto:z.ying@ostfalia.de)
* Jinang Lyu – [j.lyu@ostfalia.de](mailto:j.lyu@ostfalia.de)
* Felix Bischoff – [fel.bischoff@ostfalia.de](mailto:fel.bischoff@ostfalia.de)
