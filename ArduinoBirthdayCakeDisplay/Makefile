birthday-cake :
	javac -cp ./lib/jSerialComm-1.3.11.jar \
	src/com/cmjten/arduino/bdaydisplay/BDayDisplay.java \
	src/com/cmjten/arduino/bdaydisplay/DisplayController.java \
	src/com/cmjten/arduino/bdaydisplay/DisplayPanel.java

run :
	cd ./src && \
	java -cp ../lib/jSerialComm-1.3.11.jar:../resources: \
	com.cmjten.arduino.bdaydisplay.BDayDisplay

clean :
	rm ./src/com/cmjten/arduino/bdaydisplay/*.class
