%varobjinteractions.m

true = load ("true_structure.txt");
true = true(1:16, 17:21);

"Kendalls"
bin = load ("Kendalls.txt");
bin = bin.J(1:16,17:21);

TP = (sum (sum ((true + bin) == (ones(16,5)*2))))
TN = sum (sum ((true + bin) == (zeros(16,5))))
FP = sum (sum ((bin - true) == (ones(16,5))))
FN = sum (sum ((bin - true) == (ones(16,5)*-1)))

sen = (TP/(TP + FN))
spe = (TN/(TN + FP))

"Pearsons"
bin = load ("Pearsons.txt");
bin = bin.J(1:16,17:21);

TP = (sum (sum ((true + bin) == (ones(16,5)*2))))
TN = sum (sum ((true + bin) == (zeros(16,5))))
FP = sum (sum ((bin - true) == (ones(16,5))))
FN = sum (sum ((bin - true) == (ones(16,5)*-1)))

sen = (TP/(TP + FN))
spe = (TN/(TN + FP))

"Spearmans"
bin = load ("Spearmans.txt");
bin = bin.J(1:16,17:21);

TP = (sum (sum ((true + bin) == (ones(16,5)*2))))
TN = sum (sum ((true + bin) == (zeros(16,5))))
FP = sum (sum ((bin - true) == (ones(16,5))))
FN = sum (sum ((bin - true) == (ones(16,5)*-1)))

sen = (TP/(TP + FN))
spe = (TN/(TN + FP))