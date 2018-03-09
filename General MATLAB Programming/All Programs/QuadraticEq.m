% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 2.6 & 3.5
% 1/2/18
% this homework will ask for user input and solve a qadratic equation
%-------------------------------------------------------------------------

% Ask for user input
a = input('Please in put a of the quadratic equation (ax^2+bx+c): ');
b = input('Please in put b of the quadratic equation (ax^2+bx+c): ');
c = input('Please in put c of the quadratic equation (ax^2+bx+c): ');

%solve

top = b+sign(b)*sqrt((b^2)-(4*a*c));
x = -top/(2*a);
x1 = -(2*c)/top
x
x1
