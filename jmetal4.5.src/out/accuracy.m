%accuracy.m

true = load ("true_structure.txt");

"Kendalls"
bin = load ("Kendalls.txt");
bin = bin.J;

TP = (sum (sum ((true + bin) == (ones(21,21)*2))) - 21)/2
TN = sum (sum ((true + bin) == (zeros(21,21))))/2
FP = sum (sum ((bin - true) == (ones(21,21))))/2
FN = sum (sum ((bin - true) == (ones(21,21)*-1)))/2

sen = (TP/(TP + FN))
spe = (TN/(TN + FP))

"Pearsons"
bin = load ("Pearsons.txt");
bin = bin.J;

TP = (sum (sum ((true + bin) == (ones(21,21)*2))) - 21)/2
TN = sum (sum ((true + bin) == (zeros(21,21))))/2
FP = sum (sum ((bin - true) == (ones(21,21))))/2
FN = sum (sum ((bin - true) == (ones(21,21)*-1)))/2

sen = (TP/(TP + FN))
spe = (TN/(TN + FP))

"Spearmans"
bin = load ("Spearmans.txt");
bin = bin.J;

TP = (sum (sum ((true + bin) == (ones(21,21)*2))) - 21)/2
TN = sum (sum ((true + bin) == (zeros(21,21))))/2
FP = sum (sum ((bin - true) == (ones(21,21))))/2
FN = sum (sum ((bin - true) == (ones(21,21)*-1)))/2

sen = (TP/(TP + FN))
spe = (TN/(TN + FP))