% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 8.14
% 1/5/18
% quick program to figure out prime factors of a number
% Not very effecient can do fairly large numbers but it takes some time
%-------------------------------------------------------------------------
% variables
count = 0;

% user input 
num = input('Please input your number: ');

% Solve for what numbers are a factor of the inputted number
% If factor add to factor array
factor = 2:1:num;
remainder = rem(num,factor);
indices = find(~remainder);
factor = factor(indices);

% Loop throughentire factor list
for i = 1:length(factor)
    
    % solve for if the number is prime if it is add to a prime factor array
    num = 2:(factor(i)-1);
    remainder = rem(factor(i),num);
    indices = find(~remainder);
    if(isempty(indices) == 1)
        count = count + 1;
        primeF(count) = factor(i);
    end
end
primeF
