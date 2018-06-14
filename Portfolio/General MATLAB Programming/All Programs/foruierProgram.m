% It is of interest to know how many terms are needed for a good
% approximation to this infinite sum. Taking T =2, write a program to compute
% and plot the sum to n terms of the series for t from ?2.2 to 2.2 in steps
% of 0.01. Run the program for n=1, 3, 6. Superimpose plots of f(t) and F(t)
% against t for the three n values. In your plot, label each curve with a
% distinct color and use legends to label the curves. Include both vertical
% and horizontal grid lines, x-label and y-label in your plot. Find and output
% the largest difference between f(t) and F(t) for each n (excluding the
% points at the discontinuity).

%
% Jonathan Kosir
% Take home exam 1
% problem 4
% 1/09/2018
% This script for our fourier series plot
%----------------------------------------------------------------------------

% Creat some variables
t = -2.2:.01:2.2;
squareBuild = zeros(1,length(t));
n = [1 3 6];
T = 2;
total = 0;

% Create our square function array
squareBuild( t< T & t> 0) = 1;
squareBuild(t > -T &  t < 0) = -1;

% Start figure
figure();
hold on
plot(t,squareBuild);

% First loop loops through n array
for i = 1:length(n)
    
    % Second loop is summation loop goes throu n ammount
    for k = 0:n(i)
        
        % Do math
        firstSum = sin((((2*k)+1)*pi*t)/T);
        secondSum = 1/((2*k)+1);
        total = total + (firstSum * secondSum);
    end
    
    % Finish math
    total = total*(4/pi);
    
    % Find max diff for this specific n and f9t0
    diff = abs(squareBuild-total);
    maximum(i) = max(diff);
    
    % Plot each n
    plot(t,total)
end

% Make graph look pretty
hold off
xlabel('t');
ylabel('f(t)/F(t)');
title('Fourier series plot');
legend('f(t)', 'F(t) n=1', 'F(t) n=3', 'F(t) n=6');
grid on;

% Display maximums
disp([' Diff (n = 1): ' num2str(maximum(1)) ' Diff (n = 3): ' num2str(maximum(2)) ' Diff (n = 6): ' num2str(maximum(3))]);

