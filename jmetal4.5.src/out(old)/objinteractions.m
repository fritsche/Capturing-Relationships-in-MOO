%objinteractios.m

true = load ("true_structure.txt");
true = true(17:21, 17:21);

"Kendalls"
bin = load ("Kendalls.txt");
bin = bin.J(17:21,17:21);

TP = (sum (sum ((true + bin) == (ones(5,5)*2))) - 5)/2
TN = sum (sum ((true + bin) == (zeros(5,5))))/2
FP = sum (sum ((bin - true) == (ones(5,5))))/2
FN = sum (sum ((bin - true) == (ones(5,5)*-1)))/2

sen = (TP/(TP + FN))
spe = (TN/(TN + FP))

"Pearsons"
bin = load ("Pearsons.txt");
bin = bin.J(17:21,17:21);

TP = (sum (sum ((true + bin) == (ones(5,5)*2))) - 5)/2
TN = sum (sum ((true + bin) == (zeros(5,5))))/2
FP = sum (sum ((bin - true) == (ones(5,5))))/2
FN = sum (sum ((bin - true) == (ones(5,5)*-1)))/2

sen = (TP/(TP + FN))
spe = (TN/(TN + FP))

"Spearmans"
bin = load ("Spearmans.txt");
bin = bin.J(17:21,17:21);

TP = (sum (sum ((true + bin) == (ones(5,5)*2))) - 5)/2
TN = sum (sum ((true + bin) == (zeros(5,5))))/2
FP = sum (sum ((bin - true) == (ones(5,5))))/2
FN = sum (sum ((bin - true) == (ones(5,5)*-1)))/2

sen = (TP/(TP + FN))
spe = (TN/(TN + FP))