% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 8.11
% 1/5/18
% cos Taylor series
%-------------------------------------------------------------------------

% Some simple variables
format long
thisCos = 0;
count = 1
sign1 = 1;
x = 0:10:360;
 
%Change x to radians
x = (x .* pi)./180;
 
% Loop to solve for all x options in one go
% Could be more effecient but since i only loop through 35 times it does
% not matter
for i = 0:2:35
    thisCos = thisCos + sign1*((x.^i)/(factorial(i)));
    
    % Sign flips
    count = count + 1;
    if(rem(count,2)==0)
        sign1 = -1;
    else
        sign1 = 1;
    end
end
 
% Do some quick math to make sure answers are right and in the correct
% columns
thisCos = thisCos';
x = x';
thisCos(:,2) = cos(x)

