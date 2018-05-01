function [averageBlocked] = opticalNetwork(numPackets, lambda, mu,...
     nodes, w, aveNum, nodeChoice, switching)
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here

for(a = 1:aveNum)
    blocked = 0;
    connections = nodes-1;
    packets = zeros(numPackets,5);
    over = false;
    timerVal = tic;
    blocking = false;
    systemMatrix = zeros(w,(nodes-1));
    
    addMatrix = zeros(w,(nodes-1));
    
    % Create the arrival matrix the time arrival of each "packet" in this
    % matrix is created using a random poisson process
    arrival = -log(rand)/lambda;
    packets(1) = arrival;
    for i = 2:numPackets
        arrival = -log(rand)./lambda;
        packets(i,1) = arrival + packets(i-1,1);
    end
    activePacket = [];
    
    i = 1;
    while over ~= true
        
        % Only due this process if more packets are going to "arrive"
        if  numPackets >= i
            
            % It has arrived
            if((packets(i,1) <= toc(timerVal)))
                
                % Choose nodes
                if(nodeChoice == 1)
                    random1 = randi(connections);
                    random2 = randi(connections);
                    if(random1<random2)
                        packets(i,3) = random1;
                        packets(i,4) = random2;
                    else
                        packets(i,3) = random2;
                        packets(i,4) = random1;
                    end
                else
                    packets(i,3) = 1;
                    packets(i,4) = connections;
                end
                
                if(switching)
                    for(j = packets(i,3):packets(i,4))
                        for(k = 1:w)
                            if systemMatrix(k,j) == 0
                                addMatrix(k,j) = i;
                                blocking = false;
                                break;
                            end
                            if w==k
                                blocked = blocked + 1;
                                blocking = true;
                                break;
                            end
                        end
                        if(blocking == true)
                            break;
                        end
                        if(j == packets(i,4) && blocking ~= true)
                            systemMatrix = addMatrix + systemMatrix;
                        end
                    end
                    
                    addMatrix = zeros(w,(nodes-1));
                    if(~blocking)
                        % else mark as taken
                        packets(i,2) = exprnd(1/mu)+packets(i,1);
                        newActive = [i packets(i,2)];
                        if(i == 1)
                            activePacket = [i packets(i,2)];
                        else
                            activePacket = [activePacket; newActive];
                        end
                    end
                else
                    % Look for free wavelengths for these nodes
                    free = all(systemMatrix(:,packets(i,3):packets(i,4))~=1,2);
                    space = find(free==1, 1, 'first');
                    if(~isempty(space))
                        packets(i,5) = space;
                    else
                        packets(i,5) = 0;
                    end
                    
                    free = [];
                    space = [];
                    % if none block
                    if(packets(i,5) == 0)
                        blocked = blocked + 1;
                    else
                        % else mark as taken
                        systemMatrix(packets(i,5),packets(i,3):packets(i,4)) = 1;
                        packets(i,2) = exprnd(1/mu)+packets(i,1);
                        newActive = [i packets(i,2)];
                        if(i == 1)
                            activePacket = newActive;
                        else
                            activePacket = vertcat(activePacket, newActive);
                        end
                    end
                end
                i = i + 1;
            end
            
            
            if( i ~= 1)
                leavingPacket = find(activePacket(:,2) <= toc(timerVal),1,'first');
                if(~isempty(leavingPacket))
                    thisPacket = activePacket(leavingPacket);
                    if(switching)
                        [row, col] = find(systemMatrix == thisPacket);
                        for t = 1:length(row)
                            systemMatrix(row(t),col(t)) = 0;
                        end
                    else
                        systemMatrix(packets(thisPacket,5),(packets(thisPacket,3):packets(thisPacket,4))) = 0;
                    end
                    activePacket(leavingPacket,:) = [];
                    leavingPacket = [];
                end
            end
        else
            over = true;
        end
        
    end
    blockingMatrix(a) = blocked;
end
averageBlocked = sum(blockingMatrix)./aveNum;
end

