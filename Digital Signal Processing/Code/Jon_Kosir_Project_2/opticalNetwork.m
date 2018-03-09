%% Jonathan Kosir
% Simulation of a switched circuit optical unit
% 4/18/2017
% This will simulate a switched optical netwrok with a poisson
% Arival Rate and an exponentiall distributio departure rate
%%
% Variables
w = 10; % Wave length
speed = 1000;
nodes = 10;
connections = nodes - 1;
connectionData(1:w,connections) = 0; % If in use
depatureTime(1,w) = 0; % Depature Time
blocked = 0; % Number blocked
packetsProcessed = 0; % Counter
lambda = 5 * speed; % Arrival Rate
timerVal = tic; % Timer
packetSim = 8000;
mu = 1 * speed;
count = 0
processTime = 1/mu;
y = rand();
arrival = -log(1-y)/lambda;
itterations = 8;
sample = 0;
sampleSize = 10;
%%
for a=1:itterations
    
    while packetsProcessed <= packetSim
        
        if arrival <= toc(timerVal)
            
            % Create Next Arrival Time
            y = rand();
            arrival = toc(timerVal) + -log(1-y)/lambda;
            connectionChoice = 1;
            
            for i=1:size(connectionData,1)
                if connectionData(i,connectionChoice) == 0
                    connectionData(i,1:end) = 1;
                    depatureTime(1,i) = toc(timerVal) + exprnd(processTime);
                    count = count + 1;
                    break;
                end
                if i == size(connectionData,1)
                    blocked = blocked + 1;
                    count = count + 1;
                    
                end
            end
            packetsProcessed = packetsProcessed + 1;
        end
        
        for i = 1:size(depatureTime,2)
            
            if depatureTime(1,i) <= toc(timerVal)
                connectionData(i,1:end) = 0;
            end
        end
    end
     disp('hey');
        blockedMatrix(a) = blocked
        blocked = 0;
        packetsProcessed = 0;
        blockedAve = sum(blockedMatrix)/itterations;
end
    summation = 0;
    for i = 1:w
        summation = summation + (((lambda/mu)^i)/factorial(i));
    end
    summation
    blockingProb = (((lambda/mu)^w)/factorial(w))/summation
    blockingNumber = blockingProb * packetSim
    blockedAve