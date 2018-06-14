% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% From homework problem modified for take home exam 2
% 1/19/18
% % Solve the differential equations for x1(0)=0, x2(0)=1, a=0.2, b=1 and 
% t=[0, 500]. Plot x1 vs x2. Highlight the plot for the portion corresponding 
% to t=[300, 500] using a discrete symbol. (The solution is for the entire 
% time from 0 to 500 with equal steps.) What conclusion can you draw about 
% the solution (i.e., converges to a point or closed loop etc.)
%-------------------------------------------------------------------------
% variables
b = 1;
a = 0.2; 

% Do ode23 and output totime and x
[t, x] = ode23(@van, [0 500], [0; 1], [], b, a); 

%plot
figure()'
hold on
plot(x(:,1), x(:,2))
plot(x([300:500],1), x([300:500],2), 'color', 'red', 'marker', 'o')
hold off

% function van so you can do ode23 correctly
function f = van(t, x, b, a) 

% Create zero vector
f = zeros(2,1); 

%do math in problem given
f(1) = x(2); 
f(2) = a * (1 - x(1)^2) * x(2) - b^2 * x(1);
end
