% update_bin.m

limiar=0.3

% Kendalls
for i = 1:30
	i
	for j = 0:26:745
		in=["Kendalls/double/execucao_" num2str(i) "/" num2str(j) ".txt"];
		out=["Kendalls/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
		double = load(in);
		bin = (abs(double) > limiar);
		save("-text", out, "bin");
	endfor
	j=745;
	in=["Kendalls/double/execucao_" num2str(i) "/" num2str(j) ".txt"];
	out=["Kendalls/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
	double = load(in);
	bin = (abs(double) > limiar);
	save("-text", out, "bin");
endfor

% Spearmans
for i = 1:30
	i
	for j = 0:26:745
		in=["Spearmans/double/execucao_" num2str(i) "/" num2str(j) ".txt"];
		out=["Spearmans/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
		double = load(in);
		bin = (abs(double) > limiar);
		save("-text", out, "bin");
	endfor
	j=745;
	in=["Spearmans/double/execucao_" num2str(i) "/" num2str(j) ".txt"];
	out=["Spearmans/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
	double = load(in);
	bin = (abs(double) > limiar);
	save("-text", out, "bin");
endfor

% Pearsons
for i = 1:30
	i
	for j = 0:26:745
		in=["Pearsons/double/execucao_" num2str(i) "/" num2str(j) ".txt"];
		out=["Pearsons/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
		double = load(in);
		bin = (abs(double) > limiar);
		save("-text", out, "bin");
	endfor
	j=745;
	in=["Pearsons/double/execucao_" num2str(i) "/" num2str(j) ".txt"];
	out=["Pearsons/binario/execucao_" num2str(i) "/" num2str(j) ".txt"];
	double = load(in);
	bin = (abs(double) > limiar);
	save("-text", out, "bin");
endfor