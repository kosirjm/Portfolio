% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 2.4
% 1/3/18
% Asks user for two masses and an angle.  Solves for deflected angle in
% degrees
%-------------------------------------------------------------------------

%User Input
m1 = input('Please input m1: ' );
m2 = input('Please input m2: ' );
a = input('Please input a: ' );

% Solve using given equation
answerRight=(m2*(1+exp(1))*tand(a))/(m1-(exp(1)*m2)+(m1+m2)*(tand(a)^2));
answer = atand(answerRight);
answer