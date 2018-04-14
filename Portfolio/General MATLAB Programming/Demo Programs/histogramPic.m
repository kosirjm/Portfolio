%% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem from slides #4
% 1/16/18
% A simple image histogram equalization
%-------------------------------------------------------------------------
%%

% Load in image
imgfile='C:\Users\kosirjm\Pictures\Matlab\redBlade.jpg'
f=imread(imgfile)    % read an image into img1

% create a histogram of the original
histo = histeq(f);
subplot(2,2,1)
imhist(f,256)
title('Original Histogram');
subplot(2,2,3)
imshow(f)
title('original');

% histogram of histogram equalization
subplot(2,2,2)
imhist(histo,256)
title('Equlization histogram');
subplot(2,2,4)
imshow(f)
title('equalization pic');