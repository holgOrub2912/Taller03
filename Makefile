taller3:
	javac -g -d bin/ --class-path 'lib/*' src/*
run:
	java --class-path bin/ Taller3
debug:
	jdb -classpath bin/ Taller3
clean:
	rm bin/*
