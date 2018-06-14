% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 6.8
% 1/3/18
% A simple alphabet sort function
% If str1 is first alphabetically prints out 1 otherwise 0
%-------------------------------------------------------------------------
function ifLower = alphaComp(str1, str2)
%UNTITLED4 Summary of this function goes here
%   Detailed explanation goes here

% Check out which word is shorter and use that as length
if(length(str1) > length(str2))
    wordSize = length(str2);
else
    wordSize = length(str1);
end

% Find starting point (first non same letter)
ifEqual = lower(str1(1:wordSize)) == lower(str2(1:wordSize));
y = find(~ifEqual)

%If all is same shorter word first
if(isempty(y))
    if(length(str1) < length(str2))
        ifLower = 1;
    else
        ifLower = 0;
    end
else
    
    % Else show us the money
    ifLower = lower(str1(y(1))) < lower(str2(y(1)));
end
end


