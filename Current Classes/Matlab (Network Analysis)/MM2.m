%{
Jonathan Kosir
M/M/2/K
ECE 461
------------------------------------------------------------------------
%}

% Variables
clear all vars
lambda = 8;
mu = 5;
speed = 1;
numPackets = 10000;
numServers = 2;
serverProb = 5;
queueSize = 20;

timerVal = tic;
interval = 1/speed;
ticker = interval;
over = false;

blocked = zeros(1:numServers);
serversState = zeros(1:numServers);
queueCurrentSize = zeros(1 : numServers);
state = [6 0]; % [state serverNumber]
queue = zeros(numServers,queueSize);
packet = zeros(numPackets,4); % [Arrival processTime departure server]
processing = [0 0];

arrival = -log(rand)/lambda;
packet(1) = arrival;
for i = 2:numPackets
    arrival = -log(rand)./lambda;
    packet(i,1) = arrival + packet(i-1,1);
end

disp('Start Sim');
simEstimate = num2str(packet(length(packet),1));
disp(['Sim will take about ', simEstimate, ' Seconds']);

i = 1;
while over ~= true
    
    if  numPackets >= i
        % It has arrived
        if((packet(i,1) <= toc(timerVal)))
            % Choose server to go to
            % Change state of server to arrival
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
    if(processing(1) ~= 0)
        if(packet(processing(1),3) <= toc(timerVal))
            state = [2 packet(processing(1),4)];
        end
    end
    
    if processing(2) ~= 0
        if(packet(processing(2),3) <= toc(timerVal))
            state = [2 packet(processing(2),4)];
        end
    end
    
    
    
    % Arrivald
    % Queued ----
    % Process
    % Departure
    % Take from queue
    % Zero State
    % Processing
    % Queue
    % Depature state
    
    switch state(1)
        
        case 1 %Arrival
            processTime = exprnd(mu);
            % If server empty
            if serversState(state(2)) == 0
                packet(i,2) = toc(timerVal);
                packet(i,3) = packet(i,2) + processTime;
                processing(state(2)) = i;
                serversState(state(2)) = 1;
                
                % If server busy
            else
                % If queue is full
                if queueCurrentSize(state(2)) >= queueSize
                    blocked(state(2)) = blocked(state(2)) + 1;
                    packet(i) = 0;
                    
                    % If Queue has room
                else
                    
                    if queueCurrentSize(state(2)) == 0
                        packet(i,2) = toc(timerVal);
                        packet(i,3) = packet(i,2) + processTime;
                        
                    else
                        packet(i,2) = packet(queue(state(2),queueCurrentSize(state(2))),3);
                        packet(i,3) = packet(i,2) + processTime;
                    end
                    
                    queueCurrentSize(state(2)) = queueCurrentSize(state(2)) + 1;
                    queue(state(2),queueCurrentSize(state(2))) = i;
                end
            end
            i = i + 1;
            state = [0 0];
            
            
        case 2  % Departure
            % If Queue isnt empty
            
            if queueCurrentSize(state(2)) ~= 0
                processing(state(2)) = queue(state(2),1);
                
                if queueCurrentSize(state(2)) == 1
                    queue(state(2)) = 0;
                    queueCurrentSize(state(2)) = queueCurrentSize(state(2)) - 1;
                else
                    queue(state(2),1:queueCurrentSize(state(2))) = [queue(state(2),2:queueCurrentSize(state(2))) 0];
                    %queue(state(2),queueCurrentSize(state(2))) = 0;
                    queueCurrentSize(state(2)) = queueCurrentSize(state(2)) - 1;
                end
                % If current size is now zero set server state to idle
            else
                % If ouy of packets
                serversState(state(2)) = 0;
                processing(state(2)) = 0;
                if i >= numPackets
                    over = true;
                end
            end
            state = [0 0];
            
        otherwise
            
    end
end
disp(['Actual Time: ', num2str(toc(timerVal)), ' Estimated Time: ', simEstimate])

theoretical = theoreticalMM2(lambda, mu, queueSize, numServers)'

aveTimeInSystem = sum(packet(:,3) - packet(:,1))/numPackets;
aveTimeInQueue = sum(packet(:,2) - packet(:,1))/numPackets;
aveTimeProcessing = sum(packet(:,3) - packet(:,2))/numPackets;
blockingProbabilityTotal = sum(blocked)/numPackets;
blockingProbability1 = blocked(1)/sum(packet(:,4)==1);
blockingProbability2 = blocked(1)/sum(packet(:,4)==2);
