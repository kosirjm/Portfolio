function packet = process(packet, processTime, timerVal)
%PROCESS Summary of this function goes here
%   Detailed explanation goes here
% Packet (1,1) = ArrivalTime
% Packet (1,2) = Start Process Time
% Packet (1,3) = Departure Time

packet(1,2) = toc(timerVal);
packet(1,3) = toc(timerVal) + processTime;

packet;
end

