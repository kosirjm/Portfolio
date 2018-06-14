%% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem from slides #2
% 1/16/18
% A simple image edge detect program
%-------------------------------------------------------------------------
%%
% load in image
imgfile='pictures\rocks.tif'
f=imread(imgfile);   % read an image into img1

% Simple edge detection
BW1 = edge(f,'Canny');
BW2 = edge(f,'Prewitt');
BW3 = edge(f,'Roberts');

% Plot
subplot(2,2,1)
imshow(f);
title('Original');

subplot(2,2,2)
imshow(BW1);
title('canny')

subplot(2,2,3);
imshow(BW3);
title('Prewitt');

subplot(2,2,4);
imshow(BW3);
title('Roberts');