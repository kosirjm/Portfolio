% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 4.8
% 1/3/18
% Solve for beam equation and then plot (equation given by book)
%-------------------------------------------------------------------------
%Length = 10m
%tension = 1000N
%load = 100n/m
%EL = 10^4

% Variables
T = 1000;
L = 10;
EL = 100000;
W = 100;
a = sqrt(T/EL);
count = 0;

% Loop to create vector for chosen x values
for x = 0:.1:L
    count = count + 1;

% first part of equation
first = (EL*W)/(T^2);

% Second part of equation
secondTop = cosh(a*(L/2)-x);
secondBottom = cosh((a*L)/2);
second = (secondTop/secondBottom)-1;

% Third part of equation
thirdTop = W*x*(L-x);
thirdBottom = 2*T;
third = thirdTop/thirdBottom;

% All together now
total(count) = (first*second)+third;
end

% Plot
plot(0:.1:L,total)
axis([4 10 0 60000]);


