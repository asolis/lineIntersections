#!/bin/bash
if [ ! -d 'bin/' ];
	then mkdir bin
fi
javac -d bin/ -cp src/ src/gui/LineSegment.java
