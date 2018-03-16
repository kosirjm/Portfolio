% Problem 5. Write a program to accept any line of text.  Output the the
% most frequently used alphabet.  How many times does it appear in the text?
% Use ‘It Is Better to Fail in Originality Than to Succeed in Imitation’ 
% (please retain the upper cases in the sentence) and another sentence 
% of your choice to test your program.  
% 
% Jonathan Kosir
% Take home exam 1
% problem 5
% 1/09/2018
% This script counts letters in a string
%--------------------------------------------------------------------------
 
% Take user input
textString = input('Please input your string to be counted: ','s');
 
% Create alphabet array
lowerAlphabet = ('a':'z');
upperAlphabet =  ('A':'Z');
alphabet = horzcat(lowerAlphabet,upperAlphabet);
 
% Loop to count
for i = 1:length(alphabet)
thisCount(i) = count(textString,alphabet(i));
end
 
% Do some mods to make display look better
alphabet = num2cell(alphabet');
thisCount = thisCount';
alphabet(:,2) = num2cell(thisCount)
