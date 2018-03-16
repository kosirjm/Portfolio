% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% In class exam
% 1/18/18
% This is my trap script from class just turned into a function
%-------------------------------------------------------------------------
function ans = trapF(fh,lower,upper,step)

% Trapazodial rule given earlier in chapter pg 366
num = (upper-lower)/step;
x = lower+[1:num-1]*step;
y = sum(feval(fh, x));
y = step/2*(feval(fh, lower) + feval(fh, upper) + 2*y)

