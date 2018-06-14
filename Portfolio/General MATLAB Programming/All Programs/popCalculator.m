% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 2.26
% 1/2/18
% Population Rate Calculator
%-------------------------------------------------------------------------
%Variables
pop = 19727300;

% user Input
startYear = input('Please input the start date: ');
endYear = input('Please input the end date: ');
yearDiff = (endYear - startYear);

%Calculations
for i = 1:yearDiff
popOverTime(i) = pop/(1 + exp(-0.03134*(i-1913.25)));

if(~mod(i,10))
    format long; 
    popOverTime(i)
    popOvertime(i,2)
end
end

% Plot
plot(startYear:10:endYear, popOverTime);
