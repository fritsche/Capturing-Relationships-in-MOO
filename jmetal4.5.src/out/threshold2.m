% threshold.m

limiar=(900 * 0.3)
I = zeros(21, 21);

% Kendalls
for i = 1:30
	i
	for j = 0:26:745
		in=["Kendalls/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
		bin = load(in);
		bin = bin.bin;
		I = I + bin;
	endfor
	j=745;
	in=["Kendalls/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
	bin = load(in);
	bin = bin.bin;
	I = I + bin;
endfor
J = (I > limiar);
save Kendalls.txt J

% Pearsons
for i = 1:30
	i
	for j = 0:26:745
		in=["Pearsons/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
		bin = load(in);
		bin = bin.bin;
		I = I + bin;
	endfor
	j=745;
	in=["Pearsons/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
	bin = load(in);
	bin = bin.bin;
	I = I + bin;
endfor
J = (I > limiar);
save Pearsons.txt J

% Spearmans
for i = 1:30
	i
	for j = 0:26:745
		in=["Spearmans/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
		bin = load(in);
		bin = bin.bin;
		I = I + bin;
	endfor
	j=745;
	in=["Spearmans/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
	bin = load(in);
	bin = bin.bin;
	I = I + bin;
endfor
J = (I > limiar);
save Spearmans.txt J