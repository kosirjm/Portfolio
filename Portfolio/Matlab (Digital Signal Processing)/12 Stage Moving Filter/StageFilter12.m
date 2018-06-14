% Jonathan Kosir
% Homework 6
% ECE 425/525
% This is a script which will record my voice add noise 
% and apply a 12 stage moving filter to remove this noise
%-----------------------------------------------------------------------

Hz = 44100;

% Record voice
disp('Start Speaking');
myVoice = audiorecorder(Hz,16,1);
recordblocking(myVoice,2);
disp('end');

% Find max and multiply entire value to get .95 max
myVoice =  getaudiodata(myVoice);
multiplier=.85/max(abs(myVoice));
myVoice = multiplier * myVoice(:);

% Play voice then pause 
sound(myVoice,Hz);
pause(3);

% Get Length
lengthMyVoice = size(myVoice);

% Create radnom noise Array
rndNoiseArray = randn(1,lengthMyVoice(1));
multiplier=.1/max(abs(rndNoiseArray));
rndNoiseArray = multiplier * rndNoiseArray(:);

% Currupt My Voice
curruptVoice = rndNoiseArray + myVoice;

% Play Currupt then pause
sound(curruptVoice,Hz);
pause(3);

% Create Filter
myFilter(1:12) = .0833;

% Filter voice then play and save file
filteredVoice = filter(myFilter, 1, curruptVoice);

% Play Filtered Voice then pause
sound(filteredVoice,Hz);
pause(3);

% Define Error sequence
errorSequence = abs(mean(filteredVoice) - mean(myVoice))

% Calculate Relative Error 
% Relative error = absolute error/ measurement being taken
relativeError=norm(filteredVoice-myVoice)/norm(myVoice) 

% Write to wav
audiowrite('voiceOrig44100Hz.wav',myVoice,Hz);
audiowrite('voiceCurrupt44100Hz.wav',curruptVoice,Hz);
audiowrite('voiceFiltered44100Hz.wav',filteredVoice,Hz);

