% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 4.6
% 1/3/18
% This is a converter from seconds into hours and minutes
%-------------------------------------------------------------------------

% User input
sec = input('Please input seconds to be comverted: ');

% For the first Part not needed for actual answer
% hours = fix(min/60);
% min = rem(min,60);

% Calculate turn seconds into real time
min = fix(sec/60);
sec = rem(sec,60);
hours = fix(min/60);
min = rem(min,60);

% Display
disp (['Time: ' num2str(sec) ':' num2str(min) ':' num2str(hours)]);