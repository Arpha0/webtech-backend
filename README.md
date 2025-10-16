# webtech-backend

// Entity-Klassen Entwurf

Thema: Rezeptsammlung

Rezept-Klasse
id: Primärschlüssel (einzigartiges Rezept /keine Wiederholungen von Rezepten)
name: Der Name des Rezepts
anleitung: Der Zubereitungstext

Zutat-Klasse
id: Primärschlüssel (damit die sich nicht wiederholen)
name: Der Name der Zutat (z.B. "Mehl“, "Zucker")

Zutatenmenge-Klasse (Brücke)
id: Ein eigener Primärschlüssel für diese Verbindung
menge: Die numerische Menge (z.B. 250)
einheit: Die dazugehörige Einheit (z.B. „g", "ml", "Stück"

Ein Verweis auf die rezept_id (Welches Rezept?)
Ein Verweis auf die zutat_id (Welche Zutat?)
