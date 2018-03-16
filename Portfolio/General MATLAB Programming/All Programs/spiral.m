% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 7.3
% 1/5/18
% Quick Log Spiral
%-------------------------------------------------------------------------

% Make some variables
spiralNum =  [0:0.1:(2*pi)];

% Create x and y values
eNum = exp(spiralNum);
sinNum = sin(spiralNum);
yVal = eNum.*sinNum;
cosNum = cos(spiralNum);
xVal = cosNum .* eNum;

% Plot
plot(xVal,yVal)