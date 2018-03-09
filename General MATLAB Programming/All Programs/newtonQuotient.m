% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 9.1
% 1/5/18
% A simple newton quotient program
%-------------------------------------------------------------------------

%variables 
x = 2;
h = 10;

% for loop like question asks for
for i = 1 : 20 
    %Decrease ten fold
    h = h / 10;
    
    % Math to get derivative of x and display
    derivex = ((x + h) ^ 2 - x ^ 2) / h; 
    disp([h, derivex]); 
end
