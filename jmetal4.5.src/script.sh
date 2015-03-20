#!/bin/bash

reset
echo "fixing non ASCII chars on jmetal..."
export JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF8

echo "making jgraphx.jar ..."
cd libs/jgraphx/src
find -name "*.java" > source.txt
javac @source.txt
find -name "*.class" > class.txt
jar cf jgraphx.jar @class.txt
cd ../../../

echo "loading java files..."
find -name "*.java" > sources.aux
echo "compiling..."
javac -classpath ".:libs/commons-math3-3.4.1/commons-math3-3.4.1.jar" @sources.aux
#if error
if ! [ $? -eq 0 ]; then
	echo "COMPILATION ERROR!"
else
	echo "removing aux files..."
	rm *.aux
	echo "running..."
	#java jmetal.metaheuristics.singleObjective.cmaes.CMAES_main > output.txt
	java -classpath ".:libs/commons-math3-3.4.1/commons-math3-3.4.1.jar" jmetal.learning.SMPSOTest
fi

echo "done."
