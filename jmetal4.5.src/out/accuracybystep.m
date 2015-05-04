%accuracybystep.m

true = load ("true_structure.txt");

"Kendalls"
j=1
for i = 0:26:745
	in = ["Kendalls/" num2str(i) ".txt"];
	bin = load (in);
	bin = bin.J;

	TP = (sum (sum ((true + bin) == (ones(21,21)*2))) - 21)/2;
	TN = sum (sum ((true + bin) == (zeros(21,21))))/2;
	FP = sum (sum ((bin - true) == (ones(21,21))))/2;
	FN = sum (sum ((bin - true) == (ones(21,21)*-1)))/2;

	ksen(j) = (TP/(TP + FN));
	kspe(j) = (TN/(TN + FP));
	j++;
endfor
i = 745
in = ["Kendalls/" num2str(i) ".txt"];
bin = load (in);
bin = bin.J;

TP = (sum (sum ((true + bin) == (ones(21,21)*2))) - 21)/2;
TN = sum (sum ((true + bin) == (zeros(21,21))))/2;
FP = sum (sum ((bin - true) == (ones(21,21))))/2;
FN = sum (sum ((bin - true) == (ones(21,21)*-1)))/2;

ksen(j) = (TP/(TP + FN))
kspe(j) = (TN/(TN + FP))


"Spearmans"
j=1
for i = 0:26:745
	in = ["Spearmans/" num2str(i) ".txt"];
	bin = load (in);
	bin = bin.J;

	TP = (sum (sum ((true + bin) == (ones(21,21)*2))) - 21)/2;
	TN = sum (sum ((true + bin) == (zeros(21,21))))/2;
	FP = sum (sum ((bin - true) == (ones(21,21))))/2;
	FN = sum (sum ((bin - true) == (ones(21,21)*-1)))/2;

	ssen(j) = (TP/(TP + FN));
	sspe(j) = (TN/(TN + FP));
	j++;
endfor
i = 745
in = ["Spearmans/" num2str(i) ".txt"];
bin = load (in);
bin = bin.J;

TP = (sum (sum ((true + bin) == (ones(21,21)*2))) - 21)/2;
TN = sum (sum ((true + bin) == (zeros(21,21))))/2;
FP = sum (sum ((bin - true) == (ones(21,21))))/2;
FN = sum (sum ((bin - true) == (ones(21,21)*-1)))/2;

ssen(j) = (TP/(TP + FN))
sspe(j) = (TN/(TN + FP))


"Pearsons"
j=1
for i = 0:26:745
	in = ["Pearsons/" num2str(i) ".txt"];
	bin = load (in);
	bin = bin.J;

	TP = (sum (sum ((true + bin) == (ones(21,21)*2))) - 21)/2;
	TN = sum (sum ((true + bin) == (zeros(21,21))))/2;
	FP = sum (sum ((bin - true) == (ones(21,21))))/2;
	FN = sum (sum ((bin - true) == (ones(21,21)*-1)))/2;

	psen(j) = (TP/(TP + FN));
	pspe(j) = (TN/(TN + FP));
	j++;
endfor
i = 745
in = ["Pearsons/" num2str(i) ".txt"];
bin = load (in);
bin = bin.J;

TP = (sum (sum ((true + bin) == (ones(21,21)*2))) - 21)/2;
TN = sum (sum ((true + bin) == (zeros(21,21))))/2;
FP = sum (sum ((bin - true) == (ones(21,21))))/2;
FN = sum (sum ((bin - true) == (ones(21,21)*-1)))/2;

psen(j) = (TP/(TP + FN))
pspe(j) = (TN/(TN + FP))

base=([0:26:745]);
base(end+1)=745;

figure 1;
plot(base, ksen, 'r-+');
hold on;
plot(base, ssen, 'g-o');
plot(base, psen, 'b-*');
legend("Kendalls", "Spearmans", "Pearsons");
legend location northwest
xlabel ("it");
ylabel ("sen");
print -dtex sen.tex
print -depsc2 sen.eps

figure 2;
plot(base, kspe, 'r-+');
hold on;
plot(base, sspe, 'g-o');
plot(base, pspe, 'b-*');
legend("Kendalls", "Spearmans", "Pearsons");
legend location southwest
xlabel ("it");
ylabel ("spe");
print -dtex spe.tex
print -depsc2 spe.eps