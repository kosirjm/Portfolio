%{
Jonathan Kosir
ECE 461
5/1/18
This is our main program to analyze our different optical networks
simulations.  It consists of a theoretical plot for if the packet must
connect from the first node to the last node.  A simulation for the
previouse stated scenerio.  A simulation for randomly chosen nodes the
packet must connect to, with wavelength switching and a simulation for the
same scenerio with out wavelength switching. If the packet cannot find an
open wavelength (for optical switching each wavelength can be switched for
each node connection, for non-wavelength switching the same wavelength must
be used for all the node connections) it will be blocked.  This data is
then plotted, probaability vs wavelength and the data is compared against
each other. 
---------------------------------------------------------------------------
%}

clear all;

% Variable definitions for simulations
speed = 1000;        % Speed increase for simulation
nodes = 10;          % Number of nodes
mu = 1 * speed;      % Processing Speed
lambda = 5 * speed;  % Arrival Speed
numPackets = 8000;   % Number of packets to process
aveNum = 8;          % Number of times to run sim for average
w = 10;              % Number of wavelnegths
ro = lambda/mu;

% Simulations for all wavelengths up to the given W
syms i
for k = 1:w
    % Theoretical value for 1-node number connection
    summation(k) = symsum((ro^i)/factorial(i),i,0,k);
    theoretical(k) = (ro^k/factorial(k))/summation(k);
    
    % Simulation value for 1-node number connection
    opticalAllBlocking(k) = opticalNetwork(numPackets, lambda, mu, nodes,...
        k, aveNum, false, false);
    
    % Simulation for random node connection no wavelength switching
    opticalBlocking(k) = opticalNetwork(numPackets, lambda, mu, nodes,...
        k, aveNum, true, false);
    
    % Simulation for random node connection with wavelength switching
    conversionBlocking(k) = opticalNetwork(numPackets, lambda, mu, nodes,...
        k, aveNum, true, true);
end

% Plot the three sims blocking probability vs the amount of wavelengths
h1 = plot(opticalAllBlocking(1:w)/numPackets, 'color', 'red');
hold on;
h2 = plot(opticalBlocking(1:w)/numPackets, 'color', 'blue');
h3 = plot(conversionBlocking(1:w)/numPackets, 'color', 'green');
h4 = plot(theoretical(1:w), '--','color', 'cyan');
set([h1 h2 h3 h4], 'LineWidth',2);
ylabel('Blocking Average');
xlabel('Wavelength Number');
title('Blocking Probability vs Wavelengths In Different Types Of Optical Networks')
legend('Connection 1-NodeNumber', 'Connection Random Choice Nodes (No Switching)',...
    'Connection Random Choice Nodes (Switching)',...
    'Theoretical Values 1-NodeNumber');