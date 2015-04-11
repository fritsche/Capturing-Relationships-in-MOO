#!/bin/bash

reset
echo "fixing non ASCII chars on jmetal..."
export JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF8

echo "making jgraphx.jar ..."
cd libs/jgraphx/src
find -name "*.java" > source.aux
javac @source.aux
find -name "*.*" > class.aux
jar cf ../jgraphx.jar @class.aux
rm *.aux
cd ../../../

echo "loading java files..."
find -name "*.java" > sources.aux
echo "compiling..."
javac -classpath ".:libs/commons-math3-3.4.1/commons-math3-3.4.1.jar:libs/jgraphx/jgraphx.jar:libs/JavaMI/JavaMI.jar" @sources.aux
#if error
if ! [ $? -eq 0 ]; then
	echo "COMPILATION ERROR!"
else
	echo "removing aux files..."
	rm *.aux
	echo "running..."
	java -classpath ".:libs/commons-math3-3.4.1/commons-math3-3.4.1.jar:libs/jgraphx/jgraphx.jar:libs/JavaMI/JavaMI.jar" jmetal.learning.SMPSOTest
fi

echo "done."

