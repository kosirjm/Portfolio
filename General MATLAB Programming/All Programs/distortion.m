%% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem from slides #4
% 1/16/18
% A simple image distortion program
%-------------------------------------------------------------------------
%%
% Load in image
imgfile='pictures\redBlade.jpg'
f=imread(imgfile);    % read an image into img1

% Show original
figure(1);
subplot(2,2,1);
imshow(f);
title('original');

% Show piecewise distortion (Got code from mathwork site)
subplot(2,2,2);
movingPoints = [10 10; 10 30; 30 30; 30 10]; 
fixedPoints  = [10 10; 10 30; 40 35; 30 10]; 
t_piecewise_linear = fitgeotrans(movingPoints,fixedPoints,'pwl'); 
I_piecewise_linear = imwarp(f,t_piecewise_linear);
imshow(I_piecewise_linear);
title('piecewise linear')

% Show projective distortion (Got code from mathwork site)
T = [1  0  0.008; 
     1  1  0.01;
     0  0  1   ];
t_proj = projective2d(T);   
I_projective = imwarp(f,t_proj,'FillValues',.3);

subplot(2,2,3);
imshow(I_projective)
title('projective')