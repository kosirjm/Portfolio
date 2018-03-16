% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 7.6
% 1/5/18
% Quick pop growth model
%-------------------------------------------------------------------------
 % Variables first r and y(0) 
y(1) = .2;
 r = 3.3;
 k = 1:100;
 
 for k = 1:length(k)
     y(k+1) = r*y(k)*(1 - y(k)); 
 end
 plot(y,'*');
