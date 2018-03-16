% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 6.4
% 1/3/18
% A simple boolean palindrome script
%-------------------------------------------------------------------------

%user input
word = input('Please input your sentence for palandrome testing: ', 's');

% Take word and remove spaces and makes lower case
newWord = word(~isspace(word));
newWord = lower(newWord);

% flip word and compare
if(newWord == fliplr(neword))
    1
else
    0
end


