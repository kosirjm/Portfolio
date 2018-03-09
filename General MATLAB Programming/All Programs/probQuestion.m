% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 16.2
% 1/10/18
% Solving A QUICK LINEAR ALGEBRA PROBLEM
%-------------------------------------------------------------------------
% Variables given in the problem
A =[2 -1 1; 1 1 1;3 -1 -1];
B =[4;3;1];

% Math
x = A\B

Determinat = det(A)
condition = rcond(A)
