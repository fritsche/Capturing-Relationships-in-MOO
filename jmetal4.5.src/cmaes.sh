#!/bin/bash

reset

#compiling
find -name "*.java" > sources.txt
javac -classpath ".:libs/commons-math3-3.4.1/commons-math3-3.4.1.jar:libs/jgraphx/jgraphx.jar:libs/JavaMI/JavaMI.jar" @sources.txt
rm sources.txt
#running
java -classpath ".:libs/commons-math3-3.4.1/commons-math3-3.4.1.jar:libs/jgraphx/jgraphx.jar:libs/JavaMI/JavaMI.jar"  jmetal.metaheuristics.singleObjective.cmaes.CMAES_main > output.txt
