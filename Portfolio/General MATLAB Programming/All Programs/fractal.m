% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 7.7
% 1/5/18
% Quick fractal Plot
%-------------------------------------------------------------------------
% Variables
y(1) = .1;
x(1) = .1;

% Math
for k = 1:100
    x(k+1) = (y(k)*(1+sin(.7)*x(k)))-(1.2*sqrt(abs(x(k))));
    y(k+1) = .21-x(k);
end

% Plot
plot(x,y,'*')
