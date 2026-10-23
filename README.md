# Activitate DAM 2026

Ghisea Patrick-Teodor · grupa 1080

Proiect Android în Java, cu interfețe XML Views și minimum SDK 24. Cele două module sunt aplicații separate în același proiect Gradle.

## Deschidere și rulare

1. Deschide acest director în Android Studio și așteaptă sincronizarea Gradle.
2. Selectează JDK 17 sau 21 pentru Gradle. Versiunea inclusă în unele ediții Android Studio poate fi prea nouă pentru Gradle 8.11.1.
3. Instalează SDK Platform 35 și Build Tools 35.0.0, dacă IDE-ul le solicită.
4. Selectează configurația `laborator23` sau `laborator4`, alege emulatorul și apasă Run.

Din terminal, cu JDK 17 sau 21 și Android SDK configurate:

```bash
./gradlew assembleDebug lintDebug
```

Fișierul `local.properties` este specific calculatorului și nu este inclus în Git. Android Studio îl generează local.

## Laboratorul 2–3

`SecondActivity` pornește aplicația, conform cerinței 5. Configurația MAIN/LAUNCHER se află în manifest. `MainActivity` este prima activitate creată și poate fi deschisă cu butonul de demonstrare a lifecycle-ului.

`LifecycleActivity` implementează și explică prin comentarii `onStart`, `onResume`, `onPause` și `onStop`. Toate cele trei activități o moștenesc. Fiecare etapă produce câte un mesaj E, W, D, I și V cu tag-ul `LifecycleDemo`. Mesajele E/W sunt demonstrații ale nivelurilor de log, nu erori ale aplicației.

În activitatea 2 se introduc un mesaj și două numere întregi. Un Intent explicit deschide activitatea 3, iar valorile sunt atașate într-un Bundle. Activitatea 3 le afișează într-un Toast și pe ecran. Butonul de întoarcere calculează suma și trimite mesajul de răspuns și rezultatul prin `setResult`, apoi închide activitatea cu `finish`. Activitatea 2 primește rezultatul în `onActivityResult` și îl afișează într-un Toast și într-un TextView.

Se folosește mecanismul clasic `startActivityForResult` pentru a ilustra laboratorul. În proiecte noi se recomandă Activity Result API. Suma este calculată ca `long`, astfel încât două valori `int` valide să nu producă overflow. Câmpurile goale și numerele din afara intervalului `int` sunt respinse.

### Logcat

În Android Studio: View → Tool Windows → Logcat. Selectează emulatorul și introdu:

```text
tag:LifecycleDemo level:VERBOSE
```

Pentru o singură activitate:

```text
tag:LifecycleDemo level:VERBOSE message:SecondActivity
```

La pornire se observă `onCreate → onStart → onResume`. La trecerea în activitatea 3, activitatea 2 primește `onPause`, iar după ce nu mai este vizibilă primește `onStop`. La întoarcere apar din nou `onStart` și `onResume`. Butonul pentru activitatea 1 permite urmărirea acelorași tranziții pentru clasa inițială.

Captura reală din [Logcat](dovezi/logcat-android-studio.png) arată cele patru etape cerute și toate cele cinci niveluri. Emulatorul fusese închis la salvarea capturii, de aceea apare OFFLINE; mesajele provin din execuția anterioară. [Exportul text](dovezi/lifecycle-logcat.txt) conține logurile filtrate.

## Laboratorul 4

Obiectul ales este `PachetFotografic`, o ofertă de servicii foto. Denumirea conține inițialele G, P și T. Atributele sunt denumire, preț, durată, categorie, locație, includerea unui album, editare profesională și evaluare.

`AddActivity` este varianta de bază a formularului. `AddAiActivity` păstrează funcționalitatea și controalele, cu o prezentare vizuală asistată de AI. Ambele rămân în proiect și sunt accesibile din activitatea principală.

Formularele includ TextView, EditText, Button, CheckBox, RadioButton, Spinner, RatingBar și Switch. `FormActivity` centralizează validarea și citirea controalelor. La salvare se construiește o instanță `PachetFotografic`; atributele ei sunt serializate într-un Bundle și returnate activității principale, care reconstruiește obiectul și îl afișează în TextView.

Prețul și durata trebuie să fie pozitive, iar denumirea este obligatorie. Butonul Renunță revine fără a înlocui obiectul afișat. Datele sunt păstrate în memoria activității și prin starea salvată pentru recrearea ecranului; laboratorul nu cere o bază de date sau persistență după închiderea aplicației.

## Verificări efectuate

Compilare `assembleDebug` și Android Lint: reușite pentru ambele module. Lint păstrează avertismente de stil, internaționalizare și API-uri vechi; nu există erori blocante.

Testare efectivă pe emulator ARM64 Pixel 7, Android 15 / API 35:

- activitatea 2 este lansată la pornire;
- mesajul și valorile 10 și 20 ajung în activitatea 3, iar rezultatul 30 revine;
- 2147483647 + 2147483647 produce 4294967294 și se întoarce corect;
- un câmp numeric gol nu deschide activitatea 3;
- navigarea la activitatea 1 și revenirea funcționează;
- formularul de bază returnează „Portret de toamna”, 350 lei, 60 minute;
- formularul AI returnează „Eveniment de seara”, 500 lei, 90 minute, Exterior, album inclus;
- Logcat conține nivelurile E/W/D/I/V și tranzițiile lifecycle.

Capturile sunt în directorul `dovezi`. Compatibilitatea API 24 este declarată în proiect și verificată de Lint; rularea efectivă a fost verificată pe API 35.

## Repere pentru explicarea codului

- Intent descrie activitatea care trebuie deschisă; Bundle transportă valorile asociate cheilor.
- `setResult(RESULT_OK, intent)` pregătește răspunsul; `finish()` revine la activitatea precedentă.
- `onPause` indică pierderea prim-planului; `onStop` indică faptul că activitatea nu mai este vizibilă.
- Un control de UI se citește prin ID-ul său din XML; câmpurile se validează înainte de construirea obiectului.
- Cele două formulare folosesc același model și același contract de returnare, chiar dacă arată diferit.

Codul și varianta vizuală AI au fost realizate cu asistență AI și sunt comentate pentru parcurgere și explicare.

## Documentație

- [Ciclul de viață al activităților](https://developer.android.com/guide/components/activities/activity-lifecycle)
- [Primirea unui rezultat de la o activitate](https://developer.android.com/training/basics/intents/result)
- [Logcat în Android Studio](https://developer.android.com/studio/debug/logcat)
