 % Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 10.8
% 1/8/18
% This is a simple fibonocci function
%-------------------------------------------------------------------------
function number = fibonacci(x) 
 
 % use recursion to keep adding lower numbers until the number is down to 1
 if x > 1 
     number = fibonacci(x - 1) + fibonacci(x - 2); 
 else
     number = 1;
 end
