%varinteractios.m

true = load ("true_structure.txt");
true = true(1:16, 1:16);

"Kendalls"
bin = load ("Kendalls.txt");
bin = bin.J(1:16,1:16);

TP = (sum (sum ((true + bin) == (ones(16,16)*2))) - 16)/2
TN = sum (sum ((true + bin) == (zeros(16,16))))/2
FP = sum (sum ((bin - true) == (ones(16,16))))/2
FN = sum (sum ((bin - true) == (ones(16,16)*-1)))/2

sen = (TP/(TP + FN))
spe = (TN/(TN + FP))

"Pearsons"
bin = load ("Pearsons.txt");
bin = bin.J(1:16,1:16);

TP = (sum (sum ((true + bin) == (ones(16,16)*2))) - 16)/2
TN = sum (sum ((true + bin) == (zeros(16,16))))/2
FP = sum (sum ((bin - true) == (ones(16,16))))/2
FN = sum (sum ((bin - true) == (ones(16,16)*-1)))/2

sen = (TP/(TP + FN))
spe = (TN/(TN + FP))

"Spearmans"
bin = load ("Spearmans.txt");
bin = bin.J(1:16,1:16);

TP = (sum (sum ((true + bin) == (ones(16,16)*2))) - 16)/2
TN = sum (sum ((true + bin) == (zeros(16,16))))/2
FP = sum (sum ((bin - true) == (ones(16,16))))/2
FN = sum (sum ((bin - true) == (ones(16,16)*-1)))/2

sen = (TP/(TP + FN))
spe = (TN/(TN + FP))