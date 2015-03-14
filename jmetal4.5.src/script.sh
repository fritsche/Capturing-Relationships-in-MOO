#!/bin/bash

reset

#compiling
find -name "*.java" > sources.txt
javac @sources.txt
rm sources.txt
#running
#java jmetal.metaheuristics.singleObjective.cmaes.CMAES_main > output.txt
