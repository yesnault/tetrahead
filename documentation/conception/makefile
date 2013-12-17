all:
	pdflatex conception.tex
	makeindex conception.idx
	bibtex conception
	pdflatex conception.tex
	pdflatex conception.tex

quick:
	pdflatex conception.tex
	pdflatex conception.tex
	
clean:
	rm -r *.toc *.ilg *.ind *.lof *.log *.mtc* *.out *.aux *.bbl *.blg *.idx


