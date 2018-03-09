% The first three Legendre polynomials are P0(x)=1, P1(x)=x, and 
% P2(x)=(3x2 ? 1)/2. The general recurrence formula for Legendre Polynomials
% is (n +1)Pn+1(x) ? (2n + 1)xPn(x) + nPn?1(x) = 0.Define a recursive function 
% p(n,x) to generate Legendre polynomials, given theform of P0 and P1. Plot
% P1(x), P2(x), P3(x) for x=(-1:0.1:1) onto the same plot and add title, 
%     x-label, y-label and legends. Your plot should be similar as the example 
%     below.
% 
% Jonathan Kosir
% Take home exam 1
% problem 6
% 1/09/2018
% This script is our script for our recursive legendre function
%--------------------------------------------------------------------------
% create variables
x = -1:.1:1;
n = 1:3;

% start figure
figure();
hold on

% Loop through each n and plotting it
for i = 1:length(n)
num = p(n(i),x);
plot(x,num);
end

% Plot modification to make it look pretty
xlabel('x');
ylabel('Pn(x)');
title(' Lengendre Polynomials ');
legend('P(1)','P(2)','P(3)');
hold off;


