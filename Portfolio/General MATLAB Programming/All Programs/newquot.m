% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% in class exam 2
% 1/18/18
% A modified newton quotient rule function for exam 2
%----------------

% fun = function
% dxfun is derivative of funtion
% n is guess value
% step is step size
function x = newquot(fun,dxfun,n,step) 
% variables 
x = n;

% for loop like question asks for
for i = 1 : step
   x = x - (fun(x)/dxfun(x));
end

end