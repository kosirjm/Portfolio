%% Jonathan Kosir
% Simulation of a switched circuit optical unit
% 4/18/2017
% This will simulate a switched optical netwrok with a poisson
% Arival Rate and an exponentiall distributio departure rate
%%
% Variables
w = 6; % Wave length
speed = 1000;
nodes = 10;
connections = nodes - 1;

connectionData(1:w,connections) = 0; % If in use
depatureTime(connections,w) = 0; % Depature Time
blocked = 0; % Number blocked
packetsProcessed = 0; % Counter
lambda = 5 * speed; % Arrival Rate
timerVal = tic; % Timer
packetSim = 8000;
mu = 1 * speed;
processTime = 1/mu;
y = rand();
allMatrix = 0;
arrival = -log(1-y)/lambda;
itterations = 8;
%%
for a=1:itterations
    while packetsProcessed <= packetSim
        
        if arrival <= toc(timerVal)
            
            % Create Next Arrival Time
            y = rand();
            arrival = toc(timerVal) + -log(1-y)/lambda;
            connectionChoice1 = randi(connections)
            connectionChoice2 = randi(connections)
            allMatrix = all(connectionData);
            if(connectionChoice1 <= connectionChoice2)
                for i=connectionChoice1:connectionChoice2
                    if(allMatrix(i) == 1)
                        blocked = blocked + 1;
                        break;
                    else
                        for j=1:size(connectionData,1)
                            if connectionData(j,i) == 0
                                connectionData(j,i) = 1;
                                depatureTime(i,j) = toc(timerVal) + exprnd(processTime);
                                break;
                            end
                        end
                    end
                end
            else
                for i=connectionChoice2:connectionChoice1
                    if(allMatrix(i) == 1)
                        break;
                    else
                        for j=1:size(connectionData,1)
                            if connectionData(j,i) == 0
                                depatureTime(i,j) = toc(timerVal) + exprnd(processTime);
                                break;
                            end
                        end
                    end
                end
            end
            connectionData
            packetsProcessed = packetsProcessed + 1;
        end
        for i = 1:size(depatureTime,2)
            for j = 1:size(depatureTime,1)
                if depatureTime(j,i) <= toc(timerVal)
                    connectionData(i,j) = 0;
                end
            end
        end
    end
    disp('hey');
    blockedMatrix(a) = blocked
    blocked = 0;
    packetsProcessed = 0;
    blockedAve = sum(blockedMatrix)/itterations;
end
blockedAve
blocked