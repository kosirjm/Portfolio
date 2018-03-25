%{
Jonathan Kosir
M/M/2/K
ECE 461
------------------------------------------------------------------------
%}

% Variables
lambda = 8;
mu = 5;
ro = lambda/mu;
speed = 1;
numPackets = 1000;
serverProb = 5;
arrival = -log(rand)/lambda;       
timerVal = tic;
interval = 1/speed;
ticker = interval;
arrivalMatrix(1) = arrival;
numProcessed = 1;
numServers = 2;
serverState = zereos(1:numServers);
queues = zeros(numServers : 4);
for i = 2:numPackets
arrival = -log(rand)./lambda;
arrivalMatrix(i) = arrival + arrivalMatrix(i-1);
end
arrivalMatrix

disp('Start Sim');
disp(['Sim will take about ', num2str(arrivalMatrix(length(arrivalMatrix))), ' Seconds']);
state = 0;

while i <= length(arrivalMatrix)
    
    packet(1,1) = arrivalMatrix(i);
    
    % Arrival
    if(arrivalMatrix(i) >= toc(timerVal))
        i = i + 1;
        choice = randi(10);
        if choice <= 5
             server = 1;
             state = 1;
        else
            server = 2;
            state = 1;
        end
    end
    
    % Departure
    for i = 1:numServers
        if(queue(i,1) >= toc(timerVal)
            state = 2;
            server = i;
            break;
        end
    end
    
    % Arrival
        % Queued ----
        % Process 
    % Departure
        % Take from queue
        % Zero State
    % Processing
        % Queue
        % Depature state
        
    switch state
        
        case 0 %Empty state
            
            % If empty and new packet arrives process
            processTime = exprnd(mu);
            process(packet, processTime, timerVal)
            
        case 1 %Queue
            
            % Arrival
            queue(packet, q, server);
            
        case 2  % Process
            processTime = exprnd(mu);
            process(packet, processTime, timerVal)
            
        case 3 % Depature state
            
            server1Queue(1:end-1) = array(2:end);
            array{end} = newvalue;
            % Put new packet in system 
            
            
                % set to zero state
               % if 
            %choose next packet
                % Reduce queue
                
            % Else set at zero state
        otherwise
            
    end
    
  
   
    
    
end
