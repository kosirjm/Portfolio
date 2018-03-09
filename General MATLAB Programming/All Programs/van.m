% function van so you can do ode23 correctly
function f = van(t, x, b, e) 

% Create zero vector
f = zeros(2,1); 

%do math in problem given
f(1) = x(2); 
f(2) = e * (1 - x(1)^2) * x(2) - b^2 * x(1);
end
