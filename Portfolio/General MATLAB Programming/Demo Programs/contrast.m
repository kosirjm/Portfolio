%% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem problem one from example page 311
% 1/16/18
% Change the contrast on an image
%-------------------------------------------------------------------------
%%
% Load in image
imgfile='pictures\redBlade.jpg'
image = imread(imgfile);

% Make image gray scale
miami = rgb2gray(image);

% Due contrast adjustments (imadjust and adapthisteq)
miami_imadjust = imadjust(miami);
miami_adapthisteq = adapthisteq(miami);

% Plot images
subplot(2,2,1);
imshow(image);
title('Original');

subplot(2,2,2);
imshow(miami);
title('Gray');

subplot(2,2,3);
imshow(miami_imadjust);
title('Imadjust');

subplot(2,2,4)
imshow(miami_adapthisteq);
title('Adapthisteq');