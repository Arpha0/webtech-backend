# webtech-backend

Thema: Rezeptsammlung

Bedienungsanleitung
- Registrierung: Oben rechts Name und Passwort eingeben und auf "Sign up" klicken.
- Login: Links oben den Account auswählen, Passwort eingeben und auf "Login" klicken.
- Rezept erstellen: Formular ausfüllen (Name, Kategorie, Bild hochladen, Dauer, Anleitung) und auf "Hinzufügen" klicken.
- Details: Auf eine Rezept-Karte klicken, um die große Ansicht zu öffnen.
- Bearbeiten/Löschen: In der Detail-Ansicht auf den Stift (Bearbeiten) oder den Mülleimer (Löschen) klicken.
- Suchen: Suchbegriff eingeben oder oben auf die Kategorie-Buttons klicken.

Entity-Klassen Entwurf

Rezept-Klasse
- id: Primärschlüssel (einzigartiges Rezept)
- nameRezept: Der Name des Rezepts
- anleitungRezept: Der Zubereitungstext (enthält auch die Zutaten)
- bild: Das Bild als Text-Code gespeichert
- kategorie: Die Kategorie (z.B. "Hauptgericht")
- dauer: Die Zubereitungszeit
- owner: Verweis auf die User-ID (Wem gehört das Rezept?)

User-Klasse
- id: Primärschlüssel
- username: Der Benutzername
- password: Das Passwort