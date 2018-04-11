%{
---------------------------------------------------------------------------
Jonathan Kosir
M/M/2/K With variable prob of server choice
ECE 461
---------------------------------------------------------------------------
This aignment was to simulate a M/M/2/K server.  The packets arrive
using a Poisson distribution.  Once these packets arrive they have a set
probability of which server they will choose.  Once chosen the packet will
be either put into queue blocked or processed.  The servers process rate is
an exponential random variable set in the program.  Once all packets are
processed the program uses the data collected to caluclate needed matrix on
the systems statitstics.  It takes these stats and compares them to another
created function which calculates the estimated theoretical values and
compares the two values and calculates the error percentage.

Some slight improvements to this program could be made but in terms of this
assignment are not needed.  Such as making it more broad and allowing it to
calculate different server systems, such as, a system with more servers,
less servers or different types of arrival and departure processes.  Also
could be more user friendly as in asking user to input needed values and
the display of data given in a GUI. Also as always program speed and
effeciency could be improved in small areas.
---------------------------------------------------------------------------
%}
clear all

% Variables decided before start of program if needed could create user
% input screen but in the case of this project it is not needed.
speed = 1000; %Speed up simulation (Can't go above 1000)
lambda = 8 * speed ;
mu = 5 * speed;
ro = lambda/mu;
numPackets = 100000000; % Num packets to be processed cant go above 10,000,000
numServers = 2;
serverProb = 5; % Percent of ten ie. 5 = .5 for one of the servers other = 1-%
queueSize = 5;
dataStart = 1000;
timerVal = tic;
interval = 1/speed;
ticker = interval;
over = false;

% Variables used during the sim needed to simulate packet movement
blocked = zeros(1:numServers);
serversState = zeros(1:numServers);
queueCurrentSize = zeros(1 : numServers);
state = [2 0]; % [state serverNumber]
queue = zeros(numServers,queueSize);
packet = zeros(numPackets,4); % [Arrival processTime departure server]
processing = [0 0];

% Some variables for data during sim for calculations later in program
aveTimeInQueue1 = 0;
aveTimeInQueue2 = 0;
aveTimeProcessing1 = 0;
aveTimeProcessing2 = 0;
aveTimeInSystem1 = 0;
aveTimeInSystem2 = 0;
totalInQueue1 = 0;
totalInQueue2 = 0;
processing1 = 0;
processing2 = 0;
P01 = 0;
P02 = 0;
P0 = 0;

% Create the arrival matrix the time arrival of each "packet" in this
% matrix is created using a random poisson process
arrival = -log(rand)/lambda;
packet(1) = arrival;
for i = 2:numPackets
    arrival = -log(rand)./lambda;
    packet(i,1) = arrival + packet(i-1,1);
end

% Start Sim
disp('Start Sim');
simEstimate = num2str(packet(length(packet),1) +  ...
    (exprnd(1/mu)*(queueSize/2)));
disp(['Sim will take about ', simEstimate, ' Seconds']);

% Loop until over is triggered to true (Runs through until all packets are
% serviced)
i = 1;
while over ~= true
    
    % Only due this process if more packets are going to "arrive"
    if  numPackets >= i
        
        % It has arrived
        if((packet(i,1) <= toc(timerVal)))
            
            % Choose server to go to & change state to arrival
            % and label which server the packet went to
            choice = randi(10);
            if choice <= serverProb
                state = [1 1];
                packet(i,4) = 1;
            else
                state = [1 2];
                packet(i,4) = 2;
            end
        end
    end
    
    % If the server 1 is not empty check if packet being serviced is done
    if(processing(1) ~= 0)
        if(packet(processing(1),3) <= toc(timerVal))
            state = [2 packet(processing(1),4)];
        end
    end
    
    % If the server 2 is not empty check if packet being serviced is done
    if processing(2) ~= 0
        if(packet(processing(2),3) <= toc(timerVal))
            state = [2 packet(processing(2),4)];
        end
    end
    
    % Switch states one case is arrival and the other departure
    switch state(1)
        
        case 1 %Arrival
            
            % Give packets the ammount of time it will take for server to
            % process
            processTime = exprnd(1/mu);
            
            % If server empty send packet to server for processing
            if serversState(state(2)) == 0
                packet(i,2) = packet(i,1);
                packet(i,3) = packet(i,2) + processTime;
                processing(state(2)) = i;
                serversState(state(2)) = 1;
                
            % If server busy
            else
                
                % If queue is full block and count
                if queueCurrentSize(state(2)) >= queueSize
                    
                    % Block packet give all values 0 to show
                    if dataStart <= i
                        blocked(state(2)) = blocked(state(2)) + 1;
                    end
                    
                    packet(i) = 0;
                    
                % If Queue has room
                else
                    
                    % Queue packet give this packets server start time plus
                    % process time to be the time when the packet in leaves
                    % server
                    if queueCurrentSize(state(2)) == 0
                        packet(i,2) = packet(processing(state(2)),3);
                        packet(i,3) = packet(i,2) + processTime;
                        
                    % Same as above but instead of grabbing server leave
                    % time value for packet in server grabs the value from
                    % packet in front of it in the queue
                    else
                        packet(i,2) = packet(queue(state(2),...
                            queueCurrentSize(state(2))),3);
                        packet(i,3) = packet(i,2) + processTime;
                    end
                    
                    % Grow the queue size
                    queueCurrentSize(state(2)) = ...
                        queueCurrentSize(state(2)) + 1;
                    queue(state(2),queueCurrentSize(state(2))) = i;
                end
            end
            
            % Move to next packet that will arrive
            i = i + 1;
            state = [0 0];
            
        case 2  % Departure
            
            % If Queue isnt empty start processing next packet
            if queueCurrentSize(state(2)) ~= 0
                
                % Depart current packet and start processing new one
                processing(state(2)) = queue(state(2),1);
                queue(state(2),1:queueCurrentSize(state(2))) = ...
                    [queue(state(2),2:queueCurrentSize(state(2))) 0];
                queueCurrentSize(state(2)) = ...
                    queueCurrentSize(state(2)) - 1;
                serversState(state(2)) = 1;
                
            % If queue is empty
            else
                
                % If out of packets in the queue set state to zero
                serversState(state(2)) = 0;
                processing(state(2)) = 0;
                
                % If no more packets will arive and none in queue end sim
                if i >= numPackets
                    over = true;
                end
            end
            
            state = [0 0];
    end
    
    % Some simple data collection while sim is going on so calculations can
    % be made later. This is specically done in this area vs
    % built into the above sim code because we only want to collect data
    % after the set ammount of packets have arrived to get better steady
    % state results check at set intervals
    if((toc(timerVal) >= ticker) && i >= dataStart)
        ticker = interval + ticker;
        
        % Count current queue size
        totalInQueue1 = queueCurrentSize(1) + totalInQueue1;
        totalInQueue2 = queueCurrentSize(2) + totalInQueue2;
        
        % Count if the server currently is processing packet
        if (processing(1) ~= 0)
            processing1 = processing1 + 1;
        end
        if(processing(1) ~= 0)
            processing2 = processing2 + 1;
        end
        
        % Count if either server is empty or both are
        if (serversState(1) == 0)
            P01 = P01 + 1;
        end
        if serversState(2) == 0
            P02 = P02 + 1;
        end
        if (serversState(1) == 0) && serversState(2) == 0
            P0 = P0 + 1;
        end
    end
end

% Simulation over time to begin data calculations
disp(['Actual Time: ', num2str(toc(timerVal)), ...
    ' Estimated Time: ', simEstimate])
disp('Starting Data Calculations');

% Count how many packets went to server one or two
packetsTo1 = sum(packet(dataStart:numPackets,4)==1);
packetsTo2 = sum(packet(dataStart:numPackets,4)==2);

% Data calculations begins for packets that arrived after data start
for i = dataStart:numPackets
    
    % If packet not blocked count
    if packet(i,1) ~= 0
        
        % Count different values (Ave time, processing, and total) if packet
        % went to server 1
        if packet(i,4) == 1
            aveTimeInQueue1 = ((packet(i,2)*speed) - ...
                (packet(i,1) * speed)) + aveTimeInQueue1;
            aveTimeProcessing1 = ((packet(i,3)*speed) - ...
                (packet(i,2) * speed)) + aveTimeProcessing1;
            aveTimeInSystem1 = ((packet(i,3)*speed) - ...
                (packet(i,1) * speed)) + aveTimeInSystem1;
        end
        
        % Count different values (Ave time, processing, and total) if packet
        % went to server 2
        if packet(i,4) == 2
            aveTimeInQueue2 = ((packet(i,2)*speed) - ...
                (packet(i,1) * speed)) + aveTimeInQueue2;
            aveTimeProcessing2 = ((packet(i,3)*speed) - ...
                (packet(i,2) * speed)) + aveTimeProcessing2;
            aveTimeInSystem2 = ((packet(i,3) * speed )- ...
                (packet(i,1) * speed)) + aveTimeInSystem2;
        end
    end
end

% Total system values from the above calculations
aveTimeInQueue  = aveTimeInQueue1+ aveTimeInQueue2;
aveTimeProcessing = aveTimeProcessing1 +  aveTimeProcessing2;
aveTimeInSystem = aveTimeInSystem1 +  aveTimeInSystem2;

% P0 values and get how many samples were taken
samples = (toc(timerVal)-packet(dataStart,1))/interval;
P01 = P01/samples;
P02 = P02/samples;
P0 = P0/samples;

% Start displaying data
disp(' ');
disp(' Data ');
disp(' ');

% Wait times for the two different queues (Queue, service, and total time)
% This is the simulated data
aveTimeInQueue1 = aveTimeInQueue1 / packetsTo1;
aveTimeInQueue2 = aveTimeInQueue2 / packetsTo2;
aveTimeInSystem1 = aveTimeInSystem1/packetsTo1;
aveTimeInSystem2 = aveTimeInSystem2/packetsTo2;
aveTimeProcessing1 = aveTimeProcessing1 / packetsTo1;
aveTimeProcessing2 = aveTimeProcessing2 / packetsTo2;

% Entire system wait times (Queue, service, and total time) simulated data
aveTimeInSystem = aveTimeInSystem/(numPackets-dataStart);
aveTimeInQueue = aveTimeInQueue/(numPackets-dataStart);
aveTimeProcessing = aveTimeProcessing/(numPackets-dataStart);

% Simulated blocking probabilities for server 1, 2, and total
blockingProbabilityTotal = sum(blocked)/(numPackets-dataStart);
blockingProb1 = blocked(1)/packetsTo1;
blockingProb2 = blocked(2)/packetsTo2;

% Simulated number averages for both servers individually (queue, service,
% and total
aveNumInQueue1 = totalInQueue1 / samples;
aveNumInQueue2 = totalInQueue2 / samples;
aveInSystem1 = processing1/samples;
aveInSystem2 = processing2/samples;
aveNumTotal1 = aveNumInQueue1 + aveInSystem1;
aveNumTotal2 = aveNumInQueue2 + aveInSystem2;

% Entire systems average numbers
aveNumInQueue = aveNumInQueue1 + aveNumInQueue2;
aveNumInSystem = aveInSystem1 + aveInSystem2;
aveNumTotal = aveNumTotal1 + aveNumTotal2;
utilization = aveNumInSystem/numServers;

% Display our first server systems results this result will be given in
% three columns first being what is being displayed second being our
% theoretical data and the third being our error percentage
theoretical1 = theoreticalMM2(((lambda/speed)*(serverProb/10)), ...
    (mu/speed), queueSize, 1);
simulated1 = [aveTimeInQueue1 aveTimeInSystem1 aveNumInQueue1 ...
    aveNumTotal1 utilization P01 (ro*(serverProb/10)) blockingProb1];
error1 = (abs(simulated1 - theoretical1)./simulated1) .* 100;
displayData('Queue 1', theoretical1, simulated1, error1);

% Display our second server system results deisplayed same as above
theoretical2 = theoreticalMM2(((lambda/speed)*(1-(serverProb/10))),...
    (mu/speed), queueSize, 1);
simulated2 = [aveTimeInQueue2 aveTimeInSystem2 aveNumInQueue2...
    aveNumTotal2 (utilization) P02 (ro*(1-(serverProb/10))) blockingProb2];
error2 = (abs(simulated2 - theoretical2)./simulated2) .* 100;
displayData('Queue 2', theoretical2, simulated2, error2);

% Display our total server systems results displayed the same as above
% The math for this part is a little different then the above two sections
% Since the servers use probabilistic choice between the two servers
% the resutling system is not a standard M/M/2/K system therefore
% if values given were just inputed into the theoretical method the results
% would be incorrect.  Therefor to get more accurate results we use the 
% theoretical results from the two seperate server systems above and do
% some math
theoretical = (theoretical1 + theoretical2);
theoretical(1) = theoretical(1)/2;
theoretical(2) = theoretical(2)/2;
theoretical(5) = theoretical(5)/2;
theoretical(6) = theoretical1(6) * theoretical2(6);
theoretical(8) = theoretical(8)/2;
simulated = [aveTimeInQueue aveTimeInSystem aveNumInQueue aveNumTotal...
    utilization P0 ro blockingProbabilityTotal];
error = (abs(simulated - theoretical)./simulated) .* 100;
displayData('Total System', theoretical, simulated, error);

