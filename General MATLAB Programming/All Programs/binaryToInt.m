% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 6.6
% 1/3/18
% A simple binary to int script
%-------------------------------------------------------------------------
% variables
total = 0;

% user input
binary = input('Input binary number: ', 's');

% Flip string for easy solving
binary = fliplr(binary);

% iterate through using simple binary math 
for i = 1:length(binary)
    num = str2num(binary(i))
    total = total + (num*(2^(i-1)))
end
total
