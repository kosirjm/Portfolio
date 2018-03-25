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

disp('Start Sim');

simmedPackets = 0;
simTime(1) = 0;
state(1) = 0;

%thisTry = 1:-log(rand)/lambda:numPackets

arrival = -log(rand)/lambda;       
timerVal = tic;
interval = 1/speed;
ticker = interval;
arrivalMatrix(1) = arrival;
numProcessed = 1;


for i = 2:numPackets
arrival = -log(rand)./lambda;
arrivalMatrix(i) = arrival + arrivalMatrix(i-1);
end
arrivalMatrix
%{

processMatrix(1) = exprnd(mu);
for i = 2:numPackets
processed = exprnd(mu);
processMatrix(i) = processed + processMatrix(i-1);
end
%}

%arrivalMatrix;
%{
if(arrival > depature)
    queue()
elseif depature > arrival
    process()
end
state(
%}
