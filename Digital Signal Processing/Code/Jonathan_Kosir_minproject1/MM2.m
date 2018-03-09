%{
Jonathan Kosir
M/M/2/K
ECE 461
This Project Simulates a M/M/2/K Queueing system
------------------------------------------------------------------------
%}
clear all;
% Variable Declaration
% Queue Variables
buffer=20;
speed = 1;
lambda = 8 * speed;
mu1= 5 * speed;
mu2 = 5 * speed;
numPackets=10001;
processTime1 = 1/mu1;
processTime2 = 1/mu2;
probServer1 = 4;

% Trackers
ticEndTime1 = 1000000;
ticEndTime2 = 1000000;
timerVal = tic;
interval = 1/speed;
ticker = interval;

% Queue Data Variables
totalInQueue1 = 0;
totalInQueue2 = 0;
totalInSystem = 0;
aveProcessTime1 = 0;
aveProcessTime2 = 0;
packetsProcessed = 1;
server1Queue = 0;
server2Queue = 0;
server1Filled = 0;
server2Filled = 0;
packetMatrix = zeros(6, numPackets);
packetsBlocked1 = 0;
packetsBlocked2 = 0;
AveNumInSystem = 0;
AveWaitingTime = 0;
aveQueueTime1 = 0;
aveQueueTime2 = 0;
aveProcessTimeQueue1 = 0;
aveProcessTimeQueue2 = 0;
avepbQueue1 = 0;
avepbQueue2 = 0;

disp('Start Simulation');

% Start the loop at the first poisson arrival
% Continue until we process all packets and both queueus are emplty
y = rand();
%arrival = -log(1-y)/lambda;
arrival = lambda*exp((-lambda*y)); %% NEED to figure out arrival times 
while (packetsProcessed < numPackets) || (server1Queue ~= 0) || (server2Queue ~= 0)
    packetsProcessed
   
    % Just a check statement to make sure all packets arent in the system
    if packetsProcessed < numPackets
        
        % If another packet arrives
        if arrival >= toc(timerVal)
            
            % Create Next Arrival Time
            y = rand();
           % arrival = toc(timerVal) + -log(1-y)/lambda;
           toc(timerVal)
            arrival = lambda*exp((-lambda*y)) + toc(timerVal)
            %Check which server packet choosed
            packetChoice = randi(10); 
            
            % If first server is chosen
            if packetChoice <= probServer1
                
                % Add that it choose server one in our data matrix
                packetMatrix(1,packetsProcessed) = 1;
                
                % Check to see if server is empty
                if server1Filled == 0
                    
                    % If it is begin processing packet and collect data
                    packetMatrix(2,packetsProcessed) = 0;
                    server1Filled = 1;
                    y = rand();
                    packetMatrix(4,packetsProcessed) = exprnd(processTime1);
                    ticEndTime1 = toc(timerVal) + packetMatrix(4,packetsProcessed);
                
                % If server is full
                else
                   
                    % If buffer isnt full queue packet and collect data
                    if server1Queue < buffer
                        server1Queue(1) = server1Queue + 1;
                        server1Number(server1Queue) = packetsProcessed;
                        packetMatrix(2,packetsProcessed) = toc(timerVal);
                     
                    % Drop packet and store data
                    else
                        %packetMatrix(5,packetsProcessed) = 1;
                        packetsBlocked1 = packetsBlocked1 +1;
                        
                    end
                end
                
            % If second server is chosen
            else
                
                % Store which server is chosen
                packetMatrix(1,packetsProcessed) = 2;
                
                % Check if server is empty
                if server2Filled == 0
                    
                    % Begin processing packet and store data
                    server2Filled = 1;
                    y = rand();
                    packetMatrix(4,packetsProcessed) = exprnd(processTime2);
                    ticEndTime2 = toc(timerVal) + packetMatrix(4,packetsProcessed);
               
                % If server is busy
                else
                    
                    % If buffer isnt full queue packet and record data
                    if server2Queue < buffer
                        server2Queue = server2Queue + 1;
                        server2Number(server2Queue) = packetsProcessed;
                        packetMatrix(2,packetsProcessed) = toc(timerVal);
                    
                    % Else drop packet
                    else
                        %packetMatrix(5,packetsProcessed) = 1;
                        packetsBlocked2 = packetsBlocked2 + 1;
                    end
                end
            end
            % Increase or packets processed each iteration
            packetsProcessed = packetsProcessed + 1;
        end
    end
    
    % If the server is now empty and the packet is done processing 
    if toc(timerVal) >= ticEndTime1
        
        % If there is packets in the queue process them next and collect
        % data. 
        if server1Queue > 0
            server1Filled = 1;
            y = rand();
            packetPosition = server1Number(server1Queue);
            packetMatrix(4,packetPosition) = exprnd(processTime1);
            packetMatrix(3,packetPosition) = toc(timerVal);
            ticEndTime1 = packetMatrix(3,packetPosition) + packetMatrix(4,packetsProcessed);
            server1Queue = server1Queue - 1;
      
        % If no queueu server is now not in use
        else
            server1Filled = 0;
        end
    end
    
    % If the server is now empty and the packet is done processing 
    if toc(timerVal) >= ticEndTime2
        
        % If there is packets in the queue process them next and collect
        % data.
        if server2Queue > 0
            server2Filled = 1;
            y = rand();
            packetPosition = server2Number(server2Queue);
            packetMatrix(4,packetPosition) = exprnd(processTime2);
            packetMatrix(3,packetPosition) = toc(timerVal);
            ticEndTime2 = packetMatrix(3,packetPosition) + packetMatrix(4,packetsProcessed);
            server2Queue = server2Queue - 1;
            
        % If no queueu server is now not in use
        else
            server2Filled = 0;
        end
    end
    
    % Begin to figure out ave number in the system by using a sampling
    % process
    if((toc(timerVal) >= ticker) && packetsProcessed >= 1000)
        
        % Dont use data of the first 1000 packets
        if(packetsProcessed == 1000)
            startTime = toc(timerVal);
        end
        
        ticker = interval + ticker;
        totalInQueue1 = server1Queue + totalInQueue1;
        totalInQueue2 = server2Queue + totalInQueue2;
        
        % Count packets in the server
        if server1Filled == 1
            totalInSystem = totalInSystem + 1;
        end
        if server2Filled == 1
           totalInSystem = totalInSystem + 1;
        end
    end
end

% Figure out sampling number
samples = (toc(timerVal)-startTime)/interval;

% Experimental Results and calculations
%-----------------------------------------------------------
% Total values for each of the queues individually
packetMatrix(6,:) = packetMatrix(3,:)- packetMatrix(2,:);
for i = 1000:numPackets
    if packetMatrix(1,i) == 1
    aveQueueTime1 = aveQueueTime1 + packetMatrix(6,i);
    aveProcessTime1 = aveProcessTime1 + packetMatrix(4,i);
    avepbQueue1 = avepbQueue1 + packetMatrix(5,i);
    else
    aveQueueTime2 = aveQueueTime2 + packetMatrix(6,i);
    aveProcessTime2 = aveProcessTime2 + packetMatrix(4,i);
    avepbQueue2 = avepbQueue2 + packetMatrix(5,i);
    end   
end

% Math:  Figure out results according to collected data
aveQueueTime = (sum(packetMatrix(6,:))/numPackets)*speed
aveProcessTime = (sum(packetMatrix(4,:))/numPackets) * speed
aveTotalTime = aveQueueTime+aveProcessTime
aveQueueTime1 = (aveQueueTime1/(numPackets -1000))* speed
aveQueueTime2 = (aveQueueTime2/(numPackets-1000))* speed
aveProcessTime1 = (aveProcessTime1/(numPackets-1000))* speed
aveProcessTime2 = aveProcessTime1/(numPackets-1000)* speed
avepbQueue1 = avepbQueue1/(numPackets-1000)
avepbQueue2 = avepbQueue2/(numPackets-1000)
aveNumInQueue1 = (totalInQueue1 / samples)
aveNumInQueue2 =  (totalInQueue2 / samples)
aveNumberTotal = ((totalInQueue1 + totalInQueue2 + totalInSystem)/samples)

