% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 7.4
% 1/13/18
% A population estimate using eulor method
% The code in this problem is taking from an in book example on page 370
% I modified it to recieve the results we are looking for
%-------------------------------------------------------------------------
% Variables
h = 0.5; 
r = 0.025; 
a = 0; 
b = 30; 
m = (b - a) / h; 
N = zeros(1, m+1); 
N(1) = 1000;
t = a:h:b;

% Look through the ode
for i = 1:m
    N(i+1) = N(i)+r*h*N(i); 
end

% Finish math
Nex = N(1) * exp(r * t); 
format bank 

% Display and plot
disp( [t' N' Nex'] )
plot(t, N ), xlabel( 'Days' ), ylabel( 'Population' ) 
hold on
plot(t, Nex ), hold off
