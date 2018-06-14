% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 5.6
% 1/3/18
% A simple remove max script (Should take users input but 
% but not asked for this practice problem and maybe will do later.
%-------------------------------------------------------------------------
 %variables
x = [1 2 5 0 5]

% Remove
x(x == max(x)) = []
