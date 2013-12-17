Comment faire un document qui respecte la bonne mise en page ?

- Pour chaque document à écrire, créer un nouveau repertoire (exemple
  : rep_doc) dans le répertoire "documention".
- Se placer dans rep_doc
- Écrire son fichier LaTeX, le compiler dans ce répertoire avec "pdflatex"
- Pour que la numérotation des pages se fasse correctement, il faut
  compiler deux fois de suite le fichier "tex".

Y-t-il des choses obligatoires à mettre dans un document ?

Oui. Voici un fichier "Hello world !" (Je vous recommande de le
prendre comme exemple) : 

\input{../document_type/document_type}

% Titre du document qui appara��t sur la première page
\newcommand{\titre}{Compte-rendu de la réunion du \\ Lundi 23 janvier 2005}

% Les paramètres suivants remplissent le tableau de propriété.
% Pour le paramètre \diffusion mettre en gras celui qui nous intéresse
\newcommand{\diffusion}{Libre & Restreint & \textbf{Confidentiel}}
\newcommand{\etat}{En progression}
\newcommand{\version}{1.0}
\newcommand{\datedoc}{23/01/2006}

% Début du document
\begin{document}

% Crée la première page
\input{../document_type/titre}

\mainmatter

% Historique du document. 
\begin{historique}
% Il faut ajouter une balise \histo pour chaque mise à jour
% du document.
% \histo{Date}{Prénom Nom}{Modification}{Version}
\histo{23/01/2006}{Thibaut Lelièvre}{Cr��ation}{1.0}
\end{historique}

\frontmatter

\chapter{Description}

Hello World !

% Fin du document
\end{document}

- Remarque sur les \frontmatter, \mainmatter et \backmatter :
Dans notre cas, seule la commande \mainmatter est obligatoire
(sinon, ça ne compile pas, et je ne sais pas pourquoi). L'idéal
est de la mettre juste après la page de titre, comme dans l'exemple.
Mais les balises \frontmatter et \backmatter ne sont pas utilisées :
vous pouvez donc vous en servir. Je n'ai pas mis le \mainmatter dans
le fichier titre.tex car cela fige la construction de votre document.

- Remarque sur les images :
Comme on utilise pdflatex, les images doivent être au format "png".

Une règle d'or :
NE JAMAIS MODIFIER LES FICHIERS document_type.tex ET titre.tex.
Si vous avez besoin d'une modification de ces fichiers, contactez-moi.

Thibaut LELIÈVRE
thibaut.lelievre@voila.fr
