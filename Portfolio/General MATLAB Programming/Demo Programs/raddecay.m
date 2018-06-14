% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 10.8
% 1/11/18
% This a radioactive decay problem using ode23
%-------------------------------------------------------------------------

% Variables given in problem
t = [0 8];
y=[5*10^26;0];
format short
% You will see i created a function below so I can use ode23
[S,Y] = ode23(@func,t,y)

% Plot
figure(1)
hold on
plot(S,Y(:,1),'*');
plot(S,Y(:,2),'*');
legend('Strontium','Yttrium')

% function so I can use ode23 properly
function dx = func(t,x)
% Variables given in problem
dx = [0;0];
r1 = 0.256;
r2 = 0.127;

% equation for strontium then tyyrium
dx(1) = -r1 * x(1);
dx(2) = -r2 * x(2) + r1 * x(1);
end

