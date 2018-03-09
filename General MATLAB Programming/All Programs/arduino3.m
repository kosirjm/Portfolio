function [] = arduino3()

%% Written By: Jacob Iarve && Jonathan Kosir
% This code is written to continuously plot voltage from an arduino
% given an input for frequency.  Takes in data and sends alerts

% Needed dependencies
% Webcam that works with matlab
% Packages/ Apps
    % Arduino package
    % OS generic video interface
    % us webcams matlab package
% Arduino with sensors and components
    % Light
    % button                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            w
    % led
    % sound
    
%% Hardware information
port = 'com3';
board = 'Uno';

%%% PORTS
lightSensorPort = 'A0';
lightPin = 'D8';
noisePin = 'D4';
buttonPin = 'A1';

%% Create arduino object
myArduino = arduino(port,board);

%% Grab webcam info
list = webcamlist;
list =list{1};
cam = webcam(list);

%% Define Function Variables
time = 0;
data1 = 0;
diffData = 0;
count = 0;
peak = 0;
saveTime = 0;
onOff = 0;
senseMode = 1;
buttonTime = 0;
freq = 5000;
delay = 1/freq;
threshhold =  .05;
%%Plot Dimension
interval = 5;

% Create menu for alert type
menuFrame = menu('Autonomous Lights', 'Quiet Mode', 'Light Mode', 'Intruder Mode');
senseMode = menuFrame

%Variables for email definition 9Set prefs and definitions found online
% at Mathworks and creators information was used
setpref('Internet', 'SMTP_Server',   'smtp.mail.yahoo.com');
setpref('Internet','E_mail','jmkosir@sbcglobal.net');
setpref('Internet', 'SMTP_Username', 'jmkosir@sbcglobal.net');
setpref('Internet', 'SMTP_Password', 'J32yahooK44');

props = java.lang.System.getProperties;
props.setProperty('mail.smtp.auth',                'true');  % Note: 'true' as a string, not a logical value!
props.setProperty('mail.smtp.starttls.enable',     'true');  % Note: 'true' as a string, not a logical value!
props.setProperty('mail.smtp.socketFactory.port',  '465');   % Note: '465'  as a string, not a numeric value!
props.setProperty('mail.smtp.socketFactory.class', 'javax.net.ssl.SSLSocketFactory');

%% Setup
figure

%%% Top Plot %%%
top = subplot(2,1,1);
plotGraph = plot(time,data1,'R-');
title('Light Sensor');
xlabel('Time');
ylabel('Voltage');
axis([0 interval 0 5]);

%%% Bottom Plot %%%
bottom = subplot(2,1,2);
diffGraph = plot(time,diffData,'B-');
title('Differentiated Sensor Voltage');
xlabel('Time');
ylabel('Difference');
axis([0 interval -1.5 1.5]);

% Make sure both x axis are the same
linkaxes([top, bottom],'x')

%%%INFINITE LOOP%%%%%%%
%% Real Time Plotting Alogrithm
tic % start timer

while ishandle(plotGraph)
    
    % Statement to switch modes with button sensor
    % If button is pressed and enough time has elapsed go in
    if(readVoltage(myArduino,buttonPin) > 1 && time(count) - buttonTime  > 1)
        buttonTime = time(count);
        
        % Count up through the modes until 3 and then reset
        if(senseMode ~= 3)
            senseMode = senseMode + 1
        else
            senseMode = 1
        end
    end
    
    % Read sensor, increment counter and timer
    readLightSensor = readVoltage(myArduino,lightSensorPort);
    count = count +1 ;
    time(count) = toc;
    data1(count) = readLightSensor;
    
    % start recording differential data after first loop
    if(count > 1)
        diffData(count) = data1(count) - data1(count-1);
    end
    
    % Create our two graphes
    set(plotGraph,'XData',time,'YData',data1);
    set(diffGraph,'XData',time,'YData',diffData);
    
    % set peak to one meaning our threshhold was broken time to check
    % for oppisite peak
    if(diffData(count) > threshhold)
        saveTime = time(count);
        peak = 1;
    end
    
    % if statement for derivative values if the difference value is
    % meets the threshhold positively and negatively and the time was
    % with in reason it enters statement
    if(peak == 1 && (time(count) - saveTime) <= 1.2)
        
        % switch statment to have response to each mode
        switch senseMode
            case 1 % Quiet mode nothing happens
                
            case 2 % Light mode/ LED come on and sends a hello text
                peak = 0;
                onOff = 1;
                writeDigitalPin(myArduino,lightPin,onOff);
                sendmail('4402202880@mms.att.net', 'Hello', 'Welcome home');
                
            case 3 % Introduer mode alarm sounds and text is sent
                peak = 0;
                onOff = 1;
                
                % Take picture of intruder!
                img = snapshot(cam);
                imwrite(img, 'pic.jpg');
                
                writeDigitalPin(myArduino,noisePin,onOff);
                sendmail('4402202880@mms.att.net', 'Alert', 'Some one is in the house', 'pic.jpg');
                
                %{
             Not using for this project can ass a wake up mode
             at a later date.
             case 4 % Wake up mode (work in progress)
                peak = 0;
                onOff = 1;
                writeDigitalPin(myArduino,lightPin,onOff);
                %sendmail('4402202880@mms.att.net', 'Alert', 'Some one is in the house', );
                %}
                
            otherwise % Otherwise something went wrong
                disp 'How did you get here'
        end
    end
    
    % After 5 seconds turn off things that were turned on
    if((time(count) - saveTime)    >  2)
        onOff = 0;
        writeDigitalPin(myArduino,lightPin,onOff);
        writeDigitalPin(myArduino,noisePin,onOff);
    end
    pause(delay);
    
    % Plot the x axis correctly
    if time(count) > interval
        axis([time(count)-interval time(count) -1.5 1.5]);
    end
end
end % End function
