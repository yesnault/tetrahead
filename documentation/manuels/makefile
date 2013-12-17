all:
	pdflatex manuel.tex
	makeindex manuel.idx
	pdflatex manuel.tex
	xpdf manuel.pdf

clean:
	rm -r *.toc *.ilg *.ind *.lof *.log *.mtc* *.out *.aux
