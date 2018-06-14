% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 8.5
% 1/5/18
% Transcendental limit
%-------------------------------------------------------------------------
% Variables
x = 1; 

% Loop do math until limit is reached
 for i = 1 : 20
     e = (1 + x) ^ (1 / x); 
     disp ([x,e])
     x = x / 2;
 end
