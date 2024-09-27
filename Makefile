taller3:
	javac -g -d bin/ --class-path 'lib/*' src/*
run:
	java --class-path 'bin/:lib/*' Taller3
debug:
	jdb -classpath 'bin/:lib/*' Taller3
clean:
	rm bin/*
