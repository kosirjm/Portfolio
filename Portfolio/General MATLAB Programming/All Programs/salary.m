% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 5.5
% 1/3/18
% Just some simple matrix math with company salary
%-------------------------------------------------------------------------

% Variables
salaryLevels = [12 15 18 24 35 50 70]
employees = [3000 2500 1500 1000 400 100 25]
salaryLevels = salaryLevels * 1000;

% Solve for mean (Part A)
meanX = mean(salaryLevels)

% Number below and above (Part B)
belowLogVect = salaryLevels < meanX;
aboveLogVect = salaryLevels > meanX;

below = sum(belowLogVect .* employees)
above = sum(aboveLogVect .* employees)

% Total bill ave (Part C)
bill = salaryLevels .* employees
billTotal = sum(bill)
aveEmployee = billTotal/(sum(employees))

