%% Jonathan Kosir
% Simulation of a switched circuit optical unit
% 4/18/2017
% This will simulate a switched optical netwrok with a poisson
% Arival Rate and an exponentiall distributio departure rate
%%
% Variables
w = 10; % Wave length
speed = 1000; % Speed up sim
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

endLoop = 0;
itterations = 8;
%%
arrival = -log(1-y)/lambda;
for a=1:itterations
    % Process all Packtes until out of network
    while packetsProcessed <= packetSim
        
        if arrival <= toc(timerVal)
            
            % Create Next Arrival Time
            y = rand();
            arrival = toc(timerVal) + -log(1-y)/lambda;
            connectionChoice1 = randi(connections);
            connectionChoice2 = randi(connections);
            allMatrix = all(connectionData);
            
            if(connectionChoice1 <= connectionChoice2)
                % Connection 1 is less then connection 2
                for j=1:size(connectionData,1)
                    %Check if all spots are taken from those nodes
                    for i=connectionChoice1:connectionChoice2
                        % If so block
                        if connectionData(j,i) == 1
                            if size(connectionData,1) == j
                                blocked = blocked + 1;
                            end
                            break;
                        end
                        if i == connectionChoice2 && connectionData(j,i) == 0
                            connectionData(j,connectionChoice1:connectionChoice2) = 1;
                            depatureTime(connectionChoice1:connectionChoice2, j) = toc(timerVal) + exprnd(processTime);
                            endLoop = 1;
                            break;
                            
                        end
                        
                    end
                    if endLoop == 1
                        endLoop = 0;
                        break;
                    end
                end
                
            else
                % Connection 1 is less then connection 2
                for j=1:size(connectionData,1)
                    %Check if all spots are taken from those nodes
                    for i=connectionChoice2:connectionChoice1
                        % If so block
                        if connectionData(j,i) == 1
                            if size(connectionData,1) == j
                                blocked = blocked + 1;
                            end
                            break;
                        end
                        if i == connectionChoice1 && connectionData(j,i) == 0
                            connectionData(j,connectionChoice2:connectionChoice1) = 1;
                            depatureTime(connectionChoice2:connectionChoice1, j) = toc(timerVal) + exprnd(processTime);
                            endLoop = 1;
                            break;
                        end
                    end
                    if endLoop == 1
                        endLoop = 0;
                        break;
                    end
                end
            end
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
    blockedMatrix(a) = blocked;
    blocked = 0;
    packetsProcessed = 0;
    blockedAve = sum(blockedMatrix)/itterations;
end
blockedAve