% The first three Legendre polynomials are P0(x)=1, P1(x)=x, and 
% P2(x)=(3x2 ? 1)/2. The general recurrence formula for Legendre 
% Polynomials is (n +1)Pn+1(x) ? (2n + 1)xPn(x) + nPn?1(x) = 0.Define a
% recursive function p(n,x) to generate Legendre polynomials, given theform 
% of P0 and P1. Plot P1(x), P2(x), P3(x) for x=(-1:0.1:1) onto the same plot
%     and add title, x-label, y-label and legends. Your plot should be similar 
%     as the example below.
% 
% Jonathan Kosir
% Take home exam 1
% problem 6
% 1/09/2018
% This function recusively does the math for a legendre polynomial
%--------------------------------------------------------------------------
function num = p(n,x)
%a quick recursive function for a legendre polonomial

% If n equals value number equals set ammount
if(n ==0)
    num = 1;
elseif n ==1
    num = x;
elseif n == 2
    num = ((3*x.^2)-1)/2;
else
    
   %This is where the recusion comes in.
   % After a little bit of math on the comment below we can obtain th
   % function used after words
   % num = ((n+1)*p(n+1,x))-(((2*n)+1).*x*p(n,x))+(n*p(n-1,x))
   num = ((2*n-1)*x.*p(n-1,x) - (n-1)*p(n-2,x) )/n;
end


