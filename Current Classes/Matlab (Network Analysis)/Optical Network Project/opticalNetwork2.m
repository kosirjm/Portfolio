clear all;
blocked = 0;
% Wavelength
speed = 1000;
w = 10;
nodes = 10;
mu = 1 * speed;
lambda = 5 * speed;
numPackets = 1000000;
connections = nodes-1;
packets = zeros(numPackets,5);
over = false;
timerVal = tic;
% Create the arrival matrix the time arrival of each "packet" in this
% matrix is created using a random poisson process
arrival = -log(rand)/lambda;
packets(1) = arrival;
for i = 2:numPackets
    arrival = -log(rand)./lambda;
    packets(i,1) = arrival + packets(i-1,1);
end
%activePacket = [0 0];
% Start Sim
simEstimate = num2str(packets(length(packets),1));
disp('Start Sim');
disp(['Sim will take about ', simEstimate, ' Seconds']);
system = zeros(w,(nodes-1));
i = 1;
while over ~= true

 % Only due this process if more packets are going to "arrive"
    if  numPackets >= i
        
        % It has arrived
        if((packets(i,1) <= toc(timerVal)))
            
            % Choose nodes
            packets(i,3) = 1;
            packets(i,4) = connections;
            
            % Look for free wavelengths for these nodes
            free = all(system(:,packets(i,3):packets(i,4)),2);
            space = find(free==0, 1, 'first');
            if(~isempty(space))
             packets(i,5) = space;
            end
            % if none block
            if(packets(i,5) == 0)
                blocked = blocked + 1;
            else
                
            % else mark as taken
            system(packets(i,5),packets(i,3):packets(i,4)) = 1;
            packets(i,2) = exprnd(1/mu)+packets(i,1);
            newActive = [i packets(i,2)];
            if(i == 1)
                activePacket = newActive
            end
            activePacket = vertcat(activePacket, newActive);
            end
            i = i + 1;
        end
        
            leavingPacket = find(activePacket(:,2) <= toc(timerVal),1,'first');
            if ~isempty(leavingPacket)
                thisPacket = activePacket(leavingPacket);
                system(packets(thisPacket,5),(packets(thisPacket,3):packets(thisPacket,4))) = 0;
                activePacket(leavingPacket,:) = [];
                leavingPacket = 0;
            end
    else
        over = true;
    end
        
end

            
            