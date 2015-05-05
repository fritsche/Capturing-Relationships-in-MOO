#!/bin/bash

for i in Kendalls Pearsons Spearmans
do
	for j in 0 156 312 468 598 745
	do
		tail -n 23 $i"/shot"$j".txt" > $i"/shot"$j"_clean.txt"
	done
done

