%{
Jonathan Kosir
ECE 461
5/1/18
This is our main program to anylise our different optical networks
simulations.  

%}
clear all;

% Variable definitions for simulations
speed = 1000;        % Speed increase for simulation
nodes = 10;          % Number of nodes
mu = 1 * speed;      % Processing Speed
lambda = 5 * speed;  % Arrival Speed
numPackets = 8000;    % Number of packets to process
aveNum = 8;          % Number of times to run sim for average
w = 10;              % Number of wavelnegths
ro = lambda/mu;
% Simulations for all wavelengths up to the given W

syms i
for k = 2:2:w
    summation(k) = symsum((ro^i)/factorial(i),i,0,k);
    theoretical(k) = (ro^k/factorial(k))/summation(k);
    opticalAllBlocking(k) = opticalNetwork(numPackets, lambda, mu, nodes, k, aveNum, 0, false);
    opticalBlocking(k) = opticalNetwork(numPackets, lambda, mu, nodes, k, aveNum, 1, false);
    conversionBlocking(k) = opticalNetwork(numPackets, lambda, mu, nodes, k, aveNum, 1, true);
end

% Plot the three sims blocking probability vs the amount of wavelengths
h1 = plot(opticalAllBlocking(1:w)/numPackets, 'color', 'red');
hold on;
h2 = plot(opticalBlocking(1:w)/numPackets, 'color', 'blue');
h3 = plot(conversionBlocking(1:w)/numPackets, 'color', 'green');
h4 = plot(theoretical(1:w), '--','color', 'cyan');
set([h1 h2 h3 h4], 'LineWidth',2);
hold off;
ylabel('Blocking Average');
xlabel('Wavelength Number');
title('Blocking Probability vs Wavelengths In Different Types Of Optical Networks')
legend('Connection 1-NodeNumber', 'Connection Random Choice Nodes (No Switching)',...
    'Connection Random Choice Nodes (Switching)', 'Theoretical Values 1-NodeNumber');