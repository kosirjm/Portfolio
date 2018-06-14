% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% In class exam
% 1/18/18
% This is a simpsons function basic equation was found on wikipedia
%-------------------------------------------------------------------------
function answer = simpsons(f,a,b,step)

% figure out f(a) and f(b)
fa = feval(f, a);
fb = feval(f, b);

% even or odd and step to
odd = 0;
even = 0;
to = (b - a)/step;

% Loop until top value reached
for i = 1 : to
    
    % figure out intergral on every step
    answer = feval(f, a + i*step);

    % Switch back and fourth between even and odd
    if rem(i,2) == 0 
        even = even + answer;
    else
        odd = odd + answer;
    end
end

% finish the math
answer = step/3 * (fa + 4*odd + 2*even + fb);
end