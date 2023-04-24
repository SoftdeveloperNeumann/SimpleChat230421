# Die Firebase Realtime Database

Die Firebase Realtime Database ist ein Cloud-hosted NoSQL-Datenbanksystem, das von Firebase angeboten wird. Es bietet eine einfache Möglichkeit, Echtzeitdaten in der Cloud zu speichern und zu synchronisieren. Die Daten können in Echtzeit zwischen Clients synchronisiert werden, was bedeutet, dass Änderungen, die an einem Client vorgenommen werden, sofort an alle anderen Clients weitergegeben werden. Die Daten werden im JSON-Format gespeichert und können mit verschiedenen Programmiersprachen und Plattformen abgerufen und manipuliert werden. Die Firebase Realtime Database eignet sich besonders gut für Anwendungen, die eine Echtzeit-Synchronisierung von Daten zwischen verschiedenen Clients erfordern, wie z.B. Chat-Apps oder Online-Spiele.

## Wie funktioniert eine dokumentenbasierte No-SQL-Database

Eine dokumentenbasierte NoSQL-Datenbank funktioniert anders als relationale Datenbanken, die Tabellen mit Zeilen und Spalten verwenden. Stattdessen verwendet sie eine Sammlung von Dokumenten, die in der Regel im JSON-Format (JavaScript Object Notation) gespeichert werden.

Jedes Dokument enthält einen eindeutigen Identifikator, auch bekannt als Schlüssel oder ID, und enthält alle relevanten Informationen als Felder innerhalb des Dokuments. Dokumente können auch untergeordnete Dokumente (Nested Documents) enthalten, die in der Regel als Objekte innerhalb eines anderen Dokuments gespeichert werden.

Die Dokumente werden in Sammlungen (Collections) organisiert, die ähnlich wie Tabellen in relationalen Datenbanken sind. Jede Sammlung kann eine unbegrenzte Anzahl von Dokumenten enthalten und jeder Datensatz kann unterschiedliche Felder und Formate haben.

Das Abfragen von Daten in einer dokumentenbasierten NoSQL-Datenbank erfolgt in der Regel mithilfe von Abfragesprachen, die für spezifische Datenbanken spezifisch sind. In Firestore von Firebase können beispielsweise Abfragen in der Firestore Query Language (FQL) durchgeführt werden. Die Ergebnisse von Abfragen können dann für weitere Verarbeitungsschritte in der Anwendung verwendet werden.

Im Vergleich zu relationalen Datenbanken bieten dokumentenbasierte NoSQL-Datenbanken oft eine bessere Skalierbarkeit und Leistung für unstrukturierte oder sich ändernde Daten. Sie werden häufig in der Web- und mobilen Anwendungsentwicklung eingesetzt.

## Integration in die Android App

Um ein Android-Projekt mit Firebase zu verbinden, müssen Sie die folgenden Schritte ausführen:

1. Erstellen Sie ein Firebase-Projekt in der Firebase-Konsole: Gehen Sie zur Firebase-Konsole, melden Sie sich an und klicken Sie auf "Projekt hinzufügen". Geben Sie den Projektnamen ein und folgen Sie den Anweisungen, um das Projekt zu erstellen.

2. Fügen Sie Ihr Android-Projekt zu Ihrem Firebase-Projekt hinzu: Gehen Sie zur Firebase-Konsole, wählen Sie Ihr Projekt aus und klicken Sie auf "Android-App hinzufügen". Geben Sie den Paketnamen Ihres Android-Projekts ein und folgen Sie den Anweisungen, um die Konfigurationsdatei google-services.json herunterzuladen.
   
3. Speichern Sie google-services.json im app-Verzeichnis Ihres Projekts

4. Fügen Sie die Firebase-Bibliotheken zu Ihrem Android-Projekt hinzu: Fügen Sie der build.gradle-Datei Ihres Android-Projekts die Abhängigkeiten für die Firebase-Bibliotheken hinzu. Sie können dies manuell tun oder das Firebase-Plugin für Android Studio verwenden.

Auf Projektebene (project/build.gradle)
```Kotlin
buildscript {

    repositories {
      // Make sure that you have the following two repositories
      google()  // Google's Maven repository
      mavenCentral()  // Maven Central repository
    }

    dependencies {
      ...

      // Add the dependency for the Google services Gradle plugin
      classpath 'com.google.gms:google-services:4.3.15'
    }
}

allprojects {
  ...

  repositories {
    // Make sure that you have the following two repositories
    google()  // Google's Maven repository
    mavenCentral()  // Maven Central repository
  }
}
```
Auf Appebene (project/app/build.gradle)

```Kotlin

plugins {
    id 'com.android.application'

    // Add the Google services Gradle plugin
    id 'com.google.gms.google-services'
    ...
}
...

dependencies {
  // ...

  // Import the Firebase BoM
  implementation platform('com.google.firebase:firebase-bom:31.5.0')

  // When using the BoM, you don't specify versions in Firebase library dependencies

  // Add the dependency for the Firebase SDK for Google Analytics
  implementation 'com.google.firebase:firebase-analytics-ktx'
  
  // TODO: Add the dependencies for any other Firebase products you want to use
  // See https://firebase.google.com/docs/android/setup#available-libraries
  // For example, add the dependencies for Firebase Authentication, Cloud Firestore and Database
  implementation 'com.google.firebase:firebase-auth-ktx'
  implementation 'com.google.firebase:firebase-firestore-ktx'
  implementation 'com.google.firebase:firebase-database-ktx'
}

```

5. Authentifizierung einrichten (optional): Wenn Sie die Firebase-Authentifizierungsfunktionen verwenden möchten, müssen Sie diese in der Firebase-Konsole aktivieren und in Ihrem Android-Projekt konfigurieren.

6. Verbinden Sie Ihr Android-Projekt mit der Firebase Realtime Database oder Firestore: Sie können die Firebase Realtime Database oder Firestore in Ihrem Android-Projekt verwenden, um Daten zu speichern und abzurufen. Weitere Informationen zu den einzelnen Schritten finden Sie in der Firebase-Dokumentation.

Es ist wichtig, dass Sie die Schritte sorgfältig ausführen, um sicherzustellen, dass Ihre App erfolgreich mit Firebase verbunden ist und die gewünschten Funktionen reibungslos funktionieren.

Der Testzeitraum Endet um 1683669600000