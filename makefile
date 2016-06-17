CLASSESFULL := $(shell find -name *.class)
CLASSES := $(shell cd build/;find -name *.class)

.PHONY: all
all: release/les.jar
	
release/les.jar: $(CLASSESFULL)
	cd build/;jar -cfm ../release/les.jar ../Manifest.txt $(CLASSES);cd ../

build/nouth/les/%.class: nouth/les/%.java
	javac -d build/ $< 

