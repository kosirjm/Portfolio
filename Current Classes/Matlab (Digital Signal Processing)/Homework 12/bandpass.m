% Jonathan Kosir
% ECE 425/525
% Homework 12
% This is a iir bandpass filter
%--------------------------------------------------------------

% Read in aduio file
fileName = '.\test2.wav';
[file,fs] = audioread(fileName);
sound(file,fs);
pause(10);

% Read in test voice.wav think this is right
vals=fft(file,fs);

% Plot the read in audio file
% Phase and magnitude vs frequency
figure(1)
subplot(2, 1, 1);
plot((0:fs-1)./fs,(20*log10(abs(vals))));
title('Currupt Audio File');
ylabel('Mag');
subplot(2,1,2);
plot((0:fs-1)./fs,((360/(2*pi))*angle(vals)));
ylabel('Phase');
xlabel('Frequency');

% Design a IIR bandpass filter
% --------------------------------------------------
% Variables needed for filter design
at1=1;
at2=35; 
wp=[.3*pi,.7*pi]; 
ws=[.25*pi,.75*pi]; 

% Cheb filter design using matlab commands
[n,Wp] = cheb1ord(Wp,Ws,at1,at2) 
[b,a] = cheby1(n,Rp,Wp);       

% Plot our filters mag and phase
figure(2)
[h,w] = freqz(b,a,512,1000);           
subplot(2, 1, 1);
plot(w/pi,20*log10(abs(h)));
title('Filter');
ylabel('Mag');
subplot(2,1,2);
plot(w/pi,(360/(2*pi))*angle(h));
ylabel('Phase');
xlabel('Frequency');

% Filter audio file
final = filter(b,a,file)

% Plot final filtered sound  magnitude and phase
figure(3)
vals=fft(final,fs);
subplot(2, 1, 1);
plot((0:fs-1)./fs,(20*log10(abs(vals))));
title('Filtered Audio File');
ylabel('Mag');
subplot(2,1,2);
plot((0:fs-1)./fs,((360/(2*pi))*angle(vals)));
ylabel('Phase');
xlabel('Frequency');


% Play sound file
sound(final,fs);

% write wav out
audiowrite('filtered.wav',final,fs);