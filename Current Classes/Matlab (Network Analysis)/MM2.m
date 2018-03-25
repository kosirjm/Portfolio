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
ro = lambda/mu;
speed = 1;
numPackets = 1000;
numServers = 2;
serverProb = 5;
queueSize = 20;

timerVal = tic;
interval = 1/speed;
ticker = interval;

blocked = zeros(1:numServers);
serversState = zeros(1:numServers);
queueCurrentSize = zeros(1 : numServers);
state = [6 0]; % [state serverNumber]
queue = zeros(numServers,queueSize);
packet = zeros(numPackets,4); % [Arrival processTime departure server]

arrival = -log(rand)/lambda;
packet(1) = arrival;
for i = 2:numPackets
arrival = -log(rand)./lambda;
packet(i,1) = arrival + packet(i-1,1);
end

disp('Start Sim');
disp(['Sim will take about ', num2str(packet(length(packet),1)), ' Seconds']);

i = 1;
while i <= numPackets+1
    % Arrival
    if(packet(i,1) >= toc(timerVal))
        
        choice = randi(10);
        if choice <= serverProb
            disp('Choise 1');
             state = [serversState(1) 1];
             packet(i,4) = 1;
        else
            disp('Choice 2');
             state = [serversState(2) 2];
             packet(i,4) = 2;
        end
        i = i + 1
    end
    
    % Process
    for j = 1:numServers
        if(packet(j,3) >= toc(timerVal))
            state = [2 j];
        end
    end
    
    %{
    % Departure
    for i = 1:numServers
        if(packet(i,3) >= toc(timerVal))
            state = [3 i];
            break;
        end
    end
    %}
    
    % Arrival
        % Queued ----
        % Process 
    % Departure
        % Take from queue
        % Zero State
    % Processing
        % Queue
        % Depature state
        
    switch state(1)
        
        case 0 %Empty state
            disp ('Case 0');
            % If empty and new packet arrives process
            processTime = exprnd(mu);
            packet(i,2) = toc(timerVal);
            packet(i,3) = packet(i,2) + processTime;
            serversState(1,state(2)) = 1;
            
        case 1
            % Arrival
            % Need to fix blocking still
            %disp ('Case 1');
            if queueCurrentSize(state(1,2)) >= queueSize
                blocked(state(2)) = blocked(state(2)) + 1;
            else
                processTime = exprnd(mu);

                if queueCurrentSize(state(1,2)) == 1
                    packet(i,2) = packet(queue(state(2),queueCurrentSize-1));
                    packet(i,3) = packet(i,2) + processTime;
                else
                    packet(i,2) = toc(timerVal);
                    packet(i,3) = packet(i,2) + processTime;  
                end
            end
   
        case 2  % Process
            disp ('Case 2');
            queue(state(2),1:queueCurrentSize(state(2))) = queue(state(2),2:queueCurrentSize(state(2)));
            queue(state(2),queueCurrentSize(state(2))) = 0;
            queueCurrentSize(state(2)) = queueCurrentSize(state(1,2)) - 1;
            if queueCurrentSize(state(2)) == 0
                serversState(1,state(2)) = 0;
            end
            
            
       
        case 6 % Depature state
            %{
            queueCurrentSize(state(1,2)) = queueCurrentSize(state(1,2)) - 1;
            
            
            if QueueCurrentSize(state(1,2)) == 0
                serversState(1,state(1,2)) = 0;
            end
            
            queue(queueCurrentSize,state(1,2)) = array(2:end);
            array{end} = 0;
           %}
        otherwise
            
    end
    
end
%}