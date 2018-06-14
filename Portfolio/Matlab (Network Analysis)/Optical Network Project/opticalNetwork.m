% Jonathan Kosir
% 5/1/2018
% Ece 461 - network analysis
function [averageBlocked] = opticalNetwork(numPackets, lambda, mu,...
    nodes, w, aveNum, nodeChoice, switching)
%Optical Network - This function can simulate different types of
%optical networks.  The first one is where a packet must connect from the
%first node to last node.  The second is where the packet can randomly
%choose which nodes it needs to connect to, must connected to each node in
%between on its path to the first node it chooses to the node it would like
%to connect to.  Both these systems can be chosen to be able to have
%optical wavelngth switching or not.  The arrival rate of the packets are
%according to poisson distribution and the departure from the nodes is
%exponential.
%
%   NumPackets - Number of packets to be run through the system
%   lambda     - Arrival Rate (Poisson Process)
%   mu         - Departure Rate (Exponential)
%   nodes      - Number of nodes in the system
%   w          - Number of wavelengths in the optical network
%   AveNum     - Number of times to run the system to grab an average
%   NodeChoice - true if the packet randomly chooses the nodes it needs to
%                connect to or false if the packet needs to go from 1-Node
%                Number
%   switching  - true of the optical network has wavelngth switching for
%                 each connection or false if it does not

%averageBlocked- The function returns the average number of blocked packets
%--------------------------------------------------------------------------

% Start the sim, run as many times needed to get appropriate average
for(a = 1:aveNum)
    
    % Variables
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
    
    % Packets start arriving run until all numPackets have arrived
    i = 1;
    while over ~= true
        
        % Only due this process if more packets are going to "arrive"
        if  numPackets >= i
            
            % Packet has arrived
            if((packets(i,1) <= toc(timerVal)))
                
                % Choose nodes (Random choice of beginning and end)
                if(nodeChoice)
                    random1 = randi(connections);
                    random2 = randi(connections);
                    if(random1<random2)
                        packets(i,3) = random1;
                        packets(i,4) = random2;
                    else
                        packets(i,3) = random2;
                        packets(i,4) = random1;
                    end
                    
                    % Must connect from 1- node number
                else
                    packets(i,3) = 1;
                    packets(i,4) = connections;
                end
                
                % If the optical network has wavelength switching
                if(switching)
                    
                    % These for loops look for a free wavelength at each
                    % connection.  If non exist at any given connection the
                    % packet is blocked.  I am sure there is a more
                    % effecient way to code this, but in sake of time just
                    % doing this.
                    % Move from connection to connection
                    for(j = packets(i,3):packets(i,4))
                        % Move through each wavelength at each connection
                        for(k = 1:w)
                            
                            % At first wavelength empty assign packet
                            % number to empty spot and move to next
                            % connection
                            if systemMatrix(k,j) == 0
                                addMatrix(k,j) = i;
                                blocking = false;
                                break;
                                
                            end
                            % If you get through every wavelength at the
                            % given connection and there is no empty spot,
                            % block packet.
                            if w==k
                                blocked = blocked + 1;
                                blocking = true;
                                break;
                            end
                        end
                        
                        % If packet blocked exit completely out of search
                        % loops
                        if(blocking == true)
                            break;
                        end
                        
                        % If packet is not blocked assign packet number to
                        % our system matrix
                        if(j == packets(i,4) && blocking ~= true)
                            systemMatrix = addMatrix + systemMatrix;
                        end
                    end
                    
                    % If packet isnt blocked ass the node to our active
                    % packet matrix and assign it a depatarture time
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
                    
                    % If the optical network does not have wavelength switching
                    % this can be done almost exactly the same way as the
                    % wavelnegth switching way, but this is much more effecient
                    % for what we need it to do.
                else
                    % Look for free wavelengths for these node connections
                    free = all(systemMatrix(:,packets(i,3):packets(i,4))~=1,2);
                    space = find(free==1, 1, 'first');
                    if(~isempty(space))
                        packets(i,5) = space;
                    else
                        packets(i,5) = 0;
                    end
                    free = [];
                    space = [];
                    
                    % If no wavelengths avaliable from start node to end
                    % node block
                    if(packets(i,5) == 0)
                        blocked = blocked + 1;
                    else
                        
                        % If not blocked mark that wavelength taken for
                        % those given node connections
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
                
                % Packet complete move to next one
                i = i + 1;
            end
            
            % Departure of packets if it is not the first packet
            if( i ~= 1)
                
                % Look in the leaving packet matrix for a packet ready to
                % depart
                leavingPacket = find(activePacket(:,2) <= toc(timerVal),1,'first');
                if(~isempty(leavingPacket))
                    
                    % if packet exists then focus on this packet
                    thisPacket = activePacket(leavingPacket);
                    
                    % Switching departure has to be done slightly
                    % differently due to the fact it uses different
                    % wavelengths at each connection
                    if(switching)
                        
                        % Find departing packet and set its spot to 0
                        [row, col] = find(systemMatrix == thisPacket);
                        for t = 1:length(row)
                            systemMatrix(row(t),col(t)) = 0;
                        end
                    else
                        
                        % If non wavelength switching its easier, jus set
                        % all connections on taken wavelength between start
                        % and end node to 0
                        systemMatrix(packets(thisPacket,5),(packets(thisPacket,3):packets(thisPacket,4))) = 0;
                    end
                    
                    % Clear that packets spot
                    activePacket(leavingPacket,:) = [];
                    leavingPacket = [];
                end
            end
        else
            % If all packets are processed end loop
            over = true;
        end
        
    end
    
    % Add how many packets are blocked for that given run to a matrix
    blockingMatrix(a) = blocked;
end

% Average all the blocked packets for the given number of loops
averageBlocked = sum(blockingMatrix)./aveNum;
end

