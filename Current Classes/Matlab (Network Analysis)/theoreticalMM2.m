function [output, Pn] = theoreticalMM2(lambda, mu, queue, servers)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here

% Variables
q = queue;
ro = lambda/mu;
s = servers;

%syms k l
%S1 = (1+symsum(((ro^k)/factorial(k)) + (((ro^s)/factorial(s))*symsum((ro/s)^(l-s),l,(s+1),q)),k,1,s))^-1

% Prob of no costumers in system (First Summation)
firstSum = 0;
for i = 1:s
    firstSum = firstSum + ((ro^i)/factorial(i));
end

% Prob of no costumers in system (Second Summation)
secondSum = 0;
for j = (s+1):q
    secondSum = secondSum + (ro/s)^(j-s);
end

% Prob of no costumers in system
P0 = (1+firstSum + ((ro^s/factorial(s))*secondSum))^(-1);

% Probability of n number of users in system (first 2 values of n)
Pn(1) = ro*P0;
Pn(2) = ((ro^2)/2)*P0;

% Probability of n number of users in system (rest of n)
for i = s:q
    Pn(i) = ((ro^i)/(factorial(s)*s^(i-s)))*P0;
end

% Summation needed for average ammount of time spent in the queue
waitSum = 0;
for i = s+1:(q-1)
    waitSum = waitSum + Pn(i)*(i-s+1);
end

% Average time spent in queue
Wq = (1/(s*mu*(1-Pn(q))))* waitSum;

% Average time spent in system
W = Wq + 1/mu;

% Average number in queue
psi = ro/s;
Lq = ((P0*ro^s*(psi))/(factorial(s)*(1-(psi))^2))*(1-((psi)^(q-s))*(1-(psi)));

% Average number in entire system
L = (Lq+((lambda*(1-Pn(q)))/mu));

% Utilization Rates
U = (L-Lq)/s;

output = [Wq, W, Lq, L, U, P0, ro];
end

