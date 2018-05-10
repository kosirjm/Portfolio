% Jonathan Kosir
% ECE 425/525
% Homework 8
% This is a simple bandstop program
%--------------------------------------------------------------

% Variables
fs = 8000;
fileName = '.\test_voice.wav';
[file,fs] = audioread(fileName);

% Read in test voice.wav think this is right
line=linspace(0,pi,1020);
[h,w]=freqz(file,fs,line);

% Plot the read in audio file
% Phase and magnitude vs frequency
figure(1)
subplot(2, 1, 1);
plot(w/pi,20*log10(abs(h)));
title('Currupt Voice');
ylabel('Mag');
subplot(2,1,2);
plot(w/pi,(360/(2*pi))*angle(h));
ylabel('Phase');
xlabel('Frequency');
% Design a bandstop filter to attenuate interference
% --------------------------------------------------
% Variables needed for filter design
cutoff = [(pi*.52) (pi*.55)]; % cutoff frequencies
idealCutoff = sum(cutoff)/2 % ideal cut off
N = 40;
n = -((N-1)/2):((N-1)/2);
n = n+(n==0)*eps;  % Get our N values
[userWindow]=hamming(N) % window function

[h] = 1-(sin(cutoff(2)*n)-sin(cutoff(1)*n))/(n*pi); % ideal bandstop coeffecience
idealfilter = userWindow .* h % filter coeffecients

% Plot our filter
[q,t]=freqz(idealfilter,fs,line);
figure(2)
subplot(2,1,1);
plot(w/pi,20*log10(abs(q)));
title('Filtered Frequency Response');
ylabel('Mag');
subplot(2,1,2);
plot(w/pi,(360/(2*pi))*angle(t));
ylabel('Phase');
xlabel('Frequency');
%Filter the sound file with our bandstop filter
final = filter(h,1,file);
[h,w]=freqz(final,fs,line);

% Plot final filtered sound 
figure(3)
subplot(2, 1, 1);
plot(w/pi,20*log10(abs(h)));
title('Filtered Voice');
ylabel('Mag');
subplot(2,1,2);
plot(w/pi,(360/(2*pi))*angle(h));
ylabel('Phase');
xlabel('Frequency');

% Play sound file
sound(final,fs);
pause(5);

audiowrite('filtered.wav',final,fs);