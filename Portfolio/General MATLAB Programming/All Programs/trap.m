% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 17.7
% 1/13/18
% Evaluating a definite integral using the trap rule
%-------------------------------------------------------------------------
% Variables
fh = @(x)x.^2; %function handle to avaluate
step = 1;
lower = 0;
upper = 4;

% Trapazodial rule given earlier in chapter pg 366
num = (upper-lower)/step;
x = lower+[1:num-1]*step;
y = sum(feval(fh, x));
y = step/2*(feval(fh, lower) + feval(fh, upper) + 2*y)