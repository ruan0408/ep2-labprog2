
dist/EP4.jar: src/Parser.java
	ant

src/Parser.java: src/Parser.jj
	cd src/; javacc *.jj; cd -

