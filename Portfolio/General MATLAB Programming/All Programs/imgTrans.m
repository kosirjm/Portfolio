%% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem from slides #3
% 1/16/18
% A simple image transformation program
%-------------------------------------------------------------------------
%%
% load in image
imgfile='pictures\redBlade.jpg'
f=imread(imgfile)    % read an image into img1=

% original
figure(1)
hold on
subplot(2,2,1);
imshow(f);
title('original');

% Log transformation
logImg = log(double(f+1));
logImg = mat2gray(logImg);
subplot(2,2,2);
imshow(logImg)
title('Log transform');

% Exponential transform
eImg = exp(double(f+1));
eImg = mat2gray(eImg);
subplot(2,2,3)
imshow(eImg);
title('Exponential transform');

% Stretch contrast transformation (Got help from the matlab site)
a = min(f(:));  %minimum pixel of image X
b = max(f(:)); %maximum pixel of image X 
X = (f-a).*(255/(b-a)); %just using the formula above
subplot(2,2,4)
imshow(X);
title('Stretch-contrast transform');
hold off

% Show the histograms
figure(2)
hold on
subplot(2,2,1)
imhist(f,256)
title('Original Histogram');

subplot(2,2,2)
imhist(logImg,256)
title('Log histogram');

subplot(2,2,3)
imhist(eImg,256)
title('e histogram');

subplot(2,2,4)
imhist(X,256);
title('stretch histogram');
hold off