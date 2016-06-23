## programmierpraktikum-abschlussprojekt-nofuture-1

[![Build Status](https://travis-ci.org/ProPra16/programmierpraktikum-abschlussprojekt-nofuture-1.svg?branch=master)](https://travis-ci.org/ProPra16/programmierpraktikum-abschlussprojekt-nofuture-1)

### Arbeitsweise:
![Meaning of Git](https://imgs.xkcd.com/comics/git.png)

### Branch Politik:

`master`  merged nur mit develop <br />
	Auf diesem Branch wird nicht gearbeitet <br />

`develop` dient zum mergen der feature Branches <br />

`feature/feature_name` sollte passenden Namen haben, doch wenn er 
lokal bleibt ist die Namensgebung irrelevant

###  Generelle Philosophie
Zur sauberen Trennung sollten feature Branches nicht gepusht werden,
sondern nach Fertigstellung mit develop Branch gemerged und danach gelöscht werden.

Nach Absprache mit der Gruppe erhält einer die Aufgabe den Merge von `develop` zum  `master` 
Branch zu vollziehen.

##### Nützliche Links
[Frank Westphal über TDD](http://www.frankwestphal.de/TestgetriebeneEntwicklung.html) <br />
[Git Cheatsheet](https://services.github.com/kit/downloads/github-git-cheat-sheet.pdf) <br />
[SourceTree](https://www.sourcetreeapp.com/) <br />
* Git GUI
* Lässt sich mit GitHub verbinden
* geclontes NoFuture Repository unter Lokal hinzufügen
* bietet bessere Übersicht über alle Branches und Veränderungen

