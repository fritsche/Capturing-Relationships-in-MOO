% threshold3.m

limiar=(30 * 0.4)

% Kendalls
for j = 0:26:745
	I = zeros(21, 21);
	j
	for i = 1:30
		in=["Kendalls/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
		bin = load(in);
		bin = bin.bin;
		I = I + bin;
	endfor
	in=["Kendalls/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
	bin = load(in);
	bin = bin.bin;
	I = I + bin;
	J = (I > limiar);
	out=["Kendalls/" num2str(j) ".txt"];
	save("-text", out, "J");
endfor
j=745;
I = zeros(21, 21);
j
for i = 1:30
	in=["Kendalls/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
	bin = load(in);
	bin = bin.bin;
	I = I + bin;
endfor
in=["Kendalls/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
bin = load(in);
bin = bin.bin;
I = I + bin;
J = (I > limiar);
out=["Kendalls/" num2str(j) ".txt"];
save("-text", out, "J");

% Pearsons
for j = 0:26:745
	I = zeros(21, 21);
	j
	for i = 1:30
		in=["Pearsons/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
		bin = load(in);
		bin = bin.bin;
		I = I + bin;
	endfor
	in=["Pearsons/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
	bin = load(in);
	bin = bin.bin;
	I = I + bin;
	J = (I > limiar);
	out=["Pearsons/" num2str(j) ".txt"];
	save("-text", out, "J");
endfor
j=745;
I = zeros(21, 21);
j
for i = 1:30
	in=["Pearsons/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
	bin = load(in);
	bin = bin.bin;
	I = I + bin;
endfor
in=["Pearsons/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
bin = load(in);
bin = bin.bin;
I = I + bin;
J = (I > limiar);
out=["Pearsons/" num2str(j) ".txt"];
save("-text", out, "J");

% Spearmans
for j = 0:26:745
	I = zeros(21, 21);
	j
	for i = 1:30
		in=["Spearmans/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
		bin = load(in);
		bin = bin.bin;
		I = I + bin;
	endfor
	in=["Spearmans/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
	bin = load(in);
	bin = bin.bin;
	I = I + bin;
	J = (I > limiar);
	out=["Spearmans/" num2str(j) ".txt"];
	save("-text", out, "J");
endfor
j=745;
I = zeros(21, 21);
j
for i = 1:30
	in=["Spearmans/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
	bin = load(in);
	bin = bin.bin;
	I = I + bin;
endfor
in=["Spearmans/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
bin = load(in);
bin = bin.bin;
I = I + bin;
J = (I > limiar);
out=["Spearmans/" num2str(j) ".txt"];
save("-text", out, "J");

