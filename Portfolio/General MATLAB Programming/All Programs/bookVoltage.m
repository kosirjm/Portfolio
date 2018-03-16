%% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem problem one from example page 311
% 1/10/18
% Question from the book copied most of it from the example Changed it to
% fit our needs for this question
%-------------------------------------------------------------------------
 
% Script to check the solution to the soverning
% equation for a simple circuit, i.e. to check 
% that 
% i = (V/R) * (1 - exp(-R*t/L)) 
% 
% is the solution to the following ODE 
% 
% di/dt + (R/L) * i - V/L = 0 
% 
% Step 1: We will use the Symbolics tools; hence,
% define the symbols as follows 
% 
syms i V R L t
% 
% Step 2: Construct the solution for i 
% 
i = (V/R)*(1-exp(-R*t/L) ); 
% 
% Step 3: Find the derivative of i 
% 
didt = diff(i,t);
% 
% Step 4: Sum the terms in ODE 
% 
didt + (R/L)*i-V/L; 
% 
% Step 5: Is the answer ZERO? 
% 
simplify(ans) 
% 
% Step 6: What is i at t = 0?
% 
subs(i,t,0) 
% 
% REMARK: Both answers are zero; hence, 
% the solution is correct and the 
% initial condition is correct. 
% 
% Step 7: To illustrate the behavior of the 
% current, plot i vs. t for V/R = 1 
% and R/L = 1. The curve illustrates 
% the fact that the current approaches 
% i = V/R exponentially. 
% 
V = 10; R = 2; L = 2; 
t=0:0.01 : 6; 
i = (V/R)*(1-exp(-R.*t/L) );
plot(t,i,'ro'), title('Circuit problem example') 
xlabel('time, t'),ylabel('current,i');
