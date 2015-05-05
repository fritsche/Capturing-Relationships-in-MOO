% six_shots.m

shots = [0 156 312 468 598 745];

%Kendalls
for i = 1:6
	a = zeros(21,21);
	for j = 1:30
		in = ['Kendalls/double/execucao_' num2str(j) '/' num2str(shots(i)) '.txt'];
		b =  load(in);
		a = a + b;
	endfor
	a = a / 30;
	out=['Kendalls/shot' num2str(shots(i)) '.txt'];
	save('-text', out, 'a');
endfor


%Pearsons
for i = 1:6
	a = zeros(21,21);
	for j = 1:30
		in = ['Pearsons/double/execucao_' num2str(j) '/' num2str(shots(i)) '.txt'];
		b =  load(in);
		a = a + b;
	endfor
	a = a / 30;
	out=['Pearsons/shot' num2str(shots(i)) '.txt'];
	save('-text', out, 'a');
endfor


%Spearmans
for i = 1:6
	a = zeros(21,21);
	for j = 1:30
		in = ['Spearmans/double/execucao_' num2str(j) '/' num2str(shots(i)) '.txt'];
		b =  load(in);
		a = a + b;
	endfor
	a = a / 30;
	out=['Spearmans/shot' num2str(shots(i)) '.txt'];
	save('-text', out, 'a');
endfor