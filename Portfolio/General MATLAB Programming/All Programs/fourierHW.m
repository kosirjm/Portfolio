% Jonathan Kosir
% Homework 3
% problem 8.5
% 1/05/2018
% This script for our fourier series plot
%----------------------------------------------------------------------------

% Creat some variables
t = -1.1:.01:1.1;
squareBuild = zeros(1,length(t));
n = [1 3 20];
T = 1;
c = 0;

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
    for t = -1.1:.01:1.1;
        
        % c = count for total
        c = c + 1;
        total(c) = 0
        
        % Loop through the sumation n times
        for k = 0:n(i)
            
            % Do math
            total(c) = total(c) + 1 / (2 * k + 1) * ...
                sin((2 * k + 1) * pi * t / T);
            
        end
        
        % Finish math
        total(c) = total(c)*(4/pi);
        
    end
    
    
    % Find max diff for this specific n and f9t0
    diff = abs(squareBuild-total(c));
    maximum(i) = max(diff);
    
    % Plot each n
    t = -1.1:.01:1.1;
    plot(t,total)
    
    % Clear all variables except the needed ones
    clearvars -except T n i squareBuild maximum
    c=0;
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

