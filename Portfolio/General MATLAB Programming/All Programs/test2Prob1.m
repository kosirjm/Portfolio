% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% In class exam 2
% 1/18/18
% The main script for all three problems
%-------------------------------------------------------------------------

%% Problem 1
disp('Problem 1 ------------------------------------------');
% Define the first function handle for problem 1
equation = @(x)x^3-x^2+x-1;

% Define the derivative
dequation = @(x)3*x^2-2*x+1;

% Answer
num = (newquot(equation,dequation,.5,10))

% Define the second function handle for problem 1
equation = @(x)sin(x)-log(x);

% define the derivative
dequation = @(x)cos(x)-1/x;

% answer
num = (newquot(equation,dequation,.5,20))

%% Problem 2
disp('Problem 2 ------------------------------------------');
% Define the function for prob 2
intEq = @(x)exp(1).^((-x.^2)/2);

% Solve prob #2 using trap
trapF(intEq,0,1,.01);

% solve prob #2 
simpsons(intEq,0,1,.01);

%% Problem 3
disp('Problem 3 ------------------------------------------');
% Variables for the in class exam
mandelNum = [2,3,4];

% Loop through for each power of
for i = 1:length(mandelNum)
mandelbrot(800,40,mandelNum(i),i)
title(['Mandelbrot to the power of ' num2str(i)]);
end
