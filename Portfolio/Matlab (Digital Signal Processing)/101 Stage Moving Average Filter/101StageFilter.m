% Homework 3
% ECE 425/525
% Jonathan Kosir
% This is a script which will record my voice and apply a 101-stage 
% moving average filter

% Record voice
disp('Start Speaking');
myVoice = audiorecorder(11025,16,1);
recordblocking(myVoice,3);
disp('end');

% Find max and multiply entire value to get .95 max
myVoice =  getaudiodata(myVoice);
%maxVal = realmax('double');
temp = sort(myVoice, 'descend');
multiplier = .95/max(abs(myVoice));
myVoice = multiplier * myVoice(:);

% Make copies
myVoice1 = myVoice;
myVoice2 = myVoice;
myVoice3 = myVoice;

% Play voice then pause 
sound(myVoice,11025);
pause(3);

% Write to wav
audiowrite('voice20Hz.wav',myVoice1,20);
audiowrite('voice20kHz.wav',myVoice2,20000);

% Build Filter
myFilter = zeros(1,101);
myFilter(1) = .7;
myFilter(31) = .2;
myFilter(101) = .1;

% Filter voice then play and save file
myVoice3 = filter(myFilter, 1, myVoice3);
audiowrite('voiceFiltered.wav',myVoice3,11025);
sound(myVoice3,11025);

