% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 2.23
% 1/2/18
% Interest Rate Calculator
%-------------------------------------------------------------------------

% get user input
deposit = input('Please input your deposit ammount: ');
iR = input('Please input your interest rate: ');
endLength = input('Please input interest calculation time in months (ie. 1 year = 12 months): ');


% Loop and solve
total = 0
for i = 1:endLength
    total = total + deposit;
    total = total + (total * iR);
    months(i) = total;
end

months
    
    