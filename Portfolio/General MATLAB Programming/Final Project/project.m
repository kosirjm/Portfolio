function[] = arduino2(freq)
 

%% Written By: Jacob Iarve & Jonathan Kosir
% This code is written to continuously plot voltage from an arduino
% given an input for frequency. 
 

%% Hardware information
 

    port = '/dev/cu.usbmodem1411';
    board = 'Uno';
    

    %%% PORTS
    lightSensorPort = 'A0';
    soundSensorPort = 'A1';
    

%% Create arduino object 
    myArduino = arduino(port,board);
 

%% Define Function Variables
   time = 0;
   data1 = 0;
   data2 = 0;
   count = 0;
   seconds = seconds(datetime);
   delay = 1/freq; 
   

   %% delay = .5;
   

%% Setup 
    figure 
    

    

    %%% Top Plot %%%
    subplot(2,1,1);
    plotGraph = plot(time,data1,'R-');
    title('Light Sensor');
    xlabel('Time');
    ylabel('Voltage');
    

    %%% Bottom Plot %%%
    subplot(2,1,2);
    plotGraph1 = plot(time,data2,'B-');
    title('Sound Sensor');
    xlabel('Time');
    ylabel('Voltage');
    

    %Set Axis
    axis([0 120 0 5]);
    

%%%INFINITE LOOP%%%%%%%
%% Real Time Plotting Alogrithm    
tic
   

while ishandle(plotGraph) 
        
        seconds
        readLightSensor = readVoltage(myArduino,lightSensorPort);
        readSoundSensor = readVoltage(myArduino,soundSensorPort);
        
        secondCount = 
        count = count +1 ;
        time(count) = toc; 
        

        data1(count) = readLightSensor;
        data2(count) = readSoundSensor;
        

        set(plotGraph,'XData',time,'YData',data1);
        set(plotGraph1,'XData',time,'YData',data2);
        

        pause(delay);
        
        if (count >= 1000)
            data1 = data1(2:end);
            axis([time(count-1000) time(count) 0 5]);
            
        end
        
end
 

 

 

end