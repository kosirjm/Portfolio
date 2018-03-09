
% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 8.14
% 1/5/18
% quick charge program 
%-------------------------------------------------------------------------

%Variables first go
r=4; c=1; v=9; t=0; q=0;

format short
disp 'first go: ';

% loop until equal to 8
while q<=8
    % Do math in problem and disp
    t=t+.1;
    q=c*v*(1-exp(-t/(r*c)));
    disp([t q])
end

%Variables second go
r=4; c=1; v=9; t=0; q=0;

disp 'second go: ' ;

%Loop until up to 8 but not equal to
while q<8
    %Do math in problem
    t=t+.1;
    q=c*v*(1-exp(-t/(r*c)));
    disp([t q])
end